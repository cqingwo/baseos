/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.web.framework.controller;

import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.helper.TypeHelper;
import com.cqwo.xxx.core.helper.WebHelper;
import com.cqwo.xxx.web.framework.workcontext.AdminWorkContext;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cqnews
 */
@Controller
@RequestMapping(value = "admin")
public class BaseAdminController extends BaseController {

    protected AdminWorkContext workContext;

    public BaseAdminController() {

    }

    @Override
    Object getWorkContext() {
        return workContext;
    }

    @ModelAttribute
    public void setInitialize(HttpServletResponse response) throws IOException {


        this.response = response;
        this.workContext = new AdminWorkContext();
        this.session = request.getSession();

        this.urlReferer = cwmUtils.GetAdminRefererCookie();

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


        try {






            int uid = TypeHelper.objectToInt(SecurityUtils.getSubject().getPrincipal());
            ;
            this.workContext.setUid(uid);
            if (uid >= 1) {

                PartUserInfo partUserInfo = users.getPartUserByUid(uid);

                if (partUserInfo != null && partUserInfo.getUid() >= 1) {

                    this.workContext.setPartUserInfo(partUserInfo);
                    this.workContext.setUserRankInfo(userRanks.getUserRankByCredits(partUserInfo.getPayCredits()));

                    if (this.workContext.getUserRankInfo() != null) {


                        this.workContext.setUserRid(this.workContext.getUserRankInfo().getUserRid());
                        if (!this.workContext.getUserRankInfo().getUserRid().equals(partUserInfo.getUserRid())) {

                            partUserInfo.setUserRid(this.workContext.getUserRankInfo().getUserRid());

                            users.updateUserRankByUid(partUserInfo.getUid(), this.workContext.getUserRid());
                        }


                    }

                }
            }
        } catch (Exception ex) {

        }

        if (this.workContext.getPartUserInfo() == null || this.workContext.getPartUserInfo().getUid() <= 0) {

            this.workContext.setPartUserInfo(users.getGuestPartUserInfo());
            this.workContext.setUserRankInfo(userRanks.getUserRankByUserRid(6));


        }

        this.workContext.setUserRid(workContext.getUserRankInfo().getUserRid());
        this.workContext.setNickName(workContext.getPartUserInfo().getNickName());


        this.workContext.setImageCDN("/static/admin/images");
        this.workContext.setCssCDN("/static/admin/css");
        this.workContext.setScriptCDN("/static/admin/scripts");
        this.workContext.setFontCDN("/static/admin/fonts");


        //Log.e("URL",request.getRequestURI());
    }


    @ModelAttribute
    public void inspectInitialize() {

        System.out.println("inspectInitialize被执行了");
    }

}


