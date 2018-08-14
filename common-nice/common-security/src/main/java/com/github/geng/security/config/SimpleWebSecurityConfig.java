package com.github.geng.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/**
 * 最简单的spring security 配置
 * @author geng
 */
@Configuration
@EnableWebSecurity // 启动security
public class SimpleWebSecurityConfig extends WebSecurityConfigurerAdapter {

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
        http.csrf().disable();//由于使用的是JWT，不需要csrf
        http.headers().cacheControl();// 禁用缓存
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //基于token，所以不需要session
        http.authorizeRequests().anyRequest().permitAll();
    }
}
