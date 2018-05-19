package com.github.geng;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * api网关配置
 * @author geng
 */
@EnableZuulProxy //启动zuul网关
@SpringCloudApplication //该标签整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@EnableFeignClients //开启feign，需要手动显示声明，而且feign底层的负载均衡，需要服务注册到注册中心才能使用
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
