/*
 *
 *  *
 *  *  * Copyright (C) 2018.
 *  *  * 用于JAVA项目开发
 *  *  * 重庆青沃科技有限公司 版权所有
 *  *  * Copyright (C)  2018.  CqingWo Systems Incorporated. All rights reserved.
 *  *
 *
 */

package com.link510.softgateway.core.plugin.Interface;

/**
 * 信任登录的接口
 */
public interface IOAuthPlugin extends IPlugin {

    /**
     * 登陆动作方法
     */
    String getLoginUrl();

}