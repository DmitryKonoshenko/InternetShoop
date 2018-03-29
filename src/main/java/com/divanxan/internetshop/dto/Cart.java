package com.divanxan.internetshop.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

//сделаем корзину сессионной и инжектним ее в RegisterHandler

@Entity
@Table(name = "cart")
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
    private BigDecimal grandTotal;

    @Column(name = "cart_lines")
    private int cartLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
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
                ", user=" + ((user==null)?"null":user) +
                ", grandTotal=" + grandTotal +
                ", cartLines=" + cartLines +
                '}';
    }
}
