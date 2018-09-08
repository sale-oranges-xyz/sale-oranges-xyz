package com.github.geng.admin.service;

import com.github.geng.admin.entity.SysUser;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.exception.BizException;


/**
 * @author geng
 */
public interface SysUserService {

    /**
     * 通过登陆名查找用户
     * @param loginName 用户名
     * @return 用户信息
     */
    SysUser findByLoginName(String loginName);

    /**
     * 用户登录
     * @param userLoginForm 表单信息
     * @return 用户记录
     */
    SysUser login(UserLoginForm userLoginForm) throws BizException;
}
