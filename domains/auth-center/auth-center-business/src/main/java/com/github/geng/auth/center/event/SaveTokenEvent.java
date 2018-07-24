//package com.github.geng.auth.center.event;
//
//import com.github.geng.auth.center.configuration.ClientKeyConfig;
//import com.github.geng.auth.center.entity.ServiceToken;
//import com.github.geng.auth.center.service.SysTokenService;
//import com.github.geng.auth.client.entity.event.JwtAuthenticationInfo;
//import com.github.geng.event.EventListener;
//import com.github.geng.event.EventPublish;
//import com.github.geng.token.config.ClientTokenConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
// 微服务之间调用不配置token验证
///**
// * token 产生成功事件回调,存入数据库
// * @author geng
// */
//@Component
//@Slf4j
//public class SaveTokenEvent extends EventListener {
//
//    @Autowired
//    private ClientKeyConfig clientKeyConfig; // token 公钥私钥类
//    @Autowired
//    private ClientTokenConfig clientTokenConfig; // token 配置
//    @Autowired
//    private SysTokenService sysTokenService; // token 处理类
//
//    @Override
//    protected boolean match(EventPublish event) {
//        return event.getContent() instanceof JwtAuthenticationInfo;
//    }
//
//    @Override
//    protected void bingo(Object content) {
//        JwtAuthenticationInfo jwtAuthenticationInfo = (JwtAuthenticationInfo)content;
//        if (null != jwtAuthenticationInfo && null != jwtAuthenticationInfo.getToken()
//                && jwtAuthenticationInfo.isNew()) {
//            log.debug("开始存储token");
//            // 删除原有记录
//            sysTokenService.deleteByClientName(clientTokenConfig.getApplicationName());
//            // 保存新数据
//            ServiceToken serviceToken = new ServiceToken();
//            serviceToken.setToken(jwtAuthenticationInfo.getToken());
//            serviceToken.setPublicKey(clientKeyConfig.getServicePubKey());
//            serviceToken.setPrivateKey(clientKeyConfig.getServicePriKey());
//            serviceToken.setExpire(clientTokenConfig.realExpire());
//            serviceToken.setClientName(clientTokenConfig.getApplicationName());
//            sysTokenService.saveToken(serviceToken);
//        }
//        log.debug("token不是系统新产生，不需要重新存储");
//    }
//
//}
