package com.github.geng.auth.center.entity;

import com.github.geng.entity.BaseLongIdTimeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author geng
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "service_token")
public class ServiceToken extends BaseLongIdTimeEntity {

    private String token;                   // token
    private int expire;                     // 有效期，单位:秒
    private byte[] privateKey;              // 私钥字节数组
    private byte[] publicKey;               // 公钥字节数组
    private String clientName;              // 微服务名称

}
