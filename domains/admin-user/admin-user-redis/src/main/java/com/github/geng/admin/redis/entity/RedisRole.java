package com.github.geng.admin.redis.entity;

import com.github.geng.redis.entity.RedisModel;
import lombok.*;

/**
 * @author geng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisRole extends RedisModel {
    private static final long serialVersionUID = 1L;

    private String name;        // 角色名称
    private boolean isSuperAdmin; // 是否超级管理员

}
