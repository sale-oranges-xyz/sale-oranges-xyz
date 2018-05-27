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
public class ClientKeyConfig {

    private byte[] servicePriKey;
    private byte[] servicePubKey;

}
