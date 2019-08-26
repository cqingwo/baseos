/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.services;

import com.cqwo.xxx.core.config.CWMConfig;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.helper.*;
import com.cqwo.xxx.core.log.Logs;
import com.google.common.base.Strings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cqnews on 2017/12/12.
 */
@Service(value = "CWMUtils")
public class CWMUtils {


    @Autowired
    private HttpServletRequest request;


    @Autowired
    CWMConfig cwmConfig;

    @Autowired
    Logs logs;


    public CWMUtils() {

        //this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //System.out.print(request.toString());
        //this.response =  context.getServletContext().getAttribute("re");//((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();

    }


    //region  加密/解密

    /// <summary>
    /// AES加密
    /// </summary>
    /// <param name="encryptStr">加密字符串</param>
    public String aesEncrypt(String encryptStr) {
        try {
            return AESHelper.encode(encryptStr);
        } catch (Exception e) {
            logs.write(e, "AES加密");
        }
        return "";
    }

    /// <summary>
    /// AES解密
    /// </summary>
    /// <param name="decryptStr">解密字符串</param>
    public String aesDecrypt(String decryptStr) {
        try {
            return AESHelper.decode(decryptStr);
        } catch (Exception e) {
            logs.write(e, "AES解密");
        }
        return "";
    }

    //endregion

    //region Cookie

    /**
     * 获得用户sid
     *
     * @return
     */
    public String getSidCookie() {
        return WebHelper.getCookieValue(request, "cwmsid");
    }

    /**
     * 设置用户sid
     *
     * @param sid
     */
    public void setSidCookie(HttpServletResponse response, String sid) {
        WebHelper.setCookie(response, "cwmsid", sid);
    }

    /**
     * 获得用户id
     *
     * @return
     */
    public Integer getUidCookie() {
        return TypeHelper.stringToInt(GetCWMCookie("uid"), -1);
    }

    /**
     * 设置用户id
     *
     * @param uid
     */

    public void setUidCookie(HttpServletResponse response, Integer uid) {

        WebHelper.setCookie(response, "uid", TypeHelper.intToString(uid));

    }

    /// <summary>
    /// 获得cookie密码
    /// </summary>
    /// <returns></returns>
    public String GetCookiePassword() {
        return WebHelper.urlDecode(GetCWMCookie("password"));
    }

    /// <summary>
    /// 解密cookie密码
    /// </summary>
    /// <param name="cookiePassword">cookie密码</param>
    /// <returns></returns>
    public String DecryptCookiePassword(String cookiePassword) {
        try {

            //cookiePassword=WebHelper.urlDecode(cookiePassword);

            return aesDecrypt(cookiePassword).trim();
        } catch (Exception e) {
            return "";
        }
    }

    /// <summary>
    /// 设置cookie密码
    /// </summary>
    public void setCookiePassword(HttpServletResponse response, String password) {
        try {
            SetCWMCookie(response, "password", WebHelper.urlEncode(aesEncrypt(password)));
        } catch (Exception e) {

        }
    }

    /**
     * 清空用户
     *
     * @param response
     */
    public void ClearUserCooke(HttpServletResponse response) {


        WebHelper.removeCookie(response, "userinfo");
        WebHelper.removeCookie(response, "uid");
        WebHelper.removeCookie(response, "password");


    }


    /// <summary>
    /// 设置用户
    /// </summary>
    /// <param name="uid">用户id</param>
    /// <param name="password">密码</param>
    /// <param name="sid">sid</param>
    /// <param name="expires">过期时间</param>
    public void SetUserCookie(HttpServletResponse response, PartUserInfo partUserInfo, Integer expires) {

        String json = JsonHelper.object2json(partUserInfo).toString();

        SetCWMCookie(response, "userinfo", WebHelper.urlEncode(json), expires);
        setUidCookie(response, partUserInfo.getUid());
        try {
            setCookiePassword(response, partUserInfo.getPassword());
        } catch (Exception e) {
        }

//
//        if (cookie == null)
//            cookie=new HttpCookie("cwm");
//
//        cookie.Values["uid"]=partUserInfo.getUid().toString();
//        cookie.Values["password"]=WebHelper.urlEncode(aesEncrypt(partUserInfo.Password));
//        if (expires > 0) {
//            cookie.Values["expires"]=expires.ToString();
//            cookie.Expires=DateTime.Now.AddDays(expires);
//        }
//        String cookieDomain=CWMConfig.MallConfig.CookieDomain;
//        if (cookieDomain.Length != 0)
//            cookie.Domain=cookieDomain;
//
//        HttpContext.Current.Response.AppendCookie(cookie);
    }

