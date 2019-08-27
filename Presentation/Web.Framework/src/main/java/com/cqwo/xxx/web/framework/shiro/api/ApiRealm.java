package com.cqwo.xxx.web.framework.shiro.api;

import com.cqwo.xxx.core.domain.authors.AuthUserInfo;
import com.cqwo.xxx.core.domain.authors.AuthorActionInfo;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import com.cqwo.xxx.core.domain.authors.LoginType;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.services.Authors;
import com.cqwo.xxx.services.LoginFailLogs;
import com.cqwo.xxx.services.UserRanks;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.web.framework.model.UserTokenPasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cdyoue on 2016/10/21.
 */


public class ApiRealm extends AuthorizingRealm {

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

    @Override
    public String getName() {
        return LoginType.ApiLogin.getName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info("doGetAuthorizationInfo+" + principals.toString()); //System.out.println(principals.getPrimaryPrincipal());


        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        AuthUserInfo authorUserInfo = (AuthUserInfo) principals.getPrimaryPrincipal();


        if (authorUserInfo == null) {
            return auth;
        }

        PartUserInfo userInfo = authorUserInfo.getUserInfo();

        if (userInfo == null || userInfo.getUid() <= 0) {
            return auth;
        }

        List<AuthorRoleInfo> authorRoleList = authors.getUserAuthorRoleList(userInfo.getUid());

        //赋予管理员角色
        for (AuthorRoleInfo roleInfo : authorRoleList) {
            auth.addRole(roleInfo.getCode());
        }

        List<AuthorActionInfo> authorActionList = authors.getUserAuthorActionList(userInfo.getUid());

        for (AuthorActionInfo actionInfo : authorActionList) { //System.out.println(actionInfo.toString());
            auth.addStringPermission(actionInfo.getAction());
        }

        // //System.out.println(auth.getStringPermissions().toString());

        return auth;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());


        if (!(authenticationToken instanceof UserTokenPasswordToken)) {
            throw new UnknownAccountException("类型不匹配");
        }

        UserTokenPasswordToken token = (UserTokenPasswordToken) authenticationToken;

        if (token == null) {
            throw new UnknownAccountException("token异常");
        }

        if (!(token.getLoginType().equals(LoginType.ApiLogin))) {
            throw new UnknownAccountException("登录类型异常");
        }


        PartUserInfo userInfo = null;
        String token2 = ""; //System.out.println("我应在江湖悠悠,饮一壶浊酒..." + getName()); //System.out.println(authenticationToken.toString());



        throw new UnknownAccountException("登录失败,暂未实现");


    }
}

