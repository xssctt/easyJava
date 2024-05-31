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
            bufferedWriter.write("import org.springframework.web.bind.annotation.RestController;");
            bufferedWriter.newLine();
            //import org.springframework.web.bind.annotation.RequestMapping;
            bufferedWriter.write("import org.springframework.web.bind.annotation.RequestMapping;");
            bufferedWriter.newLine();
            //import org.springframework.web.bind.annotation.RequestBody;
            bufferedWriter.write("import org.springframework.web.bind.annotation.RequestBody;");
            bufferedWriter.newLine();
            //import import com.example.easyjava.mappers.CeshiUserMapper;
            bufferedWriter.write("import "+Constants.PACKAGE_MAPPERS+"."+classNameMappers+";");
            bufferedWriter.newLine();
            //import com.example.easyjava.controller.ABaseController;
            bufferedWriter.write("import "+Constants.PACKAGE_CONTROLLER+".ABaseController;");
            bufferedWriter.newLine();
            //import com.example.easyjava.entity.vo.ResponseVo;
            bufferedWriter.write("import "+Constants.PACKAGE_VO+".ResponseVo;");
            bufferedWriter.newLine();


            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //类名 public class---@RequestMapping("/aa")
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment()+"controller 控制层");
            bufferedWriter.write("@RestController(\""+StringUtil.lowCaseFirstLetter(className)+"\")");
            bufferedWriter.newLine();
            bufferedWriter.write("@RequestMapping(\"/"+StringUtil.lowCaseFirstLetter(classPoName)+"\")");
            bufferedWriter.newLine();
            bufferedWriter.write("public class "+className+" extends ABaseController{");
            bufferedWriter.newLine();
            bufferedWriter.newLine();


            bufferedWriter.write("\t"+"@Resource");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"private "+classNameService+" "+classNameServiceLow+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            //-------------------------------------------------------------------------

            //	@RequestMapping("/loadDataList")
            //	public ResponseVo loadDataList(CeshiUserQuery query){
            //		return getSuccessResponseVo(ceshiUserService.findListByParam(query));
            //	}
            BuildComment.createFieldComment(bufferedWriter,"");
            getRequestMapping(bufferedWriter,"loadDateList");
            bufferedWriter.write("\t"+"public ResponseVo loadDataList("+classQueryName+" query){");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".findListByParam(query));");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();
            //-------------------------------------------------------------------------

            //Integer add(CeshiUser bean);
            BuildComment.createFieldComment(bufferedWriter,"新增");
            getRequestMapping(bufferedWriter,"add");
            bufferedWriter.write("\t"+"public ResponseVo add("+classPoName+" bean){");
            bufferedWriter.newLine();

            //return this.ceshiUserMapper.insert(bean);
            bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".add(bean));");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();
            //-------------------------------------------------------------------------

            //Integer addBatch(List<CeshiUser> listBean);
            BuildComment.createFieldComment(bufferedWriter,"批量新增");
            getRequestMapping(bufferedWriter,"addBatch");
            bufferedWriter.write("\t"+"public ResponseVo addBatch(@RequestBody List<"+classPoName+"> listBean){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".addBatch(listBean));");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();
            //-------------------------------------------------------------------------

            // Integer addOrUpdateBatch(List<CeshiUser> listBean);
            BuildComment.createFieldComment(bufferedWriter,"批量新增/修改");
            getRequestMapping(bufferedWriter,"addOrUpdateBatch");
            bufferedWriter.write("\t"+"public ResponseVo addOrUpdateBatch(@RequestBody List<"+classPoName+"> listBean){");
            bufferedWriter.newLine();


            bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".addOrUpdateBatch(listBean));");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();




            Map<String, List<FieIdInfo>> keyIndexMap = tableInfo.getKeyIndexMap();

            for (Map.Entry<String, List<FieIdInfo>> entry: keyIndexMap.entrySet()) {

                //唯一索引的字段信息
                List<FieIdInfo> keyFieIdInfoList = entry.getValue();

                //*****************
                Integer index=0;
                StringBuilder methodName=new StringBuilder();
                StringBuilder methodParams=new StringBuilder();
                StringBuilder methodquery=new StringBuilder();

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
                    methodquery.append(f.getPropertyName());
                    //**********
                    if (index<keyFieIdInfoList.size()){
                        methodParams.append(", ");
                        methodquery.append(", ");
                    }

                }

//                int lastCommaIndex = methodParams.lastIndexOf(",");
//                if (lastCommaIndex != -1) {
//                    methodParams.delete(lastCommaIndex, methodParams.length());
//                }

                //-------------------------------------------------------------------------

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
                getRequestMapping(bufferedWriter,"get"+classPoName+"By"+methodName);
                bufferedWriter.write("\t"+"public ResponseVo get"+classPoName+"By"+methodName+"("+methodParams+"){");
                bufferedWriter.newLine();

                //return this.ceshiUserMapper.selectById(id);
                bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".get"+classPoName+"By"+methodName+"("+methodquery+"));");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"}");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                //-------------------------------------------------------------------------

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                getRequestMapping(bufferedWriter,"update"+classPoName+"By"+methodName);
                bufferedWriter.write("\t"+"public ResponseVo update"+classPoName+"By"+methodName+"("+classPoName+" bean, "+methodParams+"){");
                bufferedWriter.newLine();

                //return this.ceshiUserMapper.updateById(bean,id);
                bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".update"+classPoName+"By"+methodName+"(bean,"+methodquery+"));");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"}");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                //-------------------------------------------------------------------------

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                getRequestMapping(bufferedWriter,"delete"+classPoName+"By"+methodName);
                bufferedWriter.write("\t"+"public ResponseVo delete"+classPoName+"By"+methodName+"("+methodParams+"){");
                bufferedWriter.newLine();

                //return this.ceshiUserMapper.deleteById(id);
                bufferedWriter.write("\t\t"+"return getSuccessResponseVo("+classNameServiceLow+".delete"+classPoName+"By"+methodName+"("+methodquery+"));");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"}");
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

    private static void getRequestMapping(BufferedWriter bufferedWriter,String url) throws IOException {
        bufferedWriter.write("\t"+"@RequestMapping(\"/"+url+"\")");
        bufferedWriter.newLine();

    }

}
