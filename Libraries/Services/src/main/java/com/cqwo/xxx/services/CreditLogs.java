package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.users.CreditLogInfo;
import com.cqwo.xxx.core.domain.users.CreditLogInfo;
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

//积分日志
@Service(value = "CreditLogs")
public class CreditLogs {


    @Resource(name = "CreditLogsData")
    com.cqwo.xxx.data.CreditLogs creditLogs;

    @Autowired
    private Logs logs;


    //region  积分日志方法

    /**
     * 获得积分日志数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getCreditLogCount(Specification<CreditLogInfo> condition) {

        try {
            return creditLogs.getCreditLogCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得积分日志数量失败");
        }
        return 0;
    }

    /**
     * 创建一条积分日志数据
     *
     * @param creditlogInfo 积分日志模型
     * @return 返回创建信息
     **/
    public CreditLogInfo createCreditLog(CreditLogInfo creditlogInfo) {
        try {
            return creditLogs.createCreditLog(creditlogInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条积分日志数据失败");
        }
        return null;
    }

    /**
     * 更新一条积分日志数据
     *
     * @param creditlogInfo 积分日志模型
     **/
    public CreditLogInfo updateCreditLog(CreditLogInfo creditlogInfo) {
        try {
            return creditLogs.updateCreditLog(creditlogInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条积分日志数据异常");
        }

        return null;
    }

    /**
     * 删除一条积分日志数据
     *
     * @param logid 积分日志模型
     **/
    public void deleteCreditLogByLogid(Integer logid) {
        try {
            creditLogs.deleteCreditLogByLogid(logid);
        } catch (Exception e) {
            logs.write(e, "删除一条积分日志数据异常");
        }
    }

    /**
     * 批量删除一批积分日志数据
     **/
    public void deleteCreditLogByLogidList(String logidList) {
        try {
            creditLogs.deleteCreditLogByLogidList(logidList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批积分日志数据异常");
        }
    }

    /**
     * 获取一条积分日志数据
     *
     * @param logid 积分日志模型
     **/
    public CreditLogInfo getCreditLogByLogid(Integer logid) {
        try {
            return creditLogs.getCreditLogByLogid(logid);
        } catch (Exception e) {
            logs.write(e, "获取一条积分日志数据");
        }

        return null;
    }


    /**
     * 获得积分日志数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回CreditLogInfo
     **/
    public List<CreditLogInfo> getCreditLogList(Specification<CreditLogInfo> condition, Sort sort) {

        List<CreditLogInfo> creditLogList = new ArrayList<CreditLogInfo>();

        try {
            creditLogList = creditLogs.getCreditLogList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得积分日志数据列表异常");
        }

        return creditLogList;
    }


    /**
     * 获得积分日志数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回CreditLogInfo
     **/
    public Page<CreditLogInfo> getCreditLogList(Integer pageSize, Integer pageNumber, Specification<CreditLogInfo> condition, Sort sort) {

        Page<CreditLogInfo> creditLogList = null;

        try {
            creditLogList = creditLogs.getCreditLogList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得积分日志数据列表异常");
        }

        return creditLogList;
    }


    //endregion

}
