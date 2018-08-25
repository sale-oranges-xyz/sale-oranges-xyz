package com.github.geng.mvc.exception;

import com.github.geng.exception.ServiceException;
import org.springframework.http.HttpStatus;

/**
 * @author geng
 */
public class ExceptionUtil {

    /**
     * 创建服务器端500异常
     * @param message
     * @return
     */
    public static ServiceException createServiceException(String message) {
        return new ServiceException(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