    @Test
    public void Test2(HttpServletResponse response) {

        //SetUserCookie(response, new PartUserInfo("yjwwtu", "123456"), 5000);


        setUidCookie(response, 2);

    }


    /// <summary>
    /// 获得cookie
    /// </summary>
    /// <param name="key">键</param>
    /// <returns></returns>

    /**
     * 获得cookie
     *
     * @param key 键
     * @return
     */
    public String GetCWMCookie(String key) {
        return WebHelper.getCookieValue(request, key);
    }

    /**
     * 设置cookie
     *
     * @param key   键
     * @param value 值
     */
    public void SetCWMCookie(HttpServletResponse response, String key, String value) {
        WebHelper.setCookie(response, key, value);
    }

    /**
     * 设置cookie
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间
     */
    public void SetCWMCookie(HttpServletResponse response, String key, String value, Integer expire) {
        WebHelper.setCookie(response, key, value, expire);
    }

    /// <summary>
    /// 获得访问referer
    /// </summary>
    public String GetRefererCookie() {
        String referer = "/";
        try {

            referer = WebHelper.urlDecode(WebHelper.getCookieValue(request, "referer"));
            if (referer.length() <= 0) {
                referer = "/";
            }
        } catch (Exception e) {

        }
        return referer;
    }

    /// <summary>
    /// 设置访问referer
    /// </summary>
    public void SetRefererCookie(HttpServletResponse response, String url) {
        WebHelper.setCookie(response, "referer", WebHelper.urlEncode(url));
    }

    /// <summary>
    /// </summary>
    public String GetAdminRefererCookie() {
        return WebHelper.urlDecode(GetAdminRefererCookie("/admin/runinfo.html"));
    }


    /**
     * 获得后台访问referer
     *
     * @param defaultUrl 默认地址
     * @return
     */
    public String GetAdminRefererCookie(String defaultUrl) {
        String adminReferer = defaultUrl;
        try {
            adminReferer = WebHelper.urlDecode(WebHelper.getCookieValue(request, "adminreferer"));
            if (adminReferer.length() == 0) {
                adminReferer = defaultUrl;
            }
        } catch (Exception e) {
        }

        return adminReferer;
    }

    /// <summary>
    /// 获得店铺后台访问referer
    /// </summary>
    public String GetStoreAdminRefererCookie() {
        return WebHelper.urlDecode(GetStoreAdminRefererCookie("/storeadmin/runinfo.html"));
    }

    /**
     * 获得后台访问referer
     *
     * @param defaultUrl 默认地址
     * @return
     */
    public String GetStoreAdminRefererCookie(String defaultUrl) {
        String adminReferer = defaultUrl;
        try {
            adminReferer = WebHelper.urlDecode(WebHelper.getCookieValue(request, "storereferer"));
            if (adminReferer.length() == 0) {
                adminReferer = defaultUrl;
            }
        } catch (Exception e) {

        }
        return adminReferer;
    }


    /// <summary>
    /// 获得店铺后台访问referer
    /// </summary>
    public String GetMobileRefererCookie() {


        return WebHelper.urlDecode(GetMobileRefererCookie("/mob/"));
    }

    /**
     * 获得后台访问referer
     *
     * @param defaultUrl 默认地址
     * @return
     */
    public String GetMobileRefererCookie(String defaultUrl) {

        String adminReferer = WebHelper.urlDecode(WebHelper.getCookieValue(request, "mobilereferer"));
        if (adminReferer.length() == 0) {
            adminReferer = defaultUrl;
        }
        return adminReferer;
    }


