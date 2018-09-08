package com.github.geng.admin.service.impl;

import com.github.geng.admin.dto.SysPermissionForm;
import com.github.geng.admin.entity.SysPermission;
import com.github.geng.admin.repository.SysPermissionRepository;
import com.github.geng.admin.service.SysPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author geng
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private SysPermissionRepository sysPermissionRepository;

    @Override
    // @Cacheable(value = "findUserPermission",key = "#userId")
    public List<SysPermission> findUserPermission(long userId) {
        return sysPermissionRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = false)
    public SysPermission save(SysPermissionForm sysPermissionForm) {
        SysPermission sysPermission = new SysPermission();
        Date date = new Date();
        BeanUtils.copyProperties(sysPermissionForm, sysPermission);
        sysPermission.setCreatedTime(date);
        sysPermission.setModifiedTime(date);

        SysPermission savedSysPermission = this.sysPermissionRepository.save(sysPermission);
        return savedSysPermission;
    }

    @Override
    public boolean update(long id, SysPermissionForm sysPermissionForm) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Page<SysPermission> findPage(Pageable pageable, String searching) {

        return null;
    }

    @Override
    public SysPermission get(long id) {
        return null;
    }

    // ---------------------------------------------------------------
    // setters
    @Autowired
    public void setSysPermissionRepository(SysPermissionRepository sysPermissionRepository) {
        this.sysPermissionRepository = sysPermissionRepository;
    }
}
