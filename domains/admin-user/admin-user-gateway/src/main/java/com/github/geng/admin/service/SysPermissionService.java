package com.github.geng.admin.service;

import com.github.geng.admin.entity.SysPermission;

import java.util.List;

/**
 * @author geng
 */
public interface SysPermissionService {

    /**
     * 获取用户访问权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<SysPermission> findUserPermission(long userId);

}
