package com.github.geng.mvc.controller;

import com.github.geng.exception.BizException;
import com.github.geng.exception.ServiceException;
import com.github.geng.response.ApiResponseData;
import com.github.geng.mvc.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用切面拦截全局异常
 * @author geng
 */
@ControllerAdvice // 可使用 basePackages 捕获特定包异常
@Slf4j
public class SysExceptionHandler {


    @ResponseBody
    @SuppressWarnings("unchecked")
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponseData<String>> ExceptionHandler(HttpServletRequest req, Exception e){
        String url = req.getRequestURI();

        log.error(String.format("路径 %s 请求异常", url),e);

        // 处理客户端输入数据异常
        if (e instanceof BizException) {
            return ResponseEntity.ok(ResponseUtil.success(e.getMessage()));
        }
        // 服务端异常,有不同状态
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException)e;
            // 处理服务内部异常
            return ResponseEntity
                    .status(serviceException.getStatus())
                    .body(ResponseUtil.success(serviceException.getMessage()));
        }
        // 最后，不知名异常
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseUtil.error(e.getMessage()));
    }

}

