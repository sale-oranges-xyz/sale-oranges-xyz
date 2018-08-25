package com.github.geng.admin.service.impl;

import com.github.geng.admin.repository.SysUserRepository;
import com.github.geng.admin.service.SysUserService;
import com.github.geng.admin.entity.SysUser;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geng
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService {

    private SysUserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Cacheable(value = "findByLoginName",key = "#loginName") // 使用内存缓存
    public SysUser findByLoginName(String loginName){
        return userRepository.findByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = false)
    public SysUser login(UserLoginForm userLoginForm) throws BizException{
        SysUser loginUser = this.findByLoginName(userLoginForm.getLoginName());
        if (null == loginUser) {
            throw new BizException("用户名不存在");
        }
        // 判断密码
        if (bCryptPasswordEncoder.matches(userLoginForm.getPassword(), loginUser.getPassword())) {
            // 保存用户登录信息
            loginUser.setLoginTime(System.currentTimeMillis());
            // 可以获取登录ip
            return userRepository.save(loginUser);
        }
        throw new BizException("用户名密码错误");
    }

    // -------------------------------------------------------------
    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
