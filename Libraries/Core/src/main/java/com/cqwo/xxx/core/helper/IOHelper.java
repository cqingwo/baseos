/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import java.io.*;
import java.util.Objects;

/**
 * IO帮助类
 * Created by cqnews on 2017/4/7.
 */
public class IOHelper {


    /**
     * 获取当前文件的根路径
     *
     * @param path
     * @return
     */
    public static String getRootPath(String path) {

        String classPath = getMapPath(path);

        classPath = classPath.replace("/WEB-INF", ""); //去掉WEB-INF\

        return classPath;

    }

    /**
     * 获取当前文件的绝对路径
     *
     * @param path 文件名
     * @return
     */
    public static String getMapPath(String path) {
        //file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/


        String filepath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();

//        filepath = filepath.replace('/', '\\'); // 将/换成\
        filepath = filepath.replace("file:", ""); //去掉file:
        filepath = filepath.replace("/classes", ""); //去掉class\
        filepath = filepath.replace("%20", " ");
//        filepath = filepath.substring(1); //去掉第一个\,如 \D:\JavaWeb...
        //System.out.println(path);
        return filepath + path;
    }

    /**
     * 获取当前文件的绝对路径
     *
     * @return
     */
    public static String getMapPath() {

        return getMapPath("");
    }


    /**
     * 字符串转 --> InputStream
     *
     * @param str 字符串
     * @return
     */
    public static InputStream stringToInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * InputStream --> 字符串
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }


}
