package com.cqwo.xxx.plugin.oauth.miniapp;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.cqwo.xxx.core.domain.users.OauthInfo;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.domain.users.UserRankInfo;
import com.cqwo.xxx.core.errors.SateCollect;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.wechat.WeChatSession;
import com.cqwo.xxx.plugin.oauth.miniapp.framework.OauthMiniAppApiController;
import com.cqwo.xxx.plugin.oauth.miniapp.model.*;
import com.cqwo.xxx.services.*;
import com.cqwo.xxx.web.framework.validate.ValidateModel;
import com.cqwo.xxx.web.framework.validate.ValidationResult;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "OauthMiniAppAuthController")
public class AuthorController extends OauthMiniAppApiController {

    @Autowired
    private Users users;

    @Autowired
    private LoginFailLogs loginFailLogs;

    @Autowired
    private Oauths oauths;


    @Autowired
    private UserRanks userRanks;

    @Autowired
    private WechatMiniUtils wechatUtils;


    /**
     * 用户登录验证
     *
     * @return
     */
    @RequestMapping(value = "author/onlogin")
    @ResponseBody
    public String onLogin(ApiLoginModel model) {//, String code

        ValidationResult result = ValidateModel.validateEntity(model);

        if (result.isNotErrors()) {

            logs.write(request.toString());

            String code = model.getCode();


            String server = "wechat";

            if (code == null || code.length() <= 0) {
                return JsonView(SateCollect.AUTHOR_ERRLOGIN, "用户登录失败" + code);
            }

            WxMaJscode2SessionResult sessionResult = wechatUtils.getSessionInfo(code);

            if (sessionResult == null) {
                return JsonView(SateCollect.AUTHOR_ERRWECHAT, "用户登录失败,请检测微信设置");
            }


            System.out.println("sessionResult:" + sessionResult);


            // 保存session
            wechatUtils.updateSession(sessionResult);

            AuthorOnLoginModel authorModel = new AuthorOnLoginModel(sessionResult);

            System.out.println("authorModel:" + authorModel.toString());

            String openId = authorModel.getOpenId();
            String unionId = authorModel.getUnionId();


            if (Strings.isNullOrEmpty(openId)) {
                return JsonView(SateCollect.AUTHOR_ERRLOGIN, "用户登录失败");
            }

            if (Strings.isNullOrEmpty(unionId)) {
                unionId = "";
            }

            //通过openid获取开发登录信息
            OauthInfo oauthInfo = oauths.getOauthByOpenIdAndUnionId(server, openId, unionId);

            PartUserInfo userInfo = null;

            /**
             * 如果开发信息获取失败,就准备开始注册
             */
            if (oauthInfo == null) {
                userInfo = users.createOauthUserInfo(server, openId, unionId, model.getNickName(), model.getAvatar(), model.getGender());
            } else {
                userInfo = users.getPartUserByWechatOpenId(openId, unionId);
            }

            LoginMessageModel loginModel = new LoginMessageModel();

            if (userInfo == null || userInfo.getUid() <= 0) {  //用户未注册成功

                return JsonView(SateCollect.AUTHOR_ERRLOGIN, "用户登录失败");
            }
            loginModel.setUid(userInfo.getUid());
            loginModel.setToken(Users.creatUserToken(userInfo.getUid(), openId));
            loginModel.setPartUserInfo(users.securityUserInfo(userInfo));


            UserRankInfo userRankInfo = userRanks.getUserRankByUserRid(userInfo.getUserRid());

            loginModel.setUserRankInfo(userRankInfo);
            return JsonView(SateCollect.SUCCESS, loginModel, "欢迎回来...");

        }

        return JsonView(SateCollect.VALIDATION_FAILED, "用户信息校验失败");

    }

//    /**
//     * 用户注册
//     *
//     * @return
//     */
//    @RequestMapping(value = "author/onregist")
//    @ResponseBody
//    public String onRegist(ApiRegistModel model) {
//
//
//        ValidationResult result = ValidateModel.validateEntity(model);
//
//        if (result.isNotErrors()) {
//
//            PartUserInfo userInfo = null;
//
//
//            String server = "wechat";
//            String openId = model.getOpenId();
//            String unionId = "";
//
//            if (Strings.isNullOrEmpty(openId))
//                return JsonView("用户注册失败");
//
//            OauthInfo oauthInfo = oauths.getOauthByOpenId(server, model.getOpenId());
//
//
//            if (oauthInfo != null && oauthInfo.getId() >= 1)
//                return JsonView("用户已注册,请勿重复注册");
//            else {
//                userInfo = users.createOauthUserInfo(server, openId, unionId, model.getNickName(), model.getAvatar(), model.getGender());
//            }
//
//
//            if (userInfo != null) {
//
//                UserRankInfo userRankInfo = userRanks.getUserRankByUserRid(userInfo.getUserRid());
//
//                LoginMessageModel loginModel = new LoginMessageModel(userInfo.getUid(), users.creatUserToken(userInfo.getUid(), userInfo.getSalt()), userInfo.getInvitCode(), userInfo, userRankInfo);
//
//                return JsonView(1, loginModel, "欢迎回来...");
//            }
//
//            return JsonView("用户注册失败...");
//        }
//
//        return JsonView(result.toString());
//
//
///**李凯馨**/
//    }

    /**
     * 手机解析
     *
     * @param vmodel
     * @return
     */
    @RequestMapping(value = "author/encryptedmobile")
    public String encryptedMobile(EncryptedMobileModel vmodel) {

        ValidationResult result = ValidateModel.validateEntity(vmodel);

        if (result.isNotErrors()) {


            WeChatSession wechatSession = wechatUtils.getSession(workContext.getOpenId());


            if (wechatSession == null) {
                return JsonView(SateCollect.AUTHOR_NOLOGIN, "用户登录过期");
            }

            WxMaPhoneNumberInfo phoneResult = wechatUtils.getUserService().getPhoneNoInfo(wechatSession.getSessionKey(), vmodel.getEncryptedData(), vmodel.getIv());


            if (phoneResult == null) {
                return JsonView(SateCollect.AUTHOR_ERRMOBILE, "手机解析失败");
            }


            users.updateUserMobile(workContext.getUid(), phoneResult.getPhoneNumber());

            return JsonView(SateCollect.SUCCESS, phoneResult, "手机解析成功");
        }

        return JsonView(SateCollect.VALIDATION_FAILED, "手机解析失败");

    }

    /**
     * 验证微信签名
     *
     * @param model 签名model
     * @return
     */
    @RequestMapping(value = "author/checkwxopensignature")
    @ResponseBody
    public String checkWXopenSignature(CheckWxOpenSignatureModel model) {


        ValidationResult result = ValidateModel.validateEntity(model);

        if (result.isNotErrors()) {

            boolean checkSuccess = users.checkSignature(model.getOpenId(), model.getRawData(), model.getSignature());
            //return Json(new { success = checkSuccess, msg = checkSuccess ? "签名校验成功" : "签名校验失败" });

            return JsonView(checkSuccess ? "签名校验成功" : "签名校验失败");

        }

        return JsonView("微信签名验证失败");
    }

}
