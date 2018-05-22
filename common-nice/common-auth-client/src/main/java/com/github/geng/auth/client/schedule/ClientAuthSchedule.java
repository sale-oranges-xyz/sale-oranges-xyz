package com.github.geng.auth.client.schedule;

import com.github.geng.auth.center.dto.ClientForm;
import com.github.geng.auth.client.feign.ClientAuthFeign;
import com.github.geng.response.ApiResponseEntity;
import com.github.geng.token.config.ClientTokenConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * token定时刷新工具类
 * @author geng
 */
@Configuration
@Slf4j
@EnableScheduling // 开启任务调度
public class ClientAuthSchedule {

    @Autowired
    private ClientAuthFeign clientAuthFeign;
    @Autowired
    private ClientTokenConfig clientTokenConfig;

    private String clientToken; // 因为这里token对每个线程都是一样，所以可以这样使用。注意spring bean 单例
    private List<String> allowedClient;

    public String getClientToken() {
        log.debug("获取微服务:{} token", clientTokenConfig.getApplicationName());
        if (!StringUtils.hasText(clientToken)) {
            this.refreshClientToken();
        }
        return clientToken;
    }

    /**
     * 刷新token 信息，配置参考 https://www.cnblogs.com/softidea/p/5833248.html
     */
    @Scheduled(cron = "0 0 */1 * * ?") // 每小时更新一次
    public void refreshClientToken() {
        log.debug("刷新微服务:{} token",clientTokenConfig.getApplicationName());
        ClientForm clientForm = new ClientForm(clientTokenConfig.getClientId(), clientTokenConfig.getClientSecret());
        clientToken = clientAuthFeign.getAccessToken(clientForm);
    }

    public List<String> getAllowedClient() {
        log.debug("获取微服务:{}可微服务访问列表",clientTokenConfig.getApplicationName());
        if (CollectionUtils.isEmpty(allowedClient)) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }

    public void refreshAllowedClient() {
        log.debug("刷新微服务:{}可微服务访问列表",clientTokenConfig.getApplicationName());
        ClientForm clientForm = new ClientForm(clientTokenConfig.getClientId(), clientTokenConfig.getClientSecret());
        allowedClient = clientAuthFeign.getAllowedClient(clientForm);
    }

}
