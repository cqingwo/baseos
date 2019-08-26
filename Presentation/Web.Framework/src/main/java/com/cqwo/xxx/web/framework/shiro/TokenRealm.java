package com.cqwo.xxx.web.framework.shiro;

import com.cqwo.xxx.core.domain.authors.AuthorActionInfo;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.domain.users.UserRankInfo;
import com.cqwo.xxx.core.helper.DateHelper;
import com.cqwo.xxx.core.helper.UnixTimeHelper;
import com.cqwo.xxx.core.helper.ValidateHelper;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.services.*;
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

import java.util.List;

/**
 *
 * @author cdyoue
 * @date 2016/10/21
 */


public class TokenRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Users users;


    @Autowired
    private Logs logs;


    @Autowired
    private LoginFailLogs loginFailLogs;

    @Autowired
    private UserRanks userRanks;

    @Autowired
    private Authors authors;

    @Override
    public String getName(){
        return "APIRealm";
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo+" + principals.toString());

        String account = (String) principals.getPrimaryPrincipal();

        PartUserInfo userInfo = null;

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

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        if (userInfo == null) {
            return auth;
        }

        Integer uid = userInfo.getUid();

        List<AuthorRoleInfo> authorRoleList = authors.getUserAuthorRoleList(uid);

        //赋予管理员角色
        for (AuthorRoleInfo roleInfo : authorRoleList) {
            auth.addRole(roleInfo.getCode());
        }

        List<AuthorActionInfo> authorActionList = authors.getUserAuthorActionList(uid);

        for (AuthorActionInfo actionInfo : authorActionList) {
            System.out.println(actionInfo.toString());
            auth.addStringPermission(actionInfo.getAction());
        }

        System.out.println(auth.getStringPermissions().toString());

        return auth;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());


        UserTokenPasswordToken token = (UserTokenPasswordToken) authenticationToken;

        if (token.getType() != 1) {
            return null;
        }

        System.out.println("我应在江湖悠悠,饮一壶浊酒..." + getName());
        System.out.println(authenticationToken.toString());

        String account = "yjwwtu";


        PartUserInfo userInfo = null;

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

        String sourcePassword = "123456";//Arrays.toString(token.getPassword());


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

        if (userRankInfo != null && !userInfo.getUserRid().equals(userInfo.getUserRid())) {
            users.updateUserRankByUid(userInfo.getUid(), userRankInfo.getUserRid());
            userInfo.setUserRid(userRankInfo.getUserRid());
        }


        loginFailLogs.deleteLoginFailLogByIP("127.0.0.1");


        Session session = SecurityUtils.getSubject().getSession();


        session.setAttribute("userinfo", userInfo);

        System.out.println("处理数据,...sawq");

        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }
}

