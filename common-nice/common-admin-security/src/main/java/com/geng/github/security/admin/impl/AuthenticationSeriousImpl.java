package com.geng.github.security.admin.impl;

import com.github.geng.security.extra.AuthenticationSerious;
import com.github.geng.token.util.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geng
 */
@Component
public class AuthenticationSeriousImpl implements AuthenticationSerious {

    @Autowired
    private JwtTokenManager jwtTokenManager; // 用户端token管理工具

    @Override
    public void authentication(String userAuthToken, HttpServletRequest httpRequest) throws Exception {
        jwtTokenManager.getUserInfoFromToken(userAuthToken);
    }

}
