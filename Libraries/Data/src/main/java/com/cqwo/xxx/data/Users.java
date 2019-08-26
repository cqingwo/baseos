package com.cqwo.xxx.data;

import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */


//用户信息
@Service(value = "UsersData")
public class Users extends DataService {


    //region  用户信息方法

    /**
     * 获得用户信息数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getUserCount(Specification<PartUserInfo> condition) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserCount(condition);
    }

    /**
     * 创建一条用户信息数据
     *
     * @param userInfo 用户信息模型
     * @return 返回创建信息
     **/
    public PartUserInfo createUser(PartUserInfo userInfo) throws IOException {
        return getCwmData().getIUserStrategy().createPartUser(userInfo);
    }

    /**
     * 更新一条用户信息数据
     *
     * @param userInfo 用户信息模型
     **/
    public PartUserInfo updateUser(PartUserInfo userInfo) throws IOException {
        return getCwmData().getIUserStrategy().updatePartUser(userInfo);
    }

    /**
     * 删除一条用户信息数据
     *
     * @param uid 用户信息模型
     **/
    public void deleteUserByUid(Integer uid) throws IOException {
        getCwmData().getIUserStrategy().deleteUserByUid(uid);
    }

    /**
     * 批量删除一批用户信息数据
     **/
    public void deleteUserByUidList(String uidList) throws IOException {
        getCwmData().getIUserStrategy().deleteUserByUidList(uidList);
    }

    /**
     * 获取一条用户信息数据
     *
     * @param uid 用户信息模型
     **/
    public PartUserInfo getPartUserByUid(Integer uid) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserByUid(uid);
    }


    /**
     * 获得用户信息数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回UserInfo
     **/
    public List<PartUserInfo> getPartUserList(Specification<PartUserInfo> condition, Sort sort) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserList(condition, sort);
    }


    /**
     * 获得用户信息数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回UserInfo
     **/
    public Page<PartUserInfo> getPartUserList(Integer pageSize, Integer pageNumber, Specification<PartUserInfo> condition, Sort sort) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserList(pageSize, pageNumber, condition, sort);
    }


    /**
     * 通过用户名获取用户信息
     *
     * @param userName
     * @return
     */
    public PartUserInfo getPartUserByUserName(String userName) throws IOException {

        PartUserInfo userInfo = getCwmCache().getValue(CacheKeys.GET_PARTUSER_USERNAME + userName, PartUserInfo.class);



        if (userInfo == null) {

            userInfo = getCwmData().getIUserStrategy().getPartUserByUserName(userName);

            getCwmCache().setValue(CacheKeys.GET_PARTUSER_USERNAME + userName, userInfo);

        }

        return userInfo;
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param email
     * @return
     */
    public PartUserInfo getPartUserByEmail(String email) throws IOException {

        return getCwmData().getIUserStrategy().getPartUserByEmail(email);
    }

    /**
     * 通过用户手机获取用户信息
     *
     * @param mobile
     * @return
     * @throws IOException
     */
    public PartUserInfo getPartUserByMobile(String mobile) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserByMobile(mobile);
    }


    /**
     * 通过topenId获取用户信息
     *
     * @param openId
     * @return
     */
    public PartUserInfo getPartUserByOpenId(String openId) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserByOpenId(openId);
    }


    /**
     * 通过unionId获取用户信息
     *
     * @param unionId
     * @return
     */
    public PartUserInfo getPartUserByUnionId(String unionId) throws IOException {
        return getCwmData().getIUserStrategy().getPartUserByUnionId(unionId);
    }


    /**
     * 更新用户组
     *
     * @param uid     用户uid
     * @param userRid 用户分组
     */
    public void updateUserRankByUid(Integer uid, Integer userRid) throws IOException {
        getCwmData().getIUserStrategy().updateUserRankByUid(uid, userRid);
    }

    /**
     * 更新手机
     *
     * @param uid
     * @param mobile
     */
    public void updateUserMobile(Integer uid, String mobile) throws IOException {
        getCwmData().getIUserStrategy().updateUserMobile(uid, mobile);
    }


    /**
     * 获取用户头像
     *
     * @param uid
     * @return
     */
    public String getUserAvatar(Integer uid) throws IOException {
        return getCwmData().getIUserStrategy().getUserAvatar(uid);
    }

    //endregion

}
