package com.github.geng.admin.service;

import com.github.geng.admin.entity.GatewayPermission;
import com.github.geng.service.CRUDService;

import java.util.List;

/**
 * 网关配置url service
 * @author geng
 */
public interface GatewayPermissionService extends CRUDService<GatewayPermission, Long> {

    /**
     * 根据网关查找配置url
     * @param name 网关名称
     * @return 网关配置url集合
     */
    List<GatewayPermission> loadByName(String name);
}
