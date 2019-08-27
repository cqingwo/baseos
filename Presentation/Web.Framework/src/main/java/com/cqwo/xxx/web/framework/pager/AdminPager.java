/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.web.framework.pager;


import com.cqwo.xxx.core.model.PageModel;

/**
 *
 * @author cqnews
 * @date 2017/12/14
 */
public class AdminPager {

    private PageModel pageModel;

    public AdminPager(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    @Override
    public String toString() {
        return "AdminPager{" +
                "pageModel=" + pageModel +
                '}';
    }
}
