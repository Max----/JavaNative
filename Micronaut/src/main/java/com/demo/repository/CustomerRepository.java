package com.demo.repository;

import com.demo.entity.CustomerEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CustomerRepository {
    CustomerEntity save(@NotNull CustomerEntity business);
    CustomerEntity findById(@NotNull long id);
    void delete(@NotNull CustomerEntity business);
    List<CustomerEntity> findAll();
}
