package com.cqwo.xxx.services;

import com.alibaba.fastjson.JSON;
import com.cqwo.xxx.core.domain.users.OauthInfo;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.domain.users.UserDetailInfo;
import com.cqwo.xxx.core.domain.users.UserTokenInfo;
import com.cqwo.xxx.core.helper.*;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.data.UserDetails;
import com.cqwo.xxx.core.helper.DateHelper;
import com.cqwo.xxx.core.helper.RandomHelper;
import com.cqwo.xxx.core.helper.SecureHelper;
import com.cqwo.xxx.core.helper.StringHelper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//用户
@Service(value = "Users")
public class Users {

    @Autowired
    com.cqwo.xxx.data.Users users;


    @Resource(name = "UserDetailsData")
    UserDetails userDetails;

    @Autowired
    Logs logs;

    @Autowired
    private UserRanks userRanks;

    @Autowired
    private Oauths oauths;


    //region  用户信息方法

    /**
     * 获得用户信息数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getUserCount(Specification<PartUserInfo> condition) {

        try {
            return users.getUserCount(condition);
        } catch (IOException e) {
            logs.write(e, "获得用户信息数量失败");
        }
        return 0;
    }

    /**
     * 创建一条用户信息数据
     *
     * @param userInfo 用户信息模型
     * @return 返回创建信息
     **/
    public PartUserInfo createUser(PartUserInfo userInfo) {
        try {
            return users.createUser(userInfo);
        } catch (IOException e) {
            logs.write(e, "创建一条用户信息数据失败");
        }
        return null;
    }

    /**
     * 更新一条用户信息数据
     *
     * @param userInfo 用户信息模型
     **/
    public PartUserInfo updateUser(PartUserInfo userInfo) {
        try {
            return users.updateUser(userInfo);
        } catch (IOException e) {
            logs.write(e, "更新一条用户信息数据异常");
        }

        return null;
    }

    /**
     * 删除一条用户信息数据
     *
     * @param uid 用户信息模型
     **/
    public void deleteUserByUid(int uid) {
        try {
            users.deleteUserByUid(uid);
        } catch (IOException e) {
            logs.write(e, "删除一条用户信息数据异常");
        }
    }

    /**
     * 批量删除一批用户信息数据
     **/
    public void deleteUserByUidList(String uidList) {
        try {
            users.deleteUserByUidList(uidList);
        } catch (IOException e) {
            logs.write(e, "批量删除一批用户信息数据异常");
        }
    }

    /**
     * 获取一条用户信息数据
     *
     * @param uid 用户信息模型
     **/
    public PartUserInfo getUserByUid(int uid) {
        try {
            return users.getPartUserByUid(uid);
        } catch (IOException e) {
            logs.write(e, "获取一条用户信息数据");
        }

        return null;
    }


