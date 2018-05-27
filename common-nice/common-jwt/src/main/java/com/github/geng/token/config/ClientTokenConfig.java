package com.github.geng.token.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * 服务端 token 配置读取，每份服务器的配置，都有部分内容不一样
 * created by geng
 */
@Data
@Component
@PropertySource(value = "classpath:application.yml") // 指定读取主配置文件
public class ClientTokenConfig {

    @Value("${client.id}")
    private String clientId;
    @Value("${client.rsa-secret}")
    private String clientSecret;
    @Value("${client.expire}")
    private int expire;
    @Value("${client.token-header}")
    private String tokenHeader;
    @Value("${spring.application.name}")
    private String applicationName;

    // -----------------------------------------------------
    // public method
    /**
     * 获取实际有效期
     * @return 实际有效期
     */
    public int realExpire () {
        return expire * 60000;
    }
}
