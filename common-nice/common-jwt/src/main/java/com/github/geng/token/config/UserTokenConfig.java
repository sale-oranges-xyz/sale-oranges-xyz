package com.github.geng.token.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * 用户 token 配置读取
 * @author geng
 */
@Data
@Configuration
@PropertySource(value = "classpath:jwtConfig.yml")
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields =false)
public class UserTokenConfig {

    @Value("${header}")
    private String header;
    @Value("${secret}")
    private String secret;
    @Value("${expiration}")
    private int expiration;
    @Value("${tokenHead}")
    private String tokenHeader;

    // -------------------------------------------------
    // public methods
    /**
     * 解析token
     * @param header 请求头获取的授权信息
     * @return 真正token
     */
    public String getToken(String header) {
        if (header.length() <= tokenHeader.length()) {
            return "";
        } else {
            return header.substring(header.length());
        }
    }

}
