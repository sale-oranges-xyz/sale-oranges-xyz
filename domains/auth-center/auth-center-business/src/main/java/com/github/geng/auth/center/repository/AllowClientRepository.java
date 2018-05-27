package com.github.geng.auth.center.repository;

import com.github.geng.auth.center.entity.AllowClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author geng
 */
public interface AllowClientRepository extends JpaRepository<AllowClient, Long> {

    @Query(value = "select ac.clientName from AllowClient ac where ac.client.serviceName = ?1")
    List<String> findByServiceId(String serviceName);
}
