package com.github.geng.auth.center.repository;

import com.github.geng.auth.center.entity.ServiceToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author geng
 */
public interface ServiceTokenRepository extends JpaRepository<ServiceToken, Long> {

    ServiceToken findByClientName(String clientName);

    void deleteByClientName(String clientName);
}
