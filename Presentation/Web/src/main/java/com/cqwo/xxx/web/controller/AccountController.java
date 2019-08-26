package com.cqwo.xxx.web.controller;

import com.cqwo.xxx.services.LoginFailLogs;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.web.model.AccountLoginModel;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.services.LoginFailLogs;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.web.framework.controller.BaseWebController;
import com.cqwo.xxx.web.framework.model.UserTokenPasswordToken;
import com.cqwo.xxx.web.framework.validate.ValidateModel;
import com.cqwo.xxx.web.framework.validate.ValidationResult;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.services.LoginFailLogs;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.web.framework.controller.BaseWebController;
import com.cqwo.xxx.web.framework.model.UserTokenPasswordToken;
import com.cqwo.xxx.web.framework.validate.ValidateModel;
import com.cqwo.xxx.web.framework.validate.ValidationResult;
import com.cqwo.xxx.web.model.AccountLoginModel;
import com.google.common.base.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 账户关信息
 */
@Controller(value = "AccountController")
public class AccountController extends BaseWebController {


    @Autowired
    private Users users;

    @Autowired
    private LoginFailLogs loginFailLogs;

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet() {

        return View();
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(AccountLoginModel model) {

        String loginUrl = "/login.html";

        System.out.println("开始处理数据");

        System.out.println(model.toString());

        ValidationResult result = ValidateModel.validateEntity(model);
        if (result.isNotErrors()) {

            System.out.println("数据验证成功");

            //从session中取出servlet生成的验证码text值
            String kaptchaValue = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

            //比较输入的验证码和实际生成的验证码是否相同
            if (Strings.isNullOrEmpty(kaptchaValue) || !model.getVerifyCode().equalsIgnoreCase(kaptchaValue)) {
                //return PromptView("验证码不正确");
            }

            // 获取主体
            Subject subject = SecurityUtils.getSubject();
            try {


                UserTokenPasswordToken token = new UserTokenPasswordToken(model.getAccount(), model.getPassword());



                subject.login(token);


            } catch (UnknownAccountException ex) { //未找到用户信息

                return PromptView(loginUrl, "未找到用户信息" + ex.getMessage());

            } catch (IncorrectCredentialsException ex) { //账号密码错误
                logs.write(ex);
                return PromptView(loginUrl, "账号密码错误" + ex.getMessage());

            } catch (LockedAccountException ex) { //账户锁定

                logs.write(ex);
                return PromptView(loginUrl, "账号被锁定" + ex.getMessage());

            } catch (ExcessiveAttemptsException ex) { //登录次数超出限制
                logs.write(ex);
                return PromptView(loginUrl, "登录次数超出限制" + ex.getMessage());

            } catch (AuthenticationException ex) { //用户异常
                logs.write(ex);
                return PromptView(loginUrl, "用户异常" + ex.getMessage());
                //unexpected error?
            } catch (Exception ex) { //其它异常信息
                logs.write(ex);

                return PromptView(loginUrl, ex.getMessage());
            }


            return PromptView(model.getReturnUrl(), "用户登录成功");
        }

        return PromptView(loginUrl, "数据校验失败:" + result.toString());

    }


    /**
     * 退出用户登录
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {


        // 获取主体
        Subject subject = SecurityUtils.getSubject();

        subject.logout();

        return PromptView("/", "退出成功");

    }
}
