package com.divanxan.internetshop.exception;

import java.io.Serializable;

/**
 * Exception for user access
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version1.0
 */
public class UserAccessException extends Exception implements Serializable {

    private static final long serialVersionUID = 111111111L;
    private String message;

    public UserAccessException() {
        this("Данная страница не оступна!");
    }

    private UserAccessException(String message) {
        this.message = System.currentTimeMillis() + ": " + message;
    }


    public String getMessage() {
        return message;
    }
}
