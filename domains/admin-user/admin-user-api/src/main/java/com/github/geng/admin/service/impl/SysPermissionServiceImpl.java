package com.github.geng.admin.service.impl;

import com.github.geng.admin.entity.SysPermission;
import com.github.geng.admin.repository.SysPermissionRepository;
import com.github.geng.admin.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author geng
 */
@Service
@Transactional(readOnly = true)
public class SysPermissionServiceImpl implements SysPermissionService {

    private SysPermissionRepository sysPermissionRepository;




    // ---------------------------------------------------------------
    // setters
    @Autowired
    public void setSysPermissionRepository(SysPermissionRepository sysPermissionRepository) {
        this.sysPermissionRepository = sysPermissionRepository;
    }
}
