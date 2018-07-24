//package com.github.geng.auth.client.feign;
//
//import com.github.geng.auth.center.dto.ClientForm;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
///**
// * 微服务之间调用不配置token验证
// * @author geng
// */
//@FeignClient(value = "auth-center") // 默认 configuration 配置 FeignClientsConfiguration
//public interface ClientAuthFeign {
//
//    /**
//     * 获取token
//     * @return 获取token
//     */
//    @PostMapping(value = "/client/access-token")
//    String getAccessToken();
//
//    /**
//     * 获取允许访问的微服务列表
//     * @param clientForm 表单数据
//     * @return 允许访问微服务列表
//     */
//    @PostMapping(value = "/client/allowed-client")
//    List<String> getAllowedClient(ClientForm clientForm);
//
//
//    @PostMapping(value = "/client/access-token")
//    String freshToken(String token);
//}
