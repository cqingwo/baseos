/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cqnews on 2017/4/13.
 */
public class MapHelper {

    //将javabean实体类转为map类型，然后返回一个map类型的值
    public static Map<String, String> objectToMap(Object obj) {
        Map<String, String> params = new HashMap<String, String>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }


    /**
     * 转map
     * @param params
     * @return
     */
    public static MultiValueMap<String, String> toMultiValueMap(Map<String, String> params) {

        MultiValueMap<String, String> maps = new LinkedMultiValueMap<>();

        try{

            for (String key : params.keySet()) {
                maps.set(key, params.getOrDefault(key, ""));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return maps;
    }

    public static Map<String, String> toHashMap(MultiValueMap<String, String> params) {

        Map<String, String> maps = new HashMap<>();

        try{

            for (String key : params.keySet()) {
                maps.put(key, params.getFirst(key));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return maps;
    }
}
