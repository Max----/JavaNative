package application.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="loyalty")
public class LoyaltyEntity extends PanacheMongoEntity {

    private long customerId;
    private long businessId;
    private int loyaltyCounter;
    private int maxLoyalty;

    public LoyaltyEntity(){}

    public LoyaltyEntity(long customerId, long businessId){
        this.customerId = customerId;
        this.businessId = businessId;
        this.loyaltyCounter = 1;
        this.maxLoyalty = 10;
    }

    public static LoyaltyEntity findLoyalty(int customerId, int businessId){
        return find("customerId = ?1 and businessId = ?2", customerId, businessId).firstResult();
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