    /**
     * 设置后台访问referer
     *
     * @param url url地址
     */
    public void SetAdminRefererCookie(HttpServletResponse response, String url) {
        WebHelper.setCookie(response, "adminreferer", WebHelper.urlEncode(url));
    }

    /**
     * 获取
     *
     * @return
     */
    public String getRawUrl() {
        return WebHelper.getRawUrl(request);
    }


    //endregion


    //region 获取路由数据


    /**
     * 获得路由中的值
     *
     * @param key 关键词
     * @return int
     */
    public Integer getParameterInt(String key) {
        return getParameterInt(key, 0);
    }


    /**
     * 获得路由中的值
     *
     * @param key          关键词
     * @param defaultValue 默认值
     * @return int
     */
    public Integer getParameterInt(String key, Integer defaultValue) {
        return TypeHelper.stringToInt(request.getParameter(key), defaultValue);
    }


    /**
     * 获得路由中的值
     *
     * @param key 关键词
     * @return int
     */
    public String getParameterString(String key) {
        return getParameterString(key, "");
    }


    /**
     * 获得路由中的值
     *
     * @param key          关键词
     * @param defaultValue 默认值
     * @return int
     */
    public String getParameterString(String key, String defaultValue) {
        String str = request.getParameter(key);

        if (StringHelper.isBlank(str)) {
            return defaultValue;
        }
        return str.toString();
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public Integer getParameterUid() {
        return getParameterInt("uid", 0);
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public String getParameterToken() {
        return getParameterString("token", "");
    }

    /**
     * 获取机器编码
     *
     * @return
     */
    public String getParameterSN() {

        try {
            return getParameterString("sn", "");
        } catch (Exception ex) {
            logs.write(ex, "获取机器编码");
        }
        return "";

    }

    //endregion


    //region 获取header中的信息

    /**
     * 获取数据验证头
     *
     * @return
     */
    public String getApiTokenHeader() {

        return WebHelper.getApiTokenHeader(request);

    }


    /**
     * 获取数据验证头
     *
     * @return
     */
    public String getApiOpenIdHeader() {
        String s = WebHelper.getApiOpenIdHeader(request);

        if (Strings.isNullOrEmpty(s)) {
            s = "";
        }

        return s;
    }

    /**
     * 获取APIKEY验证头
     *
     * @return
     */
    public String getApiKeyHeader() {

        String s = WebHelper.getApiKeyHeader(request);
        if (Strings.isNullOrEmpty(s)) {
            s = "";
        }

        return s;
    }


    /**
     * 获取数据验证头
     *
     * @return
     */
    public String getApiSecretHeader() {

        String s = WebHelper.getOpenIdHeader(request);
        if (Strings.isNullOrEmpty(s)) {
            s = "";
        }

        return s;
    }


    /**
     * 获取数据OPENID头
     *
     * @return
     */
    public String getOpenIdHeader() {

        String s = WebHelper.getOpenIdHeader(request);
        if (Strings.isNullOrEmpty(s)) {
            s = "";
        }

        return s;
    }

    /**
     * 获取SessionId
     *
     * @return
     */
    public String getSessionIdHeader() {

        String s = WebHelper.getSessionIdHeader(request);
        if (Strings.isNullOrEmpty(s)) {
            s = "";
        }

        return s;


    }

    /**
     * 获取用户代理信息
     *
     * @return
     */
    public String getUserAgent() {
        String s = WebHelper.getUserAgent(request);
        if (Strings.isNullOrEmpty(s)) {
            s = "";
        }

        return s;
    }

    /**
     * 获取机器IP
     *
     * @return
     */
    public String getIP() {

        try {
            return WebHelper.getIP(request);
        } catch (Exception ex) {
            logs.write(ex, "");
        }

        return "127.0.0.1";

    }


    //endregion
}
