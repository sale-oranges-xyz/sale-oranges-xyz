package com.github.geng.mvc;

import com.github.geng.page.PageArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 继承 WebMvcConfigurerAdapter 这种做法在 spring5.0 已经被标记为过时
 * @author geng
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 分页数据获取处理
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageArgumentResolver());
    }

}
