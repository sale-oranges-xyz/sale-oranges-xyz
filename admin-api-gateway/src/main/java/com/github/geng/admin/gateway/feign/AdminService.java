package com.github.geng.admin.gateway.feign;

import com.github.geng.admin.dto.SysPermissionDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author geng
 */
@FeignClient(value = "admin-users")
public interface AdminService {

    @RequestMapping(value = "/user/permissions", method = RequestMethod.GET)
    List<SysPermissionDto> getUserPermissions();

}
