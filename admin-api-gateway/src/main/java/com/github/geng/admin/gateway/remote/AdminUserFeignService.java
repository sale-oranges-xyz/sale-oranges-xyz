package com.github.geng.admin.gateway.remote;

import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.response.ApiResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author geng
 */
@FeignClient(value = "admin-user")
public interface AdminUserFeignService {

    /**
     * 查找网关可通过url 配置
     * @param name 网关名称
     * @return 配置数据
     */
    @RequestMapping(value = "/gtwPermission/{name}/list", method = RequestMethod.GET)
    ApiResponseData<List<SysPermissionDto>> list(@PathVariable(value = "name") String name);

}
