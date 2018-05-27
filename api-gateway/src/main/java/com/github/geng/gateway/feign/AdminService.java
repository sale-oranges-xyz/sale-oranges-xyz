package com.github.geng.gateway.feign;


import com.github.geng.admin.dto.SysPermissionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author geng
 */
public interface AdminService {

    @GetMapping(value = "/user/permissions")
    List<SysPermissionDto> getUserPermissions(String userId);

}
