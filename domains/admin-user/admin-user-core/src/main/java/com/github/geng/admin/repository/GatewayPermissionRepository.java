package com.github.geng.admin.repository;

import com.github.geng.admin.entity.GatewayPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author geng
 */
public interface GatewayPermissionRepository extends JpaRepository<GatewayPermission,Long> {

    List<GatewayPermission> findByGatewayName(String gatewayName);
}
