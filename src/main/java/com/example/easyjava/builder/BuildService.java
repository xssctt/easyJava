package com.example.easyjava.builder;

import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import com.example.easyjava.entity.po.CeshiUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildService {

    private static final Logger logger= LoggerFactory.getLogger(BuildService.class);
    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_SERVICE);
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

        //AAAQuery
        String classQueryName=tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY;
        //AAA
        String classPoName=tableInfo.getBeanName();
        //AAAService
        String className=tableInfo.getBeanName()+Constants.SUFFIX_SERVICE;
        File pofile=new File(folder,className+".java");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;


        try{
            outputStream=new FileOutputStream(pofile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);


            bufferedWriter.write("package "+Constants.PACKAGE_SERVICE+";");
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
            bufferedWriter.write("import "+Constants.PACKAGE_PO+"."+classPoName+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.PACKAGE_QUERY+"."+classQueryName+";");
            bufferedWriter.newLine();
            //import java.util.List;
            bufferedWriter.write("import java.util.List;");
            bufferedWriter.newLine();

            bufferedWriter.write("import "+Constants.PACKAGE_VO+".PaginationResultVo;");
            bufferedWriter.newLine();


            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //类名 public class---
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment()+"service 逻辑层");
            bufferedWriter.write("public interface "+className+" {");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //List<CeshiUser> findListByParam(CeshiUser param);
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询列表");
            bufferedWriter.write("\t"+"List<"+classPoName+"> findListByParam("+classQueryName+" query);");
            bufferedWriter.newLine();


            //Integer findCountByParam(CeshiUser param);
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询多少数量");
            bufferedWriter.write("\t"+"Integer findCountByParam("+classQueryName+" query);");
            bufferedWriter.newLine();

            //
            //PaginationResultVo<CeshiUser> findListByPage(classQueryName param);
            BuildComment.createFieldComment(bufferedWriter,"分页查询");
            bufferedWriter.write("\t"+"PaginationResultVo<"+classPoName+"> findListByPage("+classQueryName+" query);");
            bufferedWriter.newLine();



            //Integer add(CeshiUser bean);
            BuildComment.createFieldComment(bufferedWriter,"新增");
            bufferedWriter.write("\t"+"Integer add("+classPoName+" bean);");
            bufferedWriter.newLine();


            //Integer addBatch(List<CeshiUser> listBean);
            BuildComment.createFieldComment(bufferedWriter,"批量新增");
            bufferedWriter.write("\t"+"Integer addBatch(List<"+classPoName+"> listBean);");
            bufferedWriter.newLine();



           // Integer addOrUpdateBatch(List<CeshiUser> listBean);
            BuildComment.createFieldComment(bufferedWriter,"批量新增/修改");
            bufferedWriter.write("\t"+"Integer addOrUpdateBatch(List<"+classPoName+"> listBean);");
            bufferedWriter.newLine();


//            //CeshiUser getCeshiById(Integer id);
//            BuildComment.createFieldComment(bufferedWriter,"根据ID查询对象");
//            bufferedWriter.write("\t"+""+classPoName+" get"+classPoName+"ById(Integer id);");
//            bufferedWriter.newLine();
//
//
//
//            //Integer updateCeshiById(CeshiUser bean,Integer id);
//            BuildComment.createFieldComment(bufferedWriter,"根据ID修改对象");
//            bufferedWriter.write("\t"+"Integer update"+classPoName+"ById("+classPoName+" bean,Integer id);");
//            bufferedWriter.newLine();
//
//
//
//            //Integer deleteCeshiById(Integer id);
//            BuildComment.createFieldComment(bufferedWriter,"根据ID删除对象");
//            bufferedWriter.write("\t"+"Integer delete"+classPoName+"ById(Integer id);");
//            bufferedWriter.newLine();


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
                    methodParams.append( f.getJavaType()+" " +f.getPropertyName());
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
                bufferedWriter.write("\t"+classPoName+" get"+classPoName+"By"+methodName+"("+methodParams+");");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"Integer update"+classPoName+"By"+methodName+"("+classPoName+" bean, "+methodParams+");");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"Integer delete"+classPoName+"By"+methodName+"("+methodParams+");");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

            }



            bufferedWriter.write("}");
            bufferedWriter.newLine();
            bufferedWriter.flush();


        }catch (Exception e){
            logger.error("创建service出错");
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

}
