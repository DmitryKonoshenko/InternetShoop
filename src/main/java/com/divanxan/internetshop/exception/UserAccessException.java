package com.divanxan.internetshop.exception;

import java.io.Serializable;

public class UserAccessException extends Exception implements Serializable {

    private static  final long serialVersionUID = 111111111L;
    private String message;

    public UserAccessException(){
        this("Данная страница не оступна!");
    }

    public UserAccessException(String message){
        this.message = System.currentTimeMillis() + ": " + message;
    }


    public String getMessage() {
        return message;
    }
}
