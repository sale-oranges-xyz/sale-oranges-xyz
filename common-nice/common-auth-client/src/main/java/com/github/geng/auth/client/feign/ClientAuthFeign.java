package com.github.geng.auth.client.feign;

import com.github.geng.auth.center.dto.ClientForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author geng
 */
@FeignClient(value = "auth-center")
public interface ClientAuthFeign {

    /**
     * 获取token
     * @param clientForm 表单数据
     * @return 获取token
     */
    @PostMapping(value = "/client/access-token")
    ResponseEntity<String> getAccessToken(ClientForm clientForm);

    /**
     * 获取允许访问的微服务列表
     * @param clientForm 表单数据
     * @return 允许访问微服务列表
     */
    @PostMapping(value = "/client/allowed-client")
    ResponseEntity<List<String>> getAllowedClient(ClientForm clientForm);


}
