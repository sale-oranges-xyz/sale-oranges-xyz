package com.github.geng.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author geng
 */
@Data
@ApiModel("用户权限表单")
public class SysPermissionForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限标识")
    private String permission;              // 权限标识
    @ApiModelProperty(value = "请求链接")
    private String url;                     // 请求链接
    @ApiModelProperty(value = "权限名称")
    private String name;                    // 权限名称
    @ApiModelProperty(value = "请求方式")
    private String method;                  // 请求方式

}
