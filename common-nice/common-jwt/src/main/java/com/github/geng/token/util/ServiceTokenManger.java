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

import java.util.Date;


/**
 * 非对称加密的token处理工具类
 * created by geng
 */
@Slf4j
public class ServiceTokenManger {
    private static RsaKeyManager rsaKeyHelper = new RsaKeyManager();

    /**
     * 密钥加密token
     * @param jwtInfo 加密信息
     * @param priKeyPath 私钥路径
     * @param expire 有效期
     * @return token
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
     * @param jwtInfo 加密信息
     * @param priKey 私钥
     * @param expire 有效期, 单位：秒
     * @return token
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
        log.debug("公钥解析token");
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
        log.debug("字节数组公钥解析token");
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

    /**
     * 判断token 是否过期
     * @param token token
     * @param pubKey 公钥
     * @return true 过期 | false 未过期
     */
    public static boolean isTokenExpired(String token, byte[] pubKey) {
        try {
            Claims claims = parserToken(token, pubKey).getBody();
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            log.error("解析token 异常", e);
            return true;
        }
    }
}
