package com.example.demo.repository;

import com.example.demo.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {
}
