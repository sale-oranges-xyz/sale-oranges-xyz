package com.github.geng.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author geng
 */
//高版本自定义配置文件处理
@Configuration
@PropertySource(value = "classpath:redisConfig.yml")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    @Value("${hostname}")
    private String hostname;
    @Value("${port}")
    private int port;
    @Value("${password}")
    private String password;

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostname);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        return jedisConnectionFactory;
    }

}
