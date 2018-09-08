package com.github.geng.admin.service.impl;

import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.admin.entity.SysRole;
import com.github.geng.admin.redis.config.AdminUserRedisConfig;
import com.github.geng.admin.redis.entity.RedisRole;
import com.github.geng.admin.redis.template.UserRoleTemplate;
import com.github.geng.admin.repository.SysUserRepository;
import com.github.geng.admin.service.SysRoleService;
import com.github.geng.admin.service.SysUserService;
import com.github.geng.admin.entity.SysUser;
import com.github.geng.bread.CollectionOptional;
import com.github.geng.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


/**
 * @author geng
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRoleTemplate userRoleTemplate;
    private SysRoleService sysRoleService;

    @Override
    // @Cacheable(value = "findByLoginName",key = "#loginName") // 使用内存缓存
    public SysUser findByLoginName(String loginName){
        return this.userRepository.findByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = false)
    public SysUser login(UserLoginForm userLoginForm) throws BizException{
        SysUser loginUser = this.findByLoginName(userLoginForm.getLoginName());
        if (null == loginUser) {
            log.warn("用户名:{}不存在，无法登录", userLoginForm.getLoginName());
            throw new BizException("用户名不存在");
        }
        // 判断密码
        if (this.bCryptPasswordEncoder.matches(userLoginForm.getPassword(), loginUser.getPassword())) {
            // 保存用户登录信息
            loginUser.setLoginTime(System.currentTimeMillis());
            SysUser sysUser = this.userRepository.save(loginUser);
            // 查询用户角色
            List<SysRole> sysRoleList = this.sysRoleService.loadByUserId(sysUser.getId());
            // 用户角色存入redis，供网关使用
            String userRoleKey = AdminUserRedisConfig.createAdminKey(sysUser.getHashId());
            // 先删除key内容
            this.userRoleTemplate.delete(userRoleKey);
            // 重新存入用户角色
            List<RedisRole> redisRoleList = CollectionOptional.mapToArrayList(
                    sysRoleList,
                    (sysRole) -> new RedisRole(sysRole.getName(), sysRole.isSuperAdmin())
            );
            this.userRoleTemplate.opsSetAdd(userRoleKey, redisRoleList);
            log.debug("用户名:{},登录成功", userLoginForm.getLoginName());
            return sysUser;
        }
        log.warn("用户名:{}密码错误，无法登录", userLoginForm.getLoginName());
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
    @Autowired
    public void setUserRoleTemplate(UserRoleTemplate userRoleTemplate) {
        this.userRoleTemplate = userRoleTemplate;
    }
    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }
}
