package com.example.easyjava.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {


    //现在时间获取转String
    public static String NowTimeToString(){
        LocalDateTime currentTime = LocalDateTime.now();
        // 格式化时间，可选
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String formattedTime = currentTime.format(formatter);

        return currentTime.format(formatter);
    }
    //现在时间获取
    public static LocalDateTime NowTime(){
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime;
    }

    //给定时间 转String
    public static String TimeToString(LocalDateTime currentTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String formattedTime = currentTime.format(formatter);

        return currentTime.format(formatter);
    }

    //给定String时间 转LocalDateTime
    public static LocalDateTime TimeToLocalDateTime(String time){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedTime = LocalDateTime.parse(time, formatter);
        return parsedTime;
    }
    //LocalDateTime parsedTime = LocalDateTime.parse(formattedTime, formatter);


    //当前时间的 下M 个月的时间String
    public static String NextTimeToString(Integer m){
        LocalDateTime currentTime = LocalDateTime.now();

        // 计算下一个月的年份和月份
        int nextYear = currentTime.getYear() + (currentTime.getMonthValue() + m - 1) / 12;
        int nextMonth = (currentTime.getMonthValue() + m - 1) % 12 + 1;

        LocalDateTime nextTime=LocalDateTime.of(nextYear,nextMonth,currentTime.getDayOfMonth(), currentTime.getHour(),currentTime.getMinute());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return nextTime.format(formatter);
    }

    //当前时间的 下M 个月的时间LocalDateTime
    public static LocalDateTime NextTime(Integer m){
        LocalDateTime currentTime = LocalDateTime.now();

        // 计算下一个月的年份和月份
        int nextYear = currentTime.getYear() + (currentTime.getMonthValue() + m - 1) / 12;
        int nextMonth = (currentTime.getMonthValue() + m - 1) % 12 + 1;

        LocalDateTime nextTime=LocalDateTime.of(nextYear,nextMonth,currentTime.getDayOfMonth(), currentTime.getHour(),currentTime.getMinute());

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return nextTime;
    }



    //给定时间的 下M 个月的时间String
    public static String NextTimeToString(LocalDateTime currentTime,Integer m){
        // LocalDateTime currentTime = LocalDateTime.now();

        // 计算下一个月的年份和月份
        int nextYear = currentTime.getYear() + (currentTime.getMonthValue() + m - 1) / 12;
        int nextMonth = (currentTime.getMonthValue() + m - 1) % 12 + 1;

        LocalDateTime nextTime=LocalDateTime.of(nextYear,nextMonth,currentTime.getDayOfMonth(), currentTime.getHour(),currentTime.getMinute());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return nextTime.format(formatter);
    }

    //给定时间的 下M 个月的时间LocalDateTime
    public static LocalDateTime NextTime(LocalDateTime currentTime,Integer m){
       // LocalDateTime currentTime = LocalDateTime.now();

        // 计算下一个月的年份和月份
        int nextYear = currentTime.getYear() + (currentTime.getMonthValue() + m - 1) / 12;
        int nextMonth = (currentTime.getMonthValue() + m - 1) % 12 + 1;

        LocalDateTime nextTime=LocalDateTime.of(nextYear,nextMonth,currentTime.getDayOfMonth(), currentTime.getHour(),currentTime.getMinute());

        return nextTime;
    }



}
