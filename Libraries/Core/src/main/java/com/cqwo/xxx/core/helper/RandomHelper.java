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

import java.util.Random;

/**
 * Created by cqnews on 2017/12/21.
 */
public class RandomHelper {

    //  定义所有的字符组成的串
    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //  定义所有的小写字符组成的串（不包括数字）
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //  定义所有的数字字符组成的串
    public static final String NUMBER_CHAR = "0123456789";

    /**
     * 产生长度为length的随机字符串（包括字母和数字）
     *
     * @param length
     * @return
     */
    public static String generateString(Integer length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (Integer i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 产生长度为length的随机字符串（包括字母，不包括数字）
     *
     * @param length
     * @return
     */
    public static String generateMixString(Integer length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (Integer i = 0; i < length; i++) {
            sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 产生长度为length的随机小写字符串（包括字母，不包括数字）
     *
     * @param length
     * @return
     */
    public static String generateLowerString(Integer length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 产生长度为length的随机大写字符串（包括字母，不包括数字）
     *
     * @param length
     * @return
     */
    public static String generateUpperString(Integer length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 产生长度为length的'0'串
     *
     * @param length
     * @return
     */
    public static String generateZeroString(Integer length) {
        StringBuffer sb = new StringBuffer();
        for (Integer i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 将输入的long整数值补全成为fixedlength长度的字符串
     *
     * @param num
     * @return
     */
    public static String toFixdLengthString(long num, Integer fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常!");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 将输入的int整数值补全成为fixedlength长度的字符串
     *
     * @param num
     * @return
     */
    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常!");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 获取区间随机值
     *
     * @param min
     * @param max
     * @return
     */
    public static Integer generateInterval(Integer min, Integer max) { //System.out.println("min:" + min); //System.out.println("max:" + max);

        Random random = new Random();

        Integer s = random.nextInt(max) % (max - min + 1) + min;

        return s;
    }

    @Test
    public void test() {

        Integer max = 685;
        Integer min = 1;
        Random random = new Random();

        Integer s = generateInterval(1, max); //System.out.println(s);

    }

    public static void main(String[] args) {


//      产生长度为15的随机字符串（包括字母和数字） //System.out.println(generateString(15));
//      产生长度为15的随机混合字符串（不分大小写，包括字母，不包括数字） //System.out.println(generateMixString(15));
//      产生长度为15的随机小写字符串（包括字母，不包括数字） //System.out.println(generateLowerString(15));
//      产生长度为15的随机大写字符串（包括字母，不包括数字） //System.out.println(generateUpperString(15));
//      产生长度为15的 0 串 //System.out.println(generateZeroString(15));
//      将输入的int整数值补全成为fixedlength长度的字符串 //System.out.println(toFixdLengthString(12351, 15));
//      将输入的long整数值补全成为fixedlength长度的字符串 //System.out.println(toFixdLengthString(12351555625555224L, 18));
    }


}
