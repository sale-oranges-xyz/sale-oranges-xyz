package com.github.geng.admin.mapper;

import com.github.geng.admin.entity.SysUser;
import com.github.geng.admin.dto.UserDto;
import com.github.geng.bread.NullOptional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author geng
 */
@Component
public class SysUserDtoMapper {

    // entity 转 传输数据 dto
    public UserDto entity2Dto(SysUser sysUser) {
        return NullOptional.validate(sysUser, UserDto::new);
    }

}
