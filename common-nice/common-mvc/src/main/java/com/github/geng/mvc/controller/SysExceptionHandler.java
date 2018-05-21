package com.github.geng.mvc.controller;

import com.github.geng.exception.BizException;
import com.github.geng.response.SysExceptionMsg;
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

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<SysExceptionMsg> ExceptionHandler(HttpServletRequest req, Exception e){
        String url = req.getRequestURI();

        log.error(String.format("路径 %s 请求异常", url),e);

        SysExceptionMsg sysException;
        // 处理客户端输入数据异常
        if (e instanceof BizException) {
            BizException bizException = (BizException)e;
            sysException = new SysExceptionMsg(e.getMessage(),
                System.currentTimeMillis(), bizException.getStatus());
            return ResponseEntity.ok(sysException);
        }
        sysException = new SysExceptionMsg(e.getMessage(),
                System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        // 处理服务内部异常
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sysException);
    }
}

