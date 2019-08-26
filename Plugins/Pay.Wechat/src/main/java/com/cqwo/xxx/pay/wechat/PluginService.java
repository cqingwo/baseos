package com.cqwo.xxx.pay.wechat;

import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.core.plugin.interface2.IPayPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;


@Component(value = "PayWechatPluginService")
public class PluginService implements IPayPlugin {

    @Autowired
    Logs logs;

    @Override
    public String getPayUrl() {
        return "/wechat/pay";
    }

    @Override
    public Integer getPayMode() {
        return 0;
    }

    @Override
    public double getPayFee(double recordAmount, Timestamp buyTime, PartUserInfo partUserInfo) {
        return 0;
    }

    @Override
    public String getConfigUrl() {
        return "/admin/wehcat/payconfig";
    }


    @PostConstruct
    @Override
    public void initPlugin() {
        logs.write("初始化插件");
        System.out.println("初始化插件");
    }

}
