package com.cqwo.xxx.web.admin.model;

import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.model.PageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserListModel {


    /**
     * 用户uid
     */
    private Integer uid = 0;

    /**
     * 用户昵称
     */
    private String nickName = "";

    /**
     * 手机号码
     */
    private String mobile = "";

    /**
     * 用户列表
     */
    private List<PartUserInfo> userInfoList;

    /**
     * 分页模型
     */
    private PageModel pageModel;


    public UserListModel(List<PartUserInfo> userInfoList, PageModel pageModel) {
        this.userInfoList = userInfoList;
        this.pageModel = pageModel;
    }

    public UserListModel(Integer uid, String nickName, String mobile) {
        this.uid = uid;
        this.nickName = nickName;
        this.mobile = mobile;
    }


}
