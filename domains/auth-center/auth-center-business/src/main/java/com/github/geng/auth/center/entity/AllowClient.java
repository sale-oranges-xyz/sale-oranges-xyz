package com.github.geng.auth.center.entity;

import com.github.geng.entity.BaseLongIdTimeEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 服务访问白名单
 * @author geng
 */
@Data
@Table(name = "service_allow_clients")
public class AllowClient extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private Long serviceId;             // 服务id
    @Column(name = "is_locked")
    private boolean locked;             // 是否禁止访问
    private String clientName;          // 可访问服务名称
    private String secretKey;           // 密钥，跟密码类似
    private Long creatorId;             // 创建人
    private Long modifierId;            // 修改人

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ServiceClient client;     // 可访问服务

}
