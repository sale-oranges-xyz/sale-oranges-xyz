package com.github.geng.admin.business.repository;

import com.github.geng.admin.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author geng
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    /**
     * 通过登录名查找用户
     * @param loginName
     * @return
     */
    SysUser findByLoginName(String loginName);
}
