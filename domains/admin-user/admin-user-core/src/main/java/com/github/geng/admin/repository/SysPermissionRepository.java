package com.github.geng.admin.repository;

import com.github.geng.admin.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author geng
 */
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

    @Query(value = "SELECT sp.* from sys_user_role sur " +
            "left join sys_role_permission srp on sur.role_id = srp.role_id " +
            "left join sys_permission sp on sp.id = srp.permission_id " +
            "where sur.user_id = ?1 ",nativeQuery = true)
    List<SysPermission> findByUserId(long userId);

}
