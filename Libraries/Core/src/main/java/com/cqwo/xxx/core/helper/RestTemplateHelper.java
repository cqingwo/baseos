package com.cqwo.xxx.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

public class RestTemplateHelper {

    private static Logger logger = LoggerFactory.getLogger(RestTemplateHelper.class);

    public String post(String reqParam, String url) {
        // 核心返回结果报文字符串
        String returnJson = "";

        try {
            //复杂构造函数的使用
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(1000);// 设置超时
            requestFactory.setReadTimeout(1000);

            //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            //设置HTTP请求头信息，实现编码等
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            //利用容器实现数据封装，发送
            HttpEntity<String> entity = new HttpEntity<String>(reqParam, headers);
            returnJson = restTemplate.postForObject(url, entity, String.class);

            // 转码原因：RestTemplate默认是使用org.springframework.http.converter.StringHttpMessageConverter来解析
            // StringHttpMessageConverter 默认用的 ISO-8859-1来编码的
            returnJson = new String(returnJson.getBytes("ISO-8859-1"), "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("----------------------------------------");
            logger.info(returnJson);
            logger.info("----------------------------------------");
        }

        return returnJson;
    }

}
