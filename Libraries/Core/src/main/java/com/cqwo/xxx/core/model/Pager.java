/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.model;

import java.io.Serializable;

/**
 * @author cqnews
 * @date 2017/12/14
 */
public class Pager implements Serializable {


    protected boolean showSummary = true;//是否显示汇总
    protected boolean showItems = true;//是否显示页项
    protected int itemCount = 7;//项数量
    protected boolean showFirst = true;//是否显示首页
    protected boolean showPre = true;//是否显示上一页
    protected boolean showNext = true;//是否显示下一页
    protected boolean showLast = true;//是否显示末页
    protected boolean showPageSize = true;//是否显示每页数
    protected boolean showGoPage = true;//是否显示页数输入框

    public boolean isShowSummary() {
        return showSummary;
    }

    public void setShowSummary(boolean showSummary) {
        this.showSummary = showSummary;
    }

    public boolean isShowItems() {
        return showItems;
    }

    public void setShowItems(boolean showItems) {
        this.showItems = showItems;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowPre() {
        return showPre;
    }

    public void setShowPre(boolean showPre) {
        this.showPre = showPre;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
    }

    public boolean isShowPageSize() {
        return showPageSize;
    }

    public void setShowPageSize(boolean showPageSize) {
        this.showPageSize = showPageSize;
    }

    public boolean isShowGoPage() {
        return showGoPage;
    }

    public void setShowGoPage(boolean showGoPage) {
        this.showGoPage = showGoPage;
    }


}
