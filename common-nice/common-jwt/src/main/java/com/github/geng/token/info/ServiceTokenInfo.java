package com.github.geng.token.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 服务端 token 信息
 * @author geng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTokenInfo {

    private String id;        //微服务配置id
    private String serviceName; //微服务名称

}
