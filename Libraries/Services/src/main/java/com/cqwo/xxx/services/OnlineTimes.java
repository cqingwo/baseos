package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.users.OnlineTimeInfo;
import com.cqwo.xxx.core.domain.users.OnlineTimeInfo;
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

//在线时间统计
@Service(value = "OnlineTimes")
public class OnlineTimes {

    @Resource(name = "OnlineTimesData")
    com.cqwo.xxx.data.OnlineTimes onlineTimes;

    @Autowired
    private Logs logs;


    //region  在线时间统计方法

    /**
     * 获得在线时间统计数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getOnlineTimeCount(Specification<OnlineTimeInfo> condition) {

        try {
            return onlineTimes.getOnlineTimeCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得在线时间统计数量失败");
        }
        return 0;
    }

    /**
     * 创建一条在线时间统计数据
     *
     * @param onlinetimeInfo 在线时间统计模型
     * @return 返回创建信息
     **/
    public OnlineTimeInfo createOnlineTime(OnlineTimeInfo onlinetimeInfo) {
        try {
            return onlineTimes.createOnlineTime(onlinetimeInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条在线时间统计数据失败");
        }
        return null;
    }

    /**
     * 更新一条在线时间统计数据
     *
     * @param onlinetimeInfo 在线时间统计模型
     **/
    public OnlineTimeInfo updateOnlineTime(OnlineTimeInfo onlinetimeInfo) {
        try {
            return onlineTimes.updateOnlineTime(onlinetimeInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条在线时间统计数据异常");
        }

        return null;
    }

    /**
     * 删除一条在线时间统计数据
     *
     * @param id 在线时间统计模型
     **/
    public void deleteOnlineTimeById(Integer id) {
        try {
            onlineTimes.deleteOnlineTimeById(id);
        } catch (Exception e) {
            logs.write(e, "删除一条在线时间统计数据异常");
        }
    }

    /**
     * 批量删除一批在线时间统计数据
     **/
    public void deleteOnlineTimeByIdList(String idList) {
        try {
            onlineTimes.deleteOnlineTimeByIdList(idList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批在线时间统计数据异常");
        }
    }

    /**
     * 获取一条在线时间统计数据
     *
     * @param id 在线时间统计模型
     **/
    public OnlineTimeInfo getOnlineTimeById(Integer id) {
        try {
            return onlineTimes.getOnlineTimeById(id);
        } catch (Exception e) {
            logs.write(e, "获取一条在线时间统计数据");
        }

        return null;
    }


    /**
     * 获得在线时间统计数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回OnlineTimeInfo
     **/
    public List<OnlineTimeInfo> getOnlineTimeList(Specification<OnlineTimeInfo> condition, Sort sort) {

        List<OnlineTimeInfo> onlineTimeList = new ArrayList<OnlineTimeInfo>();

        try {
            onlineTimeList = onlineTimes.getOnlineTimeList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得在线时间统计数据列表异常");
        }

        return onlineTimeList;
    }


    /**
     * 获得在线时间统计数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回OnlineTimeInfo
     **/
    public Page<OnlineTimeInfo> getOnlineTimeList(Integer pageSize, Integer pageNumber, Specification<OnlineTimeInfo> condition, Sort sort) {

        Page<OnlineTimeInfo> onlineTimeList = null;

        try {
            onlineTimeList = onlineTimes.getOnlineTimeList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得在线时间统计数据列表异常");
        }

        return onlineTimeList;
    }


    //endregion

}
