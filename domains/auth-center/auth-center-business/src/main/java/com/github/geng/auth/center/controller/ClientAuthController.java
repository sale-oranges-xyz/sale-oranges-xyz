package com.github.geng.auth.center.controller;

import com.github.geng.auth.center.configuration.ClientKeyConfig;
import com.github.geng.auth.center.dto.ClientForm;
import com.github.geng.auth.center.service.ClientAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value="获取token", httpMethod = "POST", notes="服务端鉴权控制中心api")
    @RequestMapping(value = "/access-token", method = RequestMethod.POST)
    public ResponseEntity<String> getAccessToken() throws Exception {
        return ResponseEntity.ok(clientService.createToken());
    }

    @ApiOperation(value="获取允许访问的微服务列表", httpMethod = "POST", notes="服务端鉴权控制中心api")
    @RequestMapping(value = "/allowed-client", method = RequestMethod.POST)
    public ResponseEntity<List<String>> allowedClient(@RequestBody ClientForm clientForm) {
        return ResponseEntity.ok(clientService.allowedClient(clientForm));
    }

    @ApiOperation(value="获取token", httpMethod = "POST", notes="服务端鉴权控制中心api")
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<String> refreshToken(@ApiParam(value = "旧token") @RequestParam String token) {
        return ResponseEntity.ok(clientService.refresh(token));
    }
}
