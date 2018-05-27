package com.github.geng.auth.center.controller;

import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.auth.center.feign.AdminUserService;
import com.github.geng.auth.center.service.UserAuthService;
import com.github.geng.mvc.controller.BaseController;
import com.github.geng.token.response.JwtAuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geng
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "UserAuthController", description = "用户端鉴权控制中心api")
public class UserAuthController extends BaseController {

    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation(value="用户获取token", httpMethod = "POST", notes="用户端鉴权控制中心api")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<JwtAuthenticationResponse>
            createAuthenticationToken(@RequestBody UserLoginForm userLoginForm) {
        String token =userAuthService.auth(userLoginForm);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @ApiOperation(value="用户刷新token", httpMethod = "POST", notes="用户端鉴权控制中心api")
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<JwtAuthenticationResponse> refreshToken () {
        String token = this.getUserToken();
        String newToken = userAuthService.refresh(token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(newToken));
    }


}
