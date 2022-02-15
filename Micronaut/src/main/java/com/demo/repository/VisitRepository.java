package com.demo.repository;

import com.demo.entity.VisitEntity;
import jakarta.inject.Singleton;

import javax.validation.constraints.NotNull;
import java.util.List;

@Singleton
public interface VisitRepository {
    VisitEntity save(@NotNull VisitEntity business);
    List<VisitEntity> findAll();
}
