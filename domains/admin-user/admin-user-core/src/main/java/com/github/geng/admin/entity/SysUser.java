package com.github.geng.admin.entity;

import com.github.geng.constant.DataConstants;
import com.github.geng.entity.BaseLongIdTimeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 系统用户
 * @author geng
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private String loginName;                   // 登录名
    private String password;                    // 密码
    private String userName;                    // 用户名
    private Long loginTime;                     // 登录时间
    private Long passwordResetTime;             // 最近一次密码修改时间
    private int status = DataConstants.ENABLE;   // 状态

    //参考 https://blog.csdn.net/maoyeqiu/article/details/50401294
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch= FetchType.EAGER)
//    @JoinTable(name = "sys_user_role",
//            joinColumns = {@JoinColumn(name ="user_id",referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
//    )
//    private Set<SysRole> sysRoleSet;             //用户角色集合

}
