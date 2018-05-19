package com.github.geng.auth.center.service.impl;

import com.github.geng.auth.center.dto.ClientForm;
import com.github.geng.auth.center.service.ClientAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geng
 */
@Service
@Slf4j
public class ClientAuthServiceImpl implements ClientAuthService {

    @Override
    public String apply(ClientForm clientForm) {
        return null;
    }

    @Override
    public List<String> allowedClient(ClientForm clientForm) {
        return null;
    }

    @Override
    public boolean validate(ClientForm clientForm) {
        return false;
    }
}
