package com.github.geng.admin.business.service.impl;

import com.github.geng.admin.business.entity.SysPermission;
import com.github.geng.admin.business.repository.SysUserRepository;
import com.github.geng.admin.business.service.SysUserService;
import com.github.geng.admin.business.entity.SysUser;
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

    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 缓存统一放在service层
     * @param loginName
     * @return
     */
    @Cacheable(value = "findByLoginName",key = "#loginName") // 使用内存缓存
    public SysUser findByLoginName(String loginName){
        return userRepository.findByLoginName(loginName);
    }

    @Override
    public List<SysPermission> findUserPermission(long userId) {
        // TODO 需要完善
        return new ArrayList<>();
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

}
