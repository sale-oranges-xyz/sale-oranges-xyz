package com.github.geng.mvc.util;

import com.github.geng.response.ApiResponseData;
import org.springframework.http.HttpStatus;

/**
 * api 响应工具处理类
 * @author geng
 */
public class ResponseUtil {

    /**
     * 创建200返回数据
     * @param data 数据
     * @param <T> 数据类型
     * @return 响应数据
     */
    public static <T> ApiResponseData<T> success(T data) {
        return new ApiResponseData<>(HttpStatus.OK.value(), data);
    }

    /**
     * 创建500返回数据
     * @param message 消息
     * @return 响应数据
     */
    @SuppressWarnings("unchecked")
    public static ApiResponseData error(String message) {
        return new ApiResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

}
