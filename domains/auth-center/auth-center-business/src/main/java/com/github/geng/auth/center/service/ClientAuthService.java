package com.github.geng.auth.center.service;

import com.github.geng.auth.center.dto.ClientForm;

import java.util.List;

/**
 * @author geng
 */
public interface ClientAuthService {

    String apply(ClientForm clientForm);

    List<String> allowedClient(ClientForm clientForm);

    /**
     * 检验
     * @param clientForm 表单数据
     * @return true 通过 | false 不通过
     */
    boolean validate(ClientForm clientForm);
}
