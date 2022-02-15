package com.demo.entity;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Introspected
@Entity
public class BusinessEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    public BusinessEntity(){}

    public BusinessEntity(String name, String email){
        this.name = name;
        this.email = email;
    }

    // return name as uppercase in the model
    public String getName(){ return this.name;}
    public String getEmail(){ return this.email;}
    public Long getId(){ return this.id; }

    public void setName(String newName){ this.name = newName;}
    public void setEmail(String newMail){ this.email = newMail;}
    public void setId(Long id){ this.id = id; }

}
