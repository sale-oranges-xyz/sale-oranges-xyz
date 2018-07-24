//package com.github.geng.auth.client.schedule;
//
//import com.github.geng.auth.center.dto.ClientForm;
//import com.github.geng.auth.client.entity.event.AllowedClient;
//import com.github.geng.auth.client.feign.ClientAuthFeign;
//import com.github.geng.event.EventListener;
//import com.github.geng.event.EventPublish;
//import com.github.geng.token.config.ClientTokenConfig;
//import com.github.geng.token.response.TokenAuthResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
// 微服务之间调用不配置token验证
///**
// * token定时刷新工具类
// * @author geng
// */
//@Configuration
//@Slf4j
//@EnableScheduling // 开启任务调度
//public class ClientAuthSchedule extends EventListener {
//
//    @Autowired
//    private ClientAuthFeign clientAuthFeign;
//    @Autowired
//    private ClientTokenConfig clientTokenConfig;
//
//    private String clientToken; // 因为这里token对每个线程都是一样，所以可以这样使用。注意spring bean 单例
//    private List<String> allowedClient;
//
//    public String getClientToken() {
//        log.debug("获取微服务:{} token", clientTokenConfig.getApplicationName());
//        if (!StringUtils.hasText(clientToken)) {
//            this.refreshClientToken();
//        }
//        return clientToken;
//    }
//
//    /**
//     * 任务调度刷新token 信息，配置参考 https://www.cnblogs.com/softidea/p/5833248.html
//     */
//    @Scheduled(cron = "0 59 * * * ?") // 每小时59分更新一次
//    public void refreshClientToken() {
//        log.debug("刷新微服务:{} token",clientTokenConfig.getApplicationName());
//        // ClientForm clientForm = new ClientForm(clientTokenConfig.getClientId(), clientTokenConfig.getClientSecret());
//        clientToken = clientAuthFeign.getAccessToken();
//    }
//
//    public List<String> getAllowedClient() {
//        log.debug("获取微服务:{}可微服务访问列表",clientTokenConfig.getApplicationName());
//        if (null == allowedClient) {
//            this.refreshAllowedClient();
//        }
//        return allowedClient;
//    }
//
//    public void refreshAllowedClient() {
//        log.debug("刷新微服务:{}可微服务访问列表",clientTokenConfig.getApplicationName());
//        ClientForm clientForm = new ClientForm(clientTokenConfig.getClientId(), clientTokenConfig.getClientSecret());
//        allowedClient = clientAuthFeign.getAllowedClient(clientForm);
//    }
//
//    // -------------------------------------------------------------
//    // 事件通知, 由 auth-center runner 下的 AuthClientRunner 发送
//    @Override
//    protected boolean match(EventPublish event) {
//        if (event.getContent() instanceof TokenAuthResponse) {
//            log.debug("接收事件通知,获取token");
//            return true;
//        }
//        if (event.getContent() instanceof AllowedClient) {
//            log.debug("接收事件通知,获取可访问微服务列表");
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    protected void bingo(Object content) {
//        if (content instanceof TokenAuthResponse) {
//            log.debug("开始获取微服务:{} token", clientTokenConfig.getApplicationName());
//            TokenAuthResponse jwtAuthenticationResponse = (TokenAuthResponse)content;
//            clientToken = jwtAuthenticationResponse.getToken();
//        }
//
//        if (content instanceof AllowedClient) {
//            log.debug("开始获取微服务:{} 可访问服务列表", clientTokenConfig.getApplicationName());
//            AllowedClient allowedClientContent = (AllowedClient)content;
//            allowedClient = allowedClientContent.getList();
//        }
//    }
//}
