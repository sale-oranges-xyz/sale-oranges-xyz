package com.github.geng.auth.center.controller;

import com.github.geng.auth.center.configuration.ClientKeyConfig;
import com.github.geng.auth.center.dto.ClientForm;
import com.github.geng.auth.center.service.ClientAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author geng
 */
@RestController
@RequestMapping(value = "/client")
@Api(value = "ClientController", description = "服务端鉴权控制中心api")
public class ClientAuthController {

    @Autowired
    private ClientAuthService clientService;
    @Autowired
    private ClientKeyConfig clientKeyConfig;

    @ApiOperation(value="获取token", httpMethod = "POST", notes="服务端鉴权控制中心api")
    @RequestMapping(value = "/access-token", method = RequestMethod.POST)
    public ResponseEntity<String> getAccessToken(@RequestBody ClientForm clientForm) throws Exception {
        return ResponseEntity.ok(clientService.apply(clientForm));
    }

    @ApiOperation(value="获取允许访问的微服务列表", httpMethod = "POST", notes="服务端鉴权控制中心api")
    @RequestMapping(value = "/allowed-client", method = RequestMethod.POST)
    public ResponseEntity<List<String>> allowedClient(@RequestBody ClientForm clientForm) {
        return ResponseEntity.ok(clientService.allowedClient(clientForm));
    }

    @ApiOperation(value="获取公钥", httpMethod = "POST", notes="服务端鉴权控制中心api")
    @RequestMapping(value = "/service-publickey",method = RequestMethod.POST)
    public ResponseEntity<String> servicePublicKey(@RequestBody ClientForm clientForm) {
        if (clientService.validate(clientForm)) {
            return ResponseEntity.ok(clientKeyConfig.getServicePubKey());
        }
        return ResponseEntity.badRequest().body("数据验证异常");
    }
}
