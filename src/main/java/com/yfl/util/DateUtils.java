package com.yfl.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateUtils {

    /**
     * 获取前一天日期
     *
     * @param
     * @return
     */
    public static Date beforeDayDate(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -amount);

        return c.getTime();
    }

    /**
     * 获取当前时间前几个小时日期
     *
     * @param
     * @return
     */
    public static Date beforeHourDate(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, (c.get(Calendar.HOUR_OF_DAY) - amount));
        return c.getTime();
    }


    public static Date afterDayDate(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);

        return c.getTime();
    }

    public static int getDayOfWeek(Date d) {
        Calendar c = Calendar.getInstance();
        String firstDayOfWeek = "2";/*
         * PropertiesAccessorUtil.getProperty( "firstDayOfWeek") == null ? "2" :
         * PropertiesAccessorUtil.getProperty( "firstDayOfWeek");
         */
        c.setTime(d);

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (StringUtils.equals(firstDayOfWeek, "2")) {
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
        }
        return dayOfWeek;
    }

    public static int getDayOfMonth(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String toString_YYYYMM(Date date) {
        return toStringFormat(date, "yyyyMM");
    }

    public static String toString_YYYYMMDD(Date date) {
        return toStringFormat(date, "yyyyMMdd");
    }

    public static String toString_YYYYMMDDHHmmss(Date date) {
        return toStringFormat(date, "yyyyMMddHHmmss");
    }

    public static String toString_YYYYMMDDDot(Date date) {
        return toStringFormat(date, "yyyy.MM.dd");
    }

    public static String toString_YYYY_MM_DD(Date date) {
        return toStringFormat(date, "yyyy-MM-dd");
    }

    public static String toString_HH_MM_SS(Date date) {
        return toStringFormat(date, "HH:mm:ss");
    }

    public static String toString_YYYY_MM_DD_HH_MM_SS(Date date) {
        return toStringFormat(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toStringFormat(Date date, String format) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parseYYYYMMDDDate(String strDate) {
        return parseFormatDate(strDate, "yyyyMMdd");
    }

    public static Date parseYYYYMMDDDateDot(String strDate) {
        return parseFormatDate(strDate, "yyyy.MM.dd");
    }

    public static Date parseYYYY_MM_DDDate(String strDate) {
        return parseFormatDate(strDate, "yyyy-MM-dd");
    }

    public static Date parseYYYY_MM_DD_HH_MM_SSDate(String strDate) {
        return parseFormatDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseFormatDate(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    public static Date parseUTCDate(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return new Date(0);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    public static String getYesterdayString_YYYYMMDD() {
        return toString_YYYYMMDD(DateUtils.beforeDayDate(new Date(), 1));
    }

    public static long getYesterdayLong_YYYYMMDD() {
        return parseYYYYMMDDDate(getYesterdayString_YYYYMMDD()).getTime();
    }

    public static Date getZeroTimeDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date get23H59M59STimeDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static String getYyyyMMByArg(int arg) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - arg); // 设置为上一个月
        date = calendar.getTime();
        String accDate = format.format(date);
        return accDate;
    }

}
