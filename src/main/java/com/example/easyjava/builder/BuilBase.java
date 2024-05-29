package com.example.easyjava.builder;

import com.example.easyjava.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BuilBase {

    private static final Logger logger= LoggerFactory.getLogger(BuilBase.class);

    public static void execute() {
        //生成必要的工具类
        List<String> HeadInfoList=new ArrayList<>();
        HeadInfoList.add("package "+Constants.PACKAGE_UTILS+";");
        build(HeadInfoList,"DateUtils", Constants.PATH_UTILS);


        //生成必要的枚举
        HeadInfoList.clear();
        HeadInfoList.add("package "+Constants.PACKAGE_ENUMS+";");
        build(HeadInfoList,"DateTimePatternEnum", Constants.PATH_ENUMS);

        //生成basemapper
        HeadInfoList.clear();
        HeadInfoList.add("package "+Constants.PACKAGE_MAPPERS+";");
        build(HeadInfoList,"BaseMapper", Constants.PATH_MAPPERS);

        //生成PageSize 分页信息
        HeadInfoList.clear();
        HeadInfoList.add("package "+Constants.PACKAGE_ENUMS+";");
        build(HeadInfoList,"PageSize", Constants.PATH_ENUMS);

        //生成SimplePage 搜索信息
        HeadInfoList.clear();
        HeadInfoList.add("package "+Constants.PACKAGE_QUERY+";");
        HeadInfoList.add("import "+Constants.PACKAGE_ENUMS+".PageSize"+";");
        build(HeadInfoList,"SimplePage", Constants.PATH_QUERY);

        //生成BaseQuery
        HeadInfoList.clear();
        HeadInfoList.add("package "+Constants.PACKAGE_QUERY+";");
        HeadInfoList.add("import "+Constants.PACKAGE_QUERY+".SimplePage"+";");
        build(HeadInfoList,"BaseQuery", Constants.PATH_QUERY);

        //生成PaginationResultVo
        HeadInfoList.clear();
        HeadInfoList.add("package "+Constants.PACKAGE_VO+";");
        build(HeadInfoList,"PaginationResultVo", Constants.PATH_VO);
    }

    /**
     *
     * @param headlist:包路径
     * @param fileName:文件名
     * @param outPutPath:路径
     * 根据预设的txt生成对应类
     */
//    private static void build(String head,String fileName,String outPutPath){
private static void build(List<String> headlist, String fileName, String outPutPath){
        File folder=new File(outPutPath);
        if(!folder.exists()){
            folder.mkdirs();
        }

        File javaFile=new File(outPutPath,fileName+".java");


        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;

        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;


        try{
            outputStream=new FileOutputStream(javaFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf-8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);

            //获取文件位置 path
            String templatePath=BuilBase.class.getClassLoader().getResource("template/"+fileName+".txt").getPath();
            inputStream=new FileInputStream(templatePath);
            inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            bufferedReader=new BufferedReader(inputStreamReader);

//            bufferedWriter.write(head);
//            bufferedWriter.newLine();
//            bufferedWriter.newLine();
            //添加头部信息
            for (String s:headlist) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                if(s.contains("package")){
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                }
            }


            //根据txt创建对应的java文件
            String reder=null;
            while ( (reder=bufferedReader.readLine()) != null){
                bufferedWriter.write(reder);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

        }catch (Exception e){
            logger.error("读取数据信息失败");
        }finally {
            if (bufferedReader !=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader !=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



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
