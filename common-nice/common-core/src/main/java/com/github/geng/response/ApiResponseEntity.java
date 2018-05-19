package com.github.geng.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 封装spring web 的ResponseEntity
 * @author geng
 */
@Slf4j
public class ApiResponseEntity {

    /**
     * 获取http 200 返回数据
     * @param responseEntity
     * @param <T>
     * @return
     */
    public static <T> T get200Body(ResponseEntity<T> responseEntity) {
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            log.error("获取http请求结果失败，原因：{}", responseEntity.getBody());
            return null;
        }
    }

}
