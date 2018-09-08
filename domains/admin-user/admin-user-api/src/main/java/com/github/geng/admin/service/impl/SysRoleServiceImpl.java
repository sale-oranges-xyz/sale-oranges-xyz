package com.github.geng.admin.service.impl;

import com.github.geng.admin.entity.SysRole;
import com.github.geng.admin.repository.SysRoleRepository;
import com.github.geng.admin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geng
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private SysRoleRepository sysRoleRepository;

    @Override
    public List<SysRole> loadByUserId(long userId) {
        return this.sysRoleRepository.findUserRole(userId);
    }


    // ---------------------------------------------------------------
    // setters
    @Autowired
    public void setSysRoleRepository(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }
}
