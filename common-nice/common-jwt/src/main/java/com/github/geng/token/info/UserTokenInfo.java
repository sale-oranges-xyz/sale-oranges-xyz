package com.github.geng.token.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author geng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenInfo {

    private String id;          // 用户id
    private String userName;    // 用户名
    private Date expireTime;    // 过期时间

}
