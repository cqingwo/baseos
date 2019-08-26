package com.cqwo.xxx.web.controller;


import com.cqwo.xxx.core.helper.TypeHelper;
import com.cqwo.xxx.services.WechatPayUtils;
import com.cqwo.xxx.web.framework.controller.BaseWebController;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.github.binarywang.wxpay.bean.result.BaseWxPayResult.fenToYuan;

@RestController(value = "WebWechatPayController")
public class WechatPayController extends BaseWebController {


    @Autowired
    private WechatPayUtils payUtils;

    @RequestMapping(value = "notify")
    @ResponseBody
    public void notify2() {

        logs.write("===============支付回调开始====================");

        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = payUtils.parseOrderNotifyResult(xmlResult);

            logs.info("回调信息:" + result.toString());
            // 结果正确
            String outTradeNo = result.getOutTradeNo();

            logs.write("outTradeNo:" + outTradeNo);



            String tradeNo = result.getTransactionId();


            logs.write("tradeNo:" + tradeNo);

            double totalFee = TypeHelper.stringToDouble(fenToYuan(result.getTotalFee()));


            logs.write("totalFee:" + totalFee);



            logs.info("订单支付成功");
            return;

        } catch (Exception e) {
            logs.write(e, "微信回调结果异常");
            logs.info("微信回调结果异常");
            return;
        }


    }

}
