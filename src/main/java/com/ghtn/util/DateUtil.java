package com.ghtn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String dateToString(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
        return "";
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date stringToDate(String dateStr) throws ParseException {
        return stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date stringToDate(String dateStr, String format) throws ParseException {
        if (!StringUtil.isNullStr(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        }
        return null;
    }

    public static java.util.Date createUtilDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static java.sql.Date createSqlDate(String dateStr, String format) {
        java.sql.Date date = new java.sql.Date(createUtilDate(dateStr, format).getTime());
        return date;
    }

    /**
     * 得到指定年月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return 指定年月的最后一天
     */
    public static int getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
