package com.github.geng.admin.business.mapper;

import com.github.geng.admin.business.entity.SysPermission;
import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.bread.ListOptional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity 与 dto 转换实体类
 * @author geng
 */
@Component
public class PermissionMapper {

    /**
     * @param sysPermissionList list 实体类
     * @return list dto
     */
    public List<SysPermissionDto> entityListToDtoList(List<SysPermission> sysPermissionList) {
        return ListOptional.createArrayList(sysPermissionList, sysPermission -> {
            SysPermissionDto permissionDto = new SysPermissionDto(sysPermission);
            BeanUtils.copyProperties(sysPermission, permissionDto,  "id");
            return permissionDto;
        });
    }

}
