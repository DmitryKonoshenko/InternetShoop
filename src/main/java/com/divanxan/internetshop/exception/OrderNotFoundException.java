package com.divanxan.internetshop.exception;

import java.io.Serializable;

/**
 * Exception for not exist order detail
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version1.0
 */
public class OrderNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = 111222444L;
    private String message;
    public OrderNotFoundException() {
        this("Данный заказ не доступен");
    }
    private OrderNotFoundException(String message) {
        this.message = System.currentTimeMillis() + ": " + message;
    }
    public String getMessage() {
        return message;
    }
}