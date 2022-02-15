package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public String getName(){ return this.name;}
    public String getEmail(){ return this.email;}
    public Long getId(){ return this.id; }

    public void setName(String newName){ this.name = newName;}
    public void setEmail(String newMail){ this.email = newMail;}
    public void setId(Long id){ this.id = id; }

}
