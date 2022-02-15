package com.example.demo.repository;

import com.example.demo.entity.LoyaltyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoyaltyRepository extends MongoRepository<LoyaltyEntity, String> {
    LoyaltyEntity findByCustomerIdAndBusinessId(Long customerId, Long businessId);
}
