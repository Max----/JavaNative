package com.demo.repository;

import com.demo.entity.BusinessEntity;

import javax.validation.constraints.NotNull;

public interface BusinessRepository {
    BusinessEntity save(@NotNull BusinessEntity business);
    BusinessEntity findById(@NotNull long id);
    void delete(@NotNull BusinessEntity business);
}
