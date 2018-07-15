package com.github.geng.token.util;

import com.github.geng.exception.NotLoginException;
import com.github.geng.token.JWTConstants;
import com.github.geng.token.TokenService;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.token.constant.TokenConstants;
import com.github.geng.token.info.TokenInfo;
import com.github.geng.token.info.UserTokenInfo;
import com.github.geng.util.SysStringUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geng
 */
@Slf4j
@Component(value = "JwtTokenService")
public class JwtTokenManager implements TokenService {

    @Autowired
    private UserTokenConfig jwtConfig;

    @Override
    public TokenInfo parseToken(String token) throws NotLoginException{
        try {
            final Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                throw new NotLoginException("用户token已过期");
            }
            // 获取用户id
            String userId = SysStringUtil.getStringValue(claims.get(TokenConstants.RECORD_ID));
            return new TokenInfo(userId, claims.getSubject(), expiration);
        } catch (ExpiredJwtException e) {
            throw new NotLoginException("用户token已过期");
        } catch (SignatureException e) {
            throw new NotLoginException("用户token签名错误");
        } catch (IllegalArgumentException e){
            throw new NotLoginException("用户token无效");
        }
    }

    @Override
    public String getName(String token) {
        String username = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            log.error("根据token获取用户名失败,token:{}",token);
        }
        return username;
    }

    @Override
    public Date getCreatedTime(String token) {
        Date created = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(TokenConstants.CLAIM_KEY_CREATED));
        } catch (Exception e) {
            log.error("根据token获取创建时间失败,token:{}",token);
        }
        return created;
    }

    @Override
    public Date getExpirationDate(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
            log.error(String.format("根据token获取过期时间失败,token:%s", token), e);
        }
        return expiration;
    }

    /**
     * 判断token是否过期
     * @return true 过期 | false 未过期
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取token
     * @param loginName 用户登录名
     * @param userId 登陆用户id，注意加密
     * @return token
     */
    public String generateToken(String loginName, String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, loginName);
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(JWTConstants.JWT_USER_ID, userId);
        log.debug("根据用户名:{}获取token", loginName);
        return generateToken(claims);
    }

    /**
     * 判断token是否需要刷新
     * @param token token
     * @param lastPasswordReset 最近一次token产生时间
     * @return true | false
     */
    public boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token 旧token
     * @return 新token
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
            log.error("根据旧token刷新失败,旧token:{}",token);
        }
        return refreshedToken;
    }

    /**
     * token验证
     * @param token 系统产生的token
     * @param loginName 用户登录名
     * @param passwordResetTime 最近一次token产生时间
     * @return true | false
     */
    public Boolean validateToken(String token, String loginName, long passwordResetTime) {
        final String oldLoginName = getLoginNameFromToken(token);
        final Date created = getCreatedDateFromToken(token);

        return (
                oldLoginName.equals(loginName)
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, new Date(passwordResetTime)));
    }

    //=======================================================
    //private methods
    private Claims getClaimsFromToken(String token) {
        Claims claims;
//        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
//        } catch (Exception e) {
//            log.error("解析token异常",e);
//            claims = null;
//        }
        return claims;
    }

    private String generateToken(Map<String, Object> claims) {
        log.debug("开始产生token");
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 60000);
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

}
