package com.example.easyjava.builder;

import com.example.easyjava.Util.DateUtil;
import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    private static final Logger logger= LoggerFactory.getLogger(BuildMapperXml.class);

    private static final String BASE_RESULT_INTEGER="java.lang.Integer";
    private static final String BASE_RESULT_LONG="java.lang.Long";
    private static final String BASE_RESULT_STRING="";


    private static final String BASE_COLUMN_LIST="base_column_list";
    private static final String BASE_QUERY_CONDITION="base_query_condition";
    private static final String BASE_QUERY_CONDITION_EXTEND="base_query_condition_extend";
    private static final String QUERY_CONDITION="query_condition";

    private static final String SELECT_LIST_ID="selectList";
    private static final String SELECT_COUNT_ID="selectCount";
    private static final String INSERT="insert";
    private static final String INSERT_OR_UPDATE="insertOrUpdate";
    private static final String INSERT_BATCH_ID="insertBatch";
    private static final String INSERT_OR_UPDATE_BATCH_ID="insertOrUpdateBatch";
    private static final String UPDATE_BY_ID="updateById";



    private static final String BASE_RESULT_MAP="base_result_map";





    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_MAPPER_XML);
        if(!folder.exists()){
            folder.mkdirs();
        }


        String className=tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS;
        File pofile=new File(folder,className+".xml");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;


        try{
            outputStream=new FileOutputStream(pofile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);


//<?xml version="1.0" encoding="UTF-8"?>
//<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bufferedWriter.newLine();
            bufferedWriter.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
            bufferedWriter.newLine();
            //<mapper namespace="com.example.mapper.ActivityMapper">

//            </mapper>


            //
            String nameSpace=Constants.PACKAGE_MAPPERS+"."+className ;
            //实体类 路径
            String poClass=Constants.PACKAGE_PO+"."+tableInfo.getBeanName();



            bufferedWriter.write("<mapper namespace=\""+nameSpace+"\">");
            bufferedWriter.newLine();





            //实体类映射
            entityColumn(bufferedWriter,tableInfo,poClass,BASE_RESULT_MAP);

            //通用查询列
            baseColumn(bufferedWriter,tableInfo,BASE_COLUMN_LIST);

            //通用基础查询条件
            baseQueryCondition(bufferedWriter,tableInfo,BASE_QUERY_CONDITION);

            //通用扩展查询条件
            queryConditionExtend(bufferedWriter,tableInfo,BASE_QUERY_CONDITION_EXTEND);
            //汇总
            querycondition(bufferedWriter,QUERY_CONDITION,BASE_QUERY_CONDITION,BASE_QUERY_CONDITION_EXTEND);

            //查询集合
            selectList(bufferedWriter,tableInfo,SELECT_LIST_ID,BASE_RESULT_MAP,BASE_COLUMN_LIST,QUERY_CONDITION);

            //查询数量
            selectCount(bufferedWriter,tableInfo,SELECT_COUNT_ID,BASE_RESULT_LONG,QUERY_CONDITION);

            //插入
            insert(bufferedWriter,tableInfo,INSERT,poClass);

            //插入或者更新
            //INSERT INTO users (id, name) VALUES (1, 'John')
            //ON DUPLICATE KEY UPDATE  name = VALUES(name), updated_at = NOW();
            insertOrUpdate(bufferedWriter,tableInfo,INSERT_OR_UPDATE,poClass);


            //批量插入
            insertBatch(bufferedWriter,tableInfo,INSERT_BATCH_ID,poClass);

            //批量插入或者更新数据
            insertBatchOrUpdate(bufferedWriter,tableInfo,INSERT_OR_UPDATE_BATCH_ID,poClass);

            //
            byIndexChange(bufferedWriter,tableInfo,BASE_RESULT_MAP,BASE_COLUMN_LIST,poClass);

            bufferedWriter.write("</mapper>");
            bufferedWriter.newLine();


            bufferedWriter.flush();


        }catch (Exception e){
            logger.error("创建mapper-xml出错");
        }finally {
            if (bufferedWriter !=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter !=null){
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream !=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    private static void entityColumn(BufferedWriter bufferedWriter,TableInfo tableInfo,String poClass,String base_result_map) throws IOException {

        bufferedWriter.write("<!--   实体类映射 -->");
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+"<resultMap id=\""+base_result_map+"\" type=\""+poClass+"\">");
        bufferedWriter.newLine();

        FieIdInfo idField=null;
        //keyname  字段信息  找到主键
        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
        for (Map.Entry<String, List<FieIdInfo>> listEntry :keyIndexMap.entrySet()) {

            if ("PRIMARY".equals(listEntry.getKey())){
                List<FieIdInfo> fieIdInfos = listEntry.getValue();
                if (fieIdInfos.size() == 1){
                    idField = fieIdInfos.get(0);
                    break;
                }

            }

        }

        for (FieIdInfo f: tableInfo.getFieIddList()) {
            if(idField != null && f.getPropertyName().equals(idField.getPropertyName())){
                bufferedWriter.write("\t\t"+"<!--   "+f.getComment()+" -->");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t"+"<id column=\""+f.getFieIdName()+"\" property=\""+f.getPropertyName()+"\" />");
                bufferedWriter.newLine();
            } else {
                bufferedWriter.write("\t\t"+"<!--   "+f.getComment()+" -->");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t"+"<result column=\""+f.getFieIdName()+"\" property=\""+f.getPropertyName()+"\" />");
                bufferedWriter.newLine();
            }
        }

        bufferedWriter.write("\t"+"</resultMap>");
        bufferedWriter.newLine();


    }


    private static void baseColumn(BufferedWriter bufferedWriter,TableInfo tableInfo,String base_column_list) throws IOException {
        bufferedWriter.write("\t"+"<!--   通用查询列 -->");
        bufferedWriter.newLine();
        //  <sql id="">
        //    </sql>
//        String base_column_list="base_column_list";


        Integer dex=0;
        StringBuilder base_column=new StringBuilder();
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            dex++;
            base_column.append(f.getFieIdName());
            if(dex < tableInfo.getFieIddList().size()){
                base_column.append(",");
            }
        }

        bufferedWriter.write("\t"+"<sql id=\""+base_column_list+"\">");
        bufferedWriter.newLine();

        bufferedWriter.write("\t\t"+base_column);
        bufferedWriter.newLine();

        bufferedWriter.write("\t"+"</sql>");
        bufferedWriter.newLine();

    }


    private static void baseQueryCondition(BufferedWriter bufferedWriter,TableInfo tableInfo,String base_query_condition) throws IOException{

        bufferedWriter.write("\t"+"<!--   通用基础查询条件 -->");
        bufferedWriter.newLine();


        bufferedWriter.write("\t"+"<sql id=\""+base_query_condition+"\">");
        bufferedWriter.newLine();

//        <if test="">
//        </if>
        for (FieIdInfo f:tableInfo.getFieIddList()) {

            String stringQuery="";
            if(ArrayUtils.contains(Constants.SQL_STRING_TYPES,f.getSqlType())){
                stringQuery=" and query."+f.getPropertyName()+" != '' ";
            }

            bufferedWriter.write("\t\t"+"<if test=\"query."+f.getPropertyName()+" != null"+stringQuery+" \">");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t\t"+"and "+f.getFieIdName()+" = #{query."+f.getPropertyName()+"}");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t"+"</if>");
            bufferedWriter.newLine();

        }


        bufferedWriter.write("\t"+"</sql>");
        bufferedWriter.newLine();

    }


    private static void queryConditionExtend(BufferedWriter bufferedWriter,TableInfo tableInfo,String base_query_condition_extend) throws IOException{
        bufferedWriter.write("\t"+"<!--   通用扩展查询条件 -->");
        bufferedWriter.newLine();

        bufferedWriter.write("\t"+"<sql id=\""+base_query_condition_extend+"\">");
        bufferedWriter.newLine();


//        <if test="">
//        </if>
        for (FieIdInfo f:tableInfo.getFieIdExtendList()) {

            String andWhere="";
            if(ArrayUtils.contains(Constants.SQL_STRING_TYPES,f.getSqlType())){
                andWhere="and "+f.getFieIdName()+" like concat('%', #{query."+f.getPropertyName()+"},'%')";
            } else if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,f.getSqlType()) || ArrayUtils.contains(Constants.SQL_DATE_TYPES,f.getSqlType())){
                if(f.getPropertyName().endsWith(Constants.SUFFIX_BEAN_QUERY_TIME_START)){
                    //str_to_date  sql函数
                    //******************
                    //在MySQL中，'%y-%m-%d' 用于表示两位年份、月份和日期，而你的字符串日期 "2024-05-01" 中的年份是四位数，
                    // 因此应该使用 '%Y-%m-%d' 格式化字符串来匹配四位数的年份
                    andWhere=" <![CDATA[ and "+f.getFieIdName()+" >= str_to_date(#{query." + f.getPropertyName() + "}, '%Y-%m-%d' ) ]]>";
                }else if( f.getPropertyName().endsWith(Constants.SUFFIX_BEAN_QUERY_TIME_END) ){
                    //date_sub  ,interval -1 day
                    andWhere=" <![CDATA[ and "+f.getFieIdName()+" < date_sub( str_to_date(#{query."+f.getPropertyName()+"}, '%Y-%m-%d' ),interval -1 day) ]]>";
                }
            }


            bufferedWriter.write("\t\t\t"+"<if test= \"query."+f.getPropertyName()+" != null and query."+f.getPropertyName()+ "!= '' \">");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t\t\t"+andWhere);
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t\t"+"</if>");
            bufferedWriter.newLine();


        }
        bufferedWriter.write("\t"+"</sql>");
        bufferedWriter.newLine();


    }


    private static void querycondition(BufferedWriter bufferedWriter,String query_condition,String base_query_condition,String base_query_condition_extend) throws IOException {

        bufferedWriter.write("\t" + "<!--   扩展查询条件汇总 -->");
        bufferedWriter.newLine();

        bufferedWriter.write("\t" + "<sql id=\"" + query_condition + "\">");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t" + "<where>");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t\t" + "<include refid=\"" + base_query_condition + "\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t\t" + "<include refid=\"" + base_query_condition_extend + "\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t" + "</where>");
        bufferedWriter.newLine();
        bufferedWriter.write("\t" + "</sql>");
        bufferedWriter.newLine();

    }


    private static void selectList(BufferedWriter bufferedWriter,TableInfo tableInfo,
                                   String selectListId, String base_result_map,
                                   String base_column_list,String query_condition) throws IOException{
        //查询集合
        bufferedWriter.write("<!--   查询集合 -->");
        bufferedWriter.newLine();
        // <select id="checkRepeat" resultType="java.lang.Integer">
        //        select count(*) from user_info where ${column} = #{value}
        //    </select>
        bufferedWriter.write("\t"+"<select id=\""+selectListId+"\" resultMap=\""+base_result_map+"\">");
        bufferedWriter.newLine();

        //SELECT <include refid="aa" />
        // FROM bb
        // <include refid="cc"/>
        bufferedWriter.write("\t\t"+"SELECT <include refid=\""+base_column_list+"\" /> FROM "+tableInfo.getTableName()+" <include refid=\""+query_condition+"\"/>");
        bufferedWriter.newLine();

        //<if test="query.orderBy != null">
        //  order by ${query.orderBy}
        // </if>
        //
        //<if test="query.simplePage != null">
        //  limit #{query.simplePage.start},#{query.simplePage.end}
        //</if>
        bufferedWriter.write("\t\t"+"<if test=\"query.orderBy != null\"> order by ${query.orderBy} </if>");
        bufferedWriter.newLine();
        bufferedWriter.newLine();

        bufferedWriter.write("\t\t"+"<if test=\"query.simplePage != null\"> limit #{query.simplePage.start},#{query.simplePage.end} </if>");
        bufferedWriter.newLine();
        bufferedWriter.newLine();


        bufferedWriter.write("\t"+"</select>");
        bufferedWriter.newLine();
    }


    private static void selectCount(BufferedWriter bufferedWriter,TableInfo tableInfo,
                                   String selectCount, String base_resul_type,String query_condition) throws IOException{
        //查询集合
        bufferedWriter.write("<!--   查询数量 -->");
        bufferedWriter.newLine();
        bufferedWriter.write("\t"+"<select id=\""+selectCount+"\" resultType=\""+base_resul_type+"\">");
        bufferedWriter.newLine();

        //select count(1) from table
        //
        bufferedWriter.write("\t\t"+"select count(1) from "+tableInfo.getTableName());
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+"<include refid=\""+query_condition+"\"/>");
        bufferedWriter.newLine();

        bufferedWriter.write("\t"+"</select>");
        bufferedWriter.newLine();
    }


    private static void insert(BufferedWriter bufferedWriter,TableInfo tableInfo,
                                    String insertId,String poClass) throws IOException{

        //插入数据
        bufferedWriter.write("<!--   插入数据 -->");
        bufferedWriter.newLine();


        //    <insert id="id" parameterType="type">
        //    </insert>
        bufferedWriter.write("\t"+" <insert id=\""+insertId+"\" parameterType=\""+poClass+"\">");
        bufferedWriter.newLine();


        //自增id
        //<selectKey keyProperty="bean.id" resultType="integer" order="AFTER">
        // select LAST_INSERT_ID()
        // </selectKey>
        //    INSERT INTO  table
        FieIdInfo autoIncrementField=null;
        for (FieIdInfo f:tableInfo.getFieIddList()) {
            if (f.getAutoIncrement() != null && f.getAutoIncrement()){
                autoIncrementField=f;
                break;
            }
        }
        if (autoIncrementField != null){
            bufferedWriter.write("\t\t"+"<selectKey keyProperty=\"bean."+autoIncrementField.getFieIdName()+"\" resultType=\""+autoIncrementField.getJavaType()+"\" order=\"AFTER\"> ");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"select LAST_INSERT_ID()");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"</selectKey>");
            bufferedWriter.newLine();
        }


        //插入语句
        //INSERT INTO table
        bufferedWriter.write("\t\t"+"INSERT INTO  "+tableInfo.getTableName());
        bufferedWriter.newLine();



        //数据库字段
        //(,,,,,,,,,)
        //  <trim prefix="(" suffix=")" suffixOverrides=",">
        //            <if test="bean.companyId != null">
        //               company_id,
        //            </if>
        //  </trim>
        bufferedWriter.write("\t\t"+"<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        bufferedWriter.newLine();

        for (FieIdInfo f: tableInfo.getFieIddList()) {

            bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\"> ");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t\t"+f.getFieIdName()+",");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"</if>");
            bufferedWriter.newLine();
        }



        bufferedWriter.write("\t\t"+"</trim>");
        bufferedWriter.newLine();

