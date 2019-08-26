/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringHelper implements ApplicationContextAware {

    private static ApplicationContext ctx;

    public static Object getBean(String id) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        return ctx.getBean(id);
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringHelper.ctx == null) {
            SpringHelper.ctx = applicationContext;
//            // System.out.println(
//                    "========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext="
//                            + applicationContext + "========");
        }
    }

}
