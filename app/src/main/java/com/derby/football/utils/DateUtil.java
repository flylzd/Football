package com.derby.football.utils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {


    /**
     * 从当前日期开始的一周内的所有天数
     *
     * @param curDate
     * @return
     */
    public static List<Date> dateToWeek(Date curDate) {

        int day = curDate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
//        Long fTime = curDate.getTime() - day * 24 * 3600000;
        Long fTime = curDate.getTime();
        for (int i = 0; i < 7; i++) {
            fdate = new Date();
            fdate.setTime(fTime + (i * 24 * 3600000));
            list.add(i, fdate);
        }
        return list;
    }
}
