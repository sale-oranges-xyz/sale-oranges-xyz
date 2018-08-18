package com.github.geng.mvc.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 封装spring web 的响应 ResponseEntity
 * @author geng
 */
@Setter
@Getter
@AllArgsConstructor
public class ApiResponseData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // --------------------------------------------------------------
    private HttpStatus httpStatus;
    private T data;
    // --------------------------------------------------------------
    // constructors
    public ApiResponseData(T data) {
        super();
        this.httpStatus = HttpStatus.OK;
        this.data = data;
    }

    // --------------------------------------------------------------
    // methods
    /**
     * 创建系统逻辑错误响应类
     * @param message 错误消息
     * @return 响应类
     */
    public static ApiResponseData error(String message) {
        return new ApiResponseData(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
