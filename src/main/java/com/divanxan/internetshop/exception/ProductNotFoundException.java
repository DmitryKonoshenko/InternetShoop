package com.divanxan.internetshop.exception;


import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{

    private static  final long serialVersionUID = 111222333L;
    private String message;

    public ProductNotFoundException(){
        this("Данный товар недоступен");
    }

    private ProductNotFoundException(String message){
        this.message = System.currentTimeMillis() + ": " + message;
    }


    public String getMessage() {
        return message;
    }
}
