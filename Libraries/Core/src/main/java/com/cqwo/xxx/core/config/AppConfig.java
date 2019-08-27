package com.cqwo.xxx.core.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "spring.application")
@Component(value = "AppConfig")
public class AppConfig {

    /**
     * 项目名称
     */
    private String name = "";

    /**
     * 项目域名地址
     */
    private String domain = "";


    /**
     * 项目ip地址
     */
    private String ip = "";


    /**
     * 管理员列表
     */
    private String[] admin = new String[]{"cqnews"};

}
