package com.cqwo.xxx.core.domain.authors;

public enum LoginType {


    /**
     * 用户登录
     */
    AdminLogin(0, "AdminLogin"),
    /**
     * Token登录
     */
    ApiLogin(1, "ApiLogin"),
    /**
     * Token登录
     */
    TokenLogin(2, "TokenLogin"),

    /**
     * 开发接口登录
     */
    OauthsLogin(3, "OauthsLogin");

    private Integer index;

    private String name;

    LoginType(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
