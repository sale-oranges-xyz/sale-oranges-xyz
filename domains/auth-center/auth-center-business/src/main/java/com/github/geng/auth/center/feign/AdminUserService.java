package com.github.geng.auth.center.feign;

import com.github.geng.admin.dto.UserDto;
import com.github.geng.admin.dto.UserLoginForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author geng
 */
@FeignClient(value = "admin-users")
public interface AdminUserService {

    @PostMapping(value = "/user/validate")
    UserDto validate(@RequestBody UserLoginForm userLoginForm);

}
