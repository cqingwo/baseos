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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by cqnews on 2017/3/20.
 */
public class CommonHelper {

    /**
     * 去前导后导空格
     *
     * @param s
     * @return
     */
    public static String trim(String s) {
        if (s == null) {
            s = "";
        }

        return s.toString().trim();
    }

    /**
     * 错误信息处理
     *
     * @param errors 错误信息
     * @return
     */
    public static String dealErorr(Errors errors) {
        String info = "";
        if (errors.hasErrors()) {
            info = "参数错误：<br/>";
            List<FieldError> list = errors.getFieldErrors();
            for (Integer i = 0; i < list.size(); i++) {
                FieldError error = list.get(i);
                info += error.getField() + "===" + error.getDefaultMessage() + "<br/>";
            }
        }
        return info;
    }

    /**
     * 删除数字带0结尾的数据
     *
     * @param str 源字符串
     * @return
     */
    public static String dropNero(String str) {
        if (str == null) {
            return "";
        }
        str = str.trim();
        String ret = "";
        Integer len = str.indexOf(".");
        if (len == -1) {
            return str;
        }
        String one = str.substring(0, len);
        String two = str.substring(len + 1);//小数部分
        while (true) {
            if (two.length() == 0) {
                break;
            }
            String t = two.substring(two.length() - 1);
            if ("0".equals(t)) {
                two = two.substring(0, two.length() - 1);
            } else {
                break;
            }
        }
        if (two.length() == 0) {
            ret = one;
        } else {
            ret = one + "." + two;
        }
        return ret;
    }

    /**
     * 创建utf文件
     *
     * @param filePath
     * @param content
     * @throws Exception
     */
    public static void createUtfFile(String filePath, String content) throws Exception {
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
        out.write(content);
        out.flush();
        out.close();

    }

    /**
     * @return String
     * @throws
     * @描述:null转化为""
     * @作者：Administrator
     * @创建日期： 2014-6-7 下午04:25:11
     */
    public static String isNull(Object s) {
        if (s == null) {
            s = "";
        }

        return s.toString().trim();
    }

    /**
     * @return String
     * @throws
     * @描述:获取天气预报数据
     * @作者：Administrator
     * @创建日期： Jun 11, 2014 9:43:40 AM
     */
    public static String getWeather() {
        String ret = "";
        HttpURLConnection urlcon = null;
        BufferedReader reader = null;

        try {
            //通知Java您要通过代理进行连接      
            System.getProperties().put("proxySet", "true");
            //指定代理所在的服务器       
            System.getProperties().put("proxyHost", "10.55.4.1");
            //指定代理监听的端口        
            System.getProperties().put("proxyPort", "8080");
            //long begintime = System.currentTimeMillis();
            URL url = new URL("http://api.map.baidu.com/telematics/v3/weather?location=%E9%87%8D%E5%BA%86&output=json&ak=VORWXzBy9c3BHcYp6YMYVijm");

            urlcon = (HttpURLConnection) url.openConnection();
            //urlcon.set
            urlcon.setConnectTimeout(10000);
            urlcon.setReadTimeout(10000);
            reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            ret = sb.toString();
            //System.out.println("总共执行时间为："+(System.currentTimeMillis()-begintime)+"毫秒");
            //ret=sb.toString();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            ret = "-1";
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            ret = "-1";
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    ret = "-1";
                    e.printStackTrace();
                }
            }
            if (urlcon != null) {
                urlcon.disconnect();
            }
        }
        return ret;
    }


//    /**
//     * 将ip地址转换成long类型
//     *
//     * @param ip
//     * @return
//     */
//    public static Long convertIPToLong(String ip) {
//        String[] ips=ip.split(".");
//
//        Long number=16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
//        return number;
//    }

    /**
     * IP 转数字
     *
     * @param ip
     * @return
     */
    public static Long convertIPToLong(String ip) {
        Long ips = 0L;
        String[] numbers = ip.split("\\.");
        //等价上面
        for (Integer i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(numbers[i]);
        }
        return ips;
    }

    /**
     * 数字转ip
     *
     * @param number
     * @return
     */
    public static String convertNumberToIP(Long number) {
        //等价上面
        String ip = "";
        for (Integer i = 3; i >= 0; i--) {
            ip += String.valueOf((number & 0xff));
            if (i != 0) {
                ip += ".";
            }
            number = number >> 8;
        }

        return ip;
    }


    @Test
    public void test() { //System.out.println(convertIPToLong("127.0.0.1"));
    }

    public static void main(String[] args) {
        String s = getWeather();
        // System.out.println(s);
    }

}