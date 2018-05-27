package com.github.geng.auth.center.entity;

import com.github.geng.entity.BaseLongIdTimeEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author geng
 */
@Data
@Entity
@Table(name = "service_client")
// 注意@Table与@Entity区别，参考 https://www.cnblogs.com/alsf/p/7822241.html
public class ServiceClient extends BaseLongIdTimeEntity {
    private static final long serialVersionUID = 1L;

    private String serviceName;                // 服务名称
    private String description;                // 描述
    private String creator;                    // 创建人名称
    private Long creator_id;                   // 创建人id
    private String modifier;                   // 修改人名称
    private Long modifier_id;                  // 修改人id

}
