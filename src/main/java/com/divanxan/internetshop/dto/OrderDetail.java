package com.divanxan.internetshop.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        )
})
@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "order_total")
    private BigDecimal orderTotal;
    @ManyToOne
    private Address shipping;
    @ManyToOne
    private Address billing;

    @OneToMany(mappedBy = "orderDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_count")
    private int orderCount;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "is_pay")
    private boolean isPay;

    @Column(name = "delivery")
    private boolean delivery;

    @Column(name = "is_delivery")
    private boolean isDelivery;

    @Column(name = "is_shipped_order")
    private boolean isShippedOrder;

    public boolean getIsShippedOrder() {
        return isShippedOrder;
    }

    public void setShippedOrder(boolean shippedOrder) {
        isShippedOrder = shippedOrder;
    }

    public boolean getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }


    public boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Address getShipping() {
        return shipping;
    }

    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
