package com.github.geng.feign.config;

import com.github.geng.exception.BizException;
import com.github.geng.exception.ServiceException;
import com.github.geng.feign.exception.ExceptionInfo;
import com.github.geng.response.SysExceptionMsg;
import com.github.geng.util.JSONUtils;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * feign 调用全局异常处理
 * <p>
 *      原理参考 https://www.jianshu.com/p/f240ca7bb7c0
 *      实现参考 https://www.jianshu.com/p/e6ce817a7dd8
 *      异常类型定义参考 https://www.cnblogs.com/yish/p/5850813.html
 *  </p>
 * @author geng
 */
@Slf4j
public class SysErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (null != response.body()) {
                String body = Util.toString(response.body().asReader());
                log.error("feign调用出错,原因:{}", body);
                ExceptionInfo exceptionInfo = JSONUtils.readValue(body, ExceptionInfo.class);
                if (null != exceptionInfo.getException()) {
                    Class clazz = Class.forName(exceptionInfo.getException());
                    Object obj = clazz.newInstance();

                    String message = exceptionInfo.getMessage();
                    String targetMsg;
                    if (obj instanceof BizException) { // 异常完整信息需要返回客户端，客户端需要根据具体异常信息做处理
                        targetMsg = message.substring(message.indexOf("{"), message.indexOf("}") + 1);
                        return JSONUtils.readValue(targetMsg, BizException.class);
                    } else {
                        targetMsg=message.substring(message.indexOf(":"),message.length());
                        return new ServiceException(targetMsg);
                    }
                } else {
                    // 信息输出前端
                    return new ServiceException(exceptionInfo.getError(), exceptionInfo.getStatus());
                }
            }
        } catch (Exception e) {
            // 显式完整堆栈信息
            log.error("feign解析异常出错", e);
            return new ServiceException(e.getMessage());
        }
        return new ServiceException("今天的风儿有点喧嚣,跟管理员说说吧");
    }
}
