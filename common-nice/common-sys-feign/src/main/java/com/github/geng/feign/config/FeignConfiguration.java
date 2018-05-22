package com.github.geng.feign.config;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geng
 */
@Configuration
@EnableFeignClients(basePackages = {"com.github.geng"}) //指定扫描路径
public class FeignConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new SysErrorDecoder();
    }

}
