package com.github.geng.admin.business.mapper;

import com.github.geng.admin.business.entity.SysUser;
import com.github.geng.admin.dto.UserDto;
import com.github.geng.bread.NullOptional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author geng
 */
@Component
public class SysUserMapper {

    // entity 转 传输数据 dto
    public UserDto entity2Dto(SysUser sysUser) {
        return NullOptional.validate(sysUser, user -> {
            UserDto userDto = new UserDto(user);
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        });
    }

}
