package com.example.easyjava.builder;

import com.example.easyjava.Util.DateUtil;
import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildeQuery {
    private static final Logger logger= LoggerFactory.getLogger(BuildeQuery.class);
    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_QUERY);
        if(!folder.exists()){
            //System.out.println("chaungjain");
            folder.mkdirs();
        }

//        File file=new File(folder,tableInfo.getTableName()+".java");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String className=tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY;
        File pofile=new File(folder,className+".java");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;


        try{
            outputStream=new FileOutputStream(pofile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);


            bufferedWriter.write("package "+Constants.PACKAGE_QUERY+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

//            bufferedWriter.write("import java.io.Serializable;");
//            bufferedWriter.newLine();

            if(tableInfo.getHaveDate() || tableInfo.getHaveDateTime()){
                bufferedWriter.write("import java.util.Date;");
                bufferedWriter.newLine();
            }

            if (tableInfo.getHavaBigDecimal()){
                bufferedWriter.write("import java.math.BigDecimal;");
                bufferedWriter.newLine();
            }

            bufferedWriter.write("import "+Constants.PACKAGE_QUERY+".BaseQuery;");
            bufferedWriter.newLine();

            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //类名 public class---
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment()+"查询对象");
            bufferedWriter.write("public class "+className+" extends BaseQuery{");
            bufferedWriter.newLine();





            //entity query  ---本来extendlist这里获取   ---extendlist=tableInfo.getFieIdExtendList();修改了
            getExtend(bufferedWriter,tableInfo);

            //public void setSqlType(String SqlType) {
            //        this.SqlType = SqlType;
            //    }
            //public String getSqlType() {
            //        return sqlType;
            //    }

           // tableInfo.setFieIdExtendList(extendlist);


//              fieIdInfoList.addAll(extendlist);


            List<FieIdInfo> extendlist=tableInfo.getFieIdExtendList();
            List<FieIdInfo> fieIdInfoList= tableInfo.getFieIddList();
            //query getset
            getSet(bufferedWriter,fieIdInfoList);
            getSet(bufferedWriter,extendlist);


            bufferedWriter.write("}");
            bufferedWriter.flush();


        }catch (Exception e){
            logger.error("创建po出错");
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

    private static void getSet(BufferedWriter bufferedWriter,List<FieIdInfo> fieIdInfoList) throws IOException {
        for (FieIdInfo f:fieIdInfoList) {
            String tempfield= StringUtil.upCaseFirstLetter(f.getPropertyName());
            String settempfield="set"+tempfield;
            String gettempfield="get"+tempfield;

            //set
            bufferedWriter.write("\t"+"public void "+settempfield+"("+f.getJavaType()+" "+f.getPropertyName()+") {");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t"+"this."+f.getPropertyName()+"="+f.getPropertyName()+";");
            bufferedWriter.newLine();

            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //get
            bufferedWriter.write("\t"+"public "+f.getJavaType()+" "+gettempfield+"() {");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t"+"return this."+f.getPropertyName()+";");
            bufferedWriter.newLine();

            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
        }

    }


    private static void getExtend(BufferedWriter bufferedWriter,TableInfo tableInfo) throws IOException {

        for (FieIdInfo f : tableInfo.getFieIddList()) {

            BuildComment.createFieldComment(bufferedWriter, f.getComment());

            bufferedWriter.write("\tprivate " + f.getJavaType() + " " + f.getPropertyName() + ";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            if (ArrayUtils.contains(Constants.SQL_STRING_TYPES, f.getSqlType())) {
                String propertyName = f.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_FUZZY;
                bufferedWriter.write("\tprivate " + f.getJavaType() + " " + propertyName + ";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

//                FieIdInfo fieIdInfo = new FieIdInfo();
//                fieIdInfo.setPropertyName(propertyName);
//                fieIdInfo.setJavaType(f.getJavaType());
//                extendlist.add(fieIdInfo);

            }

            if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, f.getSqlType()) || ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, f.getSqlType())) {
                String propertyNameStart = f.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_START;
                String propertyNameEnd = f.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_END;
                bufferedWriter.write("\tprivate String" + " " + propertyNameStart + ";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                bufferedWriter.write("\tprivate String" + " " + propertyNameEnd + ";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

//                FieIdInfo fieIdInfoStart = new FieIdInfo();
//                fieIdInfoStart.setPropertyName(propertyNameStart);
//                fieIdInfoStart.setJavaType("String");
//                extendlist.add(fieIdInfoStart);
//
//                FieIdInfo fieIdInfoEnd = new FieIdInfo();
//                fieIdInfoEnd.setPropertyName(propertyNameEnd);
//                fieIdInfoEnd.setJavaType("String");
//                extendlist.add(fieIdInfoEnd);

            }


        }

    }


}
