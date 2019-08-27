package com.cqwo.xxx.web.api.model;

import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.model.PageModel;


import java.util.List;

public class UCenterListModel {

    /**
     * 名称列表
     */
    List<PartUserInfo> userInfoList;

    /**
     * 分页模型
     */
    PageModel pageModel;


    public UCenterListModel(List<PartUserInfo> userInfoList, PageModel pageModel) {
        this.userInfoList = userInfoList;
        this.pageModel = pageModel;
    }

    public List<PartUserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<PartUserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    @Override
    public String toString() {
        return "UCenterListModel{" +
                "userInfoList=" + userInfoList +
                ", pageModel=" + pageModel +
                '}';
    }
}
