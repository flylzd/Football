package com.derby.football.utils;


public class TimeUtil {

    public static String getTimeFromSingle(String time) {
        return time + ":00";
    }

    public static String getTimePeriods(String time) {
        return time + ":00 - " + (Integer.valueOf(time) + 1) + ":00" ;
    }
}
