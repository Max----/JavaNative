package com.demo.repository;

import com.demo.entity.BusinessEntity;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class BusinessRepositoryImpl implements BusinessRepository {

    private final EntityManager entityManager;

    public BusinessRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public BusinessEntity save(BusinessEntity business) {
        entityManager.persist(business);
        return business;
    }

    @Override
    @ReadOnly
    public BusinessEntity findById(long id) {
        return entityManager.find(BusinessEntity.class, id);
    }

    @Override
    @Transactional
    public void delete(BusinessEntity business) {
        if(findById(business.getId()) != null){
            entityManager.remove(business);
        }
    }
}
