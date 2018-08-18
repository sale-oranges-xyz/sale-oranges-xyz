package com.github.geng.admin.repository;

import com.github.geng.admin.entity.SysPermission;
import com.github.geng.admin.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

