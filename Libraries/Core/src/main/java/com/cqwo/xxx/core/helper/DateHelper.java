/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 日期处理工具类
 *
 * @author dylan_xu
 * @date Mar 11, 2012
 * @modified by
 * @modified date
 * @see DateHelper
 * @since JDK1.6
 */

public class DateHelper {
    // ~ Static fields/initializers  
    // =============================================  

    private static Logger logger = LoggerFactory.getLogger(DateHelper.class);

    private static String defaultDatePattern = null;

    private static String timePattern = "HH:mm";

    public static final String TS_FORMAT = DateHelper.getDatePattern() + " HH:mm:ss.S";
    /**
     * 日期格式yyyy-MM字符串常量
     */
    private static final String MONTH_FORMAT = "yyyy-MM";
    /**
     * 日期格式yyyy-MM-dd字符串常量
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式HH:mm:ss字符串常量
     */
    private static final String HOUR_FORMAT = "HH:mm:ss";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss字符串常量
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 某天开始时分秒字符串常量  00:00:00
     */
    private static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";
    /**
     * 某天结束时分秒字符串常量  23:59:59
     */
    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";
    //private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
    //private static final SimpleDateFormat SDF_HOUR_FORMAT = new SimpleDateFormat(HOUR_FORMAT);
    //private static final SimpleDateFormat SDF_DATETIME_FORMAT = new SimpleDateFormat(DATETIME_FORMAT);


    // ~ Methods  
    // ================================================================  

