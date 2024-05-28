package com.example.easyjava.bean;






import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableInfo {
    //表名

    private String tableName;
    //bean名字
    private String beanName;
    //参数名称
    private String beanParamName;
    //表注释
    private String comment;
    //字段信息
    private List<FieIdInfo> fieIddList;
    //扩展字段信息
    private List<FieIdInfo> fieIdExtendList;
    //唯一索引集合
    private Map<String, List<FieIdInfo>> keyIndexMap=new LinkedHashMap<>();
   //是否有date 类型
    private Boolean haveDate;
    //是否有时间类型
    private Boolean haveDateTime;
    //是否有BigDecimal类型
    private Boolean havaBigDecimal;

//    private Boolean haveAutoIncrement;
//
//    public Boolean getHaveAutoIncrement() {
//        return haveAutoIncrement;
//    }
//
//    public void setHaveAutoIncrement(Boolean haveAutoIncrement) {
//        this.haveAutoIncrement = haveAutoIncrement;
//    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanParamName() {
        return beanParamName;
    }

    public void setBeanParamName(String beanParamName) {
        this.beanParamName = beanParamName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FieIdInfo> getFieIddList() {
        return fieIddList;
    }

    public void setFieIddList(List<FieIdInfo> fieIddList) {
        this.fieIddList = fieIddList;
    }

    public Map<String, List<FieIdInfo>> getKeyIndexMap() {
        return keyIndexMap;
    }

    public void setKeyIndexMap(Map<String, List<FieIdInfo>> keyIndexMap) {
        this.keyIndexMap = keyIndexMap;
    }

    public Boolean getHaveDate() {
        return haveDate;
    }

    public void setHaveDate(Boolean haveDate) {
        this.haveDate = haveDate;
    }

    public Boolean getHaveDateTime() {
        return haveDateTime;
    }

    public void setHaveDateTime(Boolean haveDateTime) {
        this.haveDateTime = haveDateTime;
    }

    public Boolean getHavaBigDecimal() {
        return havaBigDecimal;
    }

    public void setHavaBigDecimal(Boolean havaBigDecimal) {
        this.havaBigDecimal = havaBigDecimal;
    }

    public List<FieIdInfo> getFieIdExtendList() {
        return fieIdExtendList;
    }

    public void setFieIdExtendList(List<FieIdInfo> fieIdExtendList) {
        this.fieIdExtendList = fieIdExtendList;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanParamName='" + beanParamName + '\'' +
                ", comment='" + comment + '\'' +
                ", fieIddList=" + fieIddList +
                ", fieIdExtendList=" + fieIdExtendList +
                ", keyIndexMap=" + keyIndexMap +
                ", haveDate=" + haveDate +
                ", haveDateTime=" + haveDateTime +
                ", havaBigDecimal=" + havaBigDecimal +
                '}';
    }
}
