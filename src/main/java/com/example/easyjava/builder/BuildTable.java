package com.example.easyjava.builder;

import com.example.easyjava.Util.JsonUtil;
import com.example.easyjava.Util.PropertiesUtil;
import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class BuildTable {
    private static Connection connection=null;

    private static final Logger logger=  LoggerFactory.getLogger(BuildTable.class);
    private static final String SQL_SHOW_TABLE="show table";
    private static final String SQL_SHOW_TABLE_STATUS="show table status";
    private static final String SQL_SHOW_TABLE_FULL_FIELDS="show full fields from %s";
    private static final String SQL_SHOW_TABLE_INDEX="show index from %s";

    //static初始化 获取链接数据库connection
    static {
        String dirverName= PropertiesUtil.getString("db.driver.name");
        String url= PropertiesUtil.getString("db.url");
        String username= PropertiesUtil.getString("db.username");
        String password= PropertiesUtil.getString("db.password");

        //System.out.println(dirverName+url+url+password);
        logger.info(dirverName);
        logger.info(url);
        try{
            Class.forName(dirverName);

            connection= DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            logger.error("数据库连接失败");
        }


    }


    //获取table信息
    public static List<TableInfo> getTables() {
        PreparedStatement preparedStatement=null;
        ResultSet tableResult=null;

        //表信息
        List<TableInfo> tableInfoList=new ArrayList<>();



        try {
            //获取信息sql语句
            preparedStatement=connection.prepareStatement(SQL_SHOW_TABLE_STATUS);
            //结果集
            tableResult=preparedStatement.executeQuery();

            //每一个表信息
            while (tableResult.next()){

                //获取表信息
                String tableName=tableResult.getString("name");
                String comment=tableResult.getString("comment");
                //logger.info("tableNAme:{},Comment{}",tableName,comment);

                //初始化beanname
                String beanName=tableName;


                //配置文件  是否去除表前缀 demo_ceshi  -> ceshi
                if(Constants.IGNORE_TABLE_PREFIX){
                    //
                    beanName=tableName.substring(beanName.indexOf("_")+1);
                   // logger.info("==========="+beanName);
                }

                //转大写
                beanName=processFieled(beanName,true);

                //logger.info("--------------"+beanName);


                TableInfo tableInfo=new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setBeanName(beanName);
                tableInfo.setComment(comment);
                tableInfo.setBeanParamName(beanName+Constants.SUFFIX_BEAN_QUERY);





                //分析这个表的字段信息
                //List<FieIdInfo> fieIdInfoList = readFieIdInfo(tableInfo);
                readFieIdInfo(tableInfo);

//                logger.info("表{}", JsonUtil.convertObj2Json(tableInfo));
//                logger.info("字段{}",JsonUtil.convertObj2Json(fieIdInfoList));

                //分析这个表的字段的索引信息
                getKeyIndexInfo(tableInfo);
                logger.info("tableInfo{}",JsonUtil.convertObj2Json(tableInfo));



                //logger.info("表：{}，备注:{},JavaBean:{},JavaaparamBean:{}",tableInfo.getTableName(),tableInfo.getComment(),tableInfo.getBeanName(),tableInfo.getBeanParamName());
                //------------
                tableInfoList.add(tableInfo);
                //------------

            }



        }catch (Exception e){
            logger.error("获取表数据读取失败");
        }finally {

            if (tableResult != null){
                try {
                    tableResult.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }


            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }


        }

        return tableInfoList;
    }


    //获取一个表的字段信息 TableInfo
//    private static List<FieIdInfo> readFieIdInfo(TableInfo tableInfo){
    private static void readFieIdInfo(TableInfo tableInfo){
        PreparedStatement preparedStatement=null;
        ResultSet fieldResult=null;


        //表信息
        List<FieIdInfo> fieIdInfoList=new ArrayList<>();
        List<FieIdInfo> fieIdExtendList=new ArrayList<>();


        try {
            //获取信息sql语句
            preparedStatement=connection.prepareStatement(String.format(SQL_SHOW_TABLE_FULL_FIELDS,tableInfo.getTableName()));
            //结果集
            fieldResult=preparedStatement.executeQuery();

            //
            Boolean haveDateTime=false;
            Boolean haveDate=false;
            Boolean havaBigDecimal=false;

            //每一个字段属性
            while (fieldResult.next()){

                //获取表信息
                String field=fieldResult.getString("field");
                String type=fieldResult.getString("type");
                String extra=fieldResult.getString("extra");
                String comment=fieldResult.getString("comment");

                //去除sql类型的（）  varchar(21) --> varchar
                if(type.indexOf("(") > 0){
                    type=type.substring(0,type.indexOf("("));
                }

                //field ->propertyName
                String propertyName=processFieled(field,false);
//                -----------------------------------------
                FieIdInfo fieIdInfo=new FieIdInfo();
                fieIdInfoList.add(fieIdInfo);

//                -----------------------------------------
                fieIdInfo.setFieIdName(field);
                fieIdInfo.setComment(comment);
                fieIdInfo.setSqlType(type);
                fieIdInfo.setAutoIncrement("auto_increment".equals(extra) ? true : false);
                fieIdInfo.setPropertyName(propertyName);
                fieIdInfo.setJavaType(processJavaType(type));
//                -----------------------------------------
//                fieIdInfoList.add(fieIdInfo);
//                tableInfo.setFieIddList(fieIdInfoList);
//                -----------------------------------------

                //判断字段是否有这些类型
                if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type)){
                    haveDateTime=true;
                }
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,type)){
                    haveDate=true;
                }
                if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES,type)){
                    havaBigDecimal=true;
                }

                if (ArrayUtils.contains(Constants.SQL_STRING_TYPES, type)) {
                    String propertyNameSuffix = propertyName + Constants.SUFFIX_BEAN_QUERY_FUZZY;

                    FieIdInfo fuzzyField = new FieIdInfo();
                    fuzzyField.setPropertyName(propertyNameSuffix);
                    fuzzyField.setJavaType(processJavaType(type));
                    fuzzyField.setFieIdName(field);
                    fuzzyField.setSqlType(type);
                    fieIdExtendList.add(fuzzyField);
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, type ) || ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, type ) ) {
                    String propertyNameStart = propertyName + Constants.SUFFIX_BEAN_QUERY_TIME_START;
                    String propertyNameEnd = propertyName + Constants.SUFFIX_BEAN_QUERY_TIME_END;

                    FieIdInfo fieIdInfoStart = new FieIdInfo();
                    fieIdInfoStart.setPropertyName(propertyNameStart);
                    fieIdInfoStart.setFieIdName(field);
                    fieIdInfoStart.setJavaType("String");
                    fieIdInfoStart.setSqlType(type);
                    fieIdExtendList.add(fieIdInfoStart);

                    FieIdInfo fieIdInfoEnd = new FieIdInfo();
                    fieIdInfoEnd.setPropertyName(propertyNameEnd);
                    fieIdInfoEnd.setFieIdName(field);
                    fieIdInfoEnd.setJavaType("String");
                    fieIdInfoEnd.setSqlType(type);
                    fieIdExtendList.add(fieIdInfoEnd);

                }

                //logger.info(tableInfo.toString());
                //logger.info("field:{},type:{},extra:{},Comment{}",field,type,extra,comment);
               // logger.info(fieIdInfo.toString());


            }

            tableInfo.setHaveDateTime(haveDateTime);
            tableInfo.setHaveDate(haveDate);
            tableInfo.setHavaBigDecimal(havaBigDecimal);
            tableInfo.setFieIddList(fieIdInfoList);
            tableInfo.setFieIdExtendList(fieIdExtendList);



        }catch (Exception e){
            logger.error("获取字段数据读取失败");
        }finally {

            if (fieldResult != null){
                try {
                    fieldResult.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

        }

        //return fieIdInfoList;
    }



    private static List<FieIdInfo> getKeyIndexInfo(TableInfo tableInfo){
        PreparedStatement preparedStatement=null;
        ResultSet fieldResult=null;

        //信息
        List<FieIdInfo> fieIdInfoList=new ArrayList<>();


        try {
            //保存每个字段的字段信息子啊map
            Map<String,FieIdInfo> fieIdInfoMap=new HashMap<>();
            for (FieIdInfo f:tableInfo.getFieIddList()) {
                //字段名  字段信息
                fieIdInfoMap.put(f.getFieIdName(),f);
            }


            //获取信息sql语句
            preparedStatement=connection.prepareStatement(String.format(SQL_SHOW_TABLE_INDEX,tableInfo.getTableName()));
            //结果集
            fieldResult=preparedStatement.executeQuery();

            while (fieldResult.next()){

                //获取表信息
                String keyName=fieldResult.getString("key_name");
                Integer nonUnique=fieldResult.getInt("non_unique");
                String columnName=fieldResult.getString("column_name");

                //1 index 索引  0 唯一Unique
                if (nonUnique == 1){
                    continue;
                }
                // private Map<String, List<FieIdInfo>> keyIndexMap=new LinkedHashMap<>();
                List<FieIdInfo> keyFielsList=tableInfo.getKeyIndexMap().get(keyName);

                //如果为空  创建一个
                if (null == keyFielsList){
                    keyFielsList=new ArrayList<>();
                    tableInfo.getKeyIndexMap().put(keyName,keyFielsList);
                }
                //在把数据从 tableInfo.getFieIddList() --》 fieIdInfo  --》 字段相同 索引 添加
                //循环 找相同的字段 保存对应的字段信息
//                for(FieIdInfo fieIdInfo:tableInfo.getFieIddList()){
//                    if(fieIdInfo.getFieIdName().equals(columnName)){
//                        keyFielsList.add(fieIdInfo);
//                    }
//                }

                //map集合 字段名 对应字段信息
                keyFielsList.add(fieIdInfoMap.get(columnName));


            }


        }catch (Exception e){
            logger.error("获取索引数据读取失败");
        }finally {

            if (fieldResult != null){
                try {
                    fieldResult.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }

        }

        return fieIdInfoList;
    }









    //根据uperCaseFirstLetter 是否大写第一个      驼峰表名称  demo_ceshi -> DemoCeshi/demoCeshi
    private static String processFieled(String field,Boolean uperCaseFirstLetter){

        StringBuffer sb=new StringBuffer();

        String[] fields=field.split("_");

       // sb.append(uperCaseFirstLetter ? fields[0].toUpperCase() : fields[0] );
        //处理第一位
        //logger.info("fields[0]"+fields[0]);
        //logger.info("StringUtil.upCaseFirstLetter(fields[0])"+StringUtil.upCaseFirstLetter(fields[0]));
        sb.append(uperCaseFirstLetter ? StringUtil.upCaseFirstLetter(fields[0]) : fields[0] );

        for (int i = 1,len=fields.length; i <len ; i++) {
            sb.append(StringUtil.upCaseFirstLetter(fields[i]));
        }

        return sb.toString();

    }


    //sql数据类型转化 java的数据类型
    private static String processJavaType(String type){

        if( ArrayUtils.contains(Constants.SQL_INTEGER_TYPES,type) ){
            return  "Integer";
        }else if ( ArrayUtils.contains(Constants.SQL_LONG_TYPES,type) ){
            return "Long";
        }else if ( ArrayUtils.contains(Constants.SQL_STRING_TYPES,type) ){
            return "String";
        }else if ( ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type) || ArrayUtils.contains(Constants.SQL_DATE_TYPES,type)){
           return "Date";
        }else if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES,type) ){
            return "BigDecimal";
        }else {
            throw new RuntimeException("无法识别的类型"+type);
        }
    }

}




























