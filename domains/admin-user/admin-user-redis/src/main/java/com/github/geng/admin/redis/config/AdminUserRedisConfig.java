package com.github.geng.admin.redis.config;


/**
 * 后台管理员用户redis 常量配置
 * @author geng
 */
public class AdminUserRedisConfig {

    /**
     * key公共前缀
     */
    private static String COMMON_KEY = "admin:permission:";

    /**
     * 创建redis权限找角色的key
     * @param url 配置url
     * @param method http 请求方式
     * @return redis权限找角色的key
     */
    public static String createPermissionKey(String url, String method) {
        return COMMON_KEY + url.hashCode() + ":" + method;
    }

    /**
     * 创建用户找角色的key
     * @param userId 用户id
     * @return 用户找角色的key
     */
    public static String createAdminKey(String userId) {
        return COMMON_KEY + userId;
    }


//    public static void main(String[] args) {
//        String url = "/user/permission/{id}/1saveedd"; // 1148495403  273226002  358083604
//        System.out.println(url.hashCode());
//    }
}
