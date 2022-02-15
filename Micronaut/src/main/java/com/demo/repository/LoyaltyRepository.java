package com.demo.repository;

import com.demo.entity.LoyaltyEntity;
import java.util.List;

public interface LoyaltyRepository {
    public LoyaltyEntity findByLoyalty(Integer customerId, Integer businessId);
    LoyaltyEntity save(LoyaltyEntity loyaltyEntity);
    LoyaltyEntity update(LoyaltyEntity loyaltyEntity);
    List<LoyaltyEntity> findAll();

}
