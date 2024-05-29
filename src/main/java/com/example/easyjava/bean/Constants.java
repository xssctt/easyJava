package com.example.easyjava.bean;

import com.example.easyjava.Util.PropertiesUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Constants {
//
    public static Boolean IGNORE_TABLE_PREFIX;
    public static String SUFFIX_BEAN_QUERY;
    public static String SUFFIX_BEAN_QUERY_FUZZY;
    public static String SUFFIX_BEAN_QUERY_TIME_START;
    public static String SUFFIX_BEAN_QUERY_TIME_END;
    public static String SUFFIX_MAPPERS;
    public static String SUFFIX_SERVICE;
    public static String SUFFIX_SERVICE_IMPL;
    public static String SUFFIX_BEAN_CONTROLLER;


    //java resources
    public static final String PATH_JAVA="java";
    public static final String PATH_RESOURCES="resources";
//基础包路径
    public static String PACKAGE_BASE;
    public static String PATH_BASE;

    public static String PATH_BASE_RESOURCES;

// query包路径 和path
    public static String PACKAGE_QUERY;
    public static String PATH_QUERY;
// po包路径 和path
    public static String PACKAGE_PO;
    public static String PATH_PO;

    //Vo
    public static String PACKAGE_VO;
    public static String PATH_VO;
//auther
    public static String AUTHER_COMMENT;
//    ignore.bean.tojson.filed
//    ignore.bean.tojson.expression
//    ignore.bean.tojson.class
    public static String IGNORE_BEAN_TOJSON_FILED;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_TOJSON_CLASS;
    //#日期序列化
    //ignore.date.format.expression
    //ignore.date.format.class
    public static String IGNORE_DATE_FORMAT_EXPRESSION;
    public static String IGNORE_DATE_FORMAT_CLASS;
    //#日期反序列化
    //ignore.date.unformat.expression
    //ignore.date.unformat.class
    public static String IGNORE_DATE_UNFORMAT_EXPRESSION;
    public static String IGNORE_DATE_UNFORMAT_CLASS;
//util
    public static String PACKAGE_UTILS;
    public static String PATH_UTILS;
//enum
    public static String PACKAGE_ENUMS;
    public static String PATH_ENUMS;

    public static String PACKAGE_MAPPERS;
    public static String PATH_MAPPERS;

//    public static String PACKAGE_MAPPERXML;
    public static String PATH_MAPPER_XML;

    public static String PACKAGE_SERVICE;
    public static String PATH_SERVICE;
    public static String PACKAGE_SERVICE_IMPL;
    public static String PATH_SERVICE_IMPL;




    static {
        IGNORE_TABLE_PREFIX=Boolean.valueOf(PropertiesUtil.getString("ignore.table.prefix"));

        SUFFIX_BEAN_QUERY=PropertiesUtil.getString("suffix.bean.query");

        PATH_BASE=PropertiesUtil.getString("path.base");
        PACKAGE_BASE=PropertiesUtil.getString("package.base");
        PACKAGE_PO=PropertiesUtil.getString("package.po");
        PACKAGE_QUERY=PropertiesUtil.getString("package.query");
        PACKAGE_VO=PropertiesUtil.getString("package.vo");
        AUTHER_COMMENT=PropertiesUtil.getString("auther.comment");

        SUFFIX_BEAN_QUERY_FUZZY=PropertiesUtil.getString("suffix.bean.query.fuzzy");
        SUFFIX_BEAN_QUERY_TIME_START=PropertiesUtil.getString("suffix.bean.query.time.start");
        SUFFIX_BEAN_QUERY_TIME_END=PropertiesUtil.getString("suffix.bean.query.time.end");


        IGNORE_BEAN_TOJSON_FILED=PropertiesUtil.getString("ignore.bean.tojson.filed");
        IGNORE_BEAN_TOJSON_EXPRESSION=PropertiesUtil.getString("ignore.bean.tojson.expression");
        IGNORE_BEAN_TOJSON_CLASS=PropertiesUtil.getString("ignore.bean.tojson.class");

        IGNORE_DATE_FORMAT_EXPRESSION=PropertiesUtil.getString("ignore.date.format.expression");
        IGNORE_DATE_FORMAT_CLASS=PropertiesUtil.getString("ignore.date.format.class");

        IGNORE_DATE_UNFORMAT_EXPRESSION=PropertiesUtil.getString("ignore.date.unformat.expression");
        IGNORE_DATE_UNFORMAT_CLASS=PropertiesUtil.getString("ignore.date.unformat.class");

        PACKAGE_UTILS=PropertiesUtil.getString("package.utils");
        PACKAGE_ENUMS=PropertiesUtil.getString("package.enums");

        PACKAGE_MAPPERS=PropertiesUtil.getString("package.mappers");
        SUFFIX_MAPPERS=PropertiesUtil.getString("suffix.mappers");

        PACKAGE_SERVICE=PropertiesUtil.getString("package.service");
        SUFFIX_SERVICE=PropertiesUtil.getString("suffix.service");

        PACKAGE_SERVICE_IMPL=PropertiesUtil.getString("package.service.impl");
        SUFFIX_SERVICE_IMPL=PropertiesUtil.getString("suffix.service.impl");

//        PACKAGE_MAPPERXML=PropertiesUtil.getString("package.mapperxml");


        PATH_BASE_RESOURCES=PATH_BASE+PATH_RESOURCES;

        PATH_BASE=PATH_BASE+PATH_JAVA;
        PATH_BASE=PATH_BASE.replace(".","/");

        PACKAGE_PO=PACKAGE_BASE+"."+PACKAGE_PO;
        PATH_PO=PATH_BASE+"/"+PACKAGE_PO.replace(".","/");

        PACKAGE_QUERY=PACKAGE_BASE+"."+PACKAGE_QUERY;
        PATH_QUERY=PATH_BASE+"/"+PACKAGE_QUERY.replace(".","/");

        PACKAGE_VO=PACKAGE_BASE+"."+PACKAGE_VO;
        PATH_VO=PATH_BASE+"/"+PACKAGE_VO.replace(".","/");

        PACKAGE_UTILS=PACKAGE_BASE+"."+PACKAGE_UTILS;
        PATH_UTILS=PATH_BASE+"/"+PACKAGE_UTILS.replace(".","/");

        PACKAGE_ENUMS=PACKAGE_BASE+"."+PACKAGE_ENUMS;
        PATH_ENUMS=PATH_BASE+"/"+PACKAGE_ENUMS.replace(".","/");

        PATH_MAPPER_XML=PATH_BASE_RESOURCES+"/"+PACKAGE_BASE.replace(".","/")+"/"+PACKAGE_MAPPERS;

        PACKAGE_MAPPERS=PACKAGE_BASE+"."+PACKAGE_MAPPERS;
        PATH_MAPPERS=PATH_BASE+"/"+PACKAGE_MAPPERS.replace(".","/");

        PACKAGE_SERVICE=PACKAGE_BASE+"."+PACKAGE_SERVICE;
        PATH_SERVICE=PATH_BASE+"/"+PACKAGE_SERVICE.replace(".","/");

        PACKAGE_SERVICE_IMPL=PACKAGE_SERVICE+"."+"impl";
        PATH_SERVICE_IMPL=PATH_BASE+"/"+PACKAGE_SERVICE_IMPL.replace(".","/");


    }


    //时间
    public static final String[] SQL_DATE_TIME_TYPES=new String[]{"datetime","timestamp"};

    //日期
    public static final String[] SQL_DATE_TYPES=new String[]{"date"};

    //金额
    public static final String[] SQL_DECIMAL_TYPES=new String[]{"decimal","double","float"};

    //string
    public static final String[] SQL_STRING_TYPES=new String[]{"char","varchar","text","mediumtext","longtext"};

    //
    public static final String[] SQL_INTEGER_TYPES=new String[]{"int","tinyint"};

    //
    public static final String[] SQL_LONG_TYPES=new String[]{"bigint"};


    public static void main(String[] args) {
        System.out.println(PATH_BASE_RESOURCES);
        System.out.println(PATH_MAPPER_XML);
        System.out.println(PATH_BASE);
        System.out.println(PACKAGE_MAPPERS);
    }

}
