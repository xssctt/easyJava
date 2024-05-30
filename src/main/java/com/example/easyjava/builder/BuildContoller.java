package com.example.easyjava.builder;

import com.example.easyjava.Util.StringUtil;
import com.example.easyjava.bean.Constants;
import com.example.easyjava.bean.FieIdInfo;
import com.example.easyjava.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildContoller {

    private static final Logger logger= LoggerFactory.getLogger(BuildContoller.class);
    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_CONTROLLER);
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
        //AAAController
        String className=tableInfo.getBeanName()+Constants.SUFFIX_BEAN_CONTROLLER;
        //AAAService
        String classNameService=tableInfo.getBeanName()+Constants.SUFFIX_SERVICE;
        //aAAService
        String classNameServiceLow=StringUtil.lowCaseFirstLetter(tableInfo.getBeanName()+Constants.SUFFIX_SERVICE);
        //AAAService
        String classNameServiceImpl=tableInfo.getBeanName()+Constants.SUFFIX_SERVICE_IMPL;
        //aAAService
        String classNameServiceImplLow=StringUtil.lowCaseFirstLetter(tableInfo.getBeanName()+Constants.SUFFIX_SERVICE_IMPL);
        //AAAMappers
        String classNameMappers=tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS;
        //aAAMappers
        String classNameMappersLow=StringUtil.lowCaseFirstLetter(tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS);

        File pofile=new File(folder,className+".java");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;


        try{
            outputStream=new FileOutputStream(pofile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);


            bufferedWriter.write("package "+Constants.PACKAGE_CONTROLLER+";");
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
            //import com.example.easyjava.entity.query.SimplePage;
            bufferedWriter.write("import "+Constants.PACKAGE_QUERY+".SimplePage;");
            bufferedWriter.newLine();
            //import com.example.easyjava.entity.vo.PaginationResultVo;
            bufferedWriter.write("import "+Constants.PACKAGE_VO+".PaginationResultVo;");
            bufferedWriter.newLine();
            //import com.example.easyjava.enums.PageSize;
            bufferedWriter.write("import "+Constants.PACKAGE_ENUMS+".PageSize;");
            bufferedWriter.newLine();
            //import com.example.easyjava.service.CeshiUserService;
            bufferedWriter.write("import "+Constants.PACKAGE_SERVICE+"."+classNameService+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            //import javax.annotation.Resource;
            bufferedWriter.write("import javax.annotation.Resource;");
            bufferedWriter.newLine();
            //import org.springframework.stereotype.Service;
            bufferedWriter.write("import org.springframework.stereotype.Service;");
            bufferedWriter.newLine();
            //import org.springframework.stereotype.Controller;
            bufferedWriter.write("import org.springframework.stereotype.Controller;");
            bufferedWriter.newLine();
            //import org.springframework.web.bind.annotation.RequestMapping;
            bufferedWriter.write("import org.springframework.web.bind.annotation.RequestMapping;");
            bufferedWriter.newLine();
            //import import com.example.easyjava.mappers.CeshiUserMapper;
            bufferedWriter.write("import "+Constants.PACKAGE_MAPPERS+"."+classNameMappers+";");
            bufferedWriter.newLine();


            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //类名 public class---@RequestMapping("/aa")
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment()+"controller 控制层");
            bufferedWriter.write("@Controller(\""+StringUtil.lowCaseFirstLetter(className)+"\")");
            bufferedWriter.newLine();
            bufferedWriter.write("@RequestMapping(\""+StringUtil.lowCaseFirstLetter(classPoName)+"\")");
            bufferedWriter.newLine();
            bufferedWriter.write("public class "+className+" {");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("\t"+"@Resource");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+classNameService+" "+classNameServiceLow+";");
            bufferedWriter.newLine();

            bufferedWriter.newLine();


            //List<CeshiUser> findListByParam(CeshiUser param);
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询列表");
            bufferedWriter.write("\t"+"@RequestMapping(\"findListByParam\")");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"List<"+classPoName+"> findListByParam("+classQueryName+" query){");
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
