package com.cqwo.xxx.strategy.config;

import com.alibaba.fastjson.JSON;
import com.cqwo.xxx.core.cache.CWMCache;
import com.cqwo.xxx.core.config.IConfigStrategy;
import com.cqwo.xxx.core.config.info.BaseConfigInfo;
import com.cqwo.xxx.core.config.info.EmailConfigInfo;
import com.cqwo.xxx.core.config.info.SMSConfigInfo;
import com.cqwo.xxx.core.config.info.WechatConfigInfo;
import com.cqwo.xxx.core.data.rdbs.repository.base.SYSConfigRepository;
import com.cqwo.xxx.core.domain.base.SYSConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ConfigStrategy")
public class ConfigStrategy implements IConfigStrategy {


    @Autowired
    SYSConfigRepository configRepository;

    @Autowired
    CWMCache cwmCache;

    private final String baseConfigName = "BaseConfig";//商城基本配置信息文件路径
    private final String emailConfigName = "EmailConfig";//邮件配置信息文件路径
    private final String smsConfigName = "SMSConfig";//短信配置信息文件路径
    private final String wechatconfigName = "WechatConfig";//微信配置信息文件路径


    @Override
    public boolean saveBaseConfig(BaseConfigInfo configInfo) {
        cwmCache.delete(baseConfigName);
        return saveSYSConfig(baseConfigName, configInfo);
    }

    @Override
    public BaseConfigInfo getBaseConfig() {

        BaseConfigInfo configInfo = cwmCache.getValue(baseConfigName, BaseConfigInfo.class);

        if (configInfo == null) {
            configInfo = getSysConfig(baseConfigName, BaseConfigInfo.class);
            cwmCache.setValue(baseConfigName, configInfo);
        }

        return configInfo;
    }

    @Override
    public boolean saveEmailConfig(EmailConfigInfo configInfo) {
        cwmCache.delete(emailConfigName);
        return saveSYSConfig(emailConfigName, configInfo);
    }

    @Override
    public EmailConfigInfo getEmailConfig() {

        EmailConfigInfo configInfo = cwmCache.getValue(emailConfigName, EmailConfigInfo.class);
        if (configInfo == null) {
            configInfo = getSysConfig(emailConfigName, EmailConfigInfo.class);
            cwmCache.setValue(baseConfigName, configInfo);
        }
        return configInfo;
    }

    @Override
    public boolean saveSMSConfig(SMSConfigInfo configInfo) {

        cwmCache.delete(smsConfigName);
        return saveSYSConfig(smsConfigName, configInfo);
    }

    @Override
    public SMSConfigInfo getSMSConfig() {

        SMSConfigInfo configInfo = cwmCache.getValue(smsConfigName, SMSConfigInfo.class);
        if (configInfo == null) {
            configInfo = getSysConfig(smsConfigName, SMSConfigInfo.class);
            cwmCache.setValue(smsConfigName, configInfo);
        }
        return configInfo;

    }

    @Override
    public boolean saveWechatConfig(WechatConfigInfo configInfo) {

        cwmCache.delete(wechatconfigName);
        return saveSYSConfig(wechatconfigName, configInfo);
    }


    @Override
    public WechatConfigInfo getWechatConfig() {

        WechatConfigInfo configInfo = cwmCache.getValue(smsConfigName, WechatConfigInfo.class);
        if (configInfo == null) {
            configInfo = getSysConfig(smsConfigName, WechatConfigInfo.class);
            cwmCache.setValue(smsConfigName, configInfo);
        }
        return configInfo;
    }


    /**
     * 保存用户变量
     *
     * @param varname
     * @param object
     * @return
     */
    public boolean saveSYSConfig(String varname, Object object) {

        try {
            String varValue = JSON.toJSONString(object);

            SYSConfigInfo configInfo = configRepository.findByVarName(varname);

            if (configInfo == null) {
                configInfo = new SYSConfigInfo();
                configInfo.setVarName(varname);
                configInfo.setVarValue(varValue);

                configRepository.save(configInfo);

            } else {
                configInfo.setVarValue(varValue);
                configRepository.save(configInfo);

            }


            return true;

        } catch (Exception ex) {

        }

        return false;

    }


    /**
     * 获取系统配置
     *
     * @param varname
     * @return
     */
    public <T> T getSysConfig(String varname, Class<T> cls) {

        SYSConfigInfo configInfo = new SYSConfigInfo();

        try {

            configInfo = configRepository.findByVarName(varname);

        } catch (Exception ex) {

        }

        return JSON.parseObject(configInfo.getVarValue(), cls);

    }



}
