package com.cqwo.xxx.web.framework.model;

import com.cqwo.xxx.core.domain.authors.LoginType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserTokenPasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 644353652275378471L;

    /**
     * token
     */
    private String token = "token";


    /**
     * 类型
     */
    private LoginType loginType = LoginType.AdminLogin;


    public UserTokenPasswordToken(String account, String password) {
        super(account, password);
    }


    public UserTokenPasswordToken(String token, LoginType loginType) {
        super("", "0");
        this.token = token;
        this.loginType = loginType;
    }
}