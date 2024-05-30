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

public class BuildServiceImpl{

    private static final Logger logger= LoggerFactory.getLogger(BuildServiceImpl.class);
    public static void execute(TableInfo tableInfo){

        File folder=new File(Constants.PATH_SERVICE_IMPL);
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
        //AAAServiceImpl
        String className=tableInfo.getBeanName()+Constants.SUFFIX_SERVICE_IMPL;
        //AAAService
        String classNameService=tableInfo.getBeanName()+Constants.SUFFIX_SERVICE;
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


            bufferedWriter.write("package "+Constants.PACKAGE_SERVICE_IMPL+";");
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
            //import import com.example.easyjava.mappers.CeshiUserMapper;
            bufferedWriter.write("import "+Constants.PACKAGE_MAPPERS+"."+classNameMappers+";");
            bufferedWriter.newLine();

            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //--------------------------------------------------------------------------------------
            //类名 public class---
            //@Service("ceshiUserService")
            BuildComment.createClassComment(bufferedWriter,tableInfo.getComment()+"service 逻辑层");
            bufferedWriter.write("@Service(\"/"+StringUtil.lowCaseFirstLetter(classNameService)+"\")");
            bufferedWriter.newLine();
            bufferedWriter.write("public class "+className+" implements "+classNameService+"{");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //@Resource
            //	CeshiUserMapper<CeshiUser,CeshiUserQuery> ceshiUserMapper;

            bufferedWriter.write("\t"+"@Resource");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"private "+classNameMappers+"<"+classPoName+","+classQueryName+"> "+classNameMappersLow+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();


            //List<CeshiUser> findListByParam(CeshiUser param);
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询列表");
            bufferedWriter.write("\t"+"public List<"+classPoName+"> findListByParam("+classQueryName+" query){");
            bufferedWriter.newLine();

            //retun this.ceshiMapper.selectList(query);
            bufferedWriter.write("\t"+"return this."+classNameMappersLow+".selectList(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();


            //Integer findCountByParam(CeshiUser param);
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询多少数量");
            bufferedWriter.write("\t"+"public Integer findCountByParam("+classQueryName+" query){");
            bufferedWriter.newLine();

            //retun this.ceshiMapper.selectCount(query);
            bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".selectCount(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();
            //
            //PaginationResultVo<CeshiUser> findListByPage(classQueryName param);
            BuildComment.createFieldComment(bufferedWriter,"分页查询");
            bufferedWriter.write("\t"+"public PaginationResultVo<"+classPoName+"> findListByPage("+classQueryName+" query){");
            bufferedWriter.newLine();
            //Integer count=this.findCountByParam(query);
            //Integer pageSze= query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
            //SimplePage page=new SimplePage(query.getPageNo(),count,pageSze);
            //query.setSimplePage(page);
            //List<CeshiUser> list=this.findListByParam(query);
            //PaginationResultVo<CeshiUser> result=new PaginationResultVo<CeshiUser>(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
            //return result;
            bufferedWriter.write("\t\t"+"Integer count=this.findCountByParam(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"Integer pageSze= query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"SimplePage page=new SimplePage(query.getPageNo(),count,pageSze);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"query.setSimplePage(page);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"List<"+classPoName+"> list=this.findListByParam(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"PaginationResultVo<"+classPoName+"> result=new PaginationResultVo<"+classPoName+">(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"return result;");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();


            //Integer add(CeshiUser bean);
            BuildComment.createFieldComment(bufferedWriter,"新增");
            bufferedWriter.write("\t"+"public Integer add("+classPoName+" bean){");
            bufferedWriter.newLine();

            //return this.ceshiUserMapper.insert(bean);
            bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".insert(bean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();

            //Integer addBatch(List<CeshiUser> listBean);
            BuildComment.createFieldComment(bufferedWriter,"批量新增");
            bufferedWriter.write("\t"+"public Integer addBatch(List<"+classPoName+"> listBean){");
            bufferedWriter.newLine();
            //if (listBean == null || listBean.isEmpty()){
            //			return 0;
            //		}
            //		return this.ceshiUserMapper.insertBatch(listBean);
            bufferedWriter.write("\t\t"+"if (listBean == null || listBean.isEmpty()){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"return 0;");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"}");
            bufferedWriter.newLine();

            bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".insertBatch(listBean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
            bufferedWriter.newLine();

            // Integer addOrUpdateBatch(List<CeshiUser> listBean);
            BuildComment.createFieldComment(bufferedWriter,"批量新增/修改");
            bufferedWriter.write("\t"+"public Integer addOrUpdateBatch(List<"+classPoName+"> listBean){");
            bufferedWriter.newLine();

            //if (listBean == null || listBean.isEmpty()){
            //			return 0;
            //		}
            //		return this.ceshiUserMapper.insertBatch(listBean);
            bufferedWriter.write("\t\t"+"if (listBean == null || listBean.isEmpty()){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+"return 0;");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"}");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".insertOrUpdateBatch(listBean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t"+"}");
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


                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"public "+classPoName+" get"+classPoName+"By"+methodName+"("+methodParams+"){");
                bufferedWriter.newLine();

                //return this.ceshiUserMapper.selectById(id);
                bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".selectBy"+methodName+"("+methodquery+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"}");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"public Integer update"+classPoName+"By"+methodName+"("+classPoName+" bean, "+methodParams+"){");
                bufferedWriter.newLine();

                //return this.ceshiUserMapper.updateById(bean,id);
                bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".updateBy"+methodName+"(bean,"+methodquery+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"}");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"public Integer delete"+classPoName+"By"+methodName+"("+methodParams+"){");
                bufferedWriter.newLine();

                //return this.ceshiUserMapper.deleteById(id);
                bufferedWriter.write("\t\t"+"return this."+classNameMappersLow+".deleteBy"+methodName+"("+methodquery+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t"+"}");
                bufferedWriter.newLine();
                bufferedWriter.newLine();

            }













            bufferedWriter.write("}");
            bufferedWriter.newLine();
            bufferedWriter.flush();


        }catch (Exception e){
            logger.error("创建serviceimpl出错");
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
