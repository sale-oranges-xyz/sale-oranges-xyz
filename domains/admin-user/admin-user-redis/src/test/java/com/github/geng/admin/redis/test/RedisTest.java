package com.github.geng.admin.redis.test;

import com.github.geng.admin.redis.config.AdminUserRedisConfig;
import com.github.geng.admin.redis.entity.RedisRole;
import com.github.geng.admin.redis.template.PermissionRoleTemplate;
import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author geng
 */
public class RedisTest {

//    private JedisConnectionFactory createJedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName("127.0.0.1");
//        jedisConnectionFactory.setPassword("123456");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//    /**
//     * 添加权限角色到redis
//     */
//    @Test
//    public void test001() {
//        JedisConnectionFactory jedisConnectionFactory = this.createJedisConnectionFactory();
//
//        PermissionRoleTemplate permissionRoleTemplate = new PermissionRoleTemplate();
//        permissionRoleTemplate.setConnectionFactory(jedisConnectionFactory);
//        permissionRoleTemplate.afterPropertiesSet();
//
//        RedisRole redisRole1 = new RedisRole("ROLE_ADMIN", false);
//
//        permissionRoleTemplate.opsSetAdd(
//                AdminUserRedisConfig.createPermissionKey("/permission/{id}", "DELETE"),
//                redisRole1
//        );
//
//        permissionRoleTemplate.opsSetAdd(
//                AdminUserRedisConfig.createPermissionKey("/permission/list", "GET"),
//                redisRole1
//        );
//
//        permissionRoleTemplate.opsSetAdd(
//                AdminUserRedisConfig.createPermissionKey("/permission/", "POST"),
//                redisRole1
//        );
//
//        permissionRoleTemplate.opsSetAdd(
//                AdminUserRedisConfig.createPermissionKey("/permission/{id}", "PUT"),
//                redisRole1
//        );
//
//        permissionRoleTemplate.opsSetAdd(
//                AdminUserRedisConfig.createPermissionKey("/permission/{id}", "GET"),
//                redisRole1
//        );
//    }
//
//    /**
//     * 测试2
//     */
//    @Test
//    public void test002() {
//        RedisRole redisRole1 = new RedisRole("ROLE_ADMIN", false);
//        RedisRole redisRole2 = new RedisRole("ROLE_ADMIN", false);
//
//        System.out.println("redisRole1: "+ redisRole1.hashCode());
//        System.out.println("redisRole2: "+ redisRole2.hashCode());
//    }
}
