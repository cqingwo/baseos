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
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cqnews on 2017/12/7.
 */
public class WebHelper {


    //浏览器列表
    private final static String[] BROWSERLIST = new String[]{"ie", "chrome", "mozilla", "netscape", "firefox", "opera", "konqueror"};


    //region Http封装
    public WebHelper() {

    }


    // \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
    // 字符串在编译时会被转码一次,所以是 "\\b"
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
    private static final String PHONE_REG = "\\b(ip(hone|od)|android|opera m(ob|in)i"
            + "|windows (phone|ce)|blackberry"
            + "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
            + "|laystation portable)|nokia|fennec|htc[-_]"
            + "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
            + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";


    private static Pattern detectphoneregex = Pattern.compile(PHONE_REG, Pattern.CASE_INSENSITIVE);

    private static Pattern detecttableregex = Pattern.compile(PHONE_REG, Pattern.CASE_INSENSITIVE);

    private static Pattern detectiphoneregex = Pattern.compile("iphone|Mac", Pattern.CASE_INSENSITIVE);

    private static Pattern detectwechatregex = Pattern.compile("MicroMessenger|micromessenger", Pattern.CASE_INSENSITIVE);

    //region 编码

    /**
     * HTML解码
     *
     * @param s 编码串
     * @return
     */
    public static String htmlDecode(String s) {
        return StringEscapeUtils.escapeHtml4(s);
    }

    /// <summary>
    /// HTML编码
    /// </summary>
    /// <returns></returns>
    public static String htmlEncode(String s) {
        return StringEscapeUtils.unescapeHtml4(s);
    }

    /// <summary>
    /// URL解码
    /// </summary>
    /// <returns></returns>
    public static String urlDecode(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /// <summary>
    /// URL编码
    /// </summary>
    /// <returns></returns>
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    //endregion

    //region Cookies操作封装
    /**
     * 设置cookie有效期，根据需要自定义[本系统设置为30天]
     */
    private final static Integer COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 30;

    /**
     * @param cookie
     * @desc 删除指定Cookie
     */
    public static void removeCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * @param cookieName
     * @desc 删除指定Cookie
     */
    public static void removeCookie(HttpServletResponse response, String cookieName) {
        setCookie(response, cookieName, "", 1);
    }

    /**
     * @param cookie
     * @param domain
     * @desc 删除指定Cookie
     */
    public static void removeCookie(HttpServletResponse response, Cookie cookie, String domain) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    /**
     * @param name
     * @return
     * @desc 根据Cookie名称得到Cookie的值，没有返回Null
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * @param name
     * @desc 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        Cookie cookie = null;
        for (Integer i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(name)) {
                continue;
            }
            cookie = cookies[i];
            if (request.getServerName().equals(cookie.getDomain())) {
                break;
            }
        }

        return cookie;
    }

    /**
     * @param name
     * @param value
     * @desc 添加一条新的Cookie信息，默认有效时间为一个月
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, COOKIE_MAX_AGE);
    }

    /**
     * @param name
     * @param value
     * @param expire
     * @desc 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     */
    public static void setCookie(HttpServletResponse response, String name, String value, Integer expire) {
        if (value == null) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        if (expire != 0) {
            cookie.setMaxAge(expire);
        } else {
            cookie.setMaxAge(COOKIE_MAX_AGE);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    //endregion

    //region 客户端信息

    /**
     * 是否是get请求
     *
     * @return
     */
    public static boolean isGet(HttpServletRequest request) {
        return "GET".equals(request.getMethod());
    }

    /**
     * 是否是get请求
     *
     * @return
     */
    public static boolean isPost(HttpServletRequest request) {
        return "POST".equals(request.getMethod());
    }

    /**
     * 是否是Ajax请求
     *
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "text/html;type=ajax".equals(request.getHeader("Accept"));
    }


    /**
     * 获得上次请求的url
     *
     * @return
     */
    public static String getUrlReferrer(HttpServletRequest request) {
        return getUrlReferrer(request, "");
    }

    /**
     * 获得上次请求的url
     *
     * @return
     */
    public static String getUrlReferrer(HttpServletRequest request, String defaultReferer) {
        String uri = request.getHeader("Referer");
        if (uri == null) {
            return defaultReferer;
        }
        return uri;
    }

    /**
     * 获得请求的主机部分
     *
     * @return
     */
    public static String getHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }


    /**
     * 获得请求的原始url
     *
     * @return
     */
    public static String getRawUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }

    /**
     * 获得请求的ip
     *
     * @return
     */
    public static String getIP(HttpServletRequest request) { //System.out.println(request.getRemoteAddr());

        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }


    /// <summary>
    /// 获得请求的浏览器类型
    /// </summary>
    /// <returns></returns>

//    /**
//     * 获得请求的浏览器类型
//     *
//     * @return
//     */
//    public static String getBrowserType(HttpServletRequest request) {
//        String type = request.getHeader("User-Agent");
//        if (type == null || "unknown".equals(type) || "".equals(type)) {
//            return "未知";
//        }
//        return type.toLowerCase();
//    }


    /**
     * 检测是否手机访问
     *
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null) {
            return false;
        }


        Matcher matcherPhone = detectiphoneregex.matcher(userAgent);
        Matcher matcherTable = detecttableregex.matcher(userAgent);

        if (matcherPhone.find() || matcherTable.find()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 检测是否手机微信访问
     *
     * @param request
     * @return
     */
    public static boolean isWechat(HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null) {
            return false;
        }


        Matcher matcherWechat = detectwechatregex.matcher(userAgent);

        if (matcherWechat.find()) {
            return true;
        } else {
            return false;
        }


    }

