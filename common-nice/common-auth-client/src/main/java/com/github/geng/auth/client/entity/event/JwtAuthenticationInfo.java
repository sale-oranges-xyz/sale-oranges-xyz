//package com.github.geng.auth.client.entity.event;
//
//import com.github.geng.token.response.TokenAuthResponse;
//import lombok.Data;
//
// 微服务之间调用不配置token验证
///**
// * @author geng
// */
//@Data
//public class JwtAuthenticationInfo extends TokenAuthResponse {
//    private boolean isNew = true;              // 是否新产生的token
//
//    // --------------------------------------------------------
//    // constructors
//    public JwtAuthenticationInfo(String token) {
//        super(token);
//    }
//
//    public JwtAuthenticationInfo(String token, boolean isNew) {
//        super(token);
//        this.isNew = isNew;
//    }
//
//}
