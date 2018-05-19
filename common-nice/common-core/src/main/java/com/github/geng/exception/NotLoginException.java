package com.github.geng.exception;

import com.github.geng.response.RestResponseConstants;

/**
 * @author geng
 */
public class NotLoginException extends BaseException {

    public NotLoginException (String message) {
        super(message, RestResponseConstants.USER_INVALID_TOKEN);
    }

}
