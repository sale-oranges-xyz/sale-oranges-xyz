package com.github.geng.auth.center.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author geng
 */
@Data
@Configuration
@PropertySource(value = "classpath:application.yml") // 指定读取主配置文件
public class ClientKeyConfig {

    @Value("${client.rsa-secret}")
    private String serviceSecret;
    private String servicePriKey;
    private String servicePubKey;

}
