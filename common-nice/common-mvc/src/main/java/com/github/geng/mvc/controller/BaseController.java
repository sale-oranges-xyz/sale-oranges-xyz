package com.github.geng.mvc.controller;

import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.token.info.UserTokenInfo;
import com.github.geng.token.impl.JwtTokenManager;
import com.github.geng.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geng
 */
public class BaseController {

    @Autowired
    private UserTokenConfig userTokenConfig;
    @Autowired
    private JwtTokenManager jwtTokenManager;

    /**
     * 从当前request 请求头中获取用户信息
     * @return 用户信息
     */
    protected UserTokenInfo getUserTokenInfo() {
        // 获取token
        HttpServletRequest request = RequestUtils.getRequest();
        String tokenHeader = request.getHeader(userTokenConfig.getHeader());
        String token = userTokenConfig.getToken(tokenHeader);
        return jwtTokenManager.getUserInfoFromToken(token);
    }

    /**
     * 从请求头中获取用户token
     * @return token
     */
    protected String getUserToken() {
        HttpServletRequest request = RequestUtils.getRequest();
        String tokenHeader = request.getHeader(userTokenConfig.getHeader());
        return userTokenConfig.getToken(tokenHeader);
    }

}
