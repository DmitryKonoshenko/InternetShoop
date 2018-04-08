package com.divanxan.internetshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Simple POJO class of CartLine
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
@NamedQueries({
        @NamedQuery(
                name = "getByCartAndProduct",
                query = "FROM cart_line Where cartId=:cartId AND product.id=:productId"
        ),
        @NamedQuery(
                name = "listAvailable",
                query = "FROM cart_line Where cartId=:cartId AND available=:available"
        ),
        @NamedQuery(
                name = "listCartLine",
                query = "FROM cart_line where cartId=:cartId"
        )
})
@Entity(name = "cart_line")
public class CartLine implements Serializable {
    private static final long serialVersionUID = 444444444L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @OneToOne
    private Product product;

    @Getter
    @Setter
    @Column(name = "cart_id")
    private int cartId;

    @Getter
    @Setter
    @Column(name = "product_count")
    private int productCount;

    @Getter
    @Setter
    @Column(name = "total")
    private BigDecimal total;

    @Getter
    @Setter
    @Column(name = "buying_price")
    private BigDecimal buyingPrice;

    @Getter
    @Setter
    @Column(name = "is_available")
    private boolean available =true;

    @Getter
    @Setter
    @Column(name = "is_promocode")
    private boolean usePromocode =true;


    @Override
    public String toString() {
        return "CartLine{" +
                "id=" + id +
                ", product=" + product +
                ", cartId=" + cartId +
                ", productCount=" + productCount +
                ", total=" + total +
                ", buyingPrice=" + buyingPrice +
                ", available=" + available +
                '}';
    }
}
