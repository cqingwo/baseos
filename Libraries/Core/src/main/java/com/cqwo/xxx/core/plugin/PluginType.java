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

package com.cqwo.xxx.core.plugin;

import com.cqwo.xxx.core.helper.TypeHelper;
import com.cqwo.xxx.core.model.SelectListItem;
import com.cqwo.xxx.core.helper.TypeHelper;
import com.cqwo.xxx.core.model.SelectListItem;
import com.cqwo.xxx.core.helper.TypeHelper;
import com.cqwo.xxx.core.model.SelectListItem;

import java.util.ArrayList;
import java.util.List;

public enum PluginType {


    OAuthPlugin("OAuthPlugin", 0), PayPlugin("PayPlugin", 1);

    // 成员变量
    private String name;
    private Integer index;

    // 构造方法
    private PluginType(String name, Integer index) {
        this.name = name;
        this.index = index;
    }


    // 普通方法
    public static String getName(Integer index) {
        PluginType[] types = PluginType.values();
        for (PluginType c : types) {
            if (c.getIndex().equals(index)) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    /**
     * 获取枚举的列表
     *
     * @return
     */
    public static List<SelectListItem> getSelectListItem() {
        return getSelectListItem(1);
    }

    /**
     * 获取枚举的列表
     * index 默认选择项目
     *
     * @return
     */
    public static List<SelectListItem> getSelectListItem(Integer index) {
        List<SelectListItem> selectListItemList = new ArrayList<SelectListItem>();

        PluginType[] types = PluginType.values();
        for (PluginType c : types) {

            SelectListItem item = new SelectListItem();

            item.setText(c.getName());
            item.setValue(TypeHelper.intToString(c.getIndex()));

            if (c.getIndex().equals(index)) {
                item.setSelected(true);
            }

            selectListItemList.add(item);
        }

        return selectListItemList;
    }


}
