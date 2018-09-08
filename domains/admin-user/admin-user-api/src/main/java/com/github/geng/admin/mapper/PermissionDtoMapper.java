package com.github.geng.admin.mapper;

import com.github.geng.admin.entity.SysPermission;
import com.github.geng.admin.dto.SysPermissionDto;
import com.github.geng.bread.CollectionOptional;
import com.github.geng.bread.NullOptional;
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
        return CollectionOptional.mapToArrayList(sysPermissionList, this::entityToDto);
    }

    /**
     * @param sysPermission 实体
     * @return dto
     */
    public SysPermissionDto entityToDto(SysPermission sysPermission) {
        return NullOptional.validate(sysPermission, SysPermissionDto::new);
    }
}
