package com.github.geng.security.config;

import com.github.geng.security.entity.AuthorizeIgnore;
import com.github.geng.security.filter.AuthenticationTokenFilter;
import com.github.geng.security.filter.EntryPointUnauthorizedHandler;
import com.github.geng.security.filter.MyAccessDeniedHandler;
import com.github.geng.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author geng
 */
@Configuration
@EnableWebSecurity // 启动security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthorizeIgnore authorizeIgnore;

    /**
     * 白名单，验证忽略
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
        List<String> authorizeIgnoreList = authorizeIgnore.getConfigAuthorizeIgnores();

        http.csrf().disable();//由于使用的是JWT，不需要csrf
        http.headers().cacheControl();// 禁用缓存
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //基于token，所以不需要session

        http.authorizeRequests()
                // 忽略请求验证部分
                .antMatchers(authorizeIgnoreList.toArray(new String[authorizeIgnoreList.size()])).permitAll()
                // 所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
        // 配置被拦截时的处理
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler) // 添加 token 无效或者没有携带 token 时的处理
            .accessDeniedHandler(this.accessDeniedHandler);      //添加无权限时的处理
    }

    // 基础配置==========================================================================
    // 目前自定义filter必须用 AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 继承自AbstractAuthenticationProcessingFilter的filter，必须注入AuthenticationManager bean
     * 参考 http://wiki.jikexueyuan.com/project/spring-security/certification.html
     * @return 自定义token检查bean
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }
}
