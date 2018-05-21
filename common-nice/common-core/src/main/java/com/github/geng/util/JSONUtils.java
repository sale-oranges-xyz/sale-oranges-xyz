package com.github.geng.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * com.fasterxml.jackson json处理
 * @author geng
 */
@Slf4j
public final class JSONUtils {
    // thread safe
    private static final ObjectMapper objectMapper = new ObjectMapper(); // create once, reuse

    /**
     * 获取json 数据，无法解析时返回null
     * @param value 对象
     * @return json 数据
     */
    public static /* synchronized */ String createJson(Object value) {
        try {
           return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("json转换异常", e);
            return null;
        }
    }

    /**
     * 解析json
     * @param value String 字符串
     * @param clazz 对应类
     * @param <T> 返回类型
     * @return java 对象
     */
    public static <T> T readValue (String value, Class<T> clazz) {
        try {
            return objectMapper.readValue(value, clazz);
        } catch (IOException e) {
            log.error("解析json异常", e);
            return null;
        }

    }

}
