/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by cqnews on 2017/3/20.
 */
public class JsonHelper {

    /**
     * 将数组转换成JSON
     *
     * @param object
     * @return
     */
    public static JSON object2json(Object object) {
        return (JSON) JSON.toJSON(object);
    }

    /**
     * 将Json串转成JSONObject
     *
     * @param json json串
     * @return
     */
    public static JSONObject json2object(String json) {

        return JSON.parseObject(json);

    }

    public static Map<String, String> getJsonKey(JSONObject object, String... keys) {

        Map<String, String> params = new HashMap<>();

        for (String s : keys) {
            try {
                params.put(s, object.getString(s));
            } catch (Exception ignored) {

            }
        }

        return params;

    }

    /**
     * 根据key获取jsonStr的value
     *
     * @param key
     * @param jsonStr
     * @return
     */
    public static String value4Json(String key, String jsonStr) {
        String value = "";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        try {
            value = jsonObject.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
//
//    /**
//     * 可变key组装map
//     * @param object
//     * @param keys
//     * @return
//     */
//    public static Map<String, String> getJsonKey(JSONObject object, String... keys) {
//
//        Map<String, String> params = new HashMap<>();
//
//        for (String s : keys) {
//            try {
//                params.put(s, object.getString(s));
//            } catch (Exception ex) {
//
//            }
//        }
//
//        return params;
//
//    }

    /**
     * 将json转成map
     *
     * @param json json
     * @return
     */
    public static Map<String, String> getMap4Json(String json) {

        JSONObject jsonObject = JSONObject.parseObject(json);
        Set<String> keys = jsonObject.keySet();
        String value;

        Map<String, String> maps = Maps.newHashMap();

        for (String key : keys) {
            value = jsonObject.get(key).toString();
            maps.put(key, value);
        }

        return maps;
    }

}
