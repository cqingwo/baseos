/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.data;


import com.cqwo.xxx.core.data.rdbs.IBaseStrategy;
import com.cqwo.xxx.core.data.rdbs.IUserStrategy;
import com.cqwo.xxx.core.data.rdbs.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cqnews on 2017/4/10.
 */
@Service(value = "CWMData")
@Getter
public class CWMData {

    @Autowired(required = false)
    IBaseStrategy iBaseStrategy;

    @Autowired(required = false)
    IUserStrategy iUserStrategy;

    @Autowired(required = false)
    IAuthorStrategy iAuthorStrategy;

    @Autowired(required = false)
    ILog2Strategy iLog2Strategy;
    

}
