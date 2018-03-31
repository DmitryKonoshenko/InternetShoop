package com.divanxan.internetshop.dto;

import lombok.Getter;
import lombok.Setter;

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
    @Getter
    @Setter
    private int id;

    //-----
    @OneToOne
    @Getter
    @Setter
    private User user;
    //-----

    @Column(name = "grand_total")
    @Getter
    @Setter
    private BigDecimal grandTotal;

    @Column(name = "cart_lines")
    @Getter
    @Setter
    private int cartLines;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private PromoCode promoCode;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", grandTotal=" + grandTotal +
                ", cartLines=" + cartLines +
                ", promoCode=" + promoCode +
                '}';
    }
}
