//package com.github.geng.auth.client.configuration;
//
//import com.github.geng.matcher.SpringAntMatcher;
//import com.github.geng.util.SysStringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
// 微服务之间调用不配置token验证
///**
// * feign 请求系统配置
// * @author geng
// */
//@Slf4j
//@Configuration
//@PropertySource(value = "classpath:FeignConfig.yml")
//@ConfigurationProperties(prefix = "feign", ignoreUnknownFields =false)
//public class FeignIgnore {
//    private List<SpringAntMatcher> urlMatcher;
//
//    @Value("${ignore-url}")
//    private String ignoreUrl;       // 不需要在请求头加入 token 的请求url  多个英文逗号,隔开，ant 写法
//
//    public boolean isMatch (String path) {
//        if (!StringUtils.hasText(ignoreUrl) && "null".equals(ignoreUrl)) {
//            return false;
//        }
//        init();
//        boolean result = urlMatcher.stream().anyMatch(springAntMatcher -> springAntMatcher.matches(path));
//        log.debug("feign请求 {} 匹配结果:{}", path, result);
//        return result;
//    }
//
//    // ------------------------------------------
//    // private method
//    private void init() {
//        // 减少初始化次数
//        if (null == urlMatcher) {
//            urlMatcher = new ArrayList<>();
//            String[] ignoreUrls = SysStringUtil.splitBySpecificSeparate(ignoreUrl, ",");
//            for (String str : ignoreUrls) {
//                log.debug("添加{}到路径匹配", str);
//                urlMatcher.add(new SpringAntMatcher(str));
//            }
//        }
//    }
//}
