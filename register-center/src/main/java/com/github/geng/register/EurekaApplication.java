package com.github.geng.register;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心配置
 */
@SpringBootApplication
@EnableEurekaServer //需要使用该注解，才能看见管理界面
public class EurekaApplication
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(EurekaApplication.class).web(true).run(args);
    }
}
