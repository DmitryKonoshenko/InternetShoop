package com.divanxan.internetshop.model;

import com.divanxan.internetshop.dto.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Class for management data before create Order
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
public class CheckoutModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private Address shipping;
    private Cart cart;
    private List<CartLine> cartLines;
    private OrderDetail orderDetail;
    private BigDecimal checkoutTotal;

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public BigDecimal getCheckoutTotal() {
        return checkoutTotal;
    }

    public void setCheckoutTotal(BigDecimal checkoutTotal) {
        this.checkoutTotal = checkoutTotal;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShipping() {
        return shipping;
    }

    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }

}
