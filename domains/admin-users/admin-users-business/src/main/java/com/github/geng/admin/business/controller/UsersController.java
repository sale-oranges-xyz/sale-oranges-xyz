package com.github.geng.admin.business.controller;

import com.github.geng.admin.business.entity.SysPermission;
import com.github.geng.admin.business.entity.SysUser;
import com.github.geng.admin.business.mapper.PermissionMapper;
import com.github.geng.admin.business.mapper.SysUserMapper;
import com.github.geng.admin.business.service.SysUserService;
import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.admin.dto.UserDto;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.constant.RestResponseConstants;
import com.github.geng.exception.BizException;
import com.github.geng.mvc.controller.BaseController;
import com.github.geng.token.response.JwtAuthenticationResponse;
import com.github.geng.token.util.JwtTokenManager;
import com.github.geng.util.IdEncryptUtils;
import com.github.geng.util.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author geng
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
@Api(value = "UsersController", description = "用户管理api")
public class UsersController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private JwtTokenManager jwtTokenManager;

    @ApiOperation(value="获取用户权限列表", httpMethod = "GET", notes="用户管理api")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResponseEntity<List<SysPermissionDto>> userPermissions
            (@ApiParam(value = "用户混淆id") @RequestParam String userId) {
        long id = IdEncryptUtils.decode(userId);
        List<SysPermission> userPermissions = sysUserService.findUserPermission(id);
        return ResponseEntity.ok(permissionMapper.entityListToDtoList(userPermissions));
    }

    @ApiOperation(value="用户登录", httpMethod = "POST", notes="用户管理api")
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLoginForm userLoginForm) {
        try {
            SysUser sysUser = sysUserService.login(userLoginForm);
            // 获取用户token
            String token = jwtTokenManager.generateToken(sysUser.getLoginName(), IdEncryptUtils.encode(sysUser.getId()));
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } catch (BizException e) {
            log.debug("用户:{}登录异常,原因:{}", JSONUtils.createJson(userLoginForm), e.getMessage());
            return ResponseEntity.status(RestResponseConstants.USER_UNKNOWN_ERROR).body(e.getMessage());
        }
    }

}
