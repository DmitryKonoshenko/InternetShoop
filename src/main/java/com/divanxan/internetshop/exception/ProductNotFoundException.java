package com.divanxan.internetshop.exception;


import java.io.Serializable;

/**
 * Exception for not exist products
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version1.0
 */
public class ProductNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = 111222333L;
    private String message;
    public ProductNotFoundException() {
        this("Данный товар недоступен");
    }
    private ProductNotFoundException(String message) {
        this.message = System.currentTimeMillis() + ": " + message;
    }
    public String getMessage() {
        return message;
    }
}
