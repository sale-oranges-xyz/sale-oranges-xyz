//package com.github.geng.auth.center.service;
//
//import com.github.geng.auth.center.entity.ServiceToken;
//
// 微服务之间调用不配置token验证
///**
// * 服务端token存储 service
// * @author geng
// */
//public interface SysTokenService {
//
//    /**
//     * 通过微服务名称查找token
//     * @param clientName 微服务名称
//     * @return token 存储实体类
//     */
//    ServiceToken  findByClientName(String clientName);
//
//    /**
//     * 删除token
//     * @param clientName 微服务名称
//     */
//    void deleteByClientName(String clientName);
//
//    /**
//     * 保存 token
//     * @param serviceToken 保存数据
//     */
//    void saveToken(ServiceToken serviceToken);
//}
