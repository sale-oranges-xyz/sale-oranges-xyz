package com.github.geng.auth.client.exception;

import com.github.geng.exception.BaseException;

/**
 * token 过期异常
 * Created by geng
 */
public class JwtTokenExpiredException extends BaseException {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
