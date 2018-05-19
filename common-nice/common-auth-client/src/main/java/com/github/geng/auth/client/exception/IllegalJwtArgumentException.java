package com.github.geng.auth.client.exception;

import com.github.geng.exception.BaseException;

/**
 * token 参数异常
 * Created by geng
 */
public class IllegalJwtArgumentException extends BaseException {
    public IllegalJwtArgumentException(String s) {
        super(s);
    }
}
