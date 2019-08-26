package com.cqwo.xxx.strategy.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqwo.xxx.core.cache.ICacheStrategy;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component(value = "RedisStrategy")
public class RedisStrategy implements ICacheStrategy {

    /**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedisTemplate redisTemplate;

    protected int timeout = 60;

    protected TimeUnit timeUnit = TimeUnit.MINUTES;


    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }


//    /**
//     * 测试打印
//     */
//    @Override
//    public String Print() {
//
//        ValueOperations<String, Object> valueops = redisTemplate
//                .opsForValue();
//        valueops.set("222", "测试打印");
//
//
//        //System.out.print("测试打印");
//        return (String) valueops.get("222");
//    }

    /**
     * 获取缓存键值
     *
     * @param key 关键值
     * @return 值
     */
    @Override
    public Object getValue(String key) {
        try {

            ValueOperations<String, String> valueops = redisTemplate.opsForValue();

            String s = valueops.get(key);

            if (Strings.isNullOrEmpty(s)) {
                return null;
            }

            return JSON.parseObject(s, String.class);

        } catch (Exception ex) {

            logger.error(ex.getMessage());
        }

        return null;
    }

    /**
     * 获取缓存键值
     *
     * @param key 关键值
     * @return 值
     */
    @Override
    public <T> T getValue(String key, Class<T> clz) {

        try {

            ValueOperations<String, String> valueops = redisTemplate.opsForValue();

            String s = valueops.get(key);

            if (Strings.isNullOrEmpty(s)) {
                return null;
            }

            return JSON.parseObject(s, clz);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }


    /**
     * 设置缓存键值
     *
     * @param key    关键值
     * @param object 值
     */
    @Override
    public void setValue(String key, Object object) {

        if (object == null) {
            return;
        }

        setValue(key, object, 60, TimeUnit.MINUTES);
    }


    /**
     * 设置缓存键值
     *
     * @param key    关键值
     * @param object 值
     */
    @Override
    public void setValue(String key, Object object, Integer timeout, TimeUnit timeUnit) {

        try {
            if (object == null) {
                return;
            }

            ValueOperations<String, Object> valueops = redisTemplate.opsForValue();

            String s = JSON.toJSONString(object);

            valueops.set(key, s, timeout, timeUnit);
        } catch (Exception ex) {
            logger.error("设置缓存键值失败:" + ex.getMessage());
        }
    }

    /**
     * 获取缓存键值列表
     *
     * @param key 关键值
     * @return 值
     */
    @Override
    public <T> List<T> getListValue(String key, Class<T> clz) {

        try {

            ValueOperations<String, String> valueops = redisTemplate.opsForValue();

            String s = valueops.get(key);

            if (Strings.isNullOrEmpty(s)) {
                return null;
            }

            return JSONArray.parseArray(s, clz);

        } catch (Exception ignored) {

        }
        return null;
    }

    /**
     * 设置缓存键值列表
     *
     * @param key  关键值
     * @param list 值
     */
    @Override
    public void setListValue(String key, List<?> list) {
        setListValue(key, list, 60, TimeUnit.MINUTES);
    }


    /**
     * 设置缓存键值列表
     *
     * @param key  关键值
     * @param list 值
     */
    @Override
    public void setListValue(String key, List<?> list, Integer timeout, TimeUnit timeUnit) {

        try {

            list = list == null ? new ArrayList<>() : list;

            String s = JSONObject.toJSONString(list);

            ValueOperations<String, Object> valueops = redisTemplate.opsForValue();

            valueops.set(key, s, timeout, timeUnit);

        } catch (Exception ignored) {

        }
    }

    /**
     * 删除缓存键值key
     *
     * @param key
     */
    @Override
    public boolean delete(String key) {

        try {
            return redisTemplate.delete(key);

        } catch (Exception ignored) {

        }
        return false;


    }


}
