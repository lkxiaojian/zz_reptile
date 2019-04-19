package com.reptile.utlils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static String dataAddOneDay(int day){


//        Date current = new Date();
        Date current =strToDate("2019-01-01");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, day);
        current = calendar.getTime();
        String nowDate = getNowDate(current);
        return nowDate;


    }

    private static String getNowDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
}
