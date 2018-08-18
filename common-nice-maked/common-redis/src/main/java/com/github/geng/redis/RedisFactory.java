package com.github.geng.redis;

import com.github.geng.redis.config.ClusterConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * redis配置
 * @author geng
 */
//高版本自定义配置文件处理
@Slf4j
@Configuration
// @PropertySource(value = "classpath:redisConfig.yml")
// @ConfigurationProperties(prefix = "redis")
public class RedisFactory {

    private ClusterConfigurationProperties clusterProperties;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(
                new RedisClusterConfiguration(clusterProperties.getNodes())
        );
        // 设置集群密码，看了半天，源码里没有单个加点设置密码的处理过程
        jedisConnectionFactory.setPassword(clusterProperties.getPassword());
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> getRedisTemplate() {
        return new StringRedisTemplate(this.connectionFactory());
    }


    // ------------------------------------------------------------------------------------
    // setters
    @Autowired
    public void setClusterProperties(ClusterConfigurationProperties clusterProperties) {
        this.clusterProperties = clusterProperties;
    }
}
