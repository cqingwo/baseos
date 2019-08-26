package com.cqwo.xxx.plugin.oauth.miniapp;

import com.cqwo.xxx.core.log.Logs;
import com.cqwo.xxx.core.plugin.interface2.IOAuthPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component(value = "OAuthMiniAppPluginService")
public class PluginService implements IOAuthPlugin {


    @Autowired
    private Logs logs;

    @Override
    public String getLoginUrl() {
        return "/";
    }

    @Override
    public String getConfigUrl() {
        return null;
    }

    @PostConstruct
    @Override
    public void initPlugin() {
        logs.write("初始化插件");
        System.out.println("初始化插件");
    }
}