//---------------------------------------------------------------

        //插入字段
        //values (,,,,,,,,,,)
        bufferedWriter.write("\t\t"+"<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
        bufferedWriter.newLine();

        for (FieIdInfo f: tableInfo.getFieIddList()) {

            bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\"> ");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t\t#{bean."+f.getPropertyName()+"},");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"</if>");
            bufferedWriter.newLine();
        }
        bufferedWriter.write("\t\t"+"</trim>");
        bufferedWriter.newLine();



        bufferedWriter.write("\t"+"</insert>");
        bufferedWriter.newLine();
    }


    private static void insertOrUpdate(BufferedWriter bufferedWriter,TableInfo tableInfo,
                               String insertOrUpdateId,String poClass) throws IOException{

        //插入或者更新数据
        bufferedWriter.write("<!--   插入或者更新数据 -->");
        bufferedWriter.newLine();


        //    <insert id="id" parameterType="type">
        //    </insert>
        bufferedWriter.write("\t"+" <insert id=\""+insertOrUpdateId+"\" parameterType=\""+poClass+"\">");
        bufferedWriter.newLine();


        //插入语句
        //INSERT INTO table
        bufferedWriter.write("\t\t"+"INSERT INTO  "+tableInfo.getTableName());
        bufferedWriter.newLine();


        //数据库字段
        // (,,,,,,,,,,,)
        //  <trim prefix="(" suffix=")" suffixOverrides=",">
        //            <if test="bean.companyId != null">
        //               company_id,
        //            </if>
        //  </trim>
        bufferedWriter.write("\t\t"+"<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        bufferedWriter.newLine();

        for (FieIdInfo f: tableInfo.getFieIddList()) {

            bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\"> ");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t\t"+f.getFieIdName()+",");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"</if>");
            bufferedWriter.newLine();
        }



        bufferedWriter.write("\t\t"+"</trim>");
        bufferedWriter.newLine();

//---------------------------------------------------------------

        //插入字段
        //values (,,,,,,,,,,)
        bufferedWriter.write("\t\t"+"<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
        bufferedWriter.newLine();

        for (FieIdInfo f: tableInfo.getFieIddList()) {

            bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\"> ");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t\t#{bean."+f.getPropertyName()+"},");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"</if>");
            bufferedWriter.newLine();
        }
        bufferedWriter.write("\t\t"+"</trim>");
        bufferedWriter.newLine();


//        on DUPLICATE key update
        // 注意，ON DUPLICATE KEY UPDATE 只在唯一索引或主键冲突时起作用，如果没有重复键冲突，则会执行插入操作。
        bufferedWriter.write("\t\t"+"on DUPLICATE key update");
        bufferedWriter.newLine();


        //INSERT INTO users (id, name) VALUES (1, 'John')
        // ON DUPLICATE KEY UPDATE  name = VALUES(name), updated_at = NOW();
        // 这个示例在执行插入操作时，如果 (id, name) 组合已存在，则将 name 更新为指定值，并将 updated_at 更新为当前时间。
        //aa=values(aa) ,bb=values(bb) ,cc=values(cc) ,
        bufferedWriter.write("\t\t"+"<trim prefix=\"\" suffix=\"\" suffixOverrides=\",\">");
        bufferedWriter.newLine();

        //获取索引字段的字段信息
        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
        Map<String,String> keyTemp=new HashMap<>();
        for (Map.Entry<String, List<FieIdInfo>> entry: keyIndexMap.entrySet()) {
            List<FieIdInfo> infos=entry.getValue();
            for (FieIdInfo f:infos) {
                keyTemp.put(f.getFieIdName(),f.getFieIdName());
            }

        }

        int indea=0;
        StringBuilder valuesString=new StringBuilder();
        for (FieIdInfo f: tableInfo.getFieIddList()) {

            indea++;
            //去除 索引字段的更新权限
            if(keyTemp.get(f.getFieIdName()) != null){
                continue;
            }

            bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\"> ");
            bufferedWriter.newLine();
            valuesString.append(f.getFieIdName()+"= VALUES("+f.getFieIdName()+")");
            if(indea < tableInfo.getFieIddList().size()){
                valuesString.append(",");
            }
            bufferedWriter.write("\t\t\t\t"+valuesString);
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"</if>");
            bufferedWriter.newLine();
        }

        bufferedWriter.write("\t\t"+"</trim>");
        bufferedWriter.newLine();




        bufferedWriter.write("\t"+"</insert>");
        bufferedWriter.newLine();
    }

    private static void insertBatch(BufferedWriter bufferedWriter,TableInfo tableInfo,
                               String insertBatchId,String poClass) throws IOException{

        //插入数据
        bufferedWriter.write("<!--   批量插入数据 -->");
        bufferedWriter.newLine();


        //    <insert id="id" parameterType="type">
        //    </insert>
        //  poClass ==  Constants.PACKAGE_PO+"."+tableInfo.getBeanName()
        bufferedWriter.write("\t"+"<insert id=\""+insertBatchId+"\" parameterType=\""+poClass+"\" useGeneratedKeys=\"true\" keyProperty=\""+"id"+"\">");
        bufferedWriter.newLine();


        StringBuilder stringItem=new StringBuilder();
        stringItem.append("(");
        Integer dex=0;
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            dex++;
            //如果id自增长 跳过
            if(f.getAutoIncrement()){
                continue;
            }
            stringItem.append(f.getFieIdName());
            if(dex < tableInfo.getFieIddList().size()){
                stringItem.append(",");
            }
        }
        stringItem.append(") values");
        //插入语句
        //INSERT INTO table()
        bufferedWriter.write("\t\t"+"INSERT INTO  "+tableInfo.getTableName()+stringItem);
        bufferedWriter.newLine();




        // <foreach collection="list" item="item" separator=",">
        //            (
        //            #{item.appid},#{item.}
        //            )
        //        </foreach>
        //   open="("  close=")"      bufferedWriter.write("\t\t"+"(");    bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+"<foreach collection=\"list\" item=\"item\" separator=\",\" >");
        bufferedWriter.newLine();


        StringBuilder stringValues=new StringBuilder();
        Integer dexa=0;
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            dexa++;
            //如果id自增长 跳过
            if(f.getAutoIncrement()){
                continue;
            }
            stringValues.append("#{item.");
            stringValues.append(f.getPropertyName());
            stringValues.append("}");
            if(dexa < tableInfo.getFieIddList().size()){
                stringValues.append(",");
            }
        }
        bufferedWriter.write("\t\t"+"(");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+stringValues);
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+")");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+"</foreach>");
        bufferedWriter.newLine();





        bufferedWriter.write("\t"+"</insert>");
        bufferedWriter.newLine();
    }

    private static void insertBatchOrUpdate(BufferedWriter bufferedWriter,TableInfo tableInfo,
                                    String insertOrUpdateBatchId,String poClass) throws IOException{

        //批量插入或者更新数据
        bufferedWriter.write("<!--   批量插入或者更新数据 -->");
        bufferedWriter.newLine();
        //    <insert id="id" parameterType="type">
        //    </insert>
        bufferedWriter.write("\t"+" <insert id=\""+insertOrUpdateBatchId+"\" parameterType=\""+poClass+"\">");
        bufferedWriter.newLine();
//---------------------------------------------------------------


        StringBuilder stringItem=new StringBuilder();
        stringItem.append("(");
//        Integer dex=0;
//        for (FieIdInfo f: tableInfo.getFieIddList()) {
//            dex++;
//            //如果id自增长 跳过
//            if(f.getAutoIncrement()){
//                continue;
//            }
//            stringItem.append(f.getFieIdName());
//            if(dex < tableInfo.getFieIddList().size()){
//                stringItem.append(",");
//            }
//        }
        String filedString = getFiledStringFieldName(tableInfo, "", "", false);
        stringItem.append(filedString);
        stringItem.append(") values");
        //插入语句
        //INSERT INTO table()
        bufferedWriter.write("\t\t"+"INSERT INTO  "+tableInfo.getTableName()+stringItem);
        bufferedWriter.newLine();




        // <foreach collection="list" item="item" separator=",">
        //            (
        //            #{item.appid},#{item.}
        //            )
        //        </foreach>
        bufferedWriter.write("\t\t"+"<foreach collection=\"list\" item=\"item\" separator=\",\" >");
        bufferedWriter.newLine();


//        StringBuilder stringValues=new StringBuilder();
//        Integer dexa=0;
//        for (FieIdInfo f: tableInfo.getFieIddList()) {
//            dexa++;
//            //如果id自增长 跳过
//            if(f.getAutoIncrement()){
//                continue;
//            }
//            stringValues.append("#{item.");
//            stringValues.append(f.getPropertyName());
//            stringValues.append("}");
//            if(dexa < tableInfo.getFieIddList().size()){
//                stringValues.append(",");
//            }
//        }
        String stringValues=getFiledStringPropertyName(tableInfo,"#{item.","}",false);


        bufferedWriter.write("\t\t"+"(");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+stringValues);
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+")");
        bufferedWriter.newLine();
        bufferedWriter.write("\t\t"+"</foreach>");
        bufferedWriter.newLine();


