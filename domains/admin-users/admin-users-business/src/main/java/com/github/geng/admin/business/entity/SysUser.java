package com.github.geng.admin.business.entity;

import com.github.geng.entity.BaseLongIdTimeEntity;
import com.github.geng.constant.DataConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * 系统用户
 * @author geng
 */
@Data
@NoArgsConstructor
@Entity(name="sys_user")
public class SysUser extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private String loginName;                   // 登录名
    private String password;                    // 密码
    private String userName;                    // 用户名
    private Long loginTime;                     // 登录时间
    private Long passwordResetTime;             // 最近一次密码修改时间
    private int status = DataConstant.ENABLE;   // 状态

    //参考 https://blog.csdn.net/maoyeqiu/article/details/50401294
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name ="user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private Set<SysRole> sysRoleSet;             //用户角色集合

}
