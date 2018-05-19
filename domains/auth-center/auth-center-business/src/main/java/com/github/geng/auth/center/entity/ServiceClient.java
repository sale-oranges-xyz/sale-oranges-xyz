package com.github.geng.auth.center.entity;

import com.github.geng.entity.BaseLongIdTimeEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author geng
 */
@Data
@Table(name = "service_client")
public class ServiceClient extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private String serviceName;                // 服务名称
    private String description;                // 描述
    private String code;                       // 服务访问凭证
    @Column(name ="is_locked")
    private boolean locked = false;             // 是否锁定
    private String creator;                     // 创建人名
    private Long creator_id;                    // 创建人id
    private String modifier;                    // 修改人名称
    private Long modifier_id;                   // 修改人id

}
