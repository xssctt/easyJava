package com.example.easyjava.builder;

import com.example.easyjava.Util.DateUtil;
import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.io.*;

public class BuildePo {
    private static final Logger logger= LoggerFactory.getLogger(BuildePo.class);
    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_PO);
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

        File pofile=new File(folder,tableInfo.getBeanName()+".java");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;


        try{
            outputStream=new FileOutputStream(pofile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);


            //head
            headBuildPo(bufferedWriter,tableInfo);

            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment());
            bufferedWriter.write("public class "+tableInfo.getBeanName()+" implements Serializable{");
            bufferedWriter.newLine();

//
            entityBuildPo(bufferedWriter,tableInfo);


            getSetBuildPo(bufferedWriter,tableInfo);


            getToStringBuildPo(bufferedWriter,tableInfo);



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

    private static void headBuildPo(BufferedWriter bufferedWriter,TableInfo tableInfo) throws IOException {
        bufferedWriter.write("package "+Constants.PACKAGE_PO+";");
        bufferedWriter.newLine();
        bufferedWriter.newLine();

        bufferedWriter.write("import java.io.Serializable;");
        bufferedWriter.newLine();

        if(tableInfo.getHaveDate() || tableInfo.getHaveDateTime()){
            bufferedWriter.write("import java.util.Date;");
            bufferedWriter.newLine();
            bufferedWriter.write(Constants.IGNORE_DATE_FORMAT_CLASS);
            bufferedWriter.newLine();
            bufferedWriter.write(Constants.IGNORE_DATE_UNFORMAT_CLASS);
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.PACKAGE_ENUMS+".DateTimePatternEnum;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.PACKAGE_UTILS+".DateUtils;");
            bufferedWriter.newLine();
        }
        if (tableInfo.getHavaBigDecimal()){
            bufferedWriter.write("import java.math.BigDecimal;");
            bufferedWriter.newLine();
        }
        for (FieIdInfo f:tableInfo.getFieIddList()) {
            if(ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FILED.split(","),f.getPropertyName())){
                bufferedWriter.write(Constants.IGNORE_BEAN_TOJSON_CLASS);
                bufferedWriter.newLine();
                break;
            }
        }

        bufferedWriter.newLine();
        bufferedWriter.newLine();

    }

    private static void entityBuildPo(BufferedWriter bufferedWriter,TableInfo tableInfo) throws IOException {
        for (FieIdInfo f : tableInfo.getFieIddList()) {
            BuildComment.createFieldComment(bufferedWriter, f.getComment());
            if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, f.getSqlType())) {
                bufferedWriter.write("\t" + String.format(Constants.IGNORE_DATE_FORMAT_EXPRESSION, DateUtil.YYYY_MM_DD_HH_MM_SS));
                bufferedWriter.newLine();

                bufferedWriter.write("\t" + String.format(Constants.IGNORE_DATE_UNFORMAT_EXPRESSION, DateUtil.YYYY_MM_DD_HH_MM_SS));
                bufferedWriter.newLine();

            }
            if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, f.getSqlType())) {
                bufferedWriter.write("\t" + String.format(Constants.IGNORE_DATE_FORMAT_EXPRESSION, DateUtil.YYYY_MM_DD));
                bufferedWriter.newLine();

                bufferedWriter.write("\t" + String.format(Constants.IGNORE_DATE_UNFORMAT_EXPRESSION, DateUtil.YYYY_MM_DD));
                bufferedWriter.newLine();
            }
            if (ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FILED.split(","), f.getPropertyName())) {
                bufferedWriter.write("\t" + Constants.IGNORE_BEAN_TOJSON_EXPRESSION);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("\tprivate " + f.getJavaType() + " " + f.getPropertyName() + ";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
        }
    }

    //public void setSqlType(String SqlType) {
    //        this.SqlType = SqlType;
    //    }
    //public String getSqlType() {
    //        return sqlType;
    //    }
    private static void getSetBuildPo(BufferedWriter bufferedWriter,TableInfo tableInfo) throws IOException {
        for (FieIdInfo f : tableInfo.getFieIddList()) {
            String tempfield = StringUtil.upCaseFirstLetter(f.getPropertyName());
            String settempfield = "set" + tempfield;
            String gettempfield = "get" + tempfield;
            bufferedWriter.write("\t" + "public void " + settempfield + "(" + f.getJavaType() + " " + f.getPropertyName() + ") {");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t" + "this." + f.getPropertyName() + "=" + f.getPropertyName() + ";");
            bufferedWriter.newLine();

            bufferedWriter.write("\t" + "}");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("\t" + "public " + f.getJavaType() + " " + gettempfield + "() {");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t" + "return this." + f.getPropertyName() + ";");
            bufferedWriter.newLine();

            bufferedWriter.write("\t" + "}");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
        }

    }


    private static void getToStringBuildPo(BufferedWriter bufferedWriter,TableInfo tableInfo) throws IOException {

        String returnString = " \" FieIdInfo{ \" + ";
        for (FieIdInfo f : tableInfo.getFieIddList()) {
            String comment = f.getComment();
            String propertyName = f.getPropertyName();
            String propertyName2 = f.getPropertyName();
            String propertyNameUp = StringUtil.upCaseFirstLetter(propertyName);

            if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, f.getSqlType())) {
                propertyName = "DateUtils.format(" + propertyName + ",DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())";
            } else if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, f.getSqlType())) {
                propertyName = "DateUtils.format(" + propertyName + ",DateTimePatternEnum.YYYY_MM_DD.getPattern())";
            }

            returnString += "\n\t" + " \" " + (comment.isEmpty() ? "" : (comment + " : ")) + propertyNameUp + "=\'\" +(" + propertyName2 + "==null ? \"空\" : " + propertyName + ") + \"\' \"" + "+";
        }
        returnString += " \'}\'; ";
        bufferedWriter.write("\t" + "@Override");
        bufferedWriter.newLine();
        bufferedWriter.write("\t" + "public String toString() {");
        bufferedWriter.newLine();

        bufferedWriter.write("\t" + "return" + returnString);
        bufferedWriter.newLine();

        bufferedWriter.write("\t" + "}");
        bufferedWriter.newLine();

    }


}
