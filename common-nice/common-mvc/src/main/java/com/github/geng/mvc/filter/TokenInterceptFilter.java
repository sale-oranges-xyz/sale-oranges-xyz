package com.github.geng.mvc.filter;

import com.github.geng.token.JWTConstants;
import com.github.geng.token.config.ClientTokenConfig;
import com.github.geng.token.config.UserTokenConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * <pre>
 *     根据配置条件动态注入
 *     参考 https://blog.csdn.net/tianyaleixiaowu/article/details/78201587
 * </pre>
 * @author geng
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = {"tokenFilter.enable"}, matchIfMissing = false) // 默认创建过滤器
public class TokenInterceptFilter {

    // token 配置bean
    @Autowired
    private ClientTokenConfig clientTokenConfig;

    /**
     * 自定义filter https://blog.csdn.net/small_mouse0/article/details/77840130
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean () {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TokenFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        // registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("tokenFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

    public class TokenFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.debug("初始化TokenFilter");
        }

        @Override
        public void doFilter(ServletRequest request,
                             ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
            // TODO 需要配置白名单
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            String token = httpServletRequest.getHeader(clientTokenConfig.getTokenHeader());
            if (StringUtils.hasText(token)) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse)response;
                httpServletResponse.sendError(JWTConstants.CLIENT_FORBIDDEN,"无效token，拒绝访问");
            }
        }

        @Override
        public void destroy() {

        }
    }
}
