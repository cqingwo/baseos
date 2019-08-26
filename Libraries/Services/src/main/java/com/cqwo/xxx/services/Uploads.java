package com.cqwo.xxx.services;/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */


import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.core.upload.CWMUpload;
import com.cqwo.xxx.core.upload.UploadFileInfo;
import com.cqwo.xxx.core.upload.CWMUpload;
import com.cqwo.xxx.core.upload.UploadFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqnews on 2017/12/25.
 */
@Service(value = "Uploads")
public class Uploads {

    @Resource
    private CWMUpload cwmUpload;

    @Autowired
    Logs logs;

    //region 文件上传

    /**
     * 文件上传
     *
     * @param s
     * @return
     */
    public List<UploadFileInfo> fileUpload(String s) {

        List<UploadFileInfo> uploadFileList = new ArrayList<UploadFileInfo>();

        try {
            uploadFileList = cwmUpload.getIUploadStrategy().fileUpload(s);
        } catch (Exception e) {
            logs.write(e, "文件上传失败");
        }

        return uploadFileList;
    }


    /**
     * 获取客户端token
     *
     * @return
     */
    public String getUpToken() {

        String token = null;
        try {
            token = cwmUpload.getIUploadStrategy().getUpToken();
        } catch (Exception ex) {

            logs.write(ex, "获取客户端token");
        }
        return token;
    }


    //endregion


    //endregion


}
