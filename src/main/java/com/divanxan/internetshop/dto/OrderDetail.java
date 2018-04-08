package com.divanxan.internetshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Simple POJO class of OrderDetail
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
@NamedQueries({
        @NamedQuery(
                name = "listOrders",
                query = "FROM OrderDetail WHERE user.id=:userId"
        ),
        @NamedQuery(
                name = "listAllOrders",
                query = "FROM OrderDetail"
        ),
        @NamedQuery(
                name = "listDateOrders",
                query = "FROM OrderDetail WHERE orderDate BETWEEN :date1 AND :date2"
        ),
        @NamedQuery(
        name = "getOrders",
        query = "FROM OrderDetail WHERE id=:id"
)
})
@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    @Column(name = "order_total")
    private BigDecimal orderTotal;

    @Getter
    @Setter
    @ManyToOne
    private Address shipping;

    @Getter
    @Setter
    @ManyToOne
    private Address billing;

    @Getter
    @Setter
    @OneToMany(mappedBy = "orderDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Getter
    @Setter
    @Column(name = "order_count")
    private int orderCount;

    @Getter
    @Setter
    @Column(name = "order_date")
    private Date orderDate;

    @Getter
    @Setter
    @Column(name = "is_pay")
    private boolean isPay;

    @Getter
    @Setter
    @Column(name = "delivery")
    private boolean delivery;

    @Column(name = "is_delivery")
    private boolean isDelivery;

    @Getter
    @Setter
    @Column(name = "is_shipped_order")
    private boolean isShippedOrder;

    @Getter
    @Setter
    @Column(name = "discount")
    private int discount;

    public boolean getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", user=" + user +
                ", orderTotal=" + orderTotal +
                ", shipping=" + shipping +
                ", billing=" + billing +
                ", orderItems=" + orderItems +
                ", orderCount=" + orderCount +
                ", orderDate=" + orderDate +
                ", isPay=" + isPay +
                ", delivery=" + delivery +
                ", isDelivery=" + isDelivery +
                ", isShippedOrder=" + isShippedOrder +
                ", discount=" + discount +
                '}';
    }
}
