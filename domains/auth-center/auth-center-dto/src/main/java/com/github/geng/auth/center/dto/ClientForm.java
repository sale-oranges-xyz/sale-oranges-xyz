package com.github.geng.auth.center.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author geng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "请求form")
public class ClientForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务记录id")
    private String clientId;
    @ApiModelProperty(value = "密钥")
    private String secret;
    // ==============================================

}
