package com.demo.repository;

import com.demo.entity.CustomerEntity;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class CustomerRepositoryImpl implements CustomerRepository {

    //This is very bad and should be in application config
    int CUSTOMER_MAX_RESULTS = 20;

    private final EntityManager entityManager;

    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public CustomerEntity save(CustomerEntity customerEntity) {
        try{
            entityManager.persist(customerEntity);
        } catch (Error e){
            System.out.println("Persist error");
            e.printStackTrace();
        }

        return customerEntity;
    }

    @Override
    @ReadOnly
    public CustomerEntity findById(long id) {
        return entityManager.find(CustomerEntity.class, id);
    }

    @Override
    @Transactional
    public void delete(CustomerEntity customerEntity) {
        if(findById(customerEntity.getId()) != null){
            entityManager.remove(customerEntity);
        }
    }

    @Override
    @ReadOnly
    public List<CustomerEntity> findAll() {
        String qlString = "SELECT c FROM CustomerEntity as c";
        TypedQuery<CustomerEntity> query = entityManager.createQuery(qlString, CustomerEntity.class);
        query.setMaxResults(CUSTOMER_MAX_RESULTS);

        return query.getResultList();
    }
}
