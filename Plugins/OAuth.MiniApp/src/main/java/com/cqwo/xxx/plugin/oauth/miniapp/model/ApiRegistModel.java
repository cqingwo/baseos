/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.plugin.oauth.miniapp.model;

/**
 * Created by Administrator on 2017/12/25.
 */

import javax.validation.constraints.NotBlank;

/**
 * 用户注册
 */
public class ApiRegistModel {


    /**
     * openId
     */
    @NotBlank(message = "openId不能为空")
    private String openId = "";

    /**
     *  昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName = "";

    /**
     * 性别
     */
    private int gender = 0;

    /**
     * 头像
     */
    private String avatar = "";


    public ApiRegistModel() {
    }

    public ApiRegistModel(String openId, String nickName, int gender, String avatar) {
        this.openId = openId;
        this.nickName = nickName;
        this.gender = gender;
        this.avatar = avatar;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ApiRegistModel{" +
                "openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
