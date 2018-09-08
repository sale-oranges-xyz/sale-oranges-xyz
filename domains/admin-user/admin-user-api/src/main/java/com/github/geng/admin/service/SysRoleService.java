package com.github.geng.admin.service;

import com.github.geng.admin.entity.SysRole;

import java.util.List;

/**
 * @author geng
 */
public interface SysRoleService {

    /**
     * 查询用户角色
     * @param userId 用户id
     * @return 用户角色
     */
    List<SysRole> loadByUserId(long userId);
}
