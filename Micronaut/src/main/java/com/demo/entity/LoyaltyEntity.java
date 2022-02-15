package com.demo.entity;


import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;


@Introspected
public class LoyaltyEntity {

    @NonNull
    @NotBlank
    @BsonProperty("customerId")
    private long customerId;

    @NonNull
    @NotBlank
    @BsonProperty("businessId")
    private long businessId;

    @NonNull
    @NotBlank
    @BsonProperty("loyaltyCounter")
    private int loyaltyCounter;

    @NonNull
    @NotBlank
    @BsonProperty("maxLoyalty")
    private int maxLoyalty;

    @Creator
    @BsonCreator
    public LoyaltyEntity(@NonNull @BsonProperty("customerId") long customerId, @NonNull @BsonProperty("businessId") long businessId){
        this.customerId = customerId;
        this.businessId = businessId;
        this.loyaltyCounter = 1;
        this.maxLoyalty = 10;
    }

    public void incrementLoyalty(){
        this.loyaltyCounter = loyaltyCounter + 1;
        if (this.loyaltyCounter > 10) {
            doDiscount();
            this.loyaltyCounter = 0;
        }
    }


    public void setLoyaltyCounter(int loyaltyCounter) {
        this.loyaltyCounter = loyaltyCounter;
    }
    public int getLoyaltyCounter(){
        return this.loyaltyCounter;
    }

    public long getCustomerId(){ return this.customerId; }
    public long getBusinessId(){ return this.businessId; }
    public void setCustomerId(long customerId){ this.customerId = customerId; }
    public void setBusinessId(long businessId){ this.businessId = businessId; }
    private void doDiscount(){}
}
