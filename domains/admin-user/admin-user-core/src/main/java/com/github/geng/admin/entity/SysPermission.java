package com.github.geng.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统权限
 * @author geng
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_permission")
public class SysPermission extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private String permission;  // 权限标识
    private String url;         // 请求连接
    private String name;        // 权限名称
    private String method;      // 请求方式

}
