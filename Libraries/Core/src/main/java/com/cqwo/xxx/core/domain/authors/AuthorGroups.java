package com.cqwo.xxx.core.domain.authors;


import com.cqwo.xxx.core.model.SelectListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限分组
 */
public enum AuthorGroups {

    /**
     * 权限分组
     */
    User(0, true, "user", "用户"),
    Agent(1, false, "agent", "代理商"),
    Admin(2, true, "admin", "管理员"),
    Wechat(3, true, "wechat", "微信(物联英卡)");

    private Integer index;

    private boolean allot;

    private String code;

    private String name;

    AuthorGroups(Integer index, boolean allot, String code, String name) {
        this.index = index;
        this.allot = allot;
        this.code = code;
        this.name = name;
    }

    public static List<String> getRoleIdentifiers() {

        List<String> roleIdentifiers = new ArrayList<>();

        for (AuthorGroups groups : values()) {
            roleIdentifiers.add(groups.getCode());
        }
        return roleIdentifiers;
    }


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isAllot() {
        return allot;
    }

    public void setAllot(boolean allot) {
        this.allot = allot;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(Integer index) {

        for (AuthorGroups groups : values()) {

            if (index.equals(groups.getIndex())) {
                return groups.getName();
            }
        }
        return User.getName();
    }


    public String getName(String code) {

        for (AuthorGroups groups : values()) {

            if (code.equals(groups.getCode())) {
                return groups.getName();
            }
        }
        return User.getName();
    }

    public AuthorGroups getAuthorGroups(Integer index) {


        for (AuthorGroups groups : values()) {

            if (index.equals(groups.getIndex())) {
                return groups;
            }
        }
        return User;
    }


    public AuthorGroups getAuthorGroups(String code) {

        for (AuthorGroups groups : values()) {

            if (code.equals(groups.getCode())) {
                return groups;
            }
        }
        return User;
    }


    @Override
    public String toString() {
        return "AuthorGroups{" +
                "index=" + index +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 获取枚举的列表
     *
     * @return
     */
    public static List<SelectListItem> getSelectListItem() {
        return getSelectListItem(User.getCode());
    }


    /**
     * 获取枚举的列表
     * index 默认选择项目
     *
     * @return
     */
    public static List<SelectListItem> getSelectListItem(String code) {
        List<SelectListItem> selectListItemList = new ArrayList<SelectListItem>();

        for (AuthorGroups groups : values()) {

            if (!groups.isAllot()) {
                continue;
            }

            SelectListItem item = new SelectListItem();

            item.setText(groups.getName());
            item.setValue(groups.getCode());

            if (groups.getCode().equals(code)) {
                item.setSelected(true);
            }

            selectListItemList.add(item);
        }

        return selectListItemList;
    }
}