    @Test
    public void test() {
        String userAgent = "Mozilla/5.0 (Linux; U; Android 7.1.1; zh-CN; Nexus 6 Build/N6F27E) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.89 UCBrowser/11.8.1.1043 Mobile Safari/537.36 AliApp(TUnionSDK/0.1.20)";
        Matcher matcherWechat = detectphoneregex.matcher(userAgent);

        if (matcherWechat.find()) { //System.out.println("是手机访问");
        } else { //System.out.println("不是手机访问");
        }
    }

    /**
     * 页面跳转封装
     *
     * @param response
     * @param url
     * @return
     */
    public static boolean sendRedirect(HttpServletResponse response, String url) {

        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    //endregion


    //region 封装取值


    /// <summary>
    /// 获得查询字符串中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="defaultValue">默认值</param>
    /// <returns></returns>
    public static String getQueryString(HttpServletRequest request, String key, String defaultValue) {
        String s = defaultValue;
        try {
            s = request.getParameter(key);
            if (Strings.isNullOrEmpty(s)) {
                s = defaultValue;
            }

        } catch (Exception e) {

        }

        return s;
    }

    /// <summary>
    /// 获得查询字符串中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>
    public static String getQueryString(HttpServletRequest request, String key) {
        return getQueryString(request, key, "");
    }

    /// <summary>
    /// 获得查询字符串中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="defaultValue">默认值</param>
    /// <returns></returns>
    public static Integer getQueryInt(HttpServletRequest request, String key, Integer defaultValue) {
        return TypeHelper.stringToInt(request.getParameter(key), defaultValue);
    }

    /// <summary>
    /// 获得查询字符串中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>
    public static Integer getQueryInt(HttpServletRequest request, String key) {
        return getQueryInt(request, key, 0);
    }

    /// <summary>
    /// 获得表单中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="defaultValue">默认值</param>
    /// <returns></returns>
    public static String getFormString(HttpServletRequest request, String key, String defaultValue) {
        String value = request.getParameter(key);
        if (!Strings.isNullOrEmpty(value)) {
            return value;
        } else {
            return defaultValue;
        }
    }

    /// <summary>
    /// 获得表单中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>
    public static String getFormString(HttpServletRequest request, String key) {
        return getFormString(request, key, "");
    }

    /// <summary>
    /// 获得表单中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="defaultValue">默认值</param>
    /// <returns></returns>
    public static Integer getFormInt(HttpServletRequest request, String key, Integer defaultValue) {
        return TypeHelper.stringToInt(request.getParameter(key), defaultValue);
    }

    /// <summary>
    /// 获得表单中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>
    public static Integer getFormInt(HttpServletRequest request, String key) {
        return getFormInt(request, key, 0);
    }

    /// <summary>
    /// 获得请求中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="defaultValue">默认值</param>
    /// <returns></returns>
    public static String getRequestString(HttpServletRequest request, String key, String defaultValue) {

        String s = (String) request.getAttribute(key);
        if (!Strings.isNullOrEmpty(s)) {
            return s;
        } else {
            return defaultValue;
        }
    }

    /// <summary>
    /// 获得请求中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>
    public static String getRequestString(HttpServletRequest request, String key) {
        return getRequestString(request, key, "");
    }

    /// <summary>
    /// 获得请求中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <param name="defaultValue">默认值</param>
    /// <returns></returns>
    public static Integer getRequestInt(HttpServletRequest request, String key, Integer defaultValue) {

        Integer i = defaultValue;

        try {
            i = (Integer) request.getAttribute(key);
        } catch (Exception e) {

        }
        return i;
    }

    /// <summary>
    /// 获得请求中的值
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>
    public static Integer getRequestInt(HttpServletRequest request, String key) {

        return getRequestInt(request, key, 0);
    }


    /// <summary>
    /// 获得请求的url
    /// </summary>
    /// <returns></returns>
    public static String getUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }


