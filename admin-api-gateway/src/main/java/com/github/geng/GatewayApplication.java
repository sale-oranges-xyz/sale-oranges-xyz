package com.github.geng;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * api网关配置
 * @author geng
 */
@EnableZuulProxy //启动zuul网关
@SpringCloudApplication //该标签整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@EnableCaching // 启用缓存
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
