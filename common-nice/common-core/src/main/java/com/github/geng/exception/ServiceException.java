package com.github.geng.exception;

import org.springframework.http.HttpStatus;

/**
 * 服务端因为代码不严谨，出现的无法捕捉异常
 * @author geng
 */
public class ServiceException extends BaseException{

    public ServiceException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public ServiceException(String message, int code) {
        super(message, code);
    }
}
