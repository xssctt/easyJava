package com.example.easyjava.builder;

import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

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

        //aAAQuery
        String classQueryName=tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY;
        //aAA
        String classPoName=tableInfo.getBeanName();
        //aAAService
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
            bufferedWriter.write("\t"+"List<"+classPoName+"> findListByParam("+classQueryName+" param);");
            bufferedWriter.newLine();


            //Integer findCountByParam(CeshiUser param);
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询多少数量");
            bufferedWriter.write("\t"+"Integer findCountByParam("+classQueryName+" param);");
            bufferedWriter.newLine();

            //
            //PaginationResultVo<CeshiUser> findListByPage(classQueryName param);
            BuildComment.createFieldComment(bufferedWriter,"分页查询");
            bufferedWriter.write("\t"+"PaginationResultVo<"+classPoName+"> findListByPage("+classQueryName+" param);");
            bufferedWriter.newLine();







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
