/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by cqnews on 2017/3/20.
 */
public class TypeHelper {


    private static Logger logger = LoggerFactory.getLogger(DateHelper.class);

    /// <summary>
    /// 将string类型转换成int类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static Integer stringToInt(String s) {
        return stringToInt(s, 0);
    }

    /// <summary>
    /// 将string类型转换成int类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static Integer stringToInt(Object s, Integer defaultValue) {
        try {

            if (s != null && String.valueOf(s).trim().length() > 0) {
                return Integer.parseInt(s.toString());
            } else {
                return defaultValue;
            }

        } catch (Exception e) {
            logger.error("ERROR" + e.toString());
            return defaultValue;
        }
    }

    public static Integer objectToInt(Object o) {

        return objectToInt(o, 0);
    }

    public static Integer objectToInt(Object o, Integer defaultValue) {
        try {
            return (Integer) o;
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /// <summary>
    /// 将int类型转换成string类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static String intToString(Integer s) {
        return intToString(s, "0");
    }

    /// <summary>
    /// 将string类型转换成int类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static String intToString(Object s, String defaultValue) {
        try {

            if (s != null) {
                return String.valueOf(s.toString());
            } else {
                return defaultValue;
            }

        } catch (Exception e) {
            logger.error("ERROR" + e.toString());
            return defaultValue;
        }
    }

    /**
     * char 转字符串
     *
     * @param c
     * @return
     */
    public static String charToString(char c) {
        try {
            return String.valueOf(c);
        } catch (Exception e) {
            return "";
        }
    }


    /// <summary>
    /// 将int类型转换成string类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static String longToString(long s) {
        return longToString(s, "0");
    }

    /// <summary>
    /// 将string类型转换成int类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static String longToString(Object s, String defaultValue) {
        try {

            if (s != null) {
                return String.valueOf(s.toString());
            } else {
                return defaultValue;
            }

        } catch (Exception e) {
            logger.error("ERROR" + e.toString());
            return defaultValue;
        }
    }

    public static double objectToDouble(Object s) {
        return objectToDouble(s, 0.00);
    }

    public static double objectToDouble(Object s, double defaultValue) {
        return stringToDouble(s.toString(), defaultValue);
    }

    /// <summary>
    /// 将string类型转换成double类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static double stringToDouble(String s) {
        return stringToDouble(s, 0);
    }

    /// <summary>
    /// 将string类型转换成double类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static double stringToDouble(Object s, double defaultValue) {
        try {

            if (s != null) {
                return Double.parseDouble(s.toString());
            } else {
                return defaultValue;
            }

        } catch (Exception e) {
            logger.error("ERROR" + e.toString());
            return defaultValue;
        }
    }


    /// <summary>
    /// 将string类型转换成double类型
    /// </summary>
    /// <param name="s">目标字符串</param>
    /// <returns></returns>
    public static String doubleToString(double s) {
        return doubleToString(s, "");
    }

    public static String doubleToString(Object s, String defaultValue) {

        try {

            if (s != null) {
                return String.valueOf(s.toString());
            } else {
                return defaultValue;
            }

        } catch (Exception e) {
            logger.error("ERROR" + e.toString());
            return defaultValue;
        }

    }


    public static Integer longToInt(Long value) {
        return longToInt(value, 0);
    }

    public static Integer longToInt(Long value, Integer defaultValue) {
        try {
            return value.intValue();
        } catch (Exception e) {

            return defaultValue;
        }
    }


    /**
     * timestampToDate 转date
     *
     * @param timestamp
     * @return
     */
    public static Date timestampToDate(Timestamp timestamp) {

        Date date = new Date();
        try {
            date = timestamp;

        } catch (Exception e) {

        }
        return date;
    }

    /**
     * dateToTimestamp 转Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp dateToTimestamp(Date date) {

        Timestamp timestamp = DateHelper.getTimestamp(date);
        return timestamp;
    }


    /**
     * stringToDate 字符转date类型
     *
     * @param s
     * @return
     */
    public static Date stringToDate(String s) {

        if (s.isEmpty()) {
            return null;
        }

        s = s.replace("/", "-").replace("：", ":");

        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            date = sdf.parse(s);

        } catch (Exception e) {
        }

        return date;
    }

    /**
     * stringToDate 字符转date类型
     *
     * @param s
     * @return
     */
    public static Timestamp stringToTimestamp(String s) {

        if (s.isEmpty()) {
            return null;
        }

        s = s.replace("/", "-").replace("：", ":");


        Timestamp ts;

        try {

            ts = Timestamp.valueOf(s);

        } catch (Exception e) {
            ts = DateHelper.getTimestamp();
        }

        return ts;
    }


    /**
     * 字符串转输入流
     *
     * @param str
     * @return
     */
    InputStream string2InputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * 输入流转字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     *
     */
    @Test
    public void test() {

        String s = stringToInt("02552").toString();
        // System.out.println(s);

        Timestamp ts = stringToTimestamp("2020-12-30 00:00:00"); //System.out.println(ts);

    }

    /**
     * 对象转字符,主要判断为空
     *
     * @param o
     * @return
     */
    public static String objectToString(Object o) {

        if (o == null) {
            return "";
        }
        return o.toString();
    }
}