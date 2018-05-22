package com.github.geng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 微服务：系统管理用户启动类
 * @author geng
 */
@SpringCloudApplication //该标签整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@EnableSwagger2 //启用swagger配置
@EnableCaching //启用缓存
public class AdminUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUserApplication.class,args);
    }

    // BCrypt加密算法
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
