package com.cqu.project.graduation.util;

public class TimeGeneratorUtil {

    public static String timeIncrement(String startTime){
        if(startTime.equals("0010")){
            return startTime;
        }

        int hour = Integer.valueOf(startTime.substring(0, 2));
        int min = Integer.valueOf(startTime.substring(2));

        if(min == 50){
            min = 0;
            if(hour == 23){
                hour = 0;
            } else {
                hour++;
            }
        } else {
            min += 10;
        }
        return convertLess10Num(hour) + convertLess10Num(min);
    }

     public static String convertLess10Num(int time){
        if(time < 10){
            return "0" + time;
        }
        return String.valueOf(time);
    }


}
