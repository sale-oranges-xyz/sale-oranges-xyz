//package com.github.geng.auth.center.service.impl;
//
//import com.github.geng.auth.center.configuration.ClientKeyConfig;
//import com.github.geng.auth.center.dto.ClientForm;
//import com.github.geng.auth.center.entity.ServiceToken;
//import com.github.geng.auth.center.repository.AllowClientRepository;
//import com.github.geng.auth.center.service.ClientAuthService;
//import com.github.geng.auth.center.service.SysTokenService;
//import com.github.geng.auth.client.entity.event.JwtAuthenticationInfo;
//import com.github.geng.event.EventPublish;
//import com.github.geng.token.config.ClientTokenConfig;
//import com.github.geng.token.info.ServiceTokenInfo;
//import com.github.geng.token.impl.ServiceTokenManger;
//import com.github.geng.util.ListUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
// 微服务之间调用不配置token验证
///**
// * 服务端token service 实现
// * @author geng
// */
//@Service
//@Slf4j
//@Transactional(readOnly = true)
//public class ClientAuthServiceImpl implements ClientAuthService {
//
//    @Autowired
//    private ClientKeyConfig clientKeyConfig; // token 公钥私钥类
//    @Autowired
//    private ClientTokenConfig clientTokenConfig; // token 配置
//    @Autowired
//    private AllowClientRepository allowClientRepository;
//    @Autowired
//    private SysTokenService sysTokenService;
//    @Autowired
//    private ApplicationContext context;
//
//    @Override
//    @Transactional(readOnly = false)
//    public String createToken() {
//        // 如果数据库存储token未过期，返回未过期token
//        ServiceToken serviceToken = sysTokenService.findByClientName(clientTokenConfig.getClientId());
//        // 判断token 是否过期
//        if (null != serviceToken
//                && !ServiceTokenManger.isTokenExpired(serviceToken.getToken(), serviceToken.getPublicKey())) {
//            new EventPublish(context, this, new JwtAuthenticationInfo(serviceToken.getToken(), false)).publish();
//            return serviceToken.getToken();
//        }
//        // 如果数据库存储token过期，重新产生
//        ServiceTokenInfo serviceTokenInfo
//                = new ServiceTokenInfo(clientTokenConfig.getClientId(), clientTokenConfig.getApplicationName());
//        String token;
//        try {
//            token = ServiceTokenManger.generateToken(serviceTokenInfo, clientKeyConfig.getServicePriKey(), clientTokenConfig.realExpire());
//
//            // 发送事件通知
//            log.debug("token初始化成功，发送token相关事件通知");
//            new EventPublish(context, this, new JwtAuthenticationInfo(token)).publish();
//        } catch (Exception e) {
//            log.error("生成token失败，原因:", e);
//            token = "";
//        }
//        return token;
//    }
//
//    @Override
//    public List<String> allowedClient(ClientForm clientForm) {
//        return ListUtils.arrayListCheck(allowClientRepository.findByServiceId(clientForm.getClientId()));
//    }
//
//    @Override
//    @Transactional(readOnly = false)
//    public String refresh(String oldToken) {
//        String token;
//        try {
//            ServiceTokenInfo infoFromToken = ServiceTokenManger.getInfoFromToken(oldToken, clientKeyConfig.getServicePubKey());
//            // 重新生成token
//            token = ServiceTokenManger.generateToken(infoFromToken, clientKeyConfig.getServicePriKey(), clientTokenConfig.realExpire());
//
//            // 发送事件通知
//            log.debug("token初始化成功，发送token相关事件通知");
//            new EventPublish(context, this, new JwtAuthenticationInfo(token)).publish();
//        } catch (Exception e) {
//            log.error("token解析用户失败,原因:{}", e);
//            token = "";
//        }
//        return token;
//    }
//
//    @Override
//    public boolean validate(ClientForm clientForm) {
//        // TODO 暂时返回true
//        return true;
//    }
//
//}
