package com.github.geng.admin.api;

import com.github.geng.admin.entity.SysUser;
import com.github.geng.admin.mapper.PermissionDtoMapper;
import com.github.geng.admin.mapper.SysUserDtoMapper;
import com.github.geng.admin.service.SysPermissionService;
import com.github.geng.admin.service.SysUserService;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.exception.BizException;
import com.github.geng.mvc.controller.BaseController;
import com.github.geng.response.ApiResponseData;
import com.github.geng.mvc.util.ResponseUtil;
import com.github.geng.token.TokenService;
import com.github.geng.token.response.TokenAuthResponse;
import com.github.geng.util.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author geng
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "UserRestController", description = "用户管理api")
public class UserRestController extends BaseController {

    private SysUserService sysUserService;
    private PermissionDtoMapper permissionMapper;
    private SysUserDtoMapper sysUserMapper;
    private TokenService tokenService;
    private SysPermissionService permissionService;


    @ApiOperation(value="用户登录", httpMethod = "POST", notes="用户管理api")
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ApiResponseData<TokenAuthResponse> login(@RequestBody UserLoginForm userLoginForm) {
        try {
            SysUser sysUser = sysUserService.login(userLoginForm);
            // 获取用户token
            String token = tokenService.generateToken(
                    sysUser.getLoginName(),
                    sysUser.getHashId()
            );
            return ResponseUtil.success(new TokenAuthResponse(token));
        } catch (BizException e) {
            logger.debug("用户:{}登录异常,原因:{}", JSONUtil.createJson(userLoginForm), e.getMessage());
            return super.error(e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // constructors
    @Autowired
    public UserRestController(SysUserService sysUserService,
                              PermissionDtoMapper permissionMapper,
                              SysUserDtoMapper sysUserMapper,
                              TokenService tokenService,
                              SysPermissionService permissionService) {
        this.permissionMapper = permissionMapper;
        this.sysUserMapper = sysUserMapper;
        this.sysUserService = sysUserService;
        this.tokenService = tokenService;
        this.permissionService = permissionService;
    }
}
