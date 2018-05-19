package com.github.geng.exception;

import com.github.geng.response.SysExceptionMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用切面拦截全局异常
 * @author geng
 */
@ControllerAdvice
@Slf4j
public class SysExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<SysExceptionMsg> ExceptionHandler(HttpServletRequest req, Exception e){
        String url = req.getRequestURI();

        log.error(String.format("路径 %s 请求异常", url),e);
        SysExceptionMsg SysException = new SysExceptionMsg(e.getMessage(), System.currentTimeMillis());

        if (e instanceof BadCredentialsException) {// token异常，表示禁止访问
            return ResponseEntity.status(HttpStatus.OK).body(SysException);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(SysException);
    }

}
