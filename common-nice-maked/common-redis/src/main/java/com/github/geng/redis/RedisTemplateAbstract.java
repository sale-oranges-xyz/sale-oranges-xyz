package com.github.geng.redis;

import com.github.geng.redis.entity.RedisModel;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * <pre>
 * 模仿StringRedisTemplate
 * 当redisTemplate value存储使用对象时，可继承该类进行redisTemplate创建
 * 同时封装一些通用操作
 * 详情查看 {@link StringRedisTemplate}
 * </pre>
 * @param <T> T 为redisTemplate的value对象且继承了RedisModel
 * @author geng
 */
public abstract class RedisTemplateAbstract<T extends RedisModel> extends RedisTemplate<String, T>{

    // ---------------------------------------------------------------------------------------------
    // constructors
    @SuppressWarnings(value = "unchecked")
    public RedisTemplateAbstract() {
        // 利用反射获取泛型类的实例，看起来很麻烦
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
        // 初始化序列化工具
        Jackson2JsonRedisSerializer<T> jackson2JsonRedisSerializer
                = new Jackson2JsonRedisSerializer<>(clazz);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 初始化redisTemplate
        setKeySerializer(new StringRedisSerializer());
        setValueSerializer(jackson2JsonRedisSerializer);
        setHashKeySerializer(stringRedisSerializer);
        setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    public RedisTemplateAbstract (RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    // ---------------------------------------------------------------------------------------------

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

    // ---------------------------------------------------------------------------------------------
    // redis 封装操作部分
    /**
     * 根据key 查找全部redis list类型全部value,数据量大时慎用
     * @param key redis key
     * @return value 集合
     */
    public List<T> findAllOpsList(String key) {
        return this.opsForList().range(key, 0, -1);
    }
}
