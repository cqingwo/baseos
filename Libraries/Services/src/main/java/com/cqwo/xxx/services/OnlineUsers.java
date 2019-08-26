package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.users.OnlineUserInfo;
import com.cqwo.xxx.core.domain.users.OnlineUserInfo;
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

//在线用户
@Service(value = "OnlineUsers")
public class OnlineUsers {
    @Resource(name = "OnlineUsersData")
    com.cqwo.xxx.data.OnlineUsers onlineUsers;

    @Autowired
    private Logs logs;


    //region  在线用户方法

    /**
     * 获得在线用户数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getOnlineUserCount(Specification<OnlineUserInfo> condition) {

        try {
            return onlineUsers.getOnlineUserCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得在线用户数量失败");
        }
        return 0;
    }

    /**
     * 创建一条在线用户数据
     *
     * @param onlineuserInfo 在线用户模型
     * @return 返回创建信息
     **/
    public OnlineUserInfo createOnlineUser(OnlineUserInfo onlineuserInfo) {
        try {
            return onlineUsers.createOnlineUser(onlineuserInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条在线用户数据失败");
        }
        return null;
    }

    /**
     * 更新一条在线用户数据
     *
     * @param onlineuserInfo 在线用户模型
     **/
    public OnlineUserInfo updateOnlineUser(OnlineUserInfo onlineuserInfo) {
        try {
            return onlineUsers.updateOnlineUser(onlineuserInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条在线用户数据异常");
        }

        return null;
    }

    /**
     * 删除一条在线用户数据
     *
     * @param olid 在线用户模型
     **/
    public void deleteOnlineUserByOlid(Integer olid) {
        try {
            onlineUsers.deleteOnlineUserByOlid(olid);
        } catch (Exception e) {
            logs.write(e, "删除一条在线用户数据异常");
        }
    }

    /**
     * 批量删除一批在线用户数据
     **/
    public void deleteOnlineUserByOlidList(String olidList) {
        try {
            onlineUsers.deleteOnlineUserByOlidList(olidList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批在线用户数据异常");
        }
    }

    /**
     * 获取一条在线用户数据
     *
     * @param olid 在线用户模型
     **/
    public OnlineUserInfo getOnlineUserByOlid(Integer olid) {
        try {
            return onlineUsers.getOnlineUserByOlid(olid);
        } catch (Exception e) {
            logs.write(e, "获取一条在线用户数据");
        }

        return null;
    }


    /**
     * 获得在线用户数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回OnlineUserInfo
     **/
    public List<OnlineUserInfo> getOnlineUserList(Specification<OnlineUserInfo> condition, Sort sort) {

        List<OnlineUserInfo> onlineUserList = new ArrayList<OnlineUserInfo>();

        try {
            onlineUserList = onlineUsers.getOnlineUserList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得在线用户数据列表异常");
        }

        return onlineUserList;
    }


    /**
     * 获得在线用户数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回OnlineUserInfo
     **/
    public Page<OnlineUserInfo> getOnlineUserList(Integer pageSize, Integer pageNumber, Specification<OnlineUserInfo> condition, Sort sort) {

        Page<OnlineUserInfo> onlineUserList = null;

        try {
            onlineUserList = onlineUsers.getOnlineUserList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得在线用户数据列表异常");
        }

        return onlineUserList;
    }


    //endregion

}
