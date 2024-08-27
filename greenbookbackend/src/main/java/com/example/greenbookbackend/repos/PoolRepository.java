package com.example.greenbookbackend.repos;

import com.example.greenbookbackend.entites.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoolRepository extends JpaRepository<Pool, Long> {
    // You can define custom query methods here if needed
    boolean existsByPoolNameAndPoolPin(String poolName, String poolPin);
    Optional<Pool> findByPoolName(String poolName);

}