    /// <summary>
    /// 获得请求的浏览器类型
    /// </summary>
    /// <returns></returns>
    public static String getBrowserType(HttpServletRequest request) {
        String type = request.getAuthType();
        if (Strings.isNullOrEmpty(type) || "unknown".equals(type)) {
            return "未知";
        }

        return type.toLowerCase();
    }

    /// <summary>
    /// 获得请求的浏览器名称
    /// </summary>
    /// <returns></returns>
    public static String getBrowserName(HttpServletRequest request) {
        String name = request.getHeader("User-Agent");
        if (StringHelper.IsNullOrEmpty(name) || "unknown".equals(name)) {
            return "未知";
        }

        return name.toLowerCase();
    }

    /// <summary>
    /// 获得请求的浏览器版本
    /// </summary>
    /// <returns></returns>
    public static String getBrowserVersion(HttpServletRequest request) {
        String version = request.getHeader("User-Agent");
        if (StringHelper.IsNullOrEmpty(version) || "unknown".equals(version)) {
            return "未知";
        }

        return version;
    }

    /// <summary>
    /// 获得请求客户端的操作系统类型
    /// </summary>
    /// <returns></returns>
    public static String getOSType(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return "未知";
        }

        String type = null;
        if (userAgent.contains("NT 6.1")) {
            type = "Windows 7";
        } else if ("NT 5.1".contains(userAgent)) {
            type = "Windows XP";
        } else if ("NT 6.2".contains(userAgent)) {
            type = "Windows 8";
        } else if ("android".contains(userAgent)) {
            type = "Android";
        } else if ("iphone".contains(userAgent)) {
            type = "IPhone";
        } else if ("Mac".contains(userAgent)) {
            type = "Mac";
        } else if ("NT 6.0".contains(userAgent)) {
            type = "Windows Vista";
        } else if ("NT 5.2".contains(userAgent)) {
            type = "Windows 2003";
        } else if ("NT 5.0".contains(userAgent)) {
            type = "Windows 2000";
        } else if ("98".contains(userAgent)) {
            type = "Windows 98";
        } else if ("95".contains(userAgent)) {
            type = "Windows 95";
        } else if ("Me".contains(userAgent)) {
            type = "Windows Me";
        } else if ("NT 4".contains(userAgent)) {
            type = "Windows NT4";
        } else if ("Unix".contains(userAgent)) {
            type = "UNIX";
        } else if ("Linux".contains(userAgent)) {
            type = "Linux";
        } else if ("SunOS".contains(userAgent)) {
            type = "SunOS";
        } else {
            type = "未知";
        }

