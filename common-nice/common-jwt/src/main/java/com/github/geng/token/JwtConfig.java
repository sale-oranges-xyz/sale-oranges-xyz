package com.github.geng.token;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * token 配置读取
 * @author geng
 */
@Data
@Configuration
@PropertySource(value = "classpath:jwtConfig.yml")
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields =false)
public class JwtConfig {

    @Value("${header}")
    private String header;
    @Value("${secret}")
    private String secret;
    @Value("${expiration}")
    private int expiration;
    @Value("${tokenHead}")
    private String tokenHead;

}
