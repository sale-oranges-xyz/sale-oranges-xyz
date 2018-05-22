package com.github.geng;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author geng
 */
@EnableSwagger2 //启用swagger配置
@SpringCloudApplication //该标签整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
public class AuthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }

}
