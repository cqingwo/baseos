/*
 *
 *  * Copyright (C) 2017.
 *  * 用于JAVA项目开发
 *  * 重庆青沃科技有限公司 版权所有
 *  * Copyright (C)  2017.  CqingWo Systems Incorporated. All rights reserved.
 *
 */

package com.cqwo.xxx.web.framework.controller;

import com.cqwo.xxx.core.config.CWMConfig;
import com.cqwo.xxx.core.helper.DateHelper;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.helper.TypeHelper;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.services.*;
import com.cqwo.xxx.web.framework.PromptModel;
import com.cqwo.xxx.web.framework.view.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


/**
 * @author cqnews
 */
public abstract class BaseController {


    @Autowired
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;


    @Autowired
    protected Regions regions;
    //
    @Autowired
    protected CWMUtils cwmUtils;

    @Autowired
    protected Users users;

    @Autowired
    protected UserRanks userRanks;

    @Autowired
    protected CWMConfig cwmConfig;
//
//    @Autowired
//    protected CWMUpload cwmUpload;


    @Autowired
    protected Logs logs;

    protected String urlReferer = "/";


    protected BaseController() {
        // System.out.println("BaseController");
    }

    abstract Object getWorkContext();

    @ModelAttribute
    public void setBaseInitialize(HttpServletResponse response) throws IOException {
        request.setAttribute("DateHelper", new DateHelper());
    }

    /**
     * 视图封装
     *
     * @return 返回视图
     */
    protected ModelAndView View() {
        // TODO Auto-generated method stub

        ModelAndView view = new ModelAndView();
        view.addObject("WorkContext", getWorkContext());

        return view;
    }

    /**
     * 视图封装
     *
     * @param model model实体类
     * @return 返回视图
     */
    protected ModelAndView View(Object model) {
        // TODO Auto-generated method stub

        ModelAndView view = new ModelAndView();

        view.addObject("Model", model);
        view.addObject("WorkContext", getWorkContext());

        return view;
    }

    /**
     * 视图封装
     *
     * @param viewName 视图名
     * @return 返回视图
     */
    protected ModelAndView View(String viewName) {
        // TODO Auto-generated method stub

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("WorkContext", getWorkContext());

        return view;
    }

    /**
     * 视图封装
     *
     * @param viewName 视图名
     * @param model    model实体类
     * @return 返回视图
     */
    protected ModelAndView View(String viewName, Object model) {

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("Model", model);
        view.addObject("WorkContext", getWorkContext());

        return view;
    }

    /**
     * 视图封装
     *
     * @param modelMap MAP
     * @return
     */
    protected ModelAndView View(Map<String, ?> modelMap) {

        ModelAndView view = new ModelAndView();
        view.addObject("Model", modelMap);
        view.addObject("WorkContext", getWorkContext());

        return view;
    }


    /**
     * 视图封装
     *
     * @param viewName 视图名
     * @param modelMap MAP
     * @return
     */
    protected ModelAndView View(String viewName, Map<String, ?> modelMap) {

        ModelAndView view = new ModelAndView(viewName);

        view.addObject("Model", modelMap);
        view.addObject("WorkContext", getWorkContext());

        return view;
    }

    /**
     * 重定向页面
     *
     * @param url 重写向页面
     * @return
     */
    protected ModelAndView Redirect(String url) {
        ModelAndView mv = new ModelAndView(url);
        return mv;
    }

    /**
     * 提示信息视图
     *
     * @param message 提示信息
     * @return
     */
    protected ModelAndView PromptView(String message) {

        return View("prompt", new PromptModel(message, this.urlReferer));
    }


    /**
     * 提示信息视图
     *
     * @param message 提示信息
     * @return
     */
    protected ModelAndView PromptView(String message, Boolean isAutoBack) {

        return View("prompt", new PromptModel(message, this.urlReferer, isAutoBack));
    }


    /**
     * 提示信息视图
     *
     * @param backUrl 返回地址
     * @param message 提示信息
     * @return
     */
    protected ModelAndView PromptView(String backUrl, String message) {
        return View("prompt", new PromptModel(message, backUrl));
    }

    /**
     * 提示信息视图
     *
     * @param backUrl    返回地址
     * @param message    提示信息
     * @param isAutoBack 是否自动返回
     * @return
     */
    protected ModelAndView PromptView(String backUrl, String message, Boolean isAutoBack) {
        return View("prompt", new PromptModel(message, backUrl, isAutoBack));
    }

