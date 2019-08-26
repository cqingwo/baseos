/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cqnews on 2017/4/11.
 */
public class MD5Util {
    public static String md5(String pwd) {
        try {
            //创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            // 调用加密对象的方法，加密的动作已经完成
            byte[] bytes = digest.digest(pwd.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                Integer temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个“0”
                    hexString.append("0").append(Integer.toHexString(temp));
                } else {
                    hexString.append(Integer.toHexString(temp));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
