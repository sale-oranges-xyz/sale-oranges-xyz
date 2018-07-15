package com.github.geng.token.exception;

import com.github.geng.constant.RestResponseConstants;
import com.github.geng.exception.BaseException;

/**
 * 用户登录状态异常时抛出的异常
 * @author geng
 */
public class NotLoginException extends BaseException {

    public NotLoginException (String message) {
        super(message, RestResponseConstants.USER_INVALID_TOKEN);
    }

}
