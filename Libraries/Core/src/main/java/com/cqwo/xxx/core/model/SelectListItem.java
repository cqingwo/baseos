/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cqnews
 * @date 2017/12/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SelectListItem implements Serializable {

    private static final long serialVersionUID = -6865864571258634525L;

    /**
     * 如果选定此项，则为 true；否则为 false。
     */
    @Builder.Default
    public boolean selected = false;

    /**
     * 文本
     */
    @Builder.Default
    public String text = "";

    /**
     * 值
     */
    @Builder.Default
    public String value = "";

    /**
     * 颜色
     */
    @Builder.Default
    public String color = "";


    public SelectListItem(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public SelectListItem(boolean selected, String text, String value) {
        this.selected = selected;
        this.text = text;
        this.value = value;
    }

}


