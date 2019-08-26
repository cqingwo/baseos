/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Created by cqnews on 2017/4/15.
 */
@Service(value = "Logs")
public class Logs {

    @Autowired
    private ILogStrategy ilogstrategy;//日志策略

    /**
     * 写入日志
     *
     * @param message 消息
     */
    public void write(String message)
    {
        ilogstrategy.write(message);
    }

    /// <summary>
    /// 写入日志
    /// </summary>
    /// <param name="ex">异常对象</param>

    /**
     * 写入日志
     * @param ex 异常对象
     */
    public  void write(Exception ex) {
        ilogstrategy.write(MessageFormat.format("方法:{0},异常信息:{1}", ex.getStackTrace(), ex.getMessage()));
    }

    /**
     * 写入日志
     * @param ex 异常
     * @param message 消息
     */
    public void write(Exception ex, String message) {

        ilogstrategy.write(MessageFormat.format("异常描述:{0},方法:{1},异常信息:{2}", message, ex.getStackTrace(), ex.getMessage()));

    }


    /**
     * debug输出
     *
     * @param str
     */
    public void debug(String str) {
        ilogstrategy.debug(str);
    }

    /**
     * info输出
     *
     * @param str
     */
    public void info(String str) {
        ilogstrategy.info(str);
    }

    /**
     * warn输出
     *
     * @param str
     */
    public void warn(String str) {
        ilogstrategy.warn(str);
    }

    /**
     * error输出
     *
     * @param str
     */
    public void error(String str) {
        ilogstrategy.error(str);
    }

    /**
     * fatal输出
     *
     * @param str
     */
    public void fatal(String str) {
        ilogstrategy.fatal(str);
    }

}
