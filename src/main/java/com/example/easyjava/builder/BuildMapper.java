package com.example.easyjava.builder;

import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapper {

    private static final Logger logger= LoggerFactory.getLogger(BuildMapper.class);

    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_MAPPERS);
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

        String className=tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS;
        File pofile=new File(folder,className+".java");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;


        try{
            outputStream=new FileOutputStream(pofile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);

            bufferedWriter.write("package "+Constants.PACKAGE_MAPPERS+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("import "+Constants.PACKAGE_MAPPERS+".BaseMapper;");
            bufferedWriter.newLine();
            bufferedWriter.write("import org.apache.ibatis.annotations.Param;");
            bufferedWriter.newLine();

//            for (Map.Entry<String, List<FieIdInfo>> entry:  tableInfo.getKeyIndexMap().entrySet()) {
//
//                //唯一索引的字段信息
//                List<FieIdInfo> keyFieIdInfoList = entry.getValue();
//                //不同的索引 不同的字段list
//                for (FieIdInfo f:keyFieIdInfoList) {
//                    if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,f.getSqlType()) || ArrayUtils.contains(Constants.SQL_DATE_TYPES,f.getSqlType())){
//                        bufferedWriter.write("import java.util.Date;");
//                        bufferedWriter.newLine();
//                        break;
//                    }
//                }
//                for (FieIdInfo f:keyFieIdInfoList) {
//                    if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES,f.getSqlType())){
//                        bufferedWriter.write("import java.math.BigDecimal;");
//                        bufferedWriter.newLine();
//                        break;
//                    }
//                }
//            }

            if(tableInfo.getHaveDate() || tableInfo.getHaveDateTime()){
                bufferedWriter.write("import java.util.Date;");
                bufferedWriter.newLine();

            }
            if (tableInfo.getHavaBigDecimal()){
                bufferedWriter.write("import java.math.BigDecimal;");
                bufferedWriter.newLine();
            }


            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment()+ "mappers");
            bufferedWriter.write("public interface "+className+"<T, P> extends BaseMapper{");
            bufferedWriter.newLine();


            getWriterMapper(bufferedWriter,tableInfo);


            bufferedWriter.write("}");
            bufferedWriter.flush();


        }catch (Exception e){
            logger.error("创建Mpper出错");
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


    private static void getWriterMapper(BufferedWriter bufferedWriter,TableInfo tableInfo) throws IOException {
        Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();

        for (Map.Entry<String, List<FieIdInfo>> entry: keyIndexMap.entrySet()) {

            //唯一索引的字段信息
            List<FieIdInfo> keyFieIdInfoList = entry.getValue();

            //*****************
            Integer index=0;
            StringBuilder methodName=new StringBuilder();
            StringBuilder methodParams=new StringBuilder();

            //不同的索引 不同的字段list
            for (FieIdInfo f:keyFieIdInfoList) {
                //************
                index++;
                methodName.append(StringUtil.upCaseFirstLetter(f.getPropertyName()));
                //索引的字段多 And链接  ************
                if (index<keyFieIdInfoList.size()){
                    methodName.append("And");
                }
                //   @Param("bean") T t
//                    methodParams.append( "@Param(\""+f.getPropertyName()+"\") "+f.getJavaType()+" " +f.getPropertyName()+",");
                methodParams.append( "@Param(\""+f.getPropertyName()+"\") "+f.getJavaType()+" " +f.getPropertyName());
                //**********
                if (index<keyFieIdInfoList.size()){
                    methodParams.append(", ");
                }

            }

//                int lastCommaIndex = methodParams.lastIndexOf(",");
//                if (lastCommaIndex != -1) {
//                    methodParams.delete(lastCommaIndex, methodParams.length());
//                }


            BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"T selectBy"+methodName+"("+methodParams+");");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"Integer updateBy"+methodName+"(@Param(\"bean\") T t, "+methodParams+");");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"Integer deleteBy"+methodName+"("+methodParams+");");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

        }

    }

}
