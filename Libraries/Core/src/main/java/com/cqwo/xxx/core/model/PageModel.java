/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆英卡电子有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.core.model;

import java.text.MessageFormat;

/**
 * Created by cqnews on 2017/12/14.
 */
public class PageModel extends Pager {
    /**
     * 当前页索引
     */
    private int pageIndex;//当前页索引

    /**
     * 当前页数
     */
    private int pageNumber;//当前页数

    /**
     * 上一页数
     */
    private int prePageNumber;//上一页数

    /**
     * 下一页数
     */
    private int nextPageNumber;//下一页数

    /**
     * 每页数
     */
    private int pageSize;//每页数

    /**
     * 总项数
     */
    private long totalCount = 0;

    /**
     * 总页数
     */
    private int totalPages;//总页数

    /**
     * 是否有上一页
     */
    private boolean hasPrePage;//是否有上一页

    /**
     * 是否有下一页
     */
    private boolean hasNextPage;//是否有下一页

    /**
     * 是否是第一页
     */
    private boolean isFirstPage;//是否是第一页

    /**
     * 是否是最后一页
     */
    private boolean isLastPage;//是否是最后一页

    /**
     * 构造方法
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param totalCount 统计
     */
    public PageModel(int pageSize, int pageNumber, long totalCount) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 1;
        }

        if (pageNumber > 0) {
            this.pageNumber = pageNumber;
        } else {
            this.pageNumber = 1;
        }

        if (totalCount > 0) {
            this.totalCount = totalCount;
        } else {
            this.totalCount = 0;
        }

        pageIndex = this.pageNumber - 1;

        this.totalPages = this.totalCount <= 0 ? 1 : (int) Math.ceil((double) this.totalCount / (double) this.pageSize);
        ;

        hasPrePage = this.pageNumber > 1;
        hasNextPage = this.pageNumber < totalPages;

        isFirstPage = this.pageNumber == 1;
        isLastPage = this.pageNumber == totalPages;

        prePageNumber = this.pageNumber < 2 ? 1 : this.pageNumber - 1;
        nextPageNumber = this.pageNumber < totalPages ? this.pageNumber + 1 : totalPages;

    }

    public PageModel(int pageIndex, int pageNumber, int prePageNumber, int nextPageNumber, int pageSize, int totalPages, boolean hasPrePage, boolean hasNextPage, boolean isFirstPage, boolean isLastPage) {
        this.pageIndex = pageIndex;
        this.pageNumber = pageNumber;
        this.prePageNumber = prePageNumber;
        this.nextPageNumber = nextPageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.hasPrePage = hasPrePage;
        this.hasNextPage = hasNextPage;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
    }

    @Override
    public String toString() { //System.out.println(totalPages);

        if (this.totalPages == 0 || this.totalPages < this.pageNumber) {
            return "";
        }

        StringBuilder html = new StringBuilder("<ul id=\"dataPageList\" class=\"pagination\">");
        html.append(MessageFormat.format("<input  type=\"hidden\" id=\"pageSize\" name=\"pageSize\" value=\"{0,number,#}\"/>", this.pageSize));

        if (showSummary) {
            html.append(MessageFormat.format("<li class=\"summary\">当前{0,number,#}/{1,number,#}页&nbsp;共{2,number,#}页</li>", this.pageNumber, this.totalPages, this.totalPages));
        }

        if (showFirst) {
            if (this.isFirstPage) {
                html.append("<li><a href=\"javascript:void(0)\">首页</a></li>");
            } else {
                html.append("<li><a href=\"#\" page=\"1\" class=\"bt\">首页</a></li>");
            }
        }


        if (showPre) {
            if (this.hasPrePage) {
                html.append(MessageFormat.format("<li><a href=\"#\" page=\"{0,number,#}\" class=\"bt\">上一页</a></li>", this.pageNumber - 1));
            } else {
                html.append("<li><a href=\"#\">上一页</a></li>");
            }
        }

        if (showItems) {
            int startPageNumber = getStartPageNumber();
            int endPageNumber = getEndPageNumber();
            for (int i = startPageNumber; i <= endPageNumber; i++) {
                if (this.pageNumber != i) {
                    html.append(MessageFormat.format("<li><a href=\"#\" page=\"{0,number,#}\" class=\"bt\">{0}</a></li>", i));
                } else {
                    html.append(MessageFormat.format("<li class=\"active\"><a href=\"\">{0,number,#}</a></li>", i));
                }
            }
        }

        if (showFirst) {
            if (this.hasNextPage) {
                html.append(MessageFormat.format("<li><a href=\"#\" page=\"{0,number,#}\" class=\"bt\">下一页</a></li>", this.pageNumber + 1));
            } else {
                html.append("<li><a href=\"#\">下一页</a></li>");
            }
        }

        if (showLast) {
            if (this.isLastPage) {
                html.append("<li><a href=\"#\">末页</a></li>");
            } else {
                html.append(MessageFormat.format("<li><a href=\"#\" page=\"{0,number,#}\" class=\"bt\">末页</a></li>", this.totalPages));
            }
        }

        if (showGoPage) {
            html.append(MessageFormat.format("<li>跳转到:<input type=\"text\" value=\"{0,number,#}\" id=\"pageNumber\" totalPages=\"{1,number,#}\" name=\"pageNumber\" size=\"1\"/>页</li>", this.pageNumber, this.totalPages));
        }
        html.append("</ul>");

        return html.toString();
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPrePageNumber() {
        return prePageNumber;
    }

    public void setPrePageNumber(int prePageNumber) {
        this.prePageNumber = prePageNumber;
    }

    public int getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.isLastPage = lastPage;
    }


    /// <summary>
    /// 获得开始页数
    /// </summary>
    /// <returns></returns>
    protected int getStartPageNumber() {
        int mid = itemCount / 2;
        if ((this.totalPages < itemCount) || ((this.pageNumber - mid) < 1)) {
            return 1;
        }
        if ((this.pageNumber + mid) > this.totalPages) {
            return this.totalPages - itemCount + 1;
        }
        return this.pageNumber - mid;
    }

    /// <summary>
    /// 获得结束页数
    /// </summary>
    /// <returns></returns>
    protected int getEndPageNumber() {
        int mid = itemCount / 2;
        if ((itemCount % 2) == 0) {
            mid--;
        }
        if ((this.totalPages < itemCount) || ((this.pageNumber + mid) >= this.totalPages)) {
            return this.totalPages;
        }
        if ((this.pageNumber - (itemCount / 2)) < 1) {
            return itemCount;
        }
        return this.pageNumber + mid;
    }




}
