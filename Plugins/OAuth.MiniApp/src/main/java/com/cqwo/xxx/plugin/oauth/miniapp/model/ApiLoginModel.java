/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.plugin.oauth.miniapp.model;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * Created by Administrator on 2017/12/25.
 */
public class ApiLoginModel {


    /**
     * 用户验证code
     */
    @NotBlank(message = "用户验证code不能为空")
    private String code = "";


    /**
     *  昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName = "";

    /**
     * 性别
     */
    @Range(min = 0, max = 2, message = "用户性别获取失败")
    private Integer gender = 0;

    /**
     * 头像
     */
    private String avatar = "";


    public ApiLoginModel() {
    }


    public ApiLoginModel(@NotBlank(message = "用户验证code不能为空") String code, @NotBlank(message = "昵称不能为空") String nickName, @Range(min = 0, max = 2, message = "用户性别获取失败") Integer gender, String avatar) {
        this.code = code;
        this.nickName = nickName;
        this.gender = gender;
        this.avatar = avatar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
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
        return "ApiLoginModel{" +
                "code='" + code + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
