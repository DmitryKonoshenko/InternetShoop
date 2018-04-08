package com.divanxan.internetshop.model;

import com.divanxan.internetshop.dto.Cart;

import java.io.Serializable;

/**
 * This class is a user model that serves to display information in the navigation window
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
//для отображение информации о пользователе на навигационном экране
public class UserModel implements Serializable {
    private static final long serialVersionUID = 444444444L;
    private  int id;
    private String fullName;
    private String email;
    private String role;
    private Cart cart;
    private int loginCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", cart=" + cart +
                '}';
    }
}
