package com.github.geng.admin.api;

import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.admin.entity.GatewayPermission;
import com.github.geng.admin.mapper.PermissionDtoMapper;
import com.github.geng.admin.service.GatewayPermissionService;
import com.github.geng.bread.CollectionOptional;
import com.github.geng.mvc.controller.BaseController;
import com.github.geng.response.ApiResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author geng
 */
@RestController
@RequestMapping(value = "/gtwPermission")
@Api(value = "GtwPermissionRestController", description = "网关管理api")
public class GtwPermissionRestController extends BaseController {

    private GatewayPermissionService gatewayPermissionService;
    private PermissionDtoMapper permissionDtoMapper;


    @RequestMapping(value = "/{name}/list", method = RequestMethod.GET)
    @ApiOperation(value="查找网关配置url", httpMethod = "GET", notes="网关管理api")
    public ApiResponseData<List<SysPermissionDto>> list(@ApiParam(value = "网关名称") @PathVariable String name) {
        List<GatewayPermission> gatewayPermissionList = this.gatewayPermissionService.loadByName(name);
        List<SysPermissionDto> sysPermissionDtoList = CollectionOptional.mapToArrayList(
                gatewayPermissionList,
                (gatewayPermission) -> permissionDtoMapper.entityToDto(gatewayPermission.getSysPermission())
        );
        return this.success(sysPermissionDtoList);
    }


    // ---------------------------------------------------------------------
    // setters
    @Autowired
    public void setGatewayPermissionService(GatewayPermissionService gatewayPermissionService) {
        this.gatewayPermissionService = gatewayPermissionService;
    }
    @Autowired
    public void setPermissionDtoMapper(PermissionDtoMapper permissionDtoMapper) {
        this.permissionDtoMapper = permissionDtoMapper;
    }
}
