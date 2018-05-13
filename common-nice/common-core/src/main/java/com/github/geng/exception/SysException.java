package com.github.geng.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局异常类
 * @author geng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysException {

    //========================================
    //fields
    private String message;         //错误信息
    private Long timestamp;         //错误时间

}
