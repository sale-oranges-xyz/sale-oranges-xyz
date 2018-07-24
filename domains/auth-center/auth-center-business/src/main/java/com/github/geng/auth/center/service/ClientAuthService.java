//package com.github.geng.auth.center.service;
//
//import com.github.geng.auth.center.dto.ClientForm;
//
//import java.util.List;
//
// 微服务之间调用不配置token验证
///**
// * @author geng
// */
//public interface ClientAuthService {
//
//    /**
//     * 获取token
//     * @return token
//     */
//    String createToken();
//
//    /**
//     * 获取当前微服务允许访问的微服务
//     * @param clientForm
//     * @return
//     */
//    List<String> allowedClient(ClientForm clientForm);
//
//    /**
//     * 检验
//     * @param clientForm 表单数据
//     * @return true 通过 | false 不通过
//     */
//    boolean validate(ClientForm clientForm);
//
//    /**
//     * 刷新token
//     * @param oldToken 旧token
//     * @return 新token
//     */
//    String refresh(String oldToken);
//}
