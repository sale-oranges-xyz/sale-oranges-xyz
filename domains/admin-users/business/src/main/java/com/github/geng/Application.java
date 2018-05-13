package com.github.geng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 微服务：系统管理用户启动类
 * @author geng
 */
@SpringBootApplication
@EnableSwagger2 //启用swagger配置
@EnableCaching //启用缓存
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
