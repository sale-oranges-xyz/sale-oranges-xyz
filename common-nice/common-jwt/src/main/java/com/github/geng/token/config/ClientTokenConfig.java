package com.github.geng.token.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

/**
 * 服务端 token 配置读取，每份服务器的配置，都有部分不一样
 * created by geng
 */
@Data
//@Configuration
//@PropertySource(value = "classpath:jwtConfig.yml")
//@ConfigurationProperties(prefix = "client", ignoreUnknownFields =false)
public class ClientTokenConfig {

    private byte[] pubKeyByte;

    @Value("${client.id:null}")
    private String clientId;
    @Value("${client.secret}")
    private String clientSecret;
    @Value("${client.token-header}")
    private String tokenHeader;
    @Value("${spring.application.name}")
    private String applicationName;

}
