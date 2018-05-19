package com.github.geng.admin.business.controller;

import com.github.geng.admin.business.entity.SysPermission;
import com.github.geng.admin.business.entity.SysUser;
import com.github.geng.admin.business.mapper.PermissionMapper;
import com.github.geng.admin.business.mapper.SysUserMapper;
import com.github.geng.admin.business.service.SysUserService;
import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.admin.dto.UserDto;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.mvc.controller.BaseController;
import com.github.geng.token.info.UserTokenInfo;
import com.github.geng.util.IdEncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author geng
 */
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


    @ApiOperation(value="获取用户权限列表", httpMethod = "GET", notes="用户管理api")
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public ResponseEntity<List<SysPermissionDto>> userPermissions () {
        UserTokenInfo userTokenInfo = this.getUserTokenInfo();
        long userId = IdEncryptUtils.decode(userTokenInfo.getId());
        List<SysPermission> userPermissions = sysUserService.findUserPermission(userId);
        return ResponseEntity.ok(permissionMapper.entityListToDtoList(userPermissions));
    }

    @ApiOperation(value="用户登录", httpMethod = "POST", notes="用户管理api")
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ResponseEntity<UserDto> login(@RequestBody UserLoginForm userLoginForm) {
        SysUser sysUser = sysUserService.login(userLoginForm);
        return ResponseEntity.ok(sysUserMapper.entity2Dto(sysUser));
    }

}
