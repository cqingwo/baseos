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

package com.cqwo.xxx.core.wechat;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "CWMWechat")
@Getter
public class CWMWechat {

    /**
     * 微信小程序策略
     */
    @Autowired(required = false)
    private IMiniAppStrategy iMiniAppStrategy;

    /**
     * 微信公众号策略
     */
    @Autowired(required = false)
    private IWechatMPStrategy iWechatMPStrategy;


    /**
     * 微信支付策略
     */
    @Autowired(required = false)
    private IWechatPayStrategy iWechatPayStrategy;


}
