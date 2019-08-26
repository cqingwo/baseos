package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.base.RegionInfo;
import com.cqwo.xxx.core.domain.base.RegionInfo;
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

//区域信息
@Service(value = "Regions")
public class Regions {
    @Resource(name = "RegionsData")
    com.cqwo.xxx.data.Regions regions;

    @Autowired
    private Logs logs;


    //region  区域信息方法

    /**
     * 获得区域信息数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getRegionCount(Specification<RegionInfo> condition) {

        try {
            return regions.getRegionCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得区域信息数量失败");
        }
        return 0;
    }

    /**
     * 创建一条区域信息数据
     *
     * @param regionInfo 区域信息模型
     * @return 返回创建信息
     **/
    public RegionInfo createRegion(RegionInfo regionInfo) {
        try {
            return regions.createRegion(regionInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条区域信息数据失败");
        }
        return null;
    }

    /**
     * 更新一条区域信息数据
     *
     * @param regionInfo 区域信息模型
     **/
    public RegionInfo updateRegion(RegionInfo regionInfo) {
        try {
            return regions.updateRegion(regionInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条区域信息数据异常");
        }

        return null;
    }

    /**
     * 删除一条区域信息数据
     *
     * @param regionid 区域信息模型
     **/
    public void deleteRegionByRegionid(Integer regionid) {
        try {
            regions.deleteRegionByRegionid(regionid);
        } catch (Exception e) {
            logs.write(e, "删除一条区域信息数据异常");
        }
    }

    /**
     * 批量删除一批区域信息数据
     **/
    public void deleteRegionByRegionidList(String regionidList) {
        try {
            regions.deleteRegionByRegionidList(regionidList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批区域信息数据异常");
        }
    }

    /**
     * 获取一条区域信息数据
     *
     * @param regionid 区域信息模型
     **/
    public RegionInfo getRegionByRegionid(Integer regionid) {
        try {
            return regions.getRegionByRegionid(regionid);
        } catch (Exception e) {
            logs.write(e, "获取一条区域信息数据");
        }

        return null;
    }


    /**
     * 获得区域信息数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回RegionInfo
     **/
    public List<RegionInfo> getRegionList(Specification<RegionInfo> condition, Sort sort) {

        List<RegionInfo> regionList = new ArrayList<RegionInfo>();

        try {
            regionList = regions.getRegionList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得区域信息数据列表异常");
        }

        return regionList;
    }


    /**
     * 获得区域信息数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回RegionInfo
     **/
    public Page<RegionInfo> getRegionList(Integer pageSize, Integer pageNumber, Specification<RegionInfo> condition, Sort sort) {

        Page<RegionInfo> regionList = null;

        try {
            regionList = regions.getRegionList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得区域信息数据列表异常");
        }

        return regionList;
    }


    //endregion

}
