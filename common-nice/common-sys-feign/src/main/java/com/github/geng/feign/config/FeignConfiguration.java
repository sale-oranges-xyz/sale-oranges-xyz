package com.github.geng.feign.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geng
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new SysErrorDecoder();
    }

}
