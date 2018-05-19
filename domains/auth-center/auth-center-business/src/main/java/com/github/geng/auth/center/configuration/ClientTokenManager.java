package com.github.geng.auth.center.configuration;

import com.github.geng.token.info.ServiceTokenInfo;
import com.github.geng.token.util.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author geng
 */
@Configuration
public class ClientTokenManager {

    @Value("${client.expire}")
    private int expire;         // token过期时间，单位：分钟

    @Autowired
    private ClientKeyConfig clientKeyConfig;

    public String generateToken(ServiceTokenInfo tokenInfo) throws Exception {
        return JWTHelper.generateToken(tokenInfo, clientKeyConfig.getServicePriKey(), expire);
    }

    public ServiceTokenInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, clientKeyConfig.getServicePubKey());
    }

}
