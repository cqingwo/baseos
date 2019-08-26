/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by cqnews on 2017/12/8.
 */
public class HttpHelper {

    private Logger logger=LoggerFactory.getLogger(HttpHelper.class);    //日志记录


    private Regex detectiphoneregex =new Regex("iphone|Mac");

    private Regex detectwechatregex =new Regex("MicroMessenger|micromessenger");

    public HttpHelper() {

    }

    //region 数据请求

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }


    /**
     * 发送 GET 请求（HTTP），带输入数据
     *
     * @param url    请求地址
     * @param params 输入参数
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {

        String apiUrl=url;
        StringBuffer param=new StringBuffer();
        int i=0;
        if (params != null && params.size() >= 1) {

            Set<String> arr = params.keySet();
            for (String key : arr) {
                if (i == 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }
                param.append(key).append("=").append(params.get(key));
                i++;
            }
            apiUrl+=param;
        }
        String result=null;

        CloseableHttpClient httpClient=HttpClients.createDefault();

        try {

            HttpGet httpGet=new HttpGet(apiUrl);

            httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

            CloseableHttpResponse response=httpClient.execute(httpGet);
            HttpEntity entity=response.getEntity();

           // //System.out.println("Login form get: " + response.getStatusLine());

            //System.out.println("Initial set of cookies:");


            //输出网页源码
            result=EntityUtils.toString(response.getEntity(), "utf-8");
            //System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }


    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<String, Object>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param url    API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPost(String url, Map<String, Object> params) {
        String apiUrl=url;
        StringBuffer param=new StringBuffer();
        int i=0;
        if (params != null && params.size() >= 1) {

            Set<String> arr = params.keySet();
            for (String key : arr) {
                if (i == 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }
                param.append(key).append("=").append(params.get(key));
                i++;
            }
            apiUrl+=param;
        }
        String result=null;

        CloseableHttpClient httpClient=HttpClients.createDefault();

        try {

            HttpPost httpPost=new HttpPost(apiUrl);

            httpPost.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpPost.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

            CloseableHttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity(); //System.out.println("Login form get: " + response.getStatusLine()); //System.out.println("Initial set of cookies:");


            //输出网页源码
            result=EntityUtils.toString(response.getEntity(), "utf-8"); //System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param url
     * @param json json对象
     * @return
     */
    public static String doPost(String url, JSONObject json) {

        String result=null;

        CloseableHttpClient httpClient=HttpClients.createDefault();

        try {

            HttpPost httpPost=new HttpPost(url);

            httpPost.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpPost.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

            StringEntity stringEntity=new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            CloseableHttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity(); //System.out.println("Login form get: " + response.getStatusLine()); //System.out.println("Initial set of cookies:");


            //输出网页源码
            result=EntityUtils.toString(response.getEntity(), "utf-8"); //System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Test
    public void test() {
        doGet("https://cdn.cqwo.com/");
    }


    /**
     * 发送 SSL POST 请求（HTTPS）
     *
     * @param url API接口URL
     * @return
     */
    public static String doPostSSL(String url) {
        return doPostSSL(url, new HashMap<String, Object>());
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param url    API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String url, Map<String, Object> params) {

        String result=null;

        CloseableHttpClient httpClient=HttpClients.createDefault();


        try {

            HttpPost httpPost=new HttpPost(url);

            httpPost.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpPost.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");

            List<NameValuePair> pairList=new ArrayList<NameValuePair>(params.size());
            Set<Map.Entry<String, Object>> arr = params.entrySet();
            for (Map.Entry<String, Object> entry : arr) {
                NameValuePair pair=new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));

            CloseableHttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();

            //输出网页源码
            result=EntityUtils.toString(response.getEntity(), "utf-8"); //System.out.println(result);

            Integer statusCode=response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            if (entity == null) {
                return null;
            }
            return EntityUtils.toString(entity, "utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }


    /**
     * 发送POST请求
     * @param url 地址
     * @param paramName 参数名
     * @param param 参数
     * @return
     */
    public static String jsonPost(String url,String paramName,String param){
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setCharset(Charsets.UTF_8);
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = URI.create(url);
        HttpPost httpPost = new HttpPost(uri);
        entityBuilder.addTextBody(paramName, param);
        httpPost.setEntity(entityBuilder.build());
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity);
                EntityUtils.consume(resEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpclient);
            HttpClientUtils.closeQuietly(response);
// //System.out.println(result);
        }
        return result;
    }

    //endregion
}
