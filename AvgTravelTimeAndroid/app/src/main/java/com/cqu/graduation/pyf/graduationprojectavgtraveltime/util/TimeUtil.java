package com.cqu.graduation.pyf.graduationprojectavgtraveltime.util;

import java.util.Calendar;

/**
 * Created by PYF on 2018/5/23.
 */

public class TimeUtil {

    public static String time(){
        Calendar calendar = Calendar.getInstance();
        int min = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = min/10 * 10;
        return addZero(hour) + addZero(min);
    }

    public static String addZero(int num){
        if (num < 0){
            return "0" + num;
        }
        return String.valueOf(num);
    }

    public static String date(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return addZero(month) + addZero(day);
    }

    public static String formatTime(int hour, int min){
        return addZero(hour) + addZero(min);
    }

    public static String formatDate(int month, int day){
        return addZero(month) + addZero(day);
    }

    public static int year(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int month(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }
    public static int day(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static int hour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    public static int min(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
}
