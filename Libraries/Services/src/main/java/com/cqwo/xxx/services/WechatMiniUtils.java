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

package com.cqwo.xxx.services;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.core.wechat.CWMWechat;
import com.cqwo.xxx.core.wechat.WeChatSession;
import com.google.common.base.Strings;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;

@Service(value = "WechatMiniUtils")
public class WechatMiniUtils {

    @Autowired
    CWMUtils cwmUtils;

    @Autowired
    CacheUtils cacheUtils;


    @Autowired
    CWMWechat cwmWechat;

    @Autowired
    Logs logs;


    /**
     * 刷新路由,在公众开发里面使用
     */
    private void refreshRouter() {


    }


    /**
     * 获取程序token
     *
     * @return
     */
    public String getAccessToken() {

        String accessToken = "";

        try {

            accessToken = cacheUtils.getValue(CacheKeys.WECHAT_ACCESS_TOKEN, String.class);

            if (Strings.isNullOrEmpty(accessToken)) {

                accessToken = cwmWechat.getIMiniAppStrategy().getAccessToken();
                cacheUtils.setValue(CacheKeys.WECHAT_ACCESS_TOKEN, accessToken);
            }

        } catch (Exception e) {

            logs.write(e, "");

        }

        return accessToken;
    }

    /**
     * 获取用户服务
     *
     * @return
     */
    public WxMaUserService getUserService() {

        WxMaUserService wxMaUserService = null;

        try {

            wxMaUserService = cwmWechat.getIMiniAppStrategy().getUserService();

        } catch (Exception e) {

            logs.write(e, "获取用户服务");

        }

        return wxMaUserService;

    }

    /**
     * 获取二维码服务
     *
     * @return
     */
    public WxMaQrcodeService getQrcodeService() {

        WxMaQrcodeService wxMaUserService = null;

        try {

            wxMaUserService = cwmWechat.getIMiniAppStrategy().getQrcodeService();

        } catch (Exception e) {

            logs.write(e, "获取二维码服务");

        }

        return wxMaUserService;
    }

    /**
     * 通过code获取微信小程序用户session
     *
     * @param code 用户唯一码
     * @return
     */
    public WxMaJscode2SessionResult getSessionInfo(String code) {
        try {
            return getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            logs.write(e, "获取微信session");
        }
        return null;
    }


//    /**
//     * 生成二维码
//     *
//     * @param path  跳转路径
//     * @param width 宽度
//     * @return
//     */
//    public String createWxQrCode(Integer machId, String path, Integer width) {
//
//
//        try {
//
//            File code2 = getQrcodeService().createQrcode(path, width);
//
//            if (code2 != null) {
//                return uploads.saveMachineQcode(machId, code2);
//            }
//
//        } catch (WxErrorException e) {
//            logs.write(e, "生成二维码失败");
//        }
//
//        return "";
//
//    }


    /**
     * 更新微信session
     *
     * @param result
     * @return
     */
    public WeChatSession updateSession(WxMaJscode2SessionResult result) {

        //String sessionid = sessions.generateSid();

        String openId = result.getOpenid();
        String unionId = result.getUnionid();
        String sessionKey = result.getSessionKey();

        WeChatSession sessionBag = new WeChatSession(openId, unionId, sessionKey);

        cacheUtils.setValue(CacheKeys.WE_CHAT_SESSION + openId, sessionBag, 120, MINUTES);

        return sessionBag;

    }

    /**
     * 获取用户session
     *
     * @param openId
     * @return
     */
    public WeChatSession getSession(String openId) {

        WeChatSession wechatSession = cacheUtils.getValue(CacheKeys.WE_CHAT_SESSION + openId, WeChatSession.class);

        if (wechatSession == null) {
            return null;
        }

        return wechatSession;
    }


    /**
     * 发送红包消息
     *
     * @param openId
     * @param money
     * @param desc
     * @param createTime
     * @return
     */
    public boolean sendRedbagMessage(String formId, String openId, double money, String desc, Timestamp createTime) {

        WxMaTemplateMessage templateMessage = new WxMaTemplateMessage();
        templateMessage.setTemplateId("2YeKPhsKwSf-QpzZbPSyYxjI2ImUW6Rkxy7VfWUmF1");
        templateMessage.setFormId(formId);
        templateMessage.setToUser(openId);
        List<WxMaTemplateMessage.Data> datas = new ArrayList<>();

        //入账时间
        WxMaTemplateMessage.Data word1 = new WxMaTemplateMessage.Data("keyword1", createTime.toString());
        //入账金额
        WxMaTemplateMessage.Data word2 = new WxMaTemplateMessage.Data("keyword2", String.valueOf(money));
        //入账说明
        WxMaTemplateMessage.Data word3 = new WxMaTemplateMessage.Data("keyword3", desc);

        datas.add(word1);
        datas.add(word2);
        datas.add(word3);
        templateMessage.setData(datas);


        return sendMessage(templateMessage);

    }

    /**
     * 发送模型消息
     *
     * @return
     */
    public boolean sendMessage(WxMaTemplateMessage templateMessage) {


        try {

            getMsgService().sendTemplateMsg(templateMessage);

        } catch (WxErrorException ex) {
            logs.write(ex, "发送模型消息");
            return false;
        }

        return true;
    }


    /**
     * 获取消息服务
     *
     * @return
     */
    public WxMaMsgService getMsgService() {

        try {

            return cwmWechat.getIMiniAppStrategy().getMsgService();
        } catch (Exception ex) {

        }
        return null;
    }

//    public boolean sendMessage() {
//
//        WxMaTemplateMessage templateMessage = new WxMaTemplateMessage();
//
//
//        try {
//            getMsgService().sendTemplateMsg(templateMessage);
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
//
//    }


}
