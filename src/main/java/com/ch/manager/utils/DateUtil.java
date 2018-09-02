package com.ch.manager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类.
 *
 * @author xsum
 * @date 2016年12月23日 下午3:02:52
 */
public class DateUtil {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 校验字符串是否时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static boolean checkFormat(String date) {
        return checkFormat(date, null);
    }

    /**
     * 校验字符串是否指定时间格式
     *
     * @param date
     * @param format
     * @return
     */
    public static boolean checkFormat(String date, String format) {
        boolean convertSuccess = true;
        //指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写
        SimpleDateFormat sdf = null;
        if (format == null || "".equals(format)) {
            sdf = new SimpleDateFormat(format);
        } else {
            sdf = SDF;
        }
        try {
            //设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            sdf.setLenient(false);
            sdf.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 格式化当前时间.
     *
     * @param format 时间格式如："yyyy-MM-dd HH:mm:ss"
     * @return string
     * @author xsum
     */
    public static String format(Date date) {
        return SDF.format(date);
    }

    /**
     * 格式化指定时间.
     *
     * @param format
     * @param date
     * @return
     */
    public static String format(String format, Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将指时间字符串转化为时间
     *
     * @param format
     * @param date
     * @return Date
     */
    public static Date format(String format, String date) {
        try {
            if (StringUtil.isBlank(format)) {
                if(date.indexOf(" ") == -1){
                    date += " 00:00:00";
                }
                return SDF.parse(date);
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将指时间字符串转化为时间
     *
     * @param date
     * @return Date
     */
    public static Date format(String date) {
        return format(null, date);
    }

    /**
     * 计算俩个日期想差的天数.
     *
     * @param date      Date
     * @param otherDate Date
     * @return int
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int) time;
    }

    /**
     * 获取当前年.
     *
     * @param date
     * @return
     * @author xsum
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份.
     *
     * @param date Date
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前小时数. . * @param date Date
     *
     * @return int
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取当前小时数(24小时制).
     *
     * @param date Date
     * @return int
     */
    public static int getHourOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前分钟数.
     *
     * @param date Date
     * @return int
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取当前天数.
     *
     * @param date Date
     * @return int
     * @author xsum
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取之前的天数.
     *
     * @param date
     * @param day
     * @return int
     * @author xsum
     */
    public static int getBeforDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 比较date日期是否大于exDate日期
     *
     * @param date
     * @param exDate
     * @return
     */
    public static boolean getDateExce(Date date, Date exDate) {
        return date.getTime() > exDate.getTime();
    }

    /**
     * 比较date日期是否大于exDate日期
     *
     * @param date
     * @param exDate
     * @return
     */
    public static boolean getDateExce(String date, String exDate) {
        return getDateExce(format(date), format(exDate));
    }

    /**
     * 比较date日期是否大于exDate日期
     *
     * @param date
     * @param exDate
     * @param format
     * @return
     */
    public static boolean getDateExce(String date, String exDate, String format) {
        return getDateExce(format(format, date), format(format, exDate));
    }

    /**
     * 判断date日期是否大于当前日期
     *
     * @param date
     * @return
     */
    public static boolean getDateExce(Date date) {
        return getDateExce(date, new Date());
    }

}
