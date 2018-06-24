package com.github.geng.exception;

import com.github.geng.constant.RestResponseConstants;

/**
 * @author geng
 */
public class NotLoginException extends BaseException {

    public NotLoginException (String message) {
        super(message, RestResponseConstants.USER_INVALID_TOKEN);
    }

}