    /**
     * 提示信息视图
     *
     * @param backUrl       返回地址
     * @param message       提示信息
     * @param countdowntime 倒计时时间
     * @return
     */
    protected ModelAndView PromptView(String backUrl, String message, Integer countdowntime) {
        return View("prompt", new PromptModel(message, backUrl, countdowntime));
    }

    /**
     * 提示信息视图
     *
     * @param backUrl        返回地址
     * @param message        消息
     * @param countdownmodel 计数模型
     * @param countdowntime  倒计时时间
     * @param isshowbacklink 是否显示返回地址
     * @param isautoback     是否自动返回
     * @return
     */
    protected ModelAndView PromptView(String backUrl, String message, Integer countdownmodel, Integer countdowntime, boolean isshowbacklink, boolean isautoback) {
        return View("prompt", new PromptModel(message, backUrl, countdownmodel, countdowntime, isshowbacklink, isautoback));
    }


    /**
     * 返回JSON View
     *
     * @return
     */
    public String JsonView() {
        return JsonView("英卡电子欢迎您");
    }

    /**
     * 返回JSON View
     *
     * @param message 消息
     * @return
     */
    public String JsonView(String message) {
        return JsonView(-1, message);
    }

    /**
     * 返回JSON View
     *
     * @param state   状态
     * @param message 消息
     * @return
     */
    public String JsonView(Integer state, String message) {
        return JsonView(state, null, message);
    }

//    /**
//     * 返回JsonView
//     *
//     * @param object
//     * @param message
//     * @return
//     */
//    public String JsonView(Object object, String message) {
//        return JsonView(0, object, message);
//    }

    /**
     * 返回JSON View
     *
     * @param state   状态
     * @param content 内容
     * @param message 消息
     * @return
     */
    public String JsonView(Integer state, Object content, String message) {

        return JsonView.view(state, content, message, response);

    }


    /**
     * 获得路由中的值
     *
     * @param key 关键词
     * @return int
     */
    protected Integer getParameterInt(String key) {
        return getParameterInt(key, 0);
    }


    /**
     * 获得路由中的值
     *
     * @param key          关键词
     * @param defaultValue 默认值
     * @return int
     */
    protected Integer getParameterInt(String key, Integer defaultValue) {
        return TypeHelper.stringToInt(request.getParameter(key), defaultValue);
    }


    /**
     * 获得路由中的值
     *
     * @param key 关键词
     * @return int
     */
    protected String getParameterString(String key) {
        return getParameterString(key, "");
    }


    /**
     * 获得路由中的值
     *
     * @param key          关键词
     * @param defaultValue 默认值
     * @return int
     */
    protected String getParameterString(String key, String defaultValue) {
        String str = request.getParameter(key);

        if (StringHelper.isBlank(str)) {
            return defaultValue;
        }
        return str.toString();
    }

    /**
     * 从header中获取值
     *
     * @param key
     * @return
     */
    protected Integer getHeaderInt(String key) {
        return getHeaderInt(key, 0);
    }

    /**
     * 从header中获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    protected Integer getHeaderInt(String key, Integer defaultValue) {

        return TypeHelper.stringToInt(request.getHeader(key), defaultValue);
    }

    /**
     * 获得路由中的值
     *
     * @param key 关键词
     * @return int
     */
    protected String getHeaderString(String key) {
        return getHeaderString(key, "");
    }


    /**
     * 获得路由中的值
     *
     * @param key          关键词
     * @param defaultValue 默认值
     * @return int
     */
    protected String getHeaderString(String key, String defaultValue) {
        String str = request.getHeader(key);

        if (StringHelper.isBlank(str)) {
            return defaultValue;
        }
        return str.toString();
    }

    /**
     * 跳转页面
     *
     * @param url 跳转地址
     */
    protected void sendRedirect(String url) {
        sendRedirect(url, "/");
    }


    /**
     * 跳转页面
     *
     * @param url      跳转地址
     * @param errorUrl 错误地址
     */
    protected void sendRedirect(String url, String errorUrl) {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            try {
                response.sendRedirect(errorUrl);
            } catch (IOException ex) {
                logs.write(ex,"跳转页面");
            }
        }
    }


}
