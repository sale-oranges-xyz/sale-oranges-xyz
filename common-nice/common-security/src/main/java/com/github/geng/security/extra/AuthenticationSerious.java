package com.github.geng.security.extra;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义验证策略
 * @author geng
 */
public interface AuthenticationSerious {

    /**
     * 验证token，随便你怎么玩
     * @param userAuthToken 获取的用户token
     * @param httpRequest request 请求
     * @throws Exception 验证异常
     */
    void authentication(String userAuthToken, HttpServletRequest httpRequest) throws Exception;
}
