package com.example.easyjava.bean;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class FieIdInfo {
    //字段名称
    private String FieIdName;
    //bean属性名称
    private String propertyName;
    //sql类型
    private String sqlType;
    //字段类型
    private String javaType;
    //字段备注
    private String comment;
    //字段是否自增长
    private Boolean isAutoIncrement;

    public String getFieIdName() {
        return FieIdName;
    }

    public void setFieIdName(String fieIdName) {
        FieIdName = fieIdName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }


    @Override
    public String toString() {
        return "FieIdInfo{" +
                "FieIdName='" + FieIdName + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", javaType='" + javaType + '\'' +
                ", comment='" + comment + '\'' +
                ", isAutoIncrement=" + isAutoIncrement +
                '}';
    }
}
