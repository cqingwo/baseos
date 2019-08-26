package com.cqwo.xxx.services;

import com.cqwo.xxx.core.log.Logs;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.EntPayService;
import com.cqwo.xxx.core.wechat.CWMWechat;
import com.cqwo.xxx.core.wechat.CWMWechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "WechatPayUtils")
public class WechatPayUtils {

    @Autowired
    CWMWechat cwmWechat;

    @Autowired
    Logs logs;

    /**
     * 获取支付服务
     *
     * @return
     */
    public EntPayService getEntPayService() {

        try {
            return cwmWechat.getIWechatPayStrategy().getEntPayService();
        } catch (Exception ex) {
            logs.write(ex, "获取支付服务");
        }

        return null;

    }

    /**
     * 配置参数,预留
     *
     * @return
     */
    boolean setConfig() {

        boolean isSuccess = false;
        try {
            return cwmWechat.getIWechatPayStrategy().setConfig();
        } catch (Exception ex) {
            logs.write(ex, "配置参数,预留");
        }
        return isSuccess;
    }

    /**
     * 创建订单
     *
     * @param orderRequest
     * @return
     */
    public Object createOrder(WxPayUnifiedOrderRequest orderRequest) {

        try {
            return cwmWechat.getIWechatPayStrategy().createOrder(orderRequest);
        } catch (Exception ex) {
            logs.write(ex, "配置参数,预留");
        }
        return null;
    }


    /**
     * 解析回调
     *
     * @param xmlResult
     * @return
     */
    public WxPayOrderNotifyResult parseOrderNotifyResult(String xmlResult) {
        try {
            return cwmWechat.getIWechatPayStrategy().parseOrderNotifyResult(xmlResult);
        } catch (Exception ex) {
            logs.write(ex, "配置参数,预留");
        }
        return null;
    }
}
