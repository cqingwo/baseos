/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.cache;

import com.cqwo.xxx.core.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cqnews on 2017/4/10.
 */
@Component(value = "CWMCache")

public class CWMCache {

    /**
     * 缓存策略
     */
    @Autowired
    private ICacheStrategy cacheStrategy;

    @Autowired
    private Logs logs;

    /**
     * 获取缓存值
     *
     * @param key 关键值
     * @return 值
     */
    public Object getValue(String key) {
        try {
            return cacheStrategy.getValue(key);
        } catch (Exception ex) {
            logs.write(ex, "获取缓存值");
        }
        return null;
    }

    /**
     * 获取缓存键值
     *
     * @param key 关键值
     * @return 值
     */
    public <T> T getValue(String key, Class<T> clz) {
        try {
            return cacheStrategy.getValue(key, clz);
        } catch (Exception ex) {
            logs.write(ex, "获取缓存键值");
        }
        return null;
    }


    /**
     * 设置缓存键值
     *
     * @param key    key
     * @param object 实体
     */
    public void setValue(String key, Object object) {
        try {
            cacheStrategy.setValue(key, object);
        } catch (Exception ex) {
            logs.write(ex, "设置缓存键值");
        }
    }

    /**
     * 设置缓存键值
     *
     * @param key    key
     * @param object 实体
     */
    public void setValue(String key, Object object, Integer timeout, TimeUnit timeUnit) {
        try {
            cacheStrategy.setValue(key, object, timeout, timeUnit);
        } catch (Exception ex) {
            logs.write(ex, "设置缓存键值");
        }
    }

    /**
     * 获取缓存键值列表
     *
     * @param key 关键值
     * @return 值
     */
    public <T> List<T> getListValue(String key, Class<T> clz) {

        try {

            return cacheStrategy.getListValue(key, clz);
        } catch (Exception ex) {
            logs.write(ex, "获取缓存键值列表");
        }

        return null;
    }

    /**
     * 设置缓存键值列表
     *
     * @param key  key
     * @param list list
     */
    public void setListValue(String key, List<?> list) {

        try {
            cacheStrategy.setListValue(key, list);
        } catch (Exception ex) {
            logs.write(ex, "设置缓存键值列表");
        }
    }

    /**
     * 设置缓存键值列表
     *
     * @param key  key
     * @param list list
     */
    public void setListValue(String key, List<?> list, Integer timeout, TimeUnit timeUnit) {

        try {
            cacheStrategy.setListValue(key, list, timeout, timeUnit);
        } catch (Exception ex) {
            logs.write(ex, "设置缓存键值列表");
        }
    }


    /**
     * 删除缓存键值key
     *
     * @param key key
     */
    public boolean delete(String key) {
        try {
            return cacheStrategy.delete(key);
        } catch (Exception ex) {
            logs.write(ex, "删除缓存键值key");
        }
        return false;
    }

}
