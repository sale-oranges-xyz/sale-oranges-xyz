package com.github.geng.admin.business.service;

import com.github.geng.admin.entity.SysUser;

/**
 * @author geng
 */
public interface SysUserService {

    /**
     * 通过登陆名查找用户
     * @param loginName
     * @return
     */
    SysUser findByLoginName(String loginName);

}
