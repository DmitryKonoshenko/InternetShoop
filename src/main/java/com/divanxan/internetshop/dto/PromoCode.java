package com.divanxan.internetshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple POJO class of PromoCode
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
@NamedQueries({
        @NamedQuery(
                name = "getPromocode",
                query = "FROM PromoCode"
        )
})
@Entity
@Table(name = "promocode")
public class PromoCode implements Serializable {


    private static final long serialVersionUID = 333333333L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(name = "code")
    private String code;

    @Getter
    @Setter
    @Column(name = "discount")
    private int discount;

    @Override
    public String toString() {
        return "PromoCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                '}';
    }
}
