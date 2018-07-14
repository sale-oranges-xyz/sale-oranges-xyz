package com.github.geng.security.member.impl;

import com.github.geng.security.extra.AuthenticationSerious;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geng
 */
@Component
public class AuthenticationSeriousImpl implements AuthenticationSerious {

    @Override
    public void authentication(String userAuthToken, HttpServletRequest httpRequest) throws Exception {
        // 验证用户手机号与验证码 等等其他用户端验证方式
    }

}
