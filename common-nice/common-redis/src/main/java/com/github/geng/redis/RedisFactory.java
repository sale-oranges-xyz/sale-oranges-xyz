package com.github.geng.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author geng
 */
//高版本自定义配置文件处理
@Slf4j
@Configuration
@PropertySource(value = "classpath:redisConfig.yml")
@ConfigurationProperties(prefix = "redis")
public class RedisFactory {

    @Value("${hostname}")
    private String hostname;
    @Value("${port}")
    private int port;
    @Value("${password}")
    private String password;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostname);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<?, ?> getRedisTemplate() {
        return new StringRedisTemplate(this.connectionFactory());
    }

}
