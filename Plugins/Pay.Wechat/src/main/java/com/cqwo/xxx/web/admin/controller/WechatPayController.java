package com.cqwo.xxx.web.admin.controller;

import com.cqwo.xxx.core.domain.users.OauthInfo;
import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.helper.RandomHelper;
import com.cqwo.xxx.core.message.MessageException;
import com.cqwo.xxx.services.Oauths;
import com.cqwo.xxx.services.WechatPayUtils;
import com.cqwo.xxx.web.framework.controller.BaseAdminController;
import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Controller(value = "AdminWechatPayController")
public class WechatPayController extends BaseAdminController {


    private Lock lock = new ReentrantLock();




    @Autowired
    private Oauths oauths;

    @Autowired
    private WechatPayUtils payUtils;


//    /**
//     * 完成
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "bank/cash/completed")
//    public ModelAndView completed(@RequestParam(defaultValue = "0") Integer id) {
//
//        lock.lock();
//        try {
//
//            if (id <= 0) {
//                return PromptView("不正确的付款信息");
//            }
//
//            BankRollInfo info = banks.getBankRollById(id);
//
//            if (info == null) {
//                return PromptView("不正确的付款信息");
//            }
//
//            if (info.getState() > BankState.Completed.getIndex()) {
//                return PromptView("不正确的操作动作");
//            }
//
//
//            Integer nowts = DateHelper.getUnixTimeStamp();
//
//            EntPayResult entPayResult = entPay(info.getUid());
//
//
//            if (entPayResult != null) {
//
//                info.setState(BankState.Completed.getIndex());
//                info.setUpdateTime(nowts);
//                String actionDes = MessageFormat.format("管理{0}处理并完成付款,单号:{1},时间:[{2}]",
//                        workContext.getNickName(),
//                        entPayResult.getPartnerTradeNo(),
//                        nowts);
//
//                banks.cashCompleted(info.getId(), actionDes, workContext.getUid());
//
//                return PromptView("提现付款完成");
//
//            } else {
//                return PromptView("提款到账失败");
//            }
//
//
//        } catch (MessageException ex) {
//
//            return PromptView(ex.getMessage());
//
//        } catch (Exception ex) {
//
//            logs.write(ex, "提现付款失败");
//
//        } finally {
//
//            lock.unlock();
//        }
//
//
//        return PromptView("提现付款失败");
//
//    }


    public EntPayResult entPay(int uid) throws MessageException {


        try {

            if (uid <= 0) {
                throw new MessageException("用户信息不存在");
            }

            PartUserInfo userInfo = users.getPartUserByUid(uid);

            if (userInfo == null || userInfo.getUid() <= 0) {
                throw new MessageException("用户信息不存在");
            }

            OauthInfo oauthInfo = oauths.getOneOauthByUid(uid);

            if (oauthInfo == null) {
                logs.write("用户尚未绑定微信");
                throw new MessageException("用户尚未绑定微信");
            }


            String partnerTradeNo = uid + RandomHelper.generateLowerString(10);


            int amount = 100;


            String ip = "39.104.93.106";//cwmUtils.getIp();
            String openId = oauthInfo.getOpenId();

            EntPayRequest request = EntPayRequest.newBuilder()
                    .partnerTradeNo(partnerTradeNo)
                    .openid(openId)
                    .amount(amount)
                    .spbillCreateIp(ip)
                    .checkName(WxPayConstants.CheckNameOption.NO_CHECK)
                    .description("描述信息")
                    .build();

            System.out.println("request" + request.toString());


            EntPayResult wxEntPayResult = payUtils.getEntPayService().entPay(request);

            if (wxEntPayResult == null || wxEntPayResult.getPaymentNo().isEmpty()) {
                throw new MessageException("微信支付失败");
            }

            logs.write("测试2:" + wxEntPayResult.toString());
            logs.write("微信支付成功");

            return wxEntPayResult;


        } catch (Exception ex) {

            logs.write(ex, "支付失败" + ex);
            throw new MessageException("支付失败");
        }
        //logs.write("支付失败");
    }


}
