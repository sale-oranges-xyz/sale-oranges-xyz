package com.github.geng.auth.client.exception;

import com.github.geng.exception.BaseException;

/**
 * token 签名异常
 * Created by geng
 */
public class JwtSignatureException extends BaseException {
    public JwtSignatureException(String s) {
        super(s);
    }
}
