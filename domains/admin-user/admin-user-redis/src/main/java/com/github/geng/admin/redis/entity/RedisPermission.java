package com.github.geng.admin.redis.entity;

import com.github.geng.redis.entity.RedisModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisPermission extends RedisModel {

    private String url;

}
