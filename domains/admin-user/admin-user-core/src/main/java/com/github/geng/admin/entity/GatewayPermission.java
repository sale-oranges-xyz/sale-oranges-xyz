package com.github.geng.admin.entity;

import com.github.geng.entity.BaseLongIdEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 网关权限配置
 * @author geng
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_gw_permission")
public class GatewayPermission extends BaseLongIdEntity {

    private Date createdTime;               // 创建时间
    private String gatewayName;             // 网关名称

    @OneToOne
    @JoinColumn(name = "permission_id")
    private SysPermission sysPermission;   // 对应权限实体类

}
