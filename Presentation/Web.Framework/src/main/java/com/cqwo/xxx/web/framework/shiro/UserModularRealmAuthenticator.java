package com.cqwo.xxx.web.framework.shiro;

import com.cqwo.xxx.core.domain.authors.LoginType;
import com.cqwo.xxx.web.framework.model.UserTokenPasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {


    private static final Logger logger = LoggerFactory.getLogger(UserModularRealmAuthenticator.class);

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        logger.info("UserModularRealmAuthenticator:method doAuthenticate() execute ");
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();

        // 登录类型对应的所有Realm
        ArrayList<Realm> typeRealms = new ArrayList<>();
        // 所有Realm
        Collection<Realm> realms = getRealms();

        UserTokenPasswordToken userToken = new UserTokenPasswordToken();

        try {
            if (authenticationToken instanceof UserTokenPasswordToken) {

                // 强制转换回自定义的CustomizedToken
                userToken = (UserTokenPasswordToken) authenticationToken;

                //throw new Exception("类型异常");
            } else if (authenticationToken instanceof UsernamePasswordToken) {

                UsernamePasswordToken authenticationToken1 = (UsernamePasswordToken) authenticationToken;

                userToken = new UserTokenPasswordToken();

                userToken.setUsername(authenticationToken1.getUsername());
                userToken.setPassword(authenticationToken1.getPassword());

            } else {
                throw new Exception("类型异常");
            }

            //登录类型
            LoginType loginType = userToken.getLoginType();


            for (Realm realm : realms) {
                if (realm.getName().equals(loginType.getName())) {
                    typeRealms.add(realm);
                }
            } //System.out.println("食肉:" + typeRealms.size());


        } catch (Exception ex) {
            //ex.printStackTrace();

            // 所有Realm
            for (Realm realm : realms) {
                if (realm.getName().equals(LoginType.AdminLogin.getName())) {
                    typeRealms.add(realm);
                    userToken = new UserTokenPasswordToken();
                }
            }
        }


        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1) {
            logger.info("doSingleRealmAuthentication() execute ");
            return doSingleRealmAuthentication(typeRealms.iterator().next(), userToken);
        } else {
            logger.info("doMultiRealmAuthentication() execute ");
            return doMultiRealmAuthentication(typeRealms, userToken);
        }
    }
}
