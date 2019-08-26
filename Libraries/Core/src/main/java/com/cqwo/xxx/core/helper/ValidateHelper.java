/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cqnews on 2017/3/20.
 */
public class ValidateHelper {


    // 保存每个月的天数
    private static final int[] DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31,
            31, 30, 31, 30, 31};

    // 日历的起始年份
    public static final Integer START_YEAR = 1900;

    // 日历的结束年份
    public static final Integer END_YEAR = 2100;

    private static final String ZERO_STRING = "0";


    /***************************************************************************
     * 匹配英文字母 或者汉字 如"Shenzhen" "深圳"
     */
    public final static String ENGLISH_OR_CHINESE_REGEX = "^[A-Za-z]*|[\u4E00-\u9FA5]*$";


    /***************************************************************************
     * 匹配英文字母 或者汉字 如"Shenzhen" "深圳"
     *
     * @param str 待匹配字符串
     * @return true 匹配通过 false 匹配失败
     */
    public static boolean isValidEnglishOrChinese(String str) {
        // 1、[A-Za-z]* 英文字母的匹配 一次或者多次
        // 2、[\u4E00-\u9FA5]* 汉字匹配 一次或者多次
        boolean flag = false;
        Pattern p = Pattern.compile(ENGLISH_OR_CHINESE_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }


    /***************************************************************************
     * 匹配英中文姓名 与英文名 英文名格式为：姓与名之间用/隔开 例如Green/Jim King
     */
    public final static String NAME_REGEX = "^[A-Za-z]*|[\u4E00-\u9FA5]*$";

    /***************************************************************************
     * 匹配英中文姓名 与英文名 英文名格式为：姓与名之间用/隔开 例如Green/Jim King
     *
     * @param str 待匹配字符串
     * @return true 匹配通过 false 匹配失败
     */
    public static boolean isValidName(String str) {
        // 1、[A-Za-z]* 英文字母的匹配 一次或者多次
        // 2、[\u4E00-\u9FA5]* 汉字匹配 一次或者多次
        boolean flag = false;
        Pattern p = Pattern
                .compile(NAME_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }


    /***************************************************************************
     * 18位身份证号码
     */
    public static final String ID_CARD_REGEX = "^\\d{17}(\\d{1}|x)$";


    /***************************************************************************
     * 18位身份证号码
     */
    public static final String SHORT_ID_CARD_REGEX = "^\\d{15}$";

    /**
     * 验证所有的身份证号码
     */
    public static final String ID_CARD_ALL_REGEX = "^\\d{15}|^\\d{17}(\\d{1}|x)$";

    /***************************************************************************
     * 验证身份证号码 15位 18位
     *
     * @param cardStr 身份证字符串
     * @return true 合法 false 不合法
     */
    public static boolean isValidIdCard(String cardStr) {
        boolean flag = false;
        Pattern pEighteen = Pattern.compile(ID_CARD_REGEX);// 18位身份证号码
        // 包括末尾是“x”的校验码
        Pattern pFifteen = Pattern.compile(SHORT_ID_CARD_REGEX);// 15位身份证号码
        if (cardStr != null) {
            if (pEighteen.matcher(cardStr).matches()) // 18位身份证号码验证通过
            {
                if (isValidDate(cardStr.substring(6, 14)))// 18位身份证号码
                // 出生年月日验证通过
                {
                    flag = true;
                }
            }
            if (pFifteen.matcher(cardStr).matches()) // 15位身份证号码验证通过
            {
                if (isValidDay(cardStr.substring(6, 12))) // 15位身份证出身年月日的验证
                {
                    flag = true;
                }
            }
        }
        return flag;

    }

    /***************************************************************************
     * 正整数验证
     */
    public static final String INT_REGEX = "^\\d*$";

    /***************************************************************************
     * 正整数验证
     *
     * @param str 待验证字符串
     * @return true 验证通过 false 验证失败
     */
    public static boolean isValidint(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(INT_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 整数验证(包括正整数与 负整数)
     */
    public static final String NO_REGEX = "^-?\\d*$";


    /***************************************************************************
     * 整数验证(包括正整数与 负整数)
     *
     * @param str 待验证字符串
     * @return true 验证通过 false 验证失败
     */
    public static boolean isValidNo(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(NO_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 整数验证(包括正整数与 负整数)
     */
    public static final String NON_NEGATIVE_REGEX = "^\\d+$";

    /***************************************************************************
     * 验证非负整数(正整数+0)
     *
     * @param str 待验证字符串
     * @return true 验证通过 false 验证失败
     */
    public static boolean isValidNonNegative(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(NON_NEGATIVE_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 验证非负整数(正整数+0)
     */
    public final static String POSITIVEINT_REGEX = "^\\d+$";

    /***************************************************************************
     * 验证非负整数(正整数+0)
     *
     * @param str 待验证字符串
     * @return true 验证通过 false 验证失败
     */
    public static boolean isValidPositiveint(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(POSITIVEINT_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
            if (ZERO_STRING.equals(str)) {
                flag = false;
            }
        }

        return flag;
    }

    /***************************************************************************
     * 匹配英文字母(汉语拼音)
     */
    private final static String ENGLISH_REGEX = "^[A-Za-z]*$";

    /***************************************************************************
     * 匹配英文字母(汉语拼音)
     *
     * @param str 待匹配字符串
     * @return true 匹配通过 false 匹配失败
     */
    public static boolean isValidEnglish(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(ENGLISH_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 匹配英文字母(汉语拼音)
     */
    private final static String NON_SPECIAL_CHAR_REGEX = "^[A-Za-z\u4E00-\u9FA5\\d]*$";

    /***************************************************************************
     * 匹配英文字母 或者汉字，数字 过滤特殊字符
     *
     * @param str 待匹配字符串
     * @return true 匹配通过 false 匹配失败
     */
    public static boolean isValidNonSpecialChar(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(NON_SPECIAL_CHAR_REGEX);
        if (str != null) {
            Matcher match = p.matcher(str);
            flag = match.matches();
        }
        return flag;
    }


    /***************************************************************************
     * 验证HH时间格式的时间范围是否大于等于三小时 **注意此方法必须在isValidHour格式验证通过后调用
     *
     * @param startHour 开始时间 HH
     * @param endHour   结束时间HH
     * @return true 通过 false 不通过
     */
    public static boolean isVaildHourZone(String startHour, String endHour) {
        boolean flag = false;
        if (startHour != null && endHour != null) {
            if (isValidHour(startHour) && isValidHour(endHour)) // 格式验证，避免可能抛类型转换异常
            {
                Integer sHour = Integer.parseInt(startHour);
                Integer eHour = Integer.parseInt(endHour);
                flag = (eHour - sHour >= 3);
            }
        }
        return flag;

    }

    /***************************************************************************
     * 验证结束时间是否大于开始时间 **注意：此方法必须先调用isValidDate 方法后调用 yyMMdd
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return true 验证通过 false 验证失败
     */
    public static boolean isValidTimeZone(String startDate, String endDate) {
        boolean flag = false;
        if (startDate != null && endDate != null) {
            if (isValidDate(startDate) && isValidDate(endDate)) // 格式验证，避免可能抛类型转换异常
            {
                flag = (Integer.parseInt(endDate) > Integer.parseInt(startDate));
            }
        }
        return flag;
    }

    /***************************************************************************
     * 验证结束时间是否大于等于开始时间 **注意：此方法必须先调用isValidDate 方法后调用 yyMMdd（包含等于）
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return true 验证通过 false 验证失败
     */
    public static boolean isValidTwoTimes(String startDate, String endDate) {
        boolean flag = false;
        if (startDate != null && endDate != null) {
            if (isValidDate(startDate) && isValidDate(endDate)) // 格式验证，避免可能抛类型转换异常
            {
                flag = (Integer.parseInt(endDate) > Integer.parseInt(startDate) || Integer.parseInt(endDate) == Integer.parseInt(startDate));
            }
        }
        return flag;
    }

    /***************************************************************************
     * 验证电话号码 后可接分机号 区号3位或者4位 电话7位或者8位后 后面可加3位或者4位分机号
     * 1、\\d{3,4} 区号 3位或者4位的匹配
     * 2、\\d{7,8} 号码 7味或者8位的匹配
     * 3、(\\d{3,4})? 分机号3位或者4位的匹配 ？可匹配一次或者两次
     */
    public final static String TELEPHOE_NO_REGEX = "^\\d{3,4}\\d{7,8}(\\d{3,4})?$";

    /***************************************************************************
     * 验证电话号码 后可接分机号 区号3位或者4位 电话7位或者8位后 后面可加3位或者4位分机号
     *
     * @param telephoeNo 电话号码字符串
     * @return
     */
    public static boolean isValidTelephoeNo(String telephoeNo) {
        boolean flag = false;
        Pattern p = Pattern.compile(TELEPHOE_NO_REGEX);
        Matcher match = p.matcher(telephoeNo);
        if (telephoeNo != null) {
            flag = match.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 验证电话号码 后可接分机号 区号3位或者4位 电话7位或者8位后 后面可加3位或者4位分机号
     * 1、(13[0-9])|(15[02789])|(18[679]) 13段 或者15段 18段的匹配
     * 2、\\d{8} 整数出现8次
     */
    public final static String MOBILE_REGEX = "^((13[0-9])|(14[57])|(15[012356789])|(17[3678])|(18[0123456789]))\\d{8}$";

    /***************************************************************************
     * 验证手机号码
     *
     * @param mobile 电话号码字符串 130到139 和 150，152 ，157，158，159 ，186，189，187
     * @return
     */
    public static boolean isValidMobile(String mobile) {

        boolean flag = false;
        // Pattern p = Pattern.compile("^(1[358][13567890])(\\d{8})$");
        Pattern p = Pattern
                .compile(MOBILE_REGEX);
        Matcher match = p.matcher(mobile);
        if (mobile != null) {
            flag = match.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 验证是否是正确的邮箱格式
     * 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
     * 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
     * {1,3}表示可以出现一次或两次或者三次.
     */
    public final static String EMAIL_REGEX = "\\w+@(\\w+\\.){1,3}\\w+";


    /***************************************************************************
     * 验证是否是正确的邮箱格式
     *
     * @param email
     * @return true表示是正确的邮箱格式, false表示不是正确邮箱格式
     */
    public static boolean isValidEmail(String email) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        boolean flag = false;
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }

    /***************************************************************************
     * 验证整点时间格式是否正确 HH格式 时间范围00时~23时
     */

    public final static String HOUR_REGEX = "^[0-2][0-9]$";

    /***************************************************************************
     * 验证整点时间格式是否正确 HH格式 时间范围00时~23时
     *
     * @param hour 时间格式字符串
     * @return true表示是正确的整点时间格式, false表示不是正确整点时间格式
     */
    public static boolean isValidHour(String hour) {
        boolean flag = false;
        String reg = HOUR_REGEX;
        Pattern pattern = Pattern.compile(reg);
        if (hour != null) {
            Matcher matcher = pattern.matcher(hour);
            flag = matcher.matches();
            int firstNum = Integer.parseInt(hour.substring(0, 1));
            if (flag && firstNum == 2) {
                int secondNum = Integer.parseInt(hour.substring(1, 2));
                flag = secondNum < 4; // 时间小于24时
            }
        }
        return flag;
    }

    /***************************************************************************
     * 匹配日期格式 yyMMdd 并验证日期是否合法 此方法忽略了闰年的验证 用于15位身份证出生日期的验证
     */
    public final static String DAY_STR_REGEX = "^\\d{2}\\d{2}\\d{2}$";

    /***************************************************************************
     * 匹配日期格式 yyMMdd 并验证日期是否合法 此方法忽略了闰年的验证 用于15位身份证出生日期的验证
     *
     * @param dayStr 日期字符串
     * @return true 日期合法 false 日期非法
     */
    public static boolean isValidDay(String dayStr) {
        Pattern p = Pattern.compile(dayStr);
        Matcher match = p.matcher(DAY_STR_REGEX);
        if (dayStr != null) {
            if (match.matches()) // 格式验证通过 yyMMdd
            {
                Integer month = Integer.parseInt(dayStr.substring(2, 4)); // 月
                Integer day = Integer.parseInt(dayStr.substring(4, 6)); // 日
                if (!isValidMonth(month)) {
                    return false; // 月份不合法
                }
                if (!(day >= 1 && day <= DAYS_OF_MONTH[month - 1])) {
                    return false; // 日期不合法
                }
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    /***************************************************************************
     * 匹配日期格式 yyyyMMdd 并验证日期是否合法
     *
     */
    public final static String DATE_REGEX = "^\\d{4}\\d{2}\\d{2}$";

    /***************************************************************************
     * 匹配日期格式 yyyyMMdd 并验证日期是否合法
     *
     * @param date 日期字符串
     * @return true 日期合法 false 日期非法
     */
    public static boolean isValidDate(String date) {
        // 1、 \\d{4} 年，\\d{2}月，\\d{2}日匹配
        Pattern p = Pattern.compile(DATE_REGEX);
        Matcher match = p.matcher(date);
        if (date != null) {
            if (match.matches()) // 格式验证通过 yyyyMMdd
            {
                Integer year = Integer.parseInt(date.substring(0, 4)); // 年
                Integer month = Integer.parseInt(date.substring(4, 6)); // 月
                Integer day = Integer.parseInt(date.substring(6, 8)); // 日
                if (!isValidYear((year))) {
                    return false; // 年份不在有效年份中
                }
                if (!isValidMonth(month)) {
                    return false; // 月份不合法
                }
                if (!isValidDay(year, month, day)) {
                    return false; // 日期不合法
                }
                return true;
            }

            return false;
        } else {
            return false;
        }
        // return Pattern.matches("", date);
    }

    /**
     * 检查year是否在有效的年份范围内 此处验证大于1900年 小于2101年
     *
     * @param year
     * @return
     */
    public static boolean isValidYear(Integer year) {
        return year >= START_YEAR && year <= END_YEAR;
    }

    /**
     * 验证月份是否在有效月份内
     *
     * @param month
     * @return
     */
    public static boolean isValidMonth(Integer month) {
        return month >= 1 && month <= 12;
    }

    /**
     * 检查天数是否在有效的范围内，因为天数会根据年份和月份的不同而不同 所以必须依赖年份和月份进行检查
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean isValidDay(Integer year, Integer month, Integer day) {
        if (month == 2 && isLeapYear(year))// 闰年且是2月份
        {
            return day >= 1 && day <= 29;
        }
        return day >= 1 && day <= DAYS_OF_MONTH[month - 1];// 其他月份
    }

    /**
     * 验证是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(Integer year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 产品名称
     */
    public final static String PRODUCT_NAME_REGEX = "^[a-z0-9][a-z0-9]{2,19}$";

    /**
     * 协议名称
     */
    public final static String PROTOCOL_CODE_REGEX = "^[A-Za-z0-9][0-9A-Za-z]{1,19}$";

    /**
     * 参数
     */
    public final static String PARAM_CODE_REGEX = "^[a-zA-Z][0-9A-Za-z]{0,19}$";

    public final static String USER_NAME_REGEX = "^[a-zA-Z][a-zA-Z0-9]{5,19}$";

    /***************************************************************************
     * 验证用户名注册是否合法-----------由数字、26个英文字母或者下划线组成的字符串
     *
     * @param userName
     * @return
     */
    public static boolean isRegUserName(String userName) {

        boolean flag = false;
        if (userName != null) {
            Pattern p = Pattern.compile(USER_NAME_REGEX);
            Matcher match = p.matcher(userName);
            flag = match.matches();
        }
        return flag;
    }

    public final static String ACCOUNT_REGEX = USER_NAME_REGEX + "|" + MOBILE_REGEX + "|" + EMAIL_REGEX;


    /***************************************************************************
     * 验证用户名登录是否合法-----------由数字、26个英文字母或者下划线组成的字符串
     *
     * @param account
     * @return
     */
    public static boolean isLoginAccount(String account) {

        boolean flag = false;
        if (account != null) {
            Pattern p = Pattern.compile(ACCOUNT_REGEX);
            Matcher match = p.matcher(account);
            flag = match.matches();
        }
        return flag;
    }

    public final static String IP_REGEX = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 验证IP地址
     *
     * @param ip 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIP(String ip) {
        return match(IP_REGEX, ip);
    }


    /**
     * 验证网址Url
     */
    public final static String URL_REGEX = "^(http)(s)?://([\\w-:]+\\.)+[\\w-:]+(/[\\w- ./?%&=]*)?";

    /**
     * 验证网址Url
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUrl(String str) {
        return match(URL_REGEX, str);
    }


    /***************************************************************************
     * 验证输入密码条件(字符与数据同时出现)
     */
    public final static String PASSWORD_REGEX = "[A-Za-z0-9]+[A-Za-z0-9]{6,18}";


    /**
     * 验证输入密码条件(字符与数据同时出现)
     *
     * @param password 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPassword(String password) {

        return match(PASSWORD_REGEX, password);
    }

    /**
     * 验证输入密码长度 (6-18位)
     */
    public final static String PASSWORD_LENTH_REGEX = "^\\d{6,18}$";

    /**
     * 验证输入密码长度 (6-18位)
     *
     * @param password 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPasswordLength(String password) {

        return match(PASSWORD_LENTH_REGEX, password);
    }


    /**
     * 验证输入密码长度 (6-18位)
     */
    public final static String DECIMAL_REGEX = "^[0-9]+(.[0-9]{2})?$";

    /**
     * 验证输入两位小数
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isDecimal(String str) {
        return match(DECIMAL_REGEX, str);
    }

    /****************************************************************************
     * 验证大写字母
     */
    public final static String UP_CHAR_REGEX = "^[A-Z]+$";


    /****************************************************************************
     * 验证大写字母
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isUpChar(String str) {
        return match(UP_CHAR_REGEX, str);
    }


    /****************************************************************************
     * 验证小写字母
     */
    public final static String LOW_CHAR_REGEX = "^[a-z]+$";

    /**
     * 验证小写字母
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLowChar(String str) {
        return match(LOW_CHAR_REGEX, str);
    }

    /**
     * 正则验证
     *
     * @param regex 正则表达式
     * @param str   验证字符串
     * @return
     */
    public static boolean match(String regex, String str) {
        return Pattern.matches(regex, str);

    }


//    /**
//     * 正则验证
//     *
//     * @param regex 正则表达式
//     * @param str   验证字符串
//     * @return
//     */
//    public static boolean match(String regex, String str) {
//
//        return Pattern.matches(regex, str);
//
//    }


    /**
     * 查找串,返回第一个
     *
     * @param regex
     * @param str
     * @return
     */
    public static List<String> find(String regex, String str) {

        List<String> list = new ArrayList<>();

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);

        while (m.find()) {

            int n = m.groupCount();

            //System.out.println(n);

            for (int i = 0; i <= n; i++) {
                list.add(m.group(i));
            }

        }

        return list;
    }

    /**
     * 查找串,返回第一个
     *
     * @param regex
     * @param str
     * @return
     */
    public static String findOne(String regex, String str) {

        List<String> list = new ArrayList<>();

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);

        if (m.find()) {
            return m.group(0);
        }

        return "";
    }


    public static Pattern compile(String s) {
        return Pattern.compile(s);
    }

    public static void main(String[] args) {

        String ss = "http://192.168.1.188:8026/receive";


        if (isUrl(ss)) {

            // System.out.println("是网址");
        } else {
            // System.out.println("不是网址");
        }

    }

}
