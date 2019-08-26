/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.email;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cqnews on 2017/4/16.
 */

@Service(value = "CWMEmail")
@Getter
public class CWMEmail {

    /**
     * 邮件策略
     */
    @Autowired(required = false)
    private IEmailStrategy iEmailStrategy;



}
