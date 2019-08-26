/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.upload;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cqnews on 2017/12/25.
 */
@Component(value = "CWMUpload")
@Getter
public class CWMUpload {

    /**
     * 上传模块策略
     */
    @Autowired(required = false)
    private IUploadStrategy iUploadStrategy;


}
