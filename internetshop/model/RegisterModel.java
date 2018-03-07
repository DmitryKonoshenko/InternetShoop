package com.divanxan.internetshop.model;

import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.User;

import java.io.Serializable;

/**
 * Данный класс моделью для регистрации нового пользователя.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
public class RegisterModel implements Serializable {

    private static final long serialVersionUID = 555555555L;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Address billing;

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }
}
