package com.github.geng.constant;

/**
 * 系统常量配置
 * @author geng
 */
public class DataConstant {
    private DataConstant(){}

    //==================================
    //状态
    /**
     * 禁用标志
     */
    public static final int DISABLE = 0;
    /**
     * 启用标志
     */
    public static final int ENABLE = 1;
    /**
     * 删除状态
     */
    public static final int DELETE = 2;
    // --------------------------------------------------------
    // 提示语
    public static final String NO_FOUND = "查无对应记录";
    // --------------------------------------------------------
    // feign 常量
    public static final String FROM_CLIENT = "yes";
    public static final String CLIENT_NAME = "clientName";
    public static final String IS_CLIENT = "isClient";

    // --------------------------------------------------------
    // token 部分常量配置
    public static final String JWT_USER_ID = "userId";
    public static final String JWT_USER_NAME = "name";
    
}
