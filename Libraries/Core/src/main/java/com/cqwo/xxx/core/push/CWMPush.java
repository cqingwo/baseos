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

package com.cqwo.xxx.core.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cqnews on 2017/4/16.
 */

@Service(value = "CWMPush")
public class CWMPush {

    /**
     * 邮件策略
     */
    @Autowired(required = false)
    private IPushStrategy iPushStrategy;
}
