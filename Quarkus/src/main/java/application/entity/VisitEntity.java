package application.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Cacheable
public class VisitEntity extends PanacheEntity {

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
