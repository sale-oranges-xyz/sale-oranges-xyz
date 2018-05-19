package com.github.geng.auth.client.exception;

import com.github.geng.exception.BaseException;
import com.github.geng.token.JWTConstants;

/**
 * 服务端静止访问异常
 * Created by geng
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, JWTConstants.CLIENT_FORBIDDEN);
    }

}
