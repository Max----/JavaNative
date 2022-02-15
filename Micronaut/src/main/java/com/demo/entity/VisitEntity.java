package com.demo.entity;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Introspected
@Entity
public class VisitEntity {

    @Id
    @GeneratedValue
    private Long id;
    public long customerId;
    public long businessId;
    public LocalDateTime dateTime;

    public VisitEntity(){}

    public VisitEntity(long customerId, long businessId){
        this.customerId = customerId;
        this.businessId = businessId;
        this.dateTime = LocalDateTime.now();
    }

    public long getCustomerId(){return this.customerId;}
    public long getBusinessId(){return this.businessId;}
    public LocalDateTime getDateTime(){return this.dateTime;}

    public void setCustomerId(long customerId){ this.customerId = customerId;}
    public void setBusinessId(long businessId){this.businessId = businessId;}
    public  void setDateTime(LocalDateTime dateTime){this.dateTime = dateTime;}

}
