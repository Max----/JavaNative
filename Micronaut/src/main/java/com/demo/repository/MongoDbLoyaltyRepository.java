package com.demo.repository;

import com.demo.MongoDbConfiguration;
import com.demo.entity.LoyaltyEntity;
import com.mongodb.lang.NonNull;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;



@Singleton
public class MongoDbLoyaltyRepository implements LoyaltyRepository {

    private final MongoDbConfiguration mongoConf;
    private final MongoClient mongoClient;

    public MongoDbLoyaltyRepository(MongoDbConfiguration mongoConf,
                                  MongoClient mongoClient) {
        this.mongoConf = mongoConf;
        this.mongoClient = mongoClient;
    }
    @Override
    public LoyaltyEntity findByLoyalty(Integer customerId, Integer businessId) {
        return getCollection().find(Filters.and(Filters.eq("customerId", customerId), Filters.eq("businessId", businessId))).first();
    }

    @Override
    public LoyaltyEntity save(LoyaltyEntity loyaltyEntity){
        try{
            getCollection().insertOne(loyaltyEntity);
        } catch (Error e){
            return null;
        }
        return loyaltyEntity;
    }

    @Override
    public LoyaltyEntity update(LoyaltyEntity loyaltyEntity){
        try{
            getCollection().replaceOne(Filters.and(Filters.eq("customerId", loyaltyEntity.getCustomerId()), Filters.eq("businessId", loyaltyEntity.getBusinessId())), loyaltyEntity);
        } catch (Error e){
            return null;
        }
        return loyaltyEntity;
    }

    @Override
    @ReadOnly
    public List<LoyaltyEntity> findAll() {
        return getCollection().find().into(new ArrayList<>());
    }

    @NonNull
    private MongoCollection<LoyaltyEntity> getCollection(){
        return mongoClient.getDatabase(mongoConf.getName())
                .getCollection(mongoConf.getCollection(), LoyaltyEntity.class);
    }
}
