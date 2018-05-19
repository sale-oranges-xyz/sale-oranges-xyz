package com.github.geng.auth.center.service.impl;

import com.github.geng.admin.dto.UserDto;
import com.github.geng.admin.dto.UserLoginForm;
import com.github.geng.auth.center.feign.AdminUserService;
import com.github.geng.auth.center.service.UserAuthService;
import com.github.geng.response.ApiResponseEntity;
import com.github.geng.token.util.JwtTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author geng
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private JwtTokenManager jwtTokenManager;
    @Autowired
    private AdminUserService userService;

    @Override
    public String auth(UserLoginForm userLoginForm) {
        UserDto userDto = ApiResponseEntity.get200Body(userService.validate(userLoginForm));
        if (null != userDto) {
            return jwtTokenManager.generateToken(userDto.getLoginName(), userDto.getId());
        }
        return "";
    }

    @Override
    public String refresh(String oldToken) {
        // TODO
        return null;
    }

}
