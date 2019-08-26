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
import com.cqwo.xxx.core.domain.users.UserTokenInfo;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.helper.WebHelper;
import com.cqwo.xxx.services.UserRanks;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.web.framework.workcontext.ApiWorkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cqnews
 */
@Controller
@RequestMapping(value = "api")
public class BaseApiController extends BaseController {

    protected ApiWorkContext workContext;



    @Autowired
    protected Users users;

    @Autowired
    protected UserRanks userRanks;


    public BaseApiController() {

    }

    @Override
    ApiWorkContext getWorkContext() {
        return workContext;
    }

    @ModelAttribute
    public void setInitialize(HttpServletResponse response) throws IOException {

        this.response = response;
        this.workContext = new ApiWorkContext();
        this.session = request.getSession();

//		this.accessToken = wechatUtils.getAccessToken();
//		this.wechatConfigInfo = cwmConfig.getIconfigstrategy().getWechatConfig();

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


            String token = cwmUtils.getParameterToken();


            if (StringHelper.isNotNullOrWhiteSpace(token)) {

                UserTokenInfo tokenInfo = Users.decryptUserToken(token);

                this.workContext.setOpenId(tokenInfo.getOpenId());

                PartUserInfo userInfo = users.getPartUserByUid(tokenInfo.getUid());

                System.out.println("上下打印当前用户信息" + userInfo.toString());

                if (userInfo != null && userInfo.getUid() >= 1) {

                    this.workContext.setUid(userInfo.getUid());
                    this.workContext.setPartUserInfo(userInfo);
                    this.workContext.setToken(token);
                    this.workContext.setNickName(userInfo.getNickName());
                    this.workContext.setUserRankInfo(userRanks.getUserRankByCredits(userInfo.getPayCredits()));

                    if (this.workContext.getUserRankInfo() != null) {


                        this.workContext.setUserRid(this.workContext.getUserRankInfo().getUserRid());
                        if (!this.workContext.getUserRankInfo().getUserRid().equals(userInfo.getUserRid())) {

                            userInfo.setUserRid(this.workContext.getUserRankInfo().getUserRid());

                            users.updateUserRankByUid(userInfo.getUid(), this.workContext.getUserRid());
                        }

                    }


                }
            }
        } catch (Exception ex) {

            logs.write(ex, "上午文处理报错");
            System.out.println("上下文处理报错:" + ex.toString());
        }

        if (this.workContext.getPartUserInfo() == null || this.workContext.getPartUserInfo().getUid() <= 0) {

            this.workContext.setPartUserInfo(users.getGuestPartUserInfo());

        }

        System.out.println("当前用户信息:" + workContext.getPartUserInfo());


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
