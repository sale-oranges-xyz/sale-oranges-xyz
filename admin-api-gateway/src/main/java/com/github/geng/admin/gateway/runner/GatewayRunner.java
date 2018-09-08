package com.github.geng.admin.gateway.runner;

import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.admin.gateway.config.UrlMatcher;
import com.github.geng.admin.gateway.remote.AdminUserFeignService;
import com.github.geng.mvc.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 项目启动成功时调用
 * @author geng
 */
@Slf4j
@Configuration
public class GatewayRunner implements CommandLineRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    // 取出权限对应url
    // 使用restful风格api时，会出现类似 /user/{id}/permission 这样的配置
    private AdminUserFeignService adminUserService;
    private UrlMatcher urlMatcher;

    @Override
    public void run(String... args) throws Exception {
        List<SysPermissionDto> sysPermissionDtoList = ResponseUtil.extractData(
                this.adminUserService.list(applicationName)
        );
        if (CollectionUtils.isEmpty(sysPermissionDtoList)) {
            log.debug("微服务:{}获取对应url配置为空", applicationName);
        } else {
            // 初始化 SpringAntMatcher
            urlMatcher.init(sysPermissionDtoList);
        }
    }


    // ---------------------------------------------------------------
    // setters
    @Autowired
    public void setUrlMatcher(UrlMatcher urlMatcher) {
        this.urlMatcher = urlMatcher;
    }
    @Autowired
    public void setAdminUserService(AdminUserFeignService adminUserService) {
        this.adminUserService = adminUserService;
    }
}
