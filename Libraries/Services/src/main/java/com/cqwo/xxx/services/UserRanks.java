package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.users.UserRankInfo;
import com.cqwo.xxx.core.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */

//用户等级
@Service(value = "UserRanks")
public class UserRanks {
    @Resource(name = "UserRanksData")
    com.cqwo.xxx.data.UserRanks userRanks;

    @Autowired
    private Logs logs;


    //region  用户等级方法

    /**
     * 获得用户等级数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getUserRankCount(Specification<UserRankInfo> condition) {

        try {
            return userRanks.getUserRankCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得用户等级数量失败");
        }
        return 0;
    }

    /**
     * 创建一条用户等级数据
     *
     * @param userrankInfo 用户等级模型
     * @return 返回创建信息
     **/
    public UserRankInfo createUserRank(UserRankInfo userrankInfo) {
        try {
            return userRanks.createUserRank(userrankInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条用户等级数据失败");
        }
        return null;
    }

    /**
     * 更新一条用户等级数据
     *
     * @param userrankInfo 用户等级模型
     **/
    public UserRankInfo updateUserRank(UserRankInfo userrankInfo) {
        try {
            return userRanks.updateUserRank(userrankInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条用户等级数据异常");
        }

        return null;
    }

    /**
     * 删除一条用户等级数据
     *
     * @param userRid 用户等级模型
     **/
    public void deleteUserRankByUserRid(Integer userRid) {
        try {
            userRanks.deleteUserRankByUserRid(userRid);
        } catch (Exception e) {
            logs.write(e, "删除一条用户等级数据异常");
        }
    }

    /**
     * 批量删除一批用户等级数据
     **/
    public void deleteUserRankByUserRidList(String userRidList) {
        try {
            userRanks.deleteUserRankByUserRidList(userRidList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批用户等级数据异常");
        }
    }

    /**
     * 获取一条用户等级数据
     *
     * @param userRid 用户等级模型
     **/
    public UserRankInfo getUserRankByUserRid(Integer userRid) {
        try {
            return userRanks.getUserRankByUserRid(userRid);
        } catch (Exception ex) {
            logs.write(ex, "获取一条用户等级数据");
        }

        return new UserRankInfo();
    }


    /**
     * 获得用户等级数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回UserRankInfo
     **/
    public List<UserRankInfo> getUserRankList(Specification<UserRankInfo> condition, Sort sort) {

        List<UserRankInfo> userRankList = new ArrayList<UserRankInfo>();

        try {
            userRankList = userRanks.getUserRankList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得用户等级数据列表异常");
        }

        return userRankList;
    }


    /**
     * 获取全部的分类列表
     * @return
     */
    public List<UserRankInfo> getUserRankAllList(){
        List<UserRankInfo> userRankList = new ArrayList<UserRankInfo>();

        try {
            userRankList = userRanks.getUserRankAllList();
        } catch (Exception e) {
            logs.write(e, "获得用户等级数据列表异常");
        }

        return userRankList;
    }


    /**
     * 获得用户等级数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回UserRankInfo
     **/
    public Page<UserRankInfo> getUserRankList(Integer pageSize, Integer pageNumber, Specification<UserRankInfo> condition, Sort sort) {

        Page<UserRankInfo> userRankList = null;

        try {
            userRankList = userRanks.getUserRankList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得用户等级数据列表异常");
        }

        return userRankList;
    }


    /**
     * 获取用户的列表
     *
     * @return
     */
    public UserRankInfo getLowestUserRank() {

        List<UserRankInfo> list = getAllUserRankList();
        for (UserRankInfo info : list) {

            if (info.getSystem() == 0 && info.getCreditsLower() == 0) {
                return info;
            }
        }

        return getDefaultUserRank();
    }

    /**
     * 获取当前积分所属的用户组
     *
     * @param credits
     * @return
     */
    public UserRankInfo getUserRankByCredits(Integer credits) {

        List<UserRankInfo> list = getAllUserRankList();
        for (UserRankInfo item : list) {
            if (item.getSystem() == 0 && item.getCreditsLower() <= credits && (item.getCreditsUpper() > credits || item.getCreditsUpper() == -1)) {
                return item;
            }
        }
        return getDefaultUserRank();
    }


    /**
     * 默认用户组,轮空用
     *
     * @return
     */
    public UserRankInfo getDefaultUserRank() {
        return new UserRankInfo(6, 1, "游客", "", 0, 0, 0);
    }


    /**
     * 获取全部用户组
     *
     * @return
     */
    public List<UserRankInfo> getAllUserRankList() {

        List<UserRankInfo> userRankList = new ArrayList<UserRankInfo>();

        try {
            userRankList = userRanks.getAllUserRankList();
        } catch (Exception ex) {

            logs.write(ex, "获取全部用户组");
        }

        return userRankList;
    }


    //endregion

}
