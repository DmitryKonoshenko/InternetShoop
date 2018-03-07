package com.divanxan.internetshop.dto;

import javax.persistence.*;
import java.io.Serializable;

//сделаем корзину сессионной и инжектним ее в RegisterHandler

@Entity(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 666666666L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //-----
    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //-----

    @Column(name = "grand_total")
    private double grandTotal;

    @Column(name = "cart_lines")
    private int cartLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getCartLines() {
        return cartLines;
    }

    public void setCartLines(int cartLines) {
        this.cartLines = cartLines;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + user.toString() +
                ", grandTotal='" + grandTotal + '\'' +
                ", cartLines=" + cartLines +
                '}';
    }
}
