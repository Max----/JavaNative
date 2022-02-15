package com.demo.repository;

import com.demo.entity.VisitEntity;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class VisitRepositoryImpl implements VisitRepository {

    //This is very bad and should be in application config
    int VISIT_MAX_RESULTS = 100;

    private final EntityManager entityManager;

    public VisitRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public VisitEntity save(VisitEntity visitEntity) {
        entityManager.persist(visitEntity);
        return visitEntity;
    }

    @Override
    @ReadOnly
    public List<VisitEntity> findAll() {
        String qlString = "SELECT c FROM VisitEntity as c";
        TypedQuery<VisitEntity> query = entityManager.createQuery(qlString, VisitEntity.class);
        query.setMaxResults(VISIT_MAX_RESULTS);

        return query.getResultList();
    }
}
