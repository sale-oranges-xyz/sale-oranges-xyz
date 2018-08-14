package com.github.geng.redis.impl;

import com.github.geng.cache.ICacheService;
import com.github.geng.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * redis 实现
 * @author geng
 */
@Slf4j
@Service
public class RedisServiceImpl implements ICacheService {

    private RedisTemplate<String, String> redisTemplate;

    @Override
    public <T> Optional<T> get(String key, Class<T> clazz) {
        String result = this.get(key);
        return Optional.ofNullable(JSONUtil.readValue(result, clazz));
    }

    @Override
    public String get(String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
    }

    @Override
    public boolean set(String key, String value) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
    }

    @Override
    public boolean set(String key, Object value) {
        String strValue = JSONUtil.createJson(value);
        return this.set(key, strValue);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        String value = JSONUtil.createJson(list);
        return this.set(key,value);
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clz) {
        return JSONUtil.readListValue(this.get(key));
    }

    // --------------------------------------------------------------
    // setters
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
