package com.cqwo.xxx.services;

import com.cqwo.xxx.core.cache.CWMCache;
import com.cqwo.xxx.core.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 缓存对外封装
 */
@Component(value = "CacheUtils")
public class CacheUtils {

    @Autowired
    CWMCache cwmCache;

    @Autowired
    Logs logs;

    /**
     * 获取缓存的值
     *
     * @param key 关键值
     * @return 值
     */
    public Object getValue(String key) {

        try {

            return cwmCache.getValue(key);

        } catch (Exception ex) {

            logs.write(ex, "获取缓存的值");

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

            return cwmCache.getValue(key, clz);

        } catch (Exception ex) {

            logs.write(ex, "获取缓存的值");

        }

        return null;
    }

    /**
     * 设置缓存键值
     *
     * @param key
     * @param object
     */
    public void setValue(String key, Object object) {

        try {

            cwmCache.setValue(key, object);

        } catch (Exception ex) {

            logs.write(ex, "获取缓存的值");

        }
    }

    /**
     * 设置缓存键值
     *
     * @param key
     * @param object
     */
    public void setValue(String key, Object object, Integer timeout, TimeUnit timeUnit) {

        try {

            cwmCache.setValue(key, object, timeout, timeUnit);

        } catch (Exception ex) {

            logs.write(ex, "设置缓存键值");

        }
    }

    /**
     * 或取缓存键值列表
     *
     * @param key
     * @param clz
     */
    public <T> List<T> getListValue(String key, Class<T> clz) {

        List<T> list = new ArrayList<>();

        try {

            list = cwmCache.getListValue(key, clz);

        } catch (Exception ex) {

            logs.write(ex, "设置缓存键值");

        }

        return list;
    }

    /**
     * 设置缓存键值列表
     *
     * @param key
     * @param list
     */
    public void setListValue(String key, List<?> list) {

        try {

            cwmCache.setListValue(key, list);

        } catch (Exception ex) {

            logs.write(ex, "设置缓存键值");

        }

    }

    /**
     * 设置列表
     *
     * @param key
     * @param list
     */
    public void setListValue(String key, List<?> list, Integer timeout, TimeUnit timeUnit) {

        try {
            cwmCache.setListValue(key, list, timeout, timeUnit);

        } catch (Exception ex) {

            logs.write(ex, "设置缓存键值");
        }
    }


    /**
     * 删除缓存键值key
     *
     * @param key
     */
    public boolean delete(String key) {

        boolean isSuccess = false;

        try {
            cwmCache.delete(key);

        } catch (Exception ex) {

            logs.write(ex, "删除缓存键值key");
        }

        return isSuccess;
    }

}