    public DateHelper() {
    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDateTime() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            Calendar cale = Calendar.getInstance();
            return dateFormat.format(cale.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期，以格式为：format 的日期字符串形式返回
     *
     * @return
     * @author wanghong
     * @date 4 24, 2013
     */
    public static String getDate(String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.applyPattern(pattern);
            Calendar cale = Calendar.getInstance();
            return dateFormat.format(cale.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Calendar cale = Calendar.getInstance();
            return dateFormat.format(cale.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getTime() {
        String temp = " ";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(HOUR_FORMAT);
            Calendar cale = Calendar.getInstance();
            temp += dateFormat.format(cale.getTime());
            return temp;
        } catch (Exception e) {
            logger.debug("DateUtil.getTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 统计时开始日期的默认值
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getStartDate() {
        try {
            return getYear() + "-01-01";
        } catch (Exception e) {
            logger.debug("DateUtil.getStartDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 统计时结束日期的默认值
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getEndDate() {
        try {
            return getDate();
        } catch (Exception e) {
            logger.debug("DateUtil.getEndDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的年份
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getYear() {
        try {
            Calendar cale = Calendar.getInstance();
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch (Exception e) {
            logger.debug("DateUtil.getYear():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的月份
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getMonth() {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00;00");
            Calendar cale = Calendar.getInstance();
            return df.format((cale.get(Calendar.MONTH) + 1));
        } catch (Exception e) {
            logger.debug("DateUtil.getMonth():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器在当前月中天数
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getDay() {
        try {
            Calendar cale = Calendar.getInstance();
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            logger.debug("DateUtil.getDay():" + e.getMessage());
            return "";
        }
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static Integer getMargin(String date1, String date2) {
        Integer margin;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = dateFormat.parse(date1, pos);
            Date dt2 = dateFormat.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static double getDoubleMargin(String date1, String date2) {
        double margin;
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = dateFormat.parse(date1, pos);
            Date dt2 = dateFormat.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的月数
     *
     * @param date1
     * @param date2
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static Integer getMonthMargin(String date1, String date2) {
        Integer margin;
        try {
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",
                    "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 返回日期加X天后的日期
     *
     * @param date
     * @param i
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String addDay(String date, Integer i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.DATE, i);
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.format(gCal.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addDay():" + e.toString());
            return getDate();
        }
    }

    /**
     * 当前时间加上天数
     *
     * @param i 加上的天数
     * @return
     */
    public static Date addDay(Integer i) {

        Date date = new Date();

        return addDay(date, i);

    }


    public static Date addDay(Date date, Integer i) {
        try {

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, i);// 今天+1天

            Date time = c.getTime();

            return time;

        } catch (Exception e) {
            logger.debug("DateUtil.addDay():" + e.toString());
        }

        return date;

    }

    /**
     * 返回日期加X月后的日期
     *
     * @param date
     * @param i
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String addMonth(String date, Integer i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.MONTH, i);
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.format(gCal.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addMonth():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X年后的日期
     *
     * @param date
     * @param i
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String addYear(String date, Integer i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.YEAR, i);

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            return dateFormat.format(gCal.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addYear():" + e.toString());
            return "";
        }
    }

    /**
     * 返回某年某月中的最大天
     *
     * @param iyear
     * @param imonth
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static Integer getMaxDay(Integer iyear, Integer imonth) {
        Integer day = 0;
        try {
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7
                    || imonth == 8 || imonth == 10 || imonth == 12) {
                day = 31;
            } else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {
                day = 30;
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {
                day = 29;
            } else {
                day = 28;
            }
            return day;
        } catch (Exception e) {
            logger.debug("DateUtil.getMonthDay():" + e.toString());
            return 1;
        }
    }

    public static Date convertDateTime(String datetime, String format) {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);

            return dateFormat.parse(datetime);

        } catch (Exception ex) {

            logger.debug("DateUtil.convertDateTime():" + ex.toString());
        }
        return Calendar.getInstance().getTime();
    }

    /**
     * 格式化日期
     *
     * @param orgDate
     * @param type
     * @param span
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    @SuppressWarnings("static-access")
    public String rollDate(String orgDate, Integer type, Integer span) {
        try {
            String temp = "";
            Integer iyear, imonth, iday;
            Integer iPos = 0;
            char seperater = '-';
            if (orgDate == null || orgDate.length() < 6) {
                return "";
            }

            iPos = orgDate.indexOf(seperater);
            if (iPos > 0) {
                iyear = Integer.parseInt(orgDate.substring(0, iPos));
                temp = orgDate.substring(iPos + 1);
            } else {
                iyear = Integer.parseInt(orgDate.substring(0, 4));
                temp = orgDate.substring(4);
            }

            iPos = temp.indexOf(seperater);
            if (iPos > 0) {
                imonth = Integer.parseInt(temp.substring(0, iPos));
                temp = temp.substring(iPos + 1);
            } else {
                imonth = Integer.parseInt(temp.substring(0, 2));
                temp = temp.substring(2);
            }

            imonth--;
            if (imonth < 0 || imonth > 11) {
                imonth = 0;
            }

            iday = Integer.parseInt(temp);
            if (iday < 1 || iday > 31) {
                iday = 1;
            }

            Calendar orgcale = Calendar.getInstance();
            orgcale.set(iyear, imonth, iday);
            temp = rollDate(orgcale, type, span);
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    public static String rollDate(Calendar cal, Integer type, Integer span) {
        try {
            String temp = "";
            Calendar rolcale;
            rolcale = cal;
            rolcale.add(type, span);

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            temp = dateFormat.format(rolcale.getTime());
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回默认的日期格式
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static synchronized String getDatePattern() {
        defaultDatePattern = "yyyy-MM-dd";
        return defaultDatePattern;
    }

    /**
     * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
     *
     * @param aDate
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 将原有的日期格式的字符串转换为特定的格式, 如(原有和转换)格式为null, 则使用缺省格式yyyy-MM-dd.
     *
     * @param strValue       value 日期格式的字符串
     * @param strFromPattern fromPattern 原有的日期格式
     * @param strToPattern   toPattern 转换成的日期格式
     * @return String 返回日期字符串
     * @author denghw
     */
    public static String formatDate(String strValue, String strFromPattern, String strToPattern) {
        String strDate = null;
        if (strToPattern == null) {
            strToPattern = "yyyy-MM-dd";
        }
        if (strValue != null) {
            try {
                SimpleDateFormat objFormatter = null;
                if (strFromPattern != null) {
                    objFormatter = new SimpleDateFormat(strFromPattern);
                } else {
                    objFormatter = new SimpleDateFormat("yyyy-MM-dd");
                }
                Date day = objFormatter.parse(strValue);
                objFormatter.applyPattern(strToPattern);
                strDate = objFormatter.format(day);
            } catch (Exception e) {
                // logger.error(e);
                return strValue;
            }
            return strDate;
        } else {
            return null;
        }
    }

    /**
     * 取得给定日期的时间字符串，格式为当前默认时间格式
     *
     * @param theTime
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * 取得当前时间的Calendar日历对象
     *
     * @return
     * @throws ParseException
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    /**
     * 将日期类转换成指定格式的字符串形式
     *
     * @param aMask
     * @param aDate
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            logger.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 将指定的日期转换成默认格式的字符串形式
     *
     * @param aDate
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * 将日期字符串按指定格式转换成日期类型
     *
     * @param aMask   指定的日期格式，如:yyyy-MM-dd
     * @param strDate 待转换的日期字符串
     * @return
     * @throws ParseException
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            logger.error("ParseException: " + pe);
            throw pe;
        }
        return (date);
    }

    /**
     * 将日期字符串按默认格式转换成日期类型
     *
     * @param strDate
     * @return
     * @throws ParseException
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static Date convertStringToDate(String strDate)
            throws ParseException {
        Date aDate = null;

        try {

            logger.debug("converting date with pattern: " + getDatePattern());

            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            logger.error("Could not convert '" + strDate + "' to a date, throwing exception");
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return aDate;
    }

    /**
     * 返回一个JAVA简单类型的日期字符串
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static String getSimpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat();
        String ndatetime = formatter.format(new Date());
        return ndatetime;
    }

    /**
     * 将指定字符串格式的日期与当前时间比较
     *
     * @param strDate 需要比较时间
     * @return <p>
     * Integer code
     * <ul>
     * <li>-1 当前时间 < 比较时间 </li>
     * <li> 0 当前时间 = 比较时间 </li>
     * <li>>=1当前时间 > 比较时间 </li>
     * </ul>
     * </p>
     * @author DYLAN
     * @date Feb 17, 2012
     */
    public static Integer compareToCurTime(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return -1;
        }
        Calendar cale = Calendar.getInstance();
        Date curTime = cale.getTime();
        String strCurTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            strCurTime = dateFormat.format(curTime);
        } catch (Exception e) {
            logger.debug("[Could not format '" + strDate + "' to a date, throwing exception:" + e.getLocalizedMessage() + "]");

        }
        if (StringUtils.hasText(strCurTime)) {
            return strCurTime.compareTo(strDate);
        }
        return -1;
    }


    /**
     * 为查询日期添加最小时间
     *
     * @param date 目标类型Date
     * @return
     */
    public static Date addStartTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return calendar.getTime();

        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 增加分钟
     *
     * @param date   目标类型Date
     * @param minute 转换参数Date
     * @return
     */
    public static Date addMinute(Date date, Integer minute) {
        try {
            Calendar cale = Calendar.getInstance();
            cale.setTime(date);
            cale.add(Calendar.MINUTE, minute);
            return cale.getTime();
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 增加分钟
     *
     * @param minute 转换参数Date
     * @return
     */
    public static Date addMinute(Integer minute) {

        Date date = new Date();
        return addMinute(date, minute);
    }

    /**
     * 增加秒
     *
     * @param date   目标类型Date
     * @param second 转换参数Date
     * @return
     */
    public static Date addSecond(Date date, Integer second) {

        try {

            Calendar cale = Calendar.getInstance();
            cale.setTime(date);
            cale.add(Calendar.SECOND, second);
            return cale.getTime();
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 增加秒
     *
     * @param second 转换参数Date
     * @return
     */
    public static Date addSecond(Integer second) {
        Date date = new Date();
        return addSecond(date, second);
    }

    /**
     * 为查询日期添加最大时间
     *
     * @param date 转换参数Date
     * @return
     */
    public static Date addEndTime(Date date) {

        Calendar calendar = Calendar.getInstance();
//        Date date = param;
        try {
            calendar.setTime(date);

            calendar.set(Calendar.HOUR, 23);

            calendar.set(Calendar.MINUTE, 59);

            calendar.set(Calendar.SECOND, 0);

            return calendar.getTime();

        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 返回系统现在年份中指定月份的天数
     *
     * @param month 月份
     * @return 指定月的总天数
     */
    @SuppressWarnings("deprecation")
    public static String getMonthLastDay(Integer month) {


        Calendar calendar = Calendar.getInstance();


        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        Integer year = calendar.get(Calendar.YEAR) + 1900;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    /**
     * 返回指定年份中指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 指定月的总天数
     */
    public static String getMonthLastDay(Integer year, Integer month) {
        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    /**
     * 判断是平年还是闰年
     *
     * @param year
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    public static boolean isLeapyear(Integer year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 取得当前时间的日戳
     *
     * @return
     * @author dylan_xu
     * @date Mar 11, 2012
     */
    @SuppressWarnings("deprecation")
    public static Timestamp getTimestamp() {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 取得指定时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Timestamp getTimestamp(Date date) {
        Timestamp timestamp;
        try {

            timestamp = new Timestamp(date.getTime());

        } catch (Exception ex) {

            date = new Date();
            timestamp = new Timestamp(date.getTime());
        }


        return timestamp;
    }


    /**
     * 取得指定时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Timestamp getTimestamp(String s) {

        Date date = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            date = dateFormat.parse(s);
        } catch (Exception e) {
        }

        return getTimestamp(date);
    }

    /**
     * 当前日期向后n天(n可取负值,表示向前)
     *
     * @param
     * @return
     * @leizong
     * @2014-7-31
     */
    public static String nextDay(String date, Integer n, String format) throws Exception {
        // 后一天
        DateFormat fmt = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fmt.parse(date));
        // 转成毫秒  增加一天  再转回来
        cal.setTimeInMillis(cal.getTimeInMillis() + n * 24 * 60 * 60 * 1000);
        return fmt.format(cal.getTime());
    }

    /**
     * 昨天8点
     */
    public static String yesterDay8() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis() - 24 * 60 * 60 * 1000);

        DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
        String tmNext = fmt.format(cal.getTime());
        return tmNext + " 08:00";
    }


    @Test
    public void test() {

        Date date = new Date();

        Date s = addSecond(date, 5); //System.out.println(SDF_DATETIME_FORMAT.format(date)); //System.out.println(SDF_DATETIME_FORMAT.format(s));

    }

    /**
     * 获取unix时间戳格式
     *
     * @return
     */
    @Deprecated
    public static Integer getUnixTimeStamp() {

        return Math.toIntExact(System.currentTimeMillis() / 1000);

    }

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     *
     * @param timestampString 时间戳 如："1473048265";
     * @param formats         要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String timeStamp2Date(String timestampString, String formats) {

        if (Strings.isNullOrEmpty(formats)) {
            formats = DATETIME_FORMAT;
        }
        Long timestamp = Long.parseLong(timestampString) * 1000;

        SimpleDateFormat dateFormat = new SimpleDateFormat(formats);
        String date = dateFormat.format(new Date(timestamp));
        ;
        return date;
    }

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     *
     * @param timestampString 时间戳 如："1473048265";
     * @return 返回结果 如："2016-09-05";
     */
    public static String timeStamp2Date(String timestampString) {
        return timeStamp2Date(timestampString, DATE_FORMAT);
    }

    @Test
    public void test2() { //System.out.println(Timestamp.valueOf("152911714000")); //System.out.println(new Date(Long.parseLong("152911714000")));
    }

    /**
     * 添加时间转
     *
     * @param i
     * @return
     */
    public static Integer addDayToUnixTime(int i) {
        return Math.toIntExact(DateHelper.addDay(i).getTime() / 1000);

    }


    public static String timeformat(Integer time) {
        return timeStamp2Date(time.toString());
    }

    /**
     * 将时间分为固定段数
     *
     * @param startTime
     * @param stopTime
     * @param segment
     * @return 每段开始、结束时间列表
     */
    public static List<Integer[]> splitTimeToFixedSeg(Integer startTime, Integer stopTime, Integer segment) {
        List<Integer[]> timeRangeGroup = Lists.newArrayList();
        Integer k = startTime;
        while (k <= stopTime) {
            Integer[] times = new Integer[2];
            times[0] = k;
            k += (stopTime - startTime) / segment;
            times[1] = k;
            timeRangeGroup.add(times);
        }
        if (timeRangeGroup.get(timeRangeGroup.size() - 1)[1] > stopTime) {
            timeRangeGroup.remove(timeRangeGroup.size() - 1);
        }
        return timeRangeGroup;
    }
}