    /**
     * 获得用户信息数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回UserInfo
     **/
    public List<PartUserInfo> getUserList(Specification<PartUserInfo> condition, Sort sort) {

        List<PartUserInfo> userList = new ArrayList<PartUserInfo>();

        try {
            userList = users.getPartUserList(condition, sort);
        } catch (IOException e) {
            logs.write(e, "获得用户信息数据列表异常");
        }

        return userList;
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
    public Page<PartUserInfo> getUserList(Integer pageSize, Integer pageNumber, Specification<PartUserInfo> condition, Sort sort) {

        Page<PartUserInfo> userList = null;

        try {
            userList = users.getPartUserList(pageSize, pageNumber, condition, sort);
        } catch (IOException e) {
            logs.write(e, "获得用户信息数据列表异常");
        }

        return userList;
    }


    //endregion

    /**
     * 获取一条用户数据
     *
     * @param uid 用户模型
     **/
    public PartUserInfo getPartUserByUid(Integer uid) {


        try {
            if (uid <= 0) {
                return null;
            }

            return users.getPartUserByUid(uid);
        } catch (Exception ex) {
            logs.write(ex, "获取一条用户数据");
        }
        return null;
    }

    /**
     * 创建基本用户信息
     *
     * @param userInfo
     * @return
     */
    public PartUserInfo createPartUser(PartUserInfo userInfo) throws IOException {
        return users.createUser(userInfo);


    }


    /**
     * 获得用户数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @return 返回UserInfo
     **/
    public Page<PartUserInfo> getPartUserList(Integer pageSize, Integer pageNumber, Specification<PartUserInfo> condition, Sort sort) {


        try {

            return users.getPartUserList(pageSize, pageNumber, condition, sort);

        } catch (Exception ex) {

            logs.write(ex, "获取用户数据列表");
        }

        return null;

    }


    /**
     * 通过用户名获取用户信息
     *
     * @param userName
     * @return
     */
    public PartUserInfo getPartUserByUserName(String userName) {

        try {
            return users.getPartUserByUserName(userName);

        } catch (Exception ex) {
            logs.write(ex, "通过用户名获取用户信息");
        }
        return null;
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param email
     * @return
     */
    public PartUserInfo getPartUserByEmail(String email) {
        try {

            return users.getPartUserByEmail(email);

        } catch (Exception ex) {
            logs.write(ex, "通过用户名获取用户信息");
        }
        return null;
    }

    /**
     * 通过用户手机获取用户信息
     *
     * @param mobile
     * @return
     */
    public PartUserInfo getPartUserByMobile(String mobile) {
        try {

            return users.getPartUserByMobile(mobile);

        } catch (Exception ex) {
            logs.write(ex, "通过用户名获取用户信息");
        }
        return null;
    }


    /**
     * 生成盐值
     *
     * @return
     */
    public String createSalt() {
        return RandomHelper.generateString(6);
    }


    /**
     * 创建用户密码
     *
     * @param password 真实密码
     * @param salt     散列盐值
     * @return
     */
    public static String createUserPassword(String password, String salt) {

        System.out.println("password:" + password);
        System.out.println("salt:" + salt);
        System.out.println("md5:" + SecureHelper.md5(password + salt));
        return SecureHelper.md5(password + salt);
    }

    /**
     * 创建用户Token
     *
     * @param openId openid
     * @param salt   散列盐值
     * @return
     */
    public static String createUserToken(String openId, String salt) {
        return SecureHelper.md5(openId + salt);
    }


    /**
     * 更新用户组
     *
     * @param uid     用户uid
     * @param userRid 用户分组
     */
    public void updateUserRankByUid(Integer uid, Integer userRid) {

        try {
            users.updateUserRankByUid(uid, userRid);
        } catch (Exception ex) {
            logs.write(ex, "更新用户组失败");
        }
    }


    /**
     * 更新手机
     *
     * @param uid
     * @param mobile
     */
    public void updateUserMobile(Integer uid, String mobile) {

        try {


            users.updateUserMobile(uid, mobile);


        } catch (Exception ex) {
            logs.write(ex, "更新用户手机失败");
        }

    }

    /**
     * 通过用户openid和联合id查询用户信息
     *
     * @param openId
     * @param unionId
     * @return
     */
    public PartUserInfo getPartUserByWechatOpenId(String openId, String unionId) {

        PartUserInfo userInfo = null;


        try {
            userInfo = users.getPartUserByOpenId(openId);

            if (userInfo == null && StringHelper.isNotNullOrWhiteSpace(unionId)) {
                userInfo = users.getPartUserByUnionId(unionId);
            }


        } catch (IOException ex) {
            logs.write(ex, "通过用户openid和联合id查询用户信息");
        }


        return userInfo;
    }

    public PartUserInfo getGuestPartUserInfo() {
        return new PartUserInfo();
    }

    /**
     * 创过token用户
     *
     * @param uid
     * @return
     */
    public static String creatUserToken(Integer uid, String openId) {

        String token = null;

        try {

            UserTokenInfo userToken = new UserTokenInfo(uid, openId);
            String content = JSON.toJSONString(userToken);
            token = AESHelper.encode(content);

        } catch (Exception ex) {

            //logs.write("用户创建token失败");

        }

        return token;
    }

    /**
     * 用户解密
     *
     * @param token
     * @return
     */
    public static UserTokenInfo decryptUserToken(String token) {

        UserTokenInfo tokenInfo = null;

        if (Strings.isNullOrEmpty(token)) {
            return null;
        }

        try {
            String postStr = AESHelper.decode(token);
            tokenInfo = JSON.parseObject(postStr, UserTokenInfo.class);

        } catch (Exception ex) {

            //logs.write(ex, "用户解密失败");
        }

        return tokenInfo;

    }

    /**
     * 安全处理用户信息
     *
     * @param userInfo
     * @return
     */
    public PartUserInfo securityUserInfo(PartUserInfo userInfo) {

        if (userInfo == null) {
            return null;
        }

        userInfo.setPassword("*****");
        userInfo.setMobile(StringHelper.replaceWithSpecialChar(userInfo.getMobile()));
        userInfo.setSalt("******");

        return userInfo;
    }


    /**
     * 检验签名
     *
     * @param openId    openId
     * @param rawData   校验串
     * @param signature 签名
     * @return
     */
    public boolean checkSignature(String openId, String rawData, String signature) {

        if (StringHelper.IsNullOrEmpty(openId)) {
            return false;
        }

        if (StringHelper.IsNullOrEmpty(rawData)) {
            return false;
        }


        String s = SecureHelper.sha1(rawData + openId);

        if (signature.equals(s)) {
            return true;
        }


        return false;
    }

    /**
     * /**
     * 用户第三方登录
     *
     * @param nickName 用户昵称
     * @param avatar   头像
     * @param gender   性别
     * @return
     */
    public PartUserInfo createOauthUserInfo(String server, String openId, String unionId, String nickName, String avatar, Integer gender) {
        PartUserInfo userInfo = new PartUserInfo();

        Integer nowts = UnixTimeHelper.getUnixTimeStamp();

        userInfo.setSalt(createSalt());
        userInfo.setPassword(createUserPassword(userInfo.getSalt(), userInfo.getSalt()));
        userInfo.setUserRid(userRanks.getLowestUserRank().getUserRid());


        userInfo.setNickName(nickName);
        userInfo.setAvatar(avatar);

        userInfo.setRegionId(500233);
        userInfo.setUserName("");
        userInfo.setEmail("");
        userInfo.setPayCredits(0);
        userInfo.setRankCredits(0);
        userInfo.setVerifyEmail(0);
        userInfo.setVerifyMobile(0);
        userInfo.setLiftBanTime(0);

        logs.write("用户注册");

        try {
            userInfo = createPartUser(userInfo);
            logs.write("uid:" + userInfo.getUid());

            if (userInfo.getUid() >= 1) {

                OauthInfo oauthInfo = new OauthInfo(userInfo.getUid(), openId, unionId, server);

                oauths.createOauth(oauthInfo);


                UserDetailInfo userDetailInfo = new UserDetailInfo();

                userDetailInfo.setUid(userInfo.getUid());
                userDetailInfo.setGender(gender);
                userDetailInfo.setLastVisitTime(nowts);
                userDetailInfo.setLastVisitRgid(-1);
                userDetailInfo.setRegisterTime(nowts);
                userDetailInfo.setRegisterRgid(-1);
                userDetailInfo.setBirthday(nowts);
                userDetailInfo.setIdCard("");
                userDetailInfo.setBio("");


                logs.write("用户第三方登录注册:" + userDetailInfo.toString());
                createUserDetail(userDetailInfo);


                logs.write("用户第三方登录注册成功");

                return userInfo;
            }

        } catch (Exception e) {
            logs.write(e, "用户第三方登录注册失败");
        }

        return null;
    }


    /**
     * 获取用户头像
     *
     * @param uid
     * @return
     */
    public String getUserAvatarUrl(Integer uid) {

        String avatar = "http://www.510link.com/img/logo.png";

        try {

            String s = users.getUserAvatar(uid);

            logs.write("s:" + s);
            if (StringHelper.isNotNullOrWhiteSpace(s)) {
                avatar = s;
            }

        } catch (Exception ex) {

            logs.write("获取头像失败");
        }

        return avatar;

    }


    //region  用户详情方法

    /**
     * 获得用户详情数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getUserDetailCount(Specification<UserDetailInfo> condition) {

        try {
            return userDetails.getUserDetailCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得用户详情数量失败");
        }
        return 0;
    }

    /**
     * 创建一条用户详情数据
     *
     * @param userdetailInfo 用户详情模型
     * @return 返回创建信息
     **/
    public UserDetailInfo createUserDetail(UserDetailInfo userdetailInfo) {
        try {
            return userDetails.createUserDetail(userdetailInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条用户详情数据失败");
        }
        return null;
    }

    /**
     * 更新一条用户详情数据
     *
     * @param userdetailInfo 用户详情模型
     **/
    public UserDetailInfo updateUserDetail(UserDetailInfo userdetailInfo) {
        try {
            return userDetails.updateUserDetail(userdetailInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条用户详情数据异常");
        }

        return null;
    }

    /**
     * 删除一条用户详情数据
     *
     * @param id 用户详情模型
     **/
    public void deleteUserDetailById(Integer id) {
        try {
            userDetails.deleteUserDetailById(id);
        } catch (Exception e) {
            logs.write(e, "删除一条用户详情数据异常");
        }
    }

    /**
     * 批量删除一批用户详情数据
     **/
    public void deleteUserDetailByIdList(String idList) {
        try {
            userDetails.deleteUserDetailByIdList(idList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批用户详情数据异常");
        }
    }

    /**
     * 获取一条用户详情数据
     *
     * @param id 用户详情模型
     **/
    public UserDetailInfo getUserDetailById(Integer id) {
        try {
            return userDetails.getUserDetailById(id);
        } catch (Exception e) {
            logs.write(e, "获取一条用户详情数据");
        }

        return null;
    }


    /**
     * 获得用户详情数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回UserDetailInfo
     **/
    public List<UserDetailInfo> getUserDetailList(Specification<UserDetailInfo> condition, Sort sort) {

        List<UserDetailInfo> userDetailList = new ArrayList<UserDetailInfo>();

        try {
            userDetailList = userDetails.getUserDetailList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得用户详情数据列表异常");
        }

        return userDetailList;
    }


    /**
     * 获得用户详情数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回UserDetailInfo
     **/
    public Page<UserDetailInfo> getUserDetailList(Integer pageSize, Integer pageNumber, Specification<UserDetailInfo> condition, Sort sort) {

        Page<UserDetailInfo> userDetailList = null;

        try {
            userDetailList = userDetails.getUserDetailList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得用户详情数据列表异常");
        }

        return userDetailList;
    }

    /**
     * 获取用户列表的条件
     *
     * @param uid
     * @param nickName
     * @param mobile
     * @return
     */
    public Specification<PartUserInfo> getPartUserListCondition(Integer uid, String nickName, String mobile) {

        Specification<PartUserInfo> condition = new Specification<PartUserInfo>() {
            @Override
            public Predicate toPredicate(Root<PartUserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<Predicate>();


                if (uid >= 1) {
                    list.add(cb.equal(root.get("uid").as(Integer.class), uid));
                }

                if (StringHelper.isNotNullOrWhiteSpace(nickName)) {

                    list.add(cb.like(root.get("nickName").as(String.class), "%" + nickName + "%"));
                }

                if (StringHelper.isNotNullOrWhiteSpace(mobile)) {
                    list.add(cb.equal(root.get("mobile").as(String.class), mobile));
                }


                Predicate[] p = new Predicate[list.size()];

                query.where(cb.and(list.toArray(p)));


                return query.getGroupRestriction();
            }
        };

        return condition;
    }


    /**
     * 用户奖励发放
     *
     * @param uid
     * @param money
     * @param desc
     */
    public void sendReward(Integer uid, double money, String desc) {

    }


    //endregion

}