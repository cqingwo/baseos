package com.cqwo.xxx.services;


import com.cqwo.xxx.core.domain.authors.AuthorLogInfo;
import com.cqwo.xxx.core.domain.authors.AuthorLogInfo;
import com.cqwo.xxx.core.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */

//管理员日志
@Service(value = "AuthorLogs")
public class AuthorLogs {

    @Resource(name = "AuthorLogsData")
    com.cqwo.xxx.data.AuthorLogs authorLogs;

    @Autowired
    private Logs logs;


    //region  管理员日志方法

    /**
     * 获得管理员日志数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getAuthorLogCount(Specification<AuthorLogInfo> condition) {

        try {
            return authorLogs.getAuthorLogCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得管理员日志数量失败");
        }
        return 0;
    }

    /**
     * 创建一条管理员日志数据
     *
     * @param authorLogInfo 管理员日志模型
     * @return 返回创建信息
     **/
    public AuthorLogInfo createAuthorLog(AuthorLogInfo authorLogInfo) {
        try {
            return authorLogs.createAuthorLog(authorLogInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条管理员日志数据失败");
        }
        return null;
    }

    /**
     * 更新一条管理员日志数据
     *
     * @param authorLogInfo 管理员日志模型
     **/
    public AuthorLogInfo updateAuthorLog(AuthorLogInfo authorLogInfo) {
        try {
            return authorLogs.updateAuthorLog(authorLogInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条管理员日志数据异常");
        }

        return null;
    }

    /**
     * 删除一条管理员日志数据
     *
     * @param logid 管理员日志模型
     **/
    public void deleteAuthorLogByLogid(Integer logid) {
        try {
            authorLogs.deleteAuthorLogByLogid(logid);
        } catch (Exception e) {
            logs.write(e, "删除一条管理员日志数据异常");
        }
    }

    /**
     * 批量删除一批管理员日志数据
     **/
    public void deleteAuthorLogByLogidList(String logidList) {
        try {
            authorLogs.deleteAuthorLogByLogidList(logidList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批管理员日志数据异常");
        }
    }

    /**
     * 获取一条管理员日志数据
     *
     * @param logid 管理员日志模型
     **/
    public AuthorLogInfo getAuthorLogByLogid(Integer logid) {
        try {
            return authorLogs.getAuthorLogByLogid(logid);
        } catch (Exception e) {
            logs.write(e, "获取一条管理员日志数据");
        }

        return null;
    }


    /**
     * 获得管理员日志数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorLogInfo
     **/
    public List<AuthorLogInfo> getAuthorLogList(Specification<AuthorLogInfo> condition, Sort sort) {

        List<AuthorLogInfo> AuthorLogList = new ArrayList<AuthorLogInfo>();

        try {
            AuthorLogList = authorLogs.getAuthorLogList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得管理员日志数据列表异常");
        }

        return AuthorLogList;
    }


    /**
     * 获得管理员日志数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorLogInfo
     **/
    public Page<AuthorLogInfo> getAuthorLogList(Integer pageSize, Integer pageNumber, Specification<AuthorLogInfo> condition, Sort sort) {

        Page<AuthorLogInfo> AuthorLogList = null;

        try {
            AuthorLogList = authorLogs.getAuthorLogList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得管理员日志数据列表异常");
        }

        return AuthorLogList;
    }


    //endregion

}