//---------------------------------------------------------------


//        on DUPLICATE key update
        // 注意，ON DUPLICATE KEY UPDATE 只在唯一索引或主键冲突时起作用，如果没有重复键冲突，则会执行插入操作。
        bufferedWriter.write("\t\t"+"on DUPLICATE key update");
        bufferedWriter.newLine();

//---------------------------------------------------------------

        //获取索引字段的字段信息
//        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
//        Map<String,String> keyTemp=new HashMap<>();
//        for (Map.Entry<String, List<FieIdInfo>> entry: keyIndexMap.entrySet()) {
//            List<FieIdInfo> infos=entry.getValue();
//            for (FieIdInfo f:infos) {
//                keyTemp.put(f.getFieIdName(),f.getFieIdName());
//            }
//
//        }

        Map<String, String> keyMap = getKeyMap(tableInfo);

        Integer inda=0;
        StringBuilder duplicateString=new StringBuilder();
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            inda++;
            //去除 索引字段的更新权限
//            if(keyTemp.get(f.getFieIdName()) != null){
//                continue;
//            }
//            bufferedWriter.write("\t\t\t"+f.getFieIdName()+"= VALUES("+f.getFieIdName()+"),");

            if(keyMap.get(f.getFieIdName()) != null){
                continue;
            }

            duplicateString.append("\t\t"+f.getFieIdName()+"= VALUES("+f.getFieIdName()+")");
            if(inda < tableInfo.getFieIddList().size()){
                duplicateString.append(",\n");
            }
        }

        bufferedWriter.write(String.valueOf(duplicateString));
        bufferedWriter.newLine();


        bufferedWriter.write("\t"+"</insert>");
        bufferedWriter.newLine();
    }



    private static void update(BufferedWriter bufferedWriter,TableInfo tableInfo,
                                       String updateId,String poClass) throws IOException{

        //插入或者更新数据
        bufferedWriter.write("<!--   根据 ID更新数据 -->");
        bufferedWriter.newLine();

        // <update id="updateById" parameterType="poClass">
        //        UPDATE  table
        //        <set>
        //            <if test="bean.id != null" >
        //                filedid=#{bean.propreid}
        //            </if>
        //        </set>
        //    </update>

        bufferedWriter.write("\t"+"<update id=\""+updateId+"\" parameterType=\""+poClass+"\">");
        bufferedWriter.newLine();

        bufferedWriter.write("\t\t"+"UPDATE  "+tableInfo.getTableName()+"");
        bufferedWriter.newLine();

        bufferedWriter.write("\t\t"+"<set>");
        bufferedWriter.newLine();
        for (FieIdInfo f:tableInfo.getFieIddList()) {
            bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\" >");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t\t"+f.getFieIdName()+"=#{bean."+f.getPropertyName()+"}");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+" </if>");
            bufferedWriter.newLine();
        }

        bufferedWriter.write("\t\t"+"</set>");
        bufferedWriter.newLine();


        bufferedWriter.write("\t"+"</update>");
        bufferedWriter.newLine();
    }

    private static void byIndexChange(BufferedWriter bufferedWriter,TableInfo tableInfo,
                                      String base_result_map,String base_column_list,String poClass) throws IOException{

        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();

        for (Map.Entry<String, List<FieIdInfo>> entry: keyIndexMap.entrySet()) {

            //唯一索引的字段信息
            List<FieIdInfo> keyFieIdInfoList = entry.getValue();

            //*****************
            Integer index=0;
            StringBuilder methodName=new StringBuilder();

            StringBuilder paramsName=new StringBuilder();

            //不同的索引 不同的字段   list
            for (FieIdInfo f:keyFieIdInfoList) {
                //************
                index++;
                methodName.append(StringUtil.upCaseFirstLetter(f.getPropertyName()));

                //where   name=#{f.name}
                paramsName.append(f.getFieIdName()+ "=#{"+f.getPropertyName()+"}");
                //索引的字段多 And链接  ************
                if (index<keyFieIdInfoList.size()){
                    methodName.append("And");
                    paramsName.append(" and ");
                }

            }


            //	<select id="selectById"  resultMap="base_result_map">
            //	select <include refid="base_column_list"/>  from  demo_ceshi_user where id=#{id}
            //	</select>
            bufferedWriter.write("<!-- 根据"+methodName+"查询  -->");
            bufferedWriter.newLine();

            //<select id="checkRepeat" resultType="java.lang.Integer">
            bufferedWriter.write("\t"+"<select id=\""+"selectBy"+methodName+"\"  resultMap=\""+base_result_map+"\">");
            bufferedWriter.newLine();

            // "<include refid=\"" + base_query_condition + "\"/>"
            bufferedWriter.write("\t"+"select <include refid=\"" + base_column_list + "\"/>  from  "+tableInfo.getTableName()+" where "+paramsName);
            bufferedWriter.newLine();

            bufferedWriter.write("\t"+"</select>");
            bufferedWriter.newLine();
            bufferedWriter.newLine();



            bufferedWriter.write("<!-- 根据"+methodName+"更新-->");
            bufferedWriter.newLine();

            //<update id="updateById" parameterType="poClass">
            bufferedWriter.write("\t"+"<update id= \"updateBy"+methodName+"\" parameterType=\""+poClass+"\">");
            bufferedWriter.newLine();


            bufferedWriter.write("\t\t"+"UPDATE  "+tableInfo.getTableName()+"");
            bufferedWriter.newLine();
            //set sqlfieldid=bean.values,
            bufferedWriter.write("\t\t"+"<set>");
            bufferedWriter.newLine();
            Integer listindex=0;
            StringBuilder valueString=new StringBuilder();
            for (FieIdInfo f:tableInfo.getFieIddList()) {

                listindex++;

                bufferedWriter.write("\t\t\t"+"<if test=\"bean."+f.getPropertyName()+" != null\" >");
                bufferedWriter.newLine();
                valueString.append(f.getFieIdName()+"=#{bean."+f.getPropertyName()+"}");

                if (listindex < tableInfo.getFieIddList().size()){
                    valueString.append(",");
                }

                bufferedWriter.write("\t\t\t\t"+ valueString);
                bufferedWriter.newLine();

                //每个使用一次  所以清空  对 ,  的美观处理
                valueString.delete(0, valueString.length());

                bufferedWriter.write("\t\t\t"+" </if>");
                bufferedWriter.newLine();
            }

            bufferedWriter.write("\t\t"+"</set>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"where "+paramsName);
            bufferedWriter.newLine();


            bufferedWriter.write("\t"+"</update>");
            bufferedWriter.newLine();
            bufferedWriter.newLine();



            bufferedWriter.write("<!-- 根据"+methodName+"删除-->");
            bufferedWriter.newLine();

            //<delete id="deleteBy"+methodName" parameterType="">
            //    </delete>
            bufferedWriter.write("\t"+"<delete id= \"deleteBy"+methodName+"\" parameterType=\""+BASE_RESULT_INTEGER+"\">");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t"+"delete from "+tableInfo.getTableName()+" where "+paramsName);
            bufferedWriter.newLine();

            bufferedWriter.write("\t"+"</delete>");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

        }


    }


    //插入  table()  values()  id自增长 ---》false  id不存在
    //更新  id自增长 ---》true  需要id

    //





    //获取指定的 字符串拼接
    private static String getFiledStringPropertyName(TableInfo tableInfo,String headSuff,String lastSuff,Boolean autoIncrement){
        StringBuilder stringValues=new StringBuilder();
        Integer dexa=0;
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            dexa++;
            //如果id自增长 跳过
            if(f.getAutoIncrement() && autoIncrement){
                continue;
            }
            stringValues.append(headSuff);
            stringValues.append(f.getPropertyName());
            stringValues.append(lastSuff);
            if(dexa < tableInfo.getFieIddList().size()){
                stringValues.append(",");
            }
        }
        return String.valueOf(stringValues);
    }


    private static String getFiledStringFieldName(TableInfo tableInfo,String headSuff,String lastSuff,Boolean autoIncrement){
        StringBuilder stringValues=new StringBuilder();
        Integer dexa=0;
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            dexa++;
            //如果id自增长 跳过
            if(f.getAutoIncrement() && autoIncrement){
                continue;
            }
            stringValues.append(headSuff);
            stringValues.append(f.getPropertyName());
            stringValues.append(lastSuff);
            if(dexa < tableInfo.getFieIddList().size()){
                stringValues.append(",");
            }
        }
        return String.valueOf(stringValues);
    }


    private static String getFiledAndString(TableInfo tableInfo,String headSuff,String lastSuff,Boolean autoIncrement,String andString){
        StringBuilder stringValues=new StringBuilder();
        Integer dexa=0;
        for (FieIdInfo f: tableInfo.getFieIddList()) {
            dexa++;
            //如果id自增长 跳过
            if(f.getAutoIncrement() && autoIncrement){
                continue;
            }
            stringValues.append(headSuff);
            stringValues.append(f.getFieIdName()+andString+f.getFieIdName());
            stringValues.append(lastSuff);
            if(dexa < tableInfo.getFieIddList().size()){
                stringValues.append(",");
            }
        }
        return String.valueOf(stringValues);
    }



    //获取索引字段
    private static Map<String, String> getKeyMap(TableInfo tableInfo){
        //获取索引字段的字段信息
        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
        Map<String,String> keyTemp=new HashMap<>();
        for (Map.Entry<String, List<FieIdInfo>> entry: keyIndexMap.entrySet()) {
            List<FieIdInfo> infos=entry.getValue();
            for (FieIdInfo f:infos) {
                keyTemp.put(f.getFieIdName(),f.getFieIdName());
            }

        }
        return keyTemp;
    }

    //找到 PRIMARY
    private static FieIdInfo getPrimaryFieIdInfo(TableInfo tableInfo){
        FieIdInfo idField=null;
        //keyname  字段信息  找到主键
        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
        for (Map.Entry<String, List<FieIdInfo>> listEntry :keyIndexMap.entrySet()) {

            if ("PRIMARY".equals(listEntry.getKey())){
                List<FieIdInfo> fieIdInfos = listEntry.getValue();
                if (fieIdInfos.size() == 1){
                    idField = fieIdInfos.get(0);
                    break;
                }

            }

        }

        return idField;
    }




}
