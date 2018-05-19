package com.github.geng.auth.center.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author geng
 */
@Data
@Configuration
public class ClientKeyConfig {

    @Value("${client.rsaSecret}")
    private String serviceSecret;
    private String servicePriKey;
    private String servicePubKey;

}