        return type;
    }


    /// <summary>
    /// 判断是否是浏览器请求
    /// </summary>
    /// <returns></returns>
    public static boolean isBrowser(HttpServletRequest request) {
        String name = getBrowserName(request);
        for (String item : BROWSERLIST) {
            if (name.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public final static String HEADER_API_TOKEN = "X-CWMAPI-Token";

    /// <summary>
    /// 获取数据验证头
    /// </summary>
    /// <returns></returns>
    public static String getApiTokenHeader(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_TOKEN);
        } catch (Exception e) {

            return "";
        }
    }


    public final static String HEADER_API_OPENID = "X-CWMAPI-OpenId";

    /// <summary>
    /// 获取数据验证头
    /// </summary>
    /// <returns></returns>
    public static String getApiOpenIdHeader(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_OPENID);
        } catch (Exception e) {
            return "";
        }
    }

    /***
     * 获取openid
     * @param request
     * @return
     */
    public static String getApiOpenIdHeader(ServletRequest request) {
        try {
            return request.getParameter("openId");
        } catch (Exception e) {
            return "";
        }
    }


    public final static String HEADER_API_APIKEY = "X-CWMAPI-ApiKey";

    /// <summary>
    /// 获取APIKEY验证头
    /// </summary>
    /// <returns></returns>
    public static String getApiKeyHeader(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_APIKEY);
        } catch (Exception e) {

            return "";
        }
    }


    public final static String HEADER_API_APISECRET = "X-CWMAPI-ApiSecret";

    /// <summary>
    /// 获取数据验证头
    /// </summary>
    /// <returns></returns>
    public static String getApiSecretHeader(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_APISECRET);
        } catch (Exception e) {

            return "";
        }
    }


    /// <summary>
    /// 获取数据OPENID头
    /// </summary>
    /// <returns></returns>
    public static String getOpenIdHeader(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_OPENID);
        } catch (Exception e) {

            return "";
        }
    }


    public final static String HEADER_API_SESSIONID = "X-CWMAPI-SessionId";

    /// <summary>
    /// 获取SessionId
    /// </summary>
    /// <returns></returns>
    public static String getSessionIdHeader(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_SESSIONID);
        } catch (Exception e) {
            return "";
        }

    }


    public final static String HEADER_API_USERAGENT = "X-CWMAPI-UserAgent";

    public static String getUserAgent(HttpServletRequest request) {
        try {
            return request.getHeader(HEADER_API_USERAGENT);

        } catch (Exception e) {

            return "";
        }
    }

    /**
     * 处理url
     *
     * @param url
     * @return
     */
    public static String disposeUrl(String url) {

        if ("https://".contains(url) || "http://".contains(url)) {

            url = url.replaceAll("http:/", "http://");
            url = url.replaceAll("https:/", "https://");

            url = url.replaceAll("//", "/");


            url = url.replaceAll("http://https://", "https://");
            url = url.replaceAll("https://http://", "https://");
            url = url.replaceAll("http://http://", "https://");

        } else {
            url = "http://" + url;
        }
        return url;
    }


    /**
     * ip转int
     *
     * @param ip
     * @return
     */
    public static Integer ipToInteger(String ip) {
        String[] ips = ip.split("\\.");
        Integer ipFour = 0;
        //因为每个位置最大255，刚好在2进制里表示8位
        for (String ip4 : ips) {
            Integer ip4a = Integer.parseInt(ip4);
            //这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }
        return ipFour;
    }

    //ip = 3232235778
    public static String intToIp(Integer ip) {

        StringBuilder result = new StringBuilder(15);

        for (int i = 0; i < 4; i++) {

            result.insert(0, Long.toString(ip & 0xff));

            if (i < 3) {
                result.insert(0, '.');
            }

            ip = ip >> 8;
        }
        return result.toString();
    }

    //ip = 3232235778
    public String longToIp2(Integer ip) {

        return ((ip >> 24) & 0xFF) + "."
                + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }

    @Test
    public void test2() {

        String s = "http://localhost:8888/";
//        WebClient webClient = WebClient.builder()
//                .baseUrl(s)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
//                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
//                .build();
//
//        webClient.get(); //System.out.println("s:" + s);
        s = disposeUrl(s); //System.out.println("s:" + s);
    }

    //endregion

}
