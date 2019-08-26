/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.web.framework.controller;

import com.cqwo.xxx.core.helper.WebHelper;
import com.cqwo.xxx.web.framework.workcontext.WebWorkContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cqnews
 */
@Controller
public class BaseWebController extends BaseController {


    protected WebWorkContext workContext;


    @Override
    protected WebWorkContext getWorkContext() {
        return workContext;
    }

    @ModelAttribute
    public void setInitialize(HttpServletResponse response) throws IOException {

        this.response=response;
        this.workContext=new WebWorkContext();
        this.session=request.getSession();


        /**
         * 获取当前URL
         */
        this.workContext.setUrl(WebHelper.getUrl(request));

        /**
         * 获取当前controller
         */
        this.workContext.setController(this.getClass().getName());

        /**
         * 获取当前action 暂时不能实现
         */
        this.workContext.setAction(WebHelper.getRawUrl(request));

        /**
         * 获取sessionid
         */
        this.workContext.setSid(session.getId());

        /**
         * 判断是否为ajax
         */
        if (WebHelper.isAjax(request)) {
            this.workContext.setHttpAjax(true);
        }

        /**
         * 获取IP
         */
        this.workContext.setIp(WebHelper.getIP(request));


        this.workContext.setUrlReferrer(WebHelper.getUrlReferrer(request));

        this.workContext.setSid(session.getId());


//        try {
//            int uid=cwmUtils.getUidCookie();
//            this.workContext.setUid(uid);
//            if (uid >= 1) {
//
//                PartUserInfo partUserInfo=users.getPartUserByUid(uid);
//
//                if (partUserInfo != null && partUserInfo.getUid() >= 1) {
//
//                    this.workContext.setPartUserInfo(partUserInfo);
//                    this.workContext.setUserRankInfo(userRanks.getUserRankByCredits(partUserInfo.getPayCredits()));
//
//                    if (this.workContext.getUserRankInfo() != null) {
//
//
//                        this.workContext.setUserRid(this.workContext.getUserRankInfo().getUserRid());
//                        if (this.workContext.getUserRankInfo().getUserRid() != partUserInfo.getUserRid()) {
//
//                            partUserInfo.setUserRid(this.workContext.getUserRankInfo().getUserRid());
//
//                            users.updateUserRankByUid(partUserInfo.getUid(), this.workContext.getUserRid());
//                        }
//
//
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//
//        }
//
//        if (this.workContext.getPartUserInfo() == null || this.workContext.getPartUserInfo().getUid() <= 0) {
//
//            this.workContext.setPartUserInfo(users.getGuestPartUserInfo());
//
//        }



        //Log.e("URL",request.getRequestURI());
    }


    @ModelAttribute
    public void inspectInitialize() {

        System.out.println("inspectInitialize被执行了");
    }

    public BaseWebController() {
        super();
    }

}
