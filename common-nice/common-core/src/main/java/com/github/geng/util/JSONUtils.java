package com.github.geng.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * com.fasterxml.jackson json处理
 * @author geng
 */
public final class JSONUtils {
    // thread safe
    private static final ObjectMapper objectMapper = new ObjectMapper(); // create once, reuse

    /**
     * 获取json 数据，无法解析时返回null
     * 该方法同步
     * @param value 对象
     * @return json 数据
     */
    public static synchronized String createJson(Object value) {
        try {
            // 不确定该方法是否线程安全，使用同步
           return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
