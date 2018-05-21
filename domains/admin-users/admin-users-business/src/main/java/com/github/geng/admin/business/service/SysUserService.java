package com.github.geng.admin.business.service;

import com.github.geng.admin.business.entity.SysPermission;
import com.github.geng.admin.business.entity.SysUser;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.exception.BizException;

import java.util.List;

/**
 * @author geng
 */
public interface SysUserService {

//    /**
//     * 通过登陆名查找用户
//     * @param loginName 用户名
//     * @return 用户信息
//     */
//    SysUser findByLoginName(String loginName);

    /**
     * 获取用户访问权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<SysPermission> findUserPermission(long userId);

    /**
     * 用户登录
     * @param userLoginForm 表单信息
     * @return 用户记录
     */
    SysUser login(UserLoginForm userLoginForm) throws BizException;
}
