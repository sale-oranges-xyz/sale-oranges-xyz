package com.github.geng.admin.api;

import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.admin.dto.SysPermissionForm;
import com.github.geng.admin.entity.SysPermission;
import com.github.geng.admin.mapper.PermissionDtoMapper;
import com.github.geng.admin.service.SysPermissionService;
import com.github.geng.mvc.controller.BaseController;
import com.github.geng.response.ApiResponseData;
import com.github.geng.util.IdEncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author geng
 */
@RestController
@RequestMapping(value = "/permission")
@Api(value = "PermissionRestController", description = "权限管理api")
public class PermissionRestController extends BaseController {

    private SysPermissionService sysPermissionService;
    private PermissionDtoMapper permissionDtoMapper;

    @ApiOperation(value="分页查询权限", httpMethod = "GET", notes="权限管理api")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResponseData<Page<SysPermissionDto>> page(Pageable pageable, String searching) {
        Page<SysPermission> page = this.sysPermissionService.findPage(pageable, searching);
        List<SysPermissionDto> sysPermissionDtos = this.permissionDtoMapper.entityListToDtoList(page.getContent());
        return super.success(new PageImpl<>(sysPermissionDtos, pageable, page.getTotalElements()));
    }

    @ApiOperation(value="查询权限详情", httpMethod = "GET", notes="权限管理api")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponseData<SysPermissionDto> detail(@ApiParam(value = "记录id") @PathVariable String id) {
        SysPermission sysPermission = this.sysPermissionService.get(IdEncryptUtils.decode(id));
        return super.success(permissionDtoMapper.entityToDto(sysPermission));
    }

    @ApiOperation(value="新增权限", httpMethod = "POST", notes="权限管理api")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ApiResponseData<String> save(SysPermissionForm sysPermissionForm) {
        SysPermission sysPermission = this.sysPermissionService.save(sysPermissionForm);
        return super.success(sysPermission.getHashId());
    }

    @ApiOperation(value="修改权限", httpMethod = "PUT", notes="权限管理api")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ApiResponseData<Boolean> update(@ApiParam(value = "记录id") @PathVariable String id,
                                           SysPermissionForm sysPermissionForm) {
        boolean result = this.sysPermissionService.update(IdEncryptUtils.decode(id), sysPermissionForm);
        return super.success(result);
    }

    @ApiOperation(value="删除权限", httpMethod = "DELETE", notes="权限管理api")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResponseData<Boolean> delete(@ApiParam(value = "记录id") @PathVariable String id) {
        boolean result = this.sysPermissionService.delete(IdEncryptUtils.decode(id));
        return super.success(result);
    }

    // -----------------------------------------------------------------------------------
    // setters
    @Autowired
    public void setSysPermissionService(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }
    @Autowired
    public void setPermissionDtoMapper(PermissionDtoMapper permissionDtoMapper) {
        this.permissionDtoMapper = permissionDtoMapper;
    }
}
