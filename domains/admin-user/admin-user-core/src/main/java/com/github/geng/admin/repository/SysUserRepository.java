package com.github.geng.admin.repository;

import com.github.geng.admin.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author geng
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {

    /**
     * 通过登录名查找用户
     * @param loginName 登录名
     * @return 用户
     */
    SysUser findByLoginName(String loginName);

}

