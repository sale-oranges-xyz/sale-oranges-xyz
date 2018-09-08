package com.github.geng.admin.repository;

import com.github.geng.admin.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author geng
 */
public interface SysRoleRepository extends JpaRepository<SysRole,Long> {

    @Query(value = "select sr.* from sys_role sr " +
            "left join sys_user_role sur on sr.id = sur.role_id " +
            "where sur.user_id = ?1 ", nativeQuery = true)
    List<SysRole> findUserRole(long userId);
}
