package com.github.geng.admin.dto;

import com.github.geng.entity.BaseDtoEntity;
import com.github.geng.entity.BaseLongIdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author geng
 */
@Data
@ApiModel("用户信息描述")
public class UserDto extends BaseDtoEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录名")
    private String loginName;                   // 登录名
    @ApiModelProperty(value = "用户名")
    private String userName;                    // 用户名
    @ApiModelProperty(value = "登录时间")
    private Long loginTime;                     // 登录时间

    // ----------------------------------------------------
    // constructor
    public UserDto () {
        super();
    }
    public UserDto(BaseLongIdEntity baseLongIdEntity) {
        super(baseLongIdEntity);
        BeanUtils.copyProperties(baseLongIdEntity, this, "id");
    }

}
