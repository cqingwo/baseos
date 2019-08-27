package com.cqwo.xxx.web.framework.shiro.token;


import com.cqwo.xxx.core.domain.authors.AuthUserInfo;
import com.cqwo.xxx.core.domain.authors.AuthorActionInfo;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import com.cqwo.xxx.core.domain.authors.LoginType;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.helper.StringHelper;
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

import java.util.List;

/**
 * Created by cdyoue on 2016/10/21.
 */


public class TokenRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private Users users;


    @Autowired(required = false)
    private Logs logs;


    /**
     * loginFailLogs
     */
    @Autowired(required = false)
    private LoginFailLogs loginFailLogs;

    @Autowired(required = false)
    private UserRanks userRanks;

    @Autowired(required = false)
    private Authors authors;

    @Override
    public String getName() {
        return LoginType.TokenLogin.getName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo+" + principals.toString()); //System.out.println(principals.getPrimaryPrincipal());

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        AuthUserInfo authUserInfo = (AuthUserInfo) principals.getPrimaryPrincipal();


        if (authUserInfo == null) {
            return auth;
        }

        PartUserInfo userInfo = authUserInfo.getUserInfo();

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

        if (!(token.getLoginType().equals(LoginType.TokenLogin))) {
            throw new UnknownAccountException("登录类型异常");
        }


        throw new UnknownAccountException("登录失败,暂未实现");


    }
}

