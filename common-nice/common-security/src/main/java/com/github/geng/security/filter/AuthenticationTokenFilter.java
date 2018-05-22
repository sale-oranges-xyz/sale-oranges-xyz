package com.github.geng.security.filter;

import com.github.geng.exception.BizException;
import com.github.geng.token.config.ClientTokenConfig;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.token.util.ServiceTokenManger;
import com.github.geng.token.util.JwtTokenManager;
import com.github.geng.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * token 过滤器，初版
 * @author geng
 */
@Component
@Slf4j
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private UserTokenConfig userTokenConfig; // 用户端token
    @Autowired
    private ClientTokenConfig clientTokenConfig; // 客户端token
    @Autowired
    private JwtTokenManager jwtTokenManager; // 用户端token管理工具
    @Autowired
    private ServiceTokenManger clientTokenManger; // 服务端token管理工具

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 将 ServletRequest 转换为 HttpServletRequest 才能拿到请求头中的 token
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 这里要想办法区分是用户token还是服务端token
        // 尝试获取请求头的 token
        String userAuthToken = httpRequest.getHeader(userTokenConfig.getTokenHeader());
        String clientAuthToken = httpRequest.getHeader(clientTokenConfig.getTokenHeader());
        // 没有用户token和服务端token
        if (!StringUtils.hasText(userAuthToken) && !StringUtils.hasText(clientAuthToken)) {
            log.debug("无法从请求路径:{}获取token", httpRequest.getRequestURI());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            BizException bizException = new BizException("token无效", HttpStatus.FORBIDDEN.value());
            response.getWriter().print(JSONUtils.createJson(bizException));
        } else {
            chain.doFilter(request, response);
        }
    }

}
