package com.github.geng.token.util;

import com.github.geng.token.JWTConstants;
import com.github.geng.token.info.ServiceTokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


/**
 * 非对称加密的token处理工具类
 * created by geng
 */
@Component
@Slf4j
public class JWTHelper {
    private static RsaKeyManager rsaKeyHelper = new RsaKeyManager();

    /**
     * 密钥加密token
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(ServiceTokenInfo jwtInfo, String priKeyPath, int expire) throws Exception {
        log.debug("加密token,有效期:{}",expire);
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getId())
                .claim(JWTConstants.JWT_USER_NAME, jwtInfo.getServiceName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                .compact();
        return compactJws;
    }

    /**
     * 密钥加密token
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(ServiceTokenInfo jwtInfo, byte priKey[], int expire) throws Exception {
        log.debug("加密token,有效期:{}",expire);
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getId())
                .claim(JWTConstants.JWT_USER_NAME, jwtInfo.getServiceName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        log.debug("公钥:{}解析token:{}", pubKeyPath, token);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        log.debug("字节数组公钥解析token:{}", token);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 获取token中的用户信息
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static ServiceTokenInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
        log.debug("获取token中的用户信息");
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        return new ServiceTokenInfo(body.getSubject(), StringUtil.getObjectValue(body.get(JWTConstants.JWT_USER_NAME)));
    }
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static ServiceTokenInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        log.debug("获取token中的用户信息");
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new ServiceTokenInfo(body.getSubject(), StringUtil.getObjectValue(body.get(JWTConstants.JWT_USER_NAME)));
    }
}
