package com.github.geng.admin.service;

import com.github.geng.admin.dto.SysPermissionForm;
import com.github.geng.admin.entity.SysPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 新增访问权限
     * @param sysPermissionForm 表单数据
     * @return 保存后数据
     */
    SysPermission save(SysPermissionForm sysPermissionForm);

    /**
     * 更新权限
     * @param id 记录id
     * @param sysPermissionForm 表单数据
     * @return true 删除成功 | false 删除失败
     */
    boolean update(long id, SysPermissionForm sysPermissionForm);

    /**
     * 删除权限
     * @param id 记录id
     * @return true 删除成功 | false 删除失败
     */
    boolean delete(long id);

    /**
     * 分页查询
     * @param pageable 分页信息
     * @param searching 查询内容
     * @return 分页数据
     */
    Page<SysPermission> findPage(Pageable pageable, String searching);

    /**
     * 查询权限详情
     * @param id 记录id
     * @return 权限详情
     */
    SysPermission get(long id);
}
