package com.github.geng.admin.mapper;

import com.github.geng.admin.entity.SysPermission;
import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.bread.ListOptional;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity 与 dto 转换实体类
 * @author geng
 */
@Component
public class PermissionDtoMapper {

    /**
     * @param sysPermissionList list 实体类
     * @return list dto
     */
    public List<SysPermissionDto> entityListToDtoList(List<SysPermission> sysPermissionList) {
        return ListOptional.mapToArrayList(sysPermissionList, SysPermissionDto::new);
    }

}
