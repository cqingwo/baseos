package com.cqwo.xxx.core.model;

public class SimplePageModel {

    /**
     * 每页条数
     */
    private int pageSize = 10;


    /**
     * 当前页数
     */
    private int pageNumber = 1;


    /**
     * 总页数
     */
    private int totalPages = 0;

    public SimplePageModel(int pageSize, int pageNumber, int totalPages) {

        if (pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 10;
        }

        if (pageNumber > 0) {
            this.pageNumber = pageNumber;
        } else {
            this.pageNumber = 1;
        }

        this.totalPages = totalPages;

    }

    public SimplePageModel() {

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "SimplePageModel{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", totalPages=" + totalPages +
                '}';
    }
}
