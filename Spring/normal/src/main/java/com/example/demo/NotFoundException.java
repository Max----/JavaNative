package com.example.demo;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Could not find resource");
    }
}
