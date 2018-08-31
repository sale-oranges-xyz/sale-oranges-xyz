package com.github.geng.admin.gateway.runner;

import com.github.geng.admin.gateway.config.UrlMatcher;
import com.github.geng.admin.redis.config.AdminUserRedisConfig;
import com.github.geng.admin.redis.entity.RedisPermission;
import com.github.geng.admin.redis.template.PermissionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 项目启动成功时调用
 * @author geng
 */
@Configuration
public class GatewayRunner implements CommandLineRunner {

    // 取出redis中的权限对应url
    // 使用restful风格api时，会出现类似 /user/{id}/permission 这样的配置
    private PermissionTemplate permissionTemplate;
    private UrlMatcher urlMatcher;

    @Override
    public void run(String... args) throws Exception {
        List<RedisPermission> redisPermissionList = permissionTemplate.findAllOpsList(AdminUserRedisConfig.GATEWAY_URL_KEY);
        // 初始化 SpringAntMatcher
        urlMatcher.init(redisPermissionList);
    }



    // ---------------------------------------------------------------
    // setters
    @Autowired
    public void setPermissionTemplate(PermissionTemplate permissionTemplate) {
        this.permissionTemplate = permissionTemplate;
    }
    @Autowired
    public void setUrlMatcher(UrlMatcher urlMatcher) {
        this.urlMatcher = urlMatcher;
    }
}
