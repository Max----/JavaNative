package application.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

@Entity
@Cacheable
public class BusinessEntity extends PanacheEntity {

    private String name;
    private String email;

    public BusinessEntity(){}

    public BusinessEntity(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){ return this.name;}
    public String getEmail(){ return this.email;}
    public long getId(){ return this.id; }

    public void setName(String newName){ this.name = newName;}
    public void setEmail(String newMail){ this.email = newMail;}
    public void setId(long id){ this.id = id; }

}
