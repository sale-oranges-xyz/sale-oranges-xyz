package com.github.geng.admin.dto;

import com.github.geng.entity.BaseIdEncryptEntity;
import com.github.geng.entity.BaseLongIdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author geng
 */
@Data
@ApiModel("用户权限描述")
public class SysPermissionDto extends BaseIdEncryptEntity{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限标识")
    private String permission;              // 权限标识
    @ApiModelProperty(value = "请求链接")
    private String url;                     // 请求链接
    @ApiModelProperty(value = "权限名称")
    private String name;                    // 权限名称
    @ApiModelProperty(value = "请求方式")
    private String method;                  // 请求方式
    @ApiModelProperty(value = "创建时间")
    private Long createTime;                // 创建时间
    @ApiModelProperty(value = "最后修改时间")
    private Long modifyTime;                // 最后修改时间

    // -------------------------------------------------------
    // constructor
    public SysPermissionDto () {

    }
    // 多态使用
    public SysPermissionDto (BaseLongIdEntity baseLongIdEntity) {
        super(baseLongIdEntity);
    }

}
