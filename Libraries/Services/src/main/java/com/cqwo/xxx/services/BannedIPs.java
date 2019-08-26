package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.base.BannedIPInfo;
import com.cqwo.xxx.core.domain.base.BannedIPInfo;
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

//禁用IP
@Service(value = "BannedIPs")
public class BannedIPs {


    @Resource(name = "BannedIPsData")
    com.cqwo.xxx.data.BannedIPs bannedIPs;

    @Autowired
    private Logs logs;


    //region  禁用IP方法

    /**
     * 获得禁用IP数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getBannedIPCount(Specification<BannedIPInfo> condition) {

        try {
            return bannedIPs.getBannedIPCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得禁用IP数量失败");
        }
        return 0;
    }

    /**
     * 创建一条禁用IP数据
     *
     * @param bannedipInfo 禁用IP模型
     * @return 返回创建信息
     **/
    public BannedIPInfo createBannedIP(BannedIPInfo bannedipInfo) {
        try {
            return bannedIPs.createBannedIP(bannedipInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条禁用IP数据失败");
        }
        return null;
    }

    /**
     * 更新一条禁用IP数据
     *
     * @param bannedipInfo 禁用IP模型
     **/
    public BannedIPInfo updateBannedIP(BannedIPInfo bannedipInfo) {
        try {
            return bannedIPs.updateBannedIP(bannedipInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条禁用IP数据异常");
        }

        return null;
    }

    /**
     * 删除一条禁用IP数据
     *
     * @param id 禁用IP模型
     **/
    public void deleteBannedIPById(Integer id) {
        try {
            bannedIPs.deleteBannedIPById(id);
        } catch (Exception e) {
            logs.write(e, "删除一条禁用IP数据异常");
        }
    }

    /**
     * 批量删除一批禁用IP数据
     **/
    public void deleteBannedIPByIdList(String idList) {
        try {
            bannedIPs.deleteBannedIPByIdList(idList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批禁用IP数据异常");
        }
    }

    /**
     * 获取一条禁用IP数据
     *
     * @param id 禁用IP模型
     **/
    public BannedIPInfo getBannedIPById(Integer id) {
        try {
            return bannedIPs.getBannedIPById(id);
        } catch (Exception e) {
            logs.write(e, "获取一条禁用IP数据");
        }

        return null;
    }


    /**
     * 获得禁用IP数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回BannedIPInfo
     **/
    public List<BannedIPInfo> getBannedIPList(Specification<BannedIPInfo> condition, Sort sort) {

        List<BannedIPInfo> bannedIPList = new ArrayList<BannedIPInfo>();

        try {
            bannedIPList = bannedIPs.getBannedIPList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得禁用IP数据列表异常");
        }

        return bannedIPList;
    }


    /**
     * 获得禁用IP数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回BannedIPInfo
     **/
    public Page<BannedIPInfo> getBannedIPList(Integer pageSize, Integer pageNumber, Specification<BannedIPInfo> condition, Sort sort) {

        Page<BannedIPInfo> bannedIPList = null;

        try {
            bannedIPList = bannedIPs.getBannedIPList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得禁用IP数据列表异常");
        }

        return bannedIPList;
    }


    //endregion

}
