package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.users.LoginFailLogInfo;
import com.cqwo.xxx.core.helper.WebHelper;
import com.cqwo.xxx.core.domain.users.LoginFailLogInfo;
import com.cqwo.xxx.core.helper.WebHelper;
import com.cqwo.xxx.core.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */

//登录日志
@Service(value = "LoginFailLogs")
public class LoginFailLogs {


    @Resource(name = "LoginFailLogsData")
    com.cqwo.xxx.data.LoginFailLogs loginFailLogs;

    @Autowired
    private Logs logs;


    //region  登录日志方法

    /**
     * 获得登录日志数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getLoginFailLogCount(Specification<LoginFailLogInfo> condition) {

        try {
            return loginFailLogs.getLoginFailLogCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得登录日志数量失败");
        }
        return 0;
    }

    /**
     * 创建一条登录日志数据
     *
     * @param loginfaillogInfo 登录日志模型
     * @return 返回创建信息
     **/
    public LoginFailLogInfo createLoginFailLog(LoginFailLogInfo loginfaillogInfo) {
        try {
            return loginFailLogs.createLoginFailLog(loginfaillogInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条登录日志数据失败");
        }
        return null;
    }

    /**
     * 更新一条登录日志数据
     *
     * @param loginfaillogInfo 登录日志模型
     **/
    public LoginFailLogInfo updateLoginFailLog(LoginFailLogInfo loginfaillogInfo) {
        try {
            return loginFailLogs.updateLoginFailLog(loginfaillogInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条登录日志数据异常");
        }

        return null;
    }

    /**
     * 删除一条登录日志数据
     *
     * @param id 登录日志模型
     **/
    public void deleteLoginFailLogById(Integer id) {
        try {
            loginFailLogs.deleteLoginFailLogById(id);
        } catch (Exception e) {
            logs.write(e, "删除一条登录日志数据异常");
        }
    }

    /**
     * 批量删除一批登录日志数据
     **/
    public void deleteLoginFailLogByIdList(String idList) {
        try {
            loginFailLogs.deleteLoginFailLogByIdList(idList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批登录日志数据异常");
        }
    }

    /**
     * 获取一条登录日志数据
     *
     * @param id 登录日志模型
     **/
    public LoginFailLogInfo getLoginFailLogById(Integer id) {
        try {
            return loginFailLogs.getLoginFailLogById(id);
        } catch (Exception e) {
            logs.write(e, "获取一条登录日志数据");
        }

        return null;
    }


    /**
     * 获得登录日志数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回LoginFailLogInfo
     **/
    public List<LoginFailLogInfo> getLoginFailLogList(Specification<LoginFailLogInfo> condition, Sort sort) {

        List<LoginFailLogInfo> loginFailLogList = new ArrayList<LoginFailLogInfo>();

        try {
            loginFailLogList = loginFailLogs.getLoginFailLogList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得登录日志数据列表异常");
        }

        return loginFailLogList;
    }


    /**
     * 获得登录日志数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回LoginFailLogInfo
     **/
    public Page<LoginFailLogInfo> getLoginFailLogList(Integer pageSize, Integer pageNumber, Specification<LoginFailLogInfo> condition, Sort sort) {

        Page<LoginFailLogInfo> loginFailLogList = null;

        try {
            loginFailLogList = loginFailLogs.getLoginFailLogList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得登录日志数据列表异常");
        }

        return loginFailLogList;
    }


    /**
     * 添加登录次数
     *
     * @param ip
     * @param timestamp
     */
    public void addLoginFailTimes(String ip, Timestamp timestamp) {
    }


    /**
     * 删除登录ip地址
     *
     * @param ip
     */
    public void deleteLoginFailLogByIP(String ip) {

        try {
            Integer longIP = WebHelper.ipToInteger(ip);

            loginFailLogs.deleteLoginFailLogByIP(longIP);
        } catch (Exception ex) {

        }


    }


    //endregion

}
