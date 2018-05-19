package com.github.geng.response;


/**
 * 系统自定义响应状态码
 * @author geng
 */
public class RestResponseConstants {
    private RestResponseConstants() { }

    /**
     * 用户token无效
     */
    public static int USER_INVALID_TOKEN = 50001;
    /**
     * 用户token 禁止访问
     */
    public static int USER_FORBIDDEN_TOKEN = 500403;


}
