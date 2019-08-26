/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cqwo.xxx.core.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UnixTimeHelper {

    private static Logger logger = LoggerFactory.getLogger(UnixTimeHelper.class);

    /**
     * 获取unix时间戳格式
     */
    public static Integer getUnixTimeStamp() {

        return getUnixTimeStamp(Calendar.getInstance());

    }

    /**
     * 获取unix时间戳格式
     */
    public static Integer getUnixTimeStamp(Calendar c) {

        return Math.toIntExact(c.getTimeInMillis() / 1000);

    }

    //region 分钟

    /**
     * 获取当前的分钟数
     *
     * @return
     */
    public static Integer getMinuteUnixTime() {
        return getMinuteUnixTime(getUnixTimeStamp());
    }


    /**
     * 获取当前的分钟数
     *
     * @return
     */
    public static Integer getMinuteUnixTime(Integer timestamp) {

        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            return c.get(Calendar.MINUTE);
        } catch (Exception ex) {
            logger.error("获取当前的分钟数" + ex.getMessage());
        }

        return 0;
    }


    //endregion


    //region 小时数

    /**
     * 获取当前的小时数
     *
     * @return
     */
    public static Integer getHourUnixTime() {
        return getHourUnixTime(getUnixTimeStamp());
    }


    /**
     * 获取当前的分钟数
     *
     * @return
     */
    public static Integer getHourUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            return c.get(Calendar.HOUR_OF_DAY);
        } catch (Exception ex) {
            logger.error("获取当前的分钟数" + ex.getMessage());
        }

        return 0;
    }


    /**
     * 获取小时开始时间
     */
    public static Integer getStarHourUnixTime() {

        return getStarHourUnixTime(getUnixTimeStamp());
    }


    /**
     * 获取小时开始时间
     *
     * @param timestamp 时间
     */
    public static Integer getStarHourUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            // // System.out.println(c.getTime().toLocaleString());
            return getUnixTimeStamp(c);


        } catch (Exception ex) {
            logger.error("获取当前开始的时间戳" + ex.getMessage());
        }

        return 0;

    }

    /**
     * 获取小时线束时间
     */
    public static Integer getEndHourUnixTime() {

        return getEndHourUnixTime(getUnixTimeStamp());
    }


    /**
     * 获取小时线束时间
     *
     * @param timestamp 时间
     */
    public static Integer getEndHourUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);

            //System.out.println(c.getTime().toLocaleString());
            return getUnixTimeStamp(c);


        } catch (Exception ex) {
            logger.error("获取当前开始的时间戳" + ex.getMessage());
        }

        return 0;

    }


    //endregion


    //region 天数

    /**
     * 获取当前开始的时间戳
     *
     * @return
     */
    public static Integer getStartDayUnixTime() {

        return getStartDayUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取当前时间当天开始的时间戳
     *
     * @return
     */
    public static Integer getStartDayUnixTime(Integer timestamp) {

        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            //System.out.println(c.getTime().toLocaleString());
            return getUnixTimeStamp(c);


        } catch (Exception ex) {
            logger.error("获取当前开始的时间戳" + ex.getMessage());
        }

        return 0;
    }


    /**
     * 获取当前线束的时间戳
     *
     * @return
     */
    public static Integer getEndDayUnixTime() {

        return getEndDayUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取当前时间当天开始的时间戳
     *
     * @return
     */
    public static Integer getEndDayUnixTime(Integer timestamp) {

        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);


            return getUnixTimeStamp(c);


        } catch (Exception ex) {
            logger.error("获取当前开始的时间戳" + ex.getMessage());
        }

        return 0;
    }


    /**
     * 获取当前的天数
     *
     * @return
     */
    public static Integer getDayUnixTime() {
        return getDayUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取指定日期的天数
     *
     * @param timestamp 时间戳
     * @return
     */
    public static Integer getDayUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            return c.get(Calendar.DAY_OF_MONTH);
        } catch (Exception ex) {
            logger.error("获取当前的分钟数" + ex.getMessage());
        }

        return 0;
    }


    //endregion 天数


    //region 月份

    /**
     * 获取指定的月份
     *
     * @return
     */
    public static Integer getMonthUnixTime() {
        return getMonthUnixTime(getUnixTimeStamp());
    }


    /**
     * 获取指定的月份
     *
     * @param timestamp 时间戳
     * @return
     */
    public static Integer getMonthUnixTime(Integer timestamp) {

        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            return c.get(Calendar.MONTH);

        } catch (Exception ex) {
            logger.error("获取当前的分钟数" + ex.getMessage());
        }

        return 0;
    }

    /**
     * 获取本月开始时间
     *
     * @return
     */
    public static Integer getStartMonthUnixTime() {
        return getStartMonthUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取指定时间月的开始时间
     *
     * @param timestamp
     * @return
     */
    public static Integer getStartMonthUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);

            int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);

            c.set(Calendar.DAY_OF_MONTH, lastDay);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            // // System.out.println(c.getTime().toLocaleString());

            return getUnixTimeStamp(c);


        } catch (Exception ex) {
            logger.error("获取当前开始的时间戳" + ex.getMessage());
        }

        return 0;
    }


    /**
     * 获取本月结束时间
     *
     * @return
     */
    public static Integer getEndMonthUnixTime() {
        return getEndMonthUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取指定时间月的结束时间
     *
     * @param timestamp
     * @return
     */
    public static Integer getEndMonthUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);

            int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);

            c.set(Calendar.DAY_OF_MONTH, lastDay);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);

            //System.out.println(c.getTime().toLocaleString());

            return getUnixTimeStamp(c);


        } catch (Exception ex) {
            logger.error("获取当前开始的时间戳" + ex.getMessage());
        }

        return 0;
    }

    //endregion

    //region 年份

    /**
     * 获取年份
     *
     * @return
     */
    public static Integer getYearUnixTime() {
        return getYearUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static Integer getYearUnixTime(Integer timestamp) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);
            return c.get(Calendar.YEAR);
        } catch (Exception ex) {
            logger.error("获取当前的分钟数" + ex.getMessage());
        }

        return 0;
    }

    /**
     * 获取本年开始时间
     *
     * @return
     */
    public static Integer getStartYearUnixTime() {
        return getStartYearUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取指定时间的开始时间
     *
     * @param timestamp
     * @return
     */
    public static Integer getStartYearUnixTime(Integer timestamp) {
        try {


            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);

            int lastMonth = c.getActualMinimum(Calendar.MONTH);

            c.set(Calendar.MONTH, lastMonth);
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            //System.out.println(c.getTime().toLocaleString());

            return getUnixTimeStamp(c);

        } catch (Exception ex) {
            logger.error("获取指定时间的开始时间" + ex.getMessage());
        }

        return 0;
    }

    /**
     * 获取本年开始时间
     *
     * @return
     */
    public static Integer getEndYearUnixTime() {
        return getEndYearUnixTime(getUnixTimeStamp());
    }

    /**
     * 获取指定时间的开始时间
     *
     * @param timestamp
     * @return
     */
    public static Integer getEndYearUnixTime(Integer timestamp) {
        try {


            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp * 1000L);

            int lastMonth = c.getActualMaximum(Calendar.MONTH);

            c.set(Calendar.MONTH, lastMonth);
            c.set(Calendar.DAY_OF_MONTH, 31);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);

            //System.out.println(c.getTime().toLocaleString());

            return getUnixTimeStamp(c);

        } catch (Exception ex) {
            logger.error("获取指定时间的开始时间" + ex.getMessage());
        }

        return 0;
    }
    //endregion

    public static void main(String[] args) {

        showUnixTime();
    }


    /**
     * unix显示
     *
     * @return
     */
    public static String showUnixTime() {
        return showUnixTime(getUnixTimeStamp());
    }

    /**
     * unix显示
     *
     * @param timestap 时间戳
     * @return
     */
    public static String showUnixTime(Integer timestap) {

        try {


            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestap * 1000L);

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            // System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            return format.format(c.getTime());


        } catch (Exception ex) {

        }

        return "";

    }

    /**
     * 获取时间戳,增加时区
     *
     * @param datetime   时间
     * @param dateFormat 解析格式
     * @param timeZone   时区
     * @return
     */
    public static Integer getUnixTimeStamp(String datetime, String dateFormat, Integer timeZone) {

        try {

            Date date = DateHelper.convertDateTime(datetime, dateFormat);

            return Math.toIntExact(date.getTime() / 1000) + timeZone * 3600 / 4;


        } catch (Exception ex) {

        }

        return getUnixTimeStamp();

    }


    /**
     * 获取之前的时间
     *
     * @param value    时间值
     * @param timeUnit 时间单位
     * @return
     */
    public static Integer getBeforeTime(Integer timeUnit, Integer value) {

        try {
            Calendar calendar = Calendar.getInstance();

            calendar.add(timeUnit, value * -1);

            return getUnixTimeStamp(calendar);

        } catch (Exception ignored) {

        }

        return getUnixTimeStamp();

    }
}
