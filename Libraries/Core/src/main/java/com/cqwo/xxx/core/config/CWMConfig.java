/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cqnews on 2017/3/24.
 */


@Service(value = "CWMConfig")
@Getter
public class CWMConfig {


    /**
     * 配置策略
     */

    @Autowired(required = false)
    private IConfigStrategy iconfigstrategy;//配置策略


}
