package com.github.geng.admin.entity;

import com.github.geng.entity.BaseLongIdTimeEntity;
import com.github.geng.util.Constant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 系统角色
 * @author geng
 */
@Data
@NoArgsConstructor
@Entity(name = "sys_role")
public class SysRole extends BaseLongIdTimeEntity {

    private String name;                    // 角色名称
    private boolean isSuperAdmin;           // 是否超级管理员
    private int status = Constant.ENABLE;   // 状态

    //参考 https://blog.csdn.net/maoyeqiu/article/details/50401294
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "sys_role_permission",
//            joinColumns = {@JoinColumn(name ="role_id",referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")}
//    )
//    private Set<SysPermission> sysPermissionSet;
}
