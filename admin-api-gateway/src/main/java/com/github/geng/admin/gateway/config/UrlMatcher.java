package com.github.geng.admin.gateway.config;

import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.bread.CollectionOptional;
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
     * @param sysPermissionDtoList 网关可通过的配置url集合
     */
    public void init(List<SysPermissionDto> sysPermissionDtoList) {
        CollectionOptional.mapToArrayList(
                sysPermissionDtoList,
                (redisPermission) -> springAntMatcherList.add(new SpringAntMatcher(redisPermission.getUrl()))
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
