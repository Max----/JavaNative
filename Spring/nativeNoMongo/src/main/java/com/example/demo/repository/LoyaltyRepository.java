package com.example.demo.repository;

import com.example.demo.entity.LoyaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyRepository extends JpaRepository<LoyaltyEntity, Long> {
    LoyaltyEntity findByCustomerIdAndBusinessId(Long customerId, Long businessId);
}
