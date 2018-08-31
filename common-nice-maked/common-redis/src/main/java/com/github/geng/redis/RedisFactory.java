package com.github.geng.redis;

import com.github.geng.redis.config.ClusterConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * redis配置
 * @author geng
 */
//高版本自定义配置文件处理
// @PropertySource(value = "classpath:redisConfig.yml")
// @ConfigurationProperties(prefix = "redis")
@Configuration
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
    public StringRedisTemplate getRedisTemplate() {
        return new StringRedisTemplate(this.connectionFactory());
    }

    // ------------------------------------------------------------------------------
    // setters
    @Autowired
    public void setClusterProperties(ClusterConfigurationProperties clusterProperties) {
        this.clusterProperties = clusterProperties;
    }
}
