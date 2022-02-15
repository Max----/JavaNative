package com.demo.entity;

import io.micronaut.core.annotation.Introspected;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Introspected
@Entity
public class CustomerEntity {

    private @Id @GeneratedValue Long id;
    public String firstName;
    public String lastName;
    public String email;

    public CustomerEntity(){}

    public CustomerEntity(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() { return this.id;}
    public String getFirstName(){ return this.firstName;}
    public String getLastName(){ return this.lastName;}
    public String getEmail(){ return this.email;}

    public void setId(Long id){ this.id = id; }
    public void setFirstName(String newName){ this.firstName = newName;}
    public void setLastName(String newName){ this.lastName = newName;}
    public void setEmail(String newMail){ this.email = newMail;}

}
