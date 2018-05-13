package com.github.geng.admin.business.service.impl;

import com.github.geng.admin.business.repository.SysUserRepository;
import com.github.geng.admin.business.service.SysUserService;
import com.github.geng.admin.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author geng
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    /**
     * 缓存统一放在service层
     * @param loginName
     * @return
     */
    @Cacheable(value = "findByLoginName",key = "#loginName") // 使用内存缓存
    public SysUser findByLoginName(String loginName){
        return userRepository.findByLoginName(loginName);
    }

}
