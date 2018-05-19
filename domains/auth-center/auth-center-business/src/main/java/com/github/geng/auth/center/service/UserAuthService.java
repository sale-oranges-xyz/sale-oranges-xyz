package com.github.geng.auth.center.service;

import com.github.geng.admin.dto.UserLoginForm;

/**
 * @author geng
 */
public interface UserAuthService {

    /**
     * 用户授权
     * @param userLoginForm 表单信息
     * @return token
     */
    String auth(UserLoginForm userLoginForm);

    /**
     * 用户刷新token
     * @param oldToken 旧token
     * @return 新token
     */
    String refresh(String oldToken);

}
