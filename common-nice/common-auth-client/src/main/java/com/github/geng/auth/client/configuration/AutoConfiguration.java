package com.github.geng.auth.client.configuration;

import com.github.geng.token.config.ClientTokenConfig;
import org.springframework.context.annotation.Configuration;


/**
 * @author geng
 */
@Configuration
public class AutoConfiguration {

    public ClientTokenConfig ClientTokenConfig() {
        return new ClientTokenConfig();
    }
}
