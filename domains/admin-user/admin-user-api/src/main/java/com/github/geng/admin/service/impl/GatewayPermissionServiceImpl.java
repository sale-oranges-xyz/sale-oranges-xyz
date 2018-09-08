package com.github.geng.admin.service.impl;

import com.github.geng.admin.entity.GatewayPermission;
import com.github.geng.admin.repository.GatewayPermissionRepository;
import com.github.geng.admin.service.GatewayPermissionService;
import com.github.geng.dto.BaseForm;
import com.github.geng.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geng
 */
@Service
public class GatewayPermissionServiceImpl implements GatewayPermissionService {

    private GatewayPermissionRepository gatewayPermissionRepository;

    @Override
    public GatewayPermission save(BaseForm baseForm) throws BizException {
        return null;
    }

    @Override
    public boolean update(Long rowId, BaseForm baseForm) throws BizException {
        return false;
    }

    @Override
    public GatewayPermission get(Long rowId) {
        return null;
    }

    @Override
    public boolean delete(Long rowId) throws BizException {
        return false;
    }

    @Override
    public List<GatewayPermission> loadByName(String name) {
        return this.gatewayPermissionRepository.findByGatewayName(name);
    }

    // -------------------------------------------------------------------
    // setters
    @Autowired
    public void setGatewayPermissionRepository(GatewayPermissionRepository gatewayPermissionRepository) {
        this.gatewayPermissionRepository = gatewayPermissionRepository;
    }
}
