package com.github.geng.admin.gateway.config;

import com.github.geng.admin.redis.entity.RedisPermission;
import com.github.geng.bread.ListOptional;
import com.github.geng.matcher.SpringAntMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author geng
 */
@Component
public class UrlMatcher {
    
    private List<SpringAntMatcher> springAntMatcherList = new ArrayList<>();

    /**
     * 初始化
     * @param redisPermissionList redis缓存的url集合
     */
    public void init(List<RedisPermission> redisPermissionList) {
        ListOptional.mapToArrayList(
                redisPermissionList,
                (redisPermission) -> springAntMatcherList.add(new SpringAntMatcher(redisPermission.getUrl(), true))
        );
    }

    /**
     * 查找具体url匹配的ant表达式
     * @param url 具体url
     * @return ant表达式
     */
    public String findPattern(String url) {
        Optional<SpringAntMatcher> firstMatch = springAntMatcherList.stream()
                .filter((springAntMatcher) -> springAntMatcher.matches(url))
                .findFirst();
        return firstMatch.isPresent() ? firstMatch.get().getPattern() : url;
    }
}
