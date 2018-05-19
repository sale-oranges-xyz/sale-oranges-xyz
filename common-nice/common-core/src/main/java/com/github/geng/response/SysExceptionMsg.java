package com.github.geng.response;

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
public class SysExceptionMsg {

    //========================================
    //fields
    private String message;         // 错误信息
    private Long timestamp;         // 错误时间
    private int status;             // 异常编号

}
