/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CharHelper {
    /**
     * 转换编码 ISO-8859-1到GB2312
     *
     * @param s 字符串
     * @return
     */
    public String iso2GB(String s) {
        String result = "";
        try {
            result = new String(s.getBytes(StandardCharsets.ISO_8859_1), "GB2312");
        } catch (UnsupportedEncodingException ex) {
            result = ex.toString();
        }
        return result;
    }

    /**
     * 转换编码 GB2312到ISO-8859-1
     *
     * @param text
     * @return
     */
    public String gb2ISO(String text) {
        String result = "";
        try {
            result = new String(text.getBytes("GB2312"), StandardCharsets.ISO_8859_1);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Utf8URL编码
     *
     * @param s 字符串
     * @return
     */
    public String utf8URLencode(String s) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 0 && c <= 255) {

                result.append(c);

            } else {
                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                } catch (Exception ignored) {
                }

                for (byte aB : b) {
                    int k = aB;
                    if (k < 0) {
                        k += 256;
                    }
                    result.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return result.toString();
    }

    /**
     * Utf8URL解码
     *
     * @param s 字符串
     * @return
     */
    public String utf8URLdecode(String s) {
        StringBuilder result = new StringBuilder();
        int p = 0;
        if (s != null && s.length() > 0) {
            s = s.toLowerCase();
            p = s.indexOf("%e");
            if (p == -1) {
                return s;
            }
            while (p != -1) {
                result.append(s.substring(0, p));
                s = s.substring(p, s.length());
                if ("".equals(s) || s.length() < 9) {
                    return result.toString();
                }
                result.append(codeToWord(s.substring(0, 9)));
                s = s.substring(9, s.length());
                p = s.indexOf("%e");
            }
        }
        return result + s;
    }

    /**
     * utf8URL编码转字符
     *
     * @param s string串
     * @return
     */
    private String codeToWord(String s) {
        String result;
        if (utf8CodeCheck(s)) {
            byte[] code = new byte[3];
            code[0] = (byte) (Integer.parseInt(s.substring(1, 3), 16) - 256);
            code[1] = (byte) (Integer.parseInt(s.substring(4, 6), 16) - 256);
            code[2] = (byte) (Integer.parseInt(s.substring(7, 9), 16) - 256);
            result = new String(code, StandardCharsets.UTF_8);
        } else {
            result = s;
        }
        return result;
    }

    /**
     * 编码是否有效
     *
     * @param s 文本
     * @return
     */
    private boolean utf8CodeCheck(String s) {
        StringBuilder sign = new StringBuilder();
        if (s.startsWith("%e")) {
            for (int i = 0, p = 0; p != -1; i++) {
                p = s.indexOf("%", p);
                if (p != -1) {
                    p++;
                }
                sign.append(p);
            }
        }
        return "147-1".equals(sign.toString());
    }

    /**
     * 是否Utf8Url编码
     *
     * @param s 字符串
     * @return
     */
    public boolean isUtf8Url(String s) {
        s = s.toLowerCase();
        int p = s.indexOf("%");
        if (p != -1 && s.length() - p > 9) {
            s = s.substring(p, p + 9);
        }
        return utf8CodeCheck(s);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        CharHelper charTools = new CharHelper();
        String url;
        url = "http://www.google.com/search?hl=zh-CN&newwindow=1&q=%E4%B8%AD%E5%9B%BD%E5%A4%A7%E7%99%BE%E7%A7%91%E5%9C%A8%E7%BA%BF%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2&btnG=%E6%90%9C%E7%B4%A2&lr=";
        if (charTools.isUtf8Url(url)) { //System.out.println(charTools.utf8URLdecode(url));
        } else { //System.out.println(URLDecoder.decode(url));
        }
        url = "http://www.baidu.com/baidu?word=%D6%D0%B9%FA%B4%F3%B0%D9%BF%C6%D4%DA%CF%DF%C8%AB%CE%C4%BC%EC%CB%F7&tn=myie2dg";
        if (charTools.isUtf8Url(url)) { //System.out.println(charTools.utf8URLdecode(url));
        } else { //System.out.println(URLDecoder.decode(url));
        }

        url = "http://www.baidu.com/s?bs=bai%B2%CE%CA%FD%C2%D2%C2%EB&f=8&wd=bai%B2%CE%CA%FD%C2%D2%C2%EB"; //System.out.println(URLDecoder.decode(url));
    }
}
