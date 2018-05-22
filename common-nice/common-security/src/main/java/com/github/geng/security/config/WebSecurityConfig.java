package com.github.geng.security.config;

import com.github.geng.security.filter.AuthenticationTokenFilter;
import com.github.geng.security.filter.EntryPointUnauthorizedHandler;
import com.github.geng.security.filter.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author geng
 */
@Configuration
@EnableWebSecurity // 启动security 配置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;
    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    /**
     * 白名单
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.css",
                "/**/*.js",
                "/**/*.png",
                "/**/*.jpg",
                "/**/fonts/**",
                "/images/**",
                "/**/*.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//由于使用的是JWT，我们这里不需要csrf
        http.headers().cacheControl();// 禁用缓存
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //基于token，所以不需要session
        http.authorizeRequests().antMatchers(  // swagger部分不需要鉴权
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs").permitAll()
        // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated();
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
        // 配置被拦截时的处理
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler) // 添加 token 无效或者没有携带 token 时的处理
            .accessDeniedHandler(this.accessDeniedHandler);      //添加无权限时的处理
    }

}
