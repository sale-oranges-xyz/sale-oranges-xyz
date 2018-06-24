package com.github.geng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @author geng
 */
@EnableAutoConfiguration
@EnableEurekaClient
@EnableConfigServer
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}
