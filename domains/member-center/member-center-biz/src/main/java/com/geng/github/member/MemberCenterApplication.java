package com.geng.github.member;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author geng
 */
@SpringCloudApplication //该标签整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@EnableSwagger2 //启用swagger配置
@EnableCaching //启用缓存
public class MemberCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberCenterApplication.class,args);
    }

    // BCrypt加密算法
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
