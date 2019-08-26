package com.cqwo.xxx.core.helper.mailpop3;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 该类用来生成Authenticator
 */
public final class AuthenticatorGenerator {

    /**
     * 根据用户名和密码，生成Authenticator
     *
     * @param userName
     * @param password
     * @return
     */
    public static Authenticator getAuthenticator(final String userName, final String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
    }

}
