package com.github.geng.token.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 服务端 token 配置读取，每份服务器的配置，都有部分不一样
 * created by geng
 */
@Data
@Component
@PropertySource(value = "classpath:application.yml") // 指定读取主配置文件
public class ClientTokenConfig {

    private byte[] pubKeyByte;

    @Value("${client.id}")
    private String clientId;
    @Value("${client.rsa-secret}")
    private String clientSecret;
    @Value("${client.token-header}")
    private String tokenHeader;
    @Value("${spring.application.name}")
    private String applicationName;

}
