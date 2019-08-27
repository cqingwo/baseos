package com.cqwo.xxx.web.framework.shiro.admin;

import com.cqwo.xxx.core.config.AppConfig;
import com.cqwo.xxx.core.domain.authors.*;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.domain.users.UserRankInfo;
import com.cqwo.xxx.core.helper.DateHelper;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.helper.UnixTimeHelper;
import com.cqwo.xxx.core.helper.ValidateHelper;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.services.Authors;
import com.cqwo.xxx.services.LoginFailLogs;
import com.cqwo.xxx.services.UserRanks;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.web.framework.model.UserTokenPasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cdyoue on 2016/10/21.
 */


public class AdminRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired(required = false)
    private Users users;


    @Autowired(required = false)
    private Logs logs;

    @Autowired(required = false)
    private LoginFailLogs loginFailLogs;

    @Autowired(required = false)
    private UserRanks userRanks;

    @Autowired(required = false)
    private Authors authors;

    @Autowired(required = false)
    private AppConfig appConfig;


    @Override
    public String getName() {
        return LoginType.AdminLogin.getName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info("doGetAuthorizationInfo+" + principals.toString());

        //System.out.println(principals.getPrimaryPrincipal());

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        //region 获取用户信息
        AuthUserInfo authorUserInfo = (AuthUserInfo) principals.getPrimaryPrincipal();

        if (authorUserInfo == null) {
            return auth;
        }

        PartUserInfo userInfo = authorUserInfo.getUserInfo();

        if (userInfo == null || userInfo.getUid() <= 0) {
            return auth;
        }

        //endregion

        //region 用户管理权限

        List<AuthorRoleInfo> authorRoleList = authors.getUserAuthorRoleList(userInfo.getUid());

        //赋予管理员角色
        for (AuthorRoleInfo roleInfo : authorRoleList) {
            auth.addRole(roleInfo.getCode());
        }

        List<AuthorActionInfo> authorActionList = authors.getUserAuthorActionList(userInfo.getUid());

        for (AuthorActionInfo actionInfo : authorActionList) {
            auth.addStringPermission(actionInfo.getAction());
        }

        //endregion 用户管理权限


        //region 判断用户是否是超级管理员
        if (Arrays.asList(appConfig.getAdmin()).contains(userInfo.getUserName())) {

            for (AuthorGroups groups : AuthorGroups.values()) {

                auth.addRole(groups.getCode());
            }


            List<AuthorActionInfo> allActionInfo = authors.getAllAuthorActionList();

            for (AuthorActionInfo actionInfo : allActionInfo) {
                auth.addStringPermission(actionInfo.getAction());
            }

        }

        //endregion

        return auth;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());

        try {


            if (!(authenticationToken instanceof UserTokenPasswordToken)) {
                throw new UnknownAccountException("类型不匹配");
            }

            UserTokenPasswordToken token = (UserTokenPasswordToken) authenticationToken;

            if (token == null) {
                throw new UnknownAccountException("token异常");
            }

            if (!(token.getLoginType().equals(LoginType.AdminLogin))) {
                throw new UnknownAccountException("登录类型异常");
            }


            PartUserInfo userInfo = null;

            String token2 = "";
            //System.out.println("我应在江湖悠悠,饮一壶浊酒..." + getName());
            //System.out.println(authenticationToken.toString());


            String account = token.getUsername();

            String sourcePassword = StringHelper.charToString(token.getPassword());

            if (StringHelper.isNullOrWhiteSpace(account) || StringHelper.isNullOrWhiteSpace(sourcePassword)) {
                throw new UnknownAccountException("账号或密码不能为空");
            }


            //检测账号类型
            if (ValidateHelper.isValidEmail(account)) {
                userInfo = users.getPartUserByEmail(account);
                logs.write("邮箱登录");
            } else if (ValidateHelper.isValidMobile(account)) {
                userInfo = users.getPartUserByMobile(account);
                logs.write("手机登录");
            } else {
                userInfo = users.getPartUserByUserName(account);
                logs.write("账号登录");
            }


            //检测模型是否存在
            if (userInfo == null || userInfo.getUid() <= 0) {
                throw new UnknownAccountException("未找到用户信息");
            }


            System.out.println(userInfo.toString());

            String password = Users.createUserPassword(sourcePassword, userInfo.getSalt());

            logger.error("password:" + password);
            logger.error("userInfo.getPassword():" + userInfo.getPassword());

            //检测密码是否正确
            if (!userInfo.getPassword().equals(password)) {
                loginFailLogs.addLoginFailTimes("127.0.0.1", DateHelper.getTimestamp());//增加登陆失败次数 要完善

                throw new IncorrectCredentialsException("用户账号名或密码错误..");


            }


            if (userInfo.getLiftBanTime() >= UnixTimeHelper.getUnixTimeStamp())//达到解禁时间
            {
                throw new LockedAccountException("您的账号当前被锁定,不能访问");
            }

            UserRankInfo userRankInfo = userRanks.getUserRankByCredits(userInfo.getPayCredits());

            if (userRankInfo != null && !(userInfo.getUserRid().equals(userRankInfo.getUserRid()))) {
                users.updateUserRankByUid(userInfo.getUid(), userRankInfo.getUserRid());
                userInfo.setUserRid(userRankInfo.getUserRid());
            }


            loginFailLogs.deleteLoginFailLogByIP("127.0.0.1");


            Session session = SecurityUtils.getSubject().getSession();


            session.setAttribute("userinfo", userInfo);


            System.out.println("处理数据,...sawq");

            AuthUserInfo authUserInfo = new AuthUserInfo(LoginType.AdminLogin, token.getUsername(), userInfo.getUid(), userInfo);

            return new SimpleAuthenticationInfo(authUserInfo, token.getPassword(), getName());


        }catch (Exception ex){
            ex.printStackTrace();
        }
        throw new UnknownAccountException("未找到用户信息");
    }

}

