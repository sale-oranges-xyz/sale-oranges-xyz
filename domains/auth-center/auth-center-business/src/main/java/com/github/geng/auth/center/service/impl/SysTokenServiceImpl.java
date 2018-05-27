package com.github.geng.auth.center.service.impl;

import com.github.geng.auth.center.entity.ServiceToken;
import com.github.geng.auth.center.repository.ServiceTokenRepository;
import com.github.geng.auth.center.service.SysTokenService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author geng
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class SysTokenServiceImpl implements SysTokenService {

    @Autowired
    private ServiceTokenRepository serviceTokenRepository;

    @Override
    @Cacheable(value = "findByClientName", key = "#clientName") // 使用内存缓存 参考 http://elim.iteye.com/blog/2123030
    public ServiceToken findByClientName(String clientName) {
        return serviceTokenRepository.findByClientName(clientName);
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(value = "findByClientName", key = "#clientName") // 清除内存缓存
    public void deleteByClientName(String clientName) {
        serviceTokenRepository.deleteByClientName(clientName);
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(value = "findByClientName", key = "#serviceToken.clientName") // 清除内存缓存
    public void saveToken(ServiceToken serviceToken) {
        long currentTime = System.currentTimeMillis();
        serviceToken.setCreatedTime(currentTime);
        serviceToken.setModifiedTime(currentTime);
        serviceTokenRepository.save(serviceToken);
    }

}
