//package com.github.geng.token.impl;
//
//import com.github.geng.token.TokenService;
//import com.github.geng.token.config.ClientKeyConfig;
//import com.github.geng.token.config.ClientTokenConfig;
//import com.github.geng.token.constant.TokenConstants;
//import com.github.geng.token.info.TokenInfo;
//import com.github.geng.token.util.RsaKeyManager;
//import com.github.geng.util.SysStringUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//
///**
// * 微服务之间调用不配置token验证
// * 非对称加密的token处理工具类
// * created by geng
// */
//@Slf4j
//@Component(value = "ServiceTokenManger")
//public class ServiceTokenManger implements TokenService {
//    private static RsaKeyManager rsaKeyHelper = new RsaKeyManager();
//
//    @Autowired
//    private ClientTokenConfig clientTokenConfig;
//    @Autowired
//    private ClientKeyConfig clientKeyConfig;
//
//    @Override
//    public TokenInfo parseToken(String token) {
//        try {
//            Jws<Claims> claimsJws = this.parserToken(token);
//            Claims body = claimsJws.getBody();
//            return new TokenInfo(
//                    body.getSubject(),
//                    SysStringUtil.getStringValue(body.get(TokenConstants.RECORD_NAME)),
//                    null
//            );
//        } catch (Exception e) {
//            log.error("服务端解析token异常,原因:", e);
//            return null;
//        }
//    }
//
//    @Override
//    public String generateToken(String recordId, String recordName) {
//        try {
//            return Jwts.builder()
//                    .setSubject(recordId)
//                    .claim(TokenConstants.RECORD_NAME, recordName)
//                    .claim(TokenConstants.CLAIM_KEY_CREATED, new Date())
//                    .setExpiration(DateTime.now().plusSeconds(clientTokenConfig.getExpire()).toDate())
//                    .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(clientKeyConfig.getServicePriKey()))
//                    .compact();
//        } catch (Exception e) {
//            log.error("使用公钥私钥产生token异常,原因:", e);
//            return "";
//        }
//    }
//
//    @Override
//    public String refreshToken(String oldToken) {
//        try {
//            Jws<Claims> claimsJws = this.parserToken(oldToken);
//            Claims body = claimsJws.getBody();
//            return this.generateToken(
//                    body.getSubject(),
//                    SysStringUtil.getStringValue(body.get(TokenConstants.RECORD_NAME))
//            );
//        } catch (Exception e) {
//            log.error("服务端解析token异常,原因:", e);
//            return "";
//        }
//    }
//
//    @Override
//    public String getName(String token) {
//        try {
//            Jws<Claims> claimsJws = this.parserToken(token);
//            return SysStringUtil.getStringValue(claimsJws.getBody().get(TokenConstants.RECORD_NAME));
//        } catch (Exception e) {
//            log.error("服务端解析token异常,原因:", e);
//            return "";
//        }
//    }
//
//    @Override
//    public Date getCreatedTime(String token) {
//        try {
//            final Jws<Claims> claims = this.parserToken(token);
//            return new Date((Long)claims.getBody().get(TokenConstants.CLAIM_KEY_CREATED));
//        } catch (Exception e) {
//            log.error("服务端解析token异常,原因:", e);
//            return null;
//        }
//    }
//
//    @Override
//    public Date getExpirationDate(String token){
//        try {
//            Claims claims = parserToken(token).getBody();
//            return claims.getExpiration();
//        } catch (Exception e) {
//            log.error("服务端解析token异常,原因:", e);
//            return null;
//        }
//    }
//
//    @Override
//    public boolean isTokenExpired(String token) {
//        try {
//            Claims claims = parserToken(token).getBody();
//            Date expiration = claims.getExpiration();
//            return expiration.before(new Date());
//        } catch (Exception e) {
//            log.error("解析token 异常", e);
//            return true;
//        }
//    }
//
//    // ----------------------------------------------------------------------------
//    // private methods
//    private Jws<Claims> parserToken(String token) throws Exception{
//        return Jwts.parser()
//                .setSigningKey(rsaKeyHelper.getPublicKey(clientKeyConfig.getServicePubKey()))
//                .parseClaimsJws(token);
//    }
//
//}
