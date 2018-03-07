package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.OrderDetail;

import java.util.List;

public interface CartLineDao {

    CartLine get(int id);
    boolean add(CartLine cartLine);
    boolean update(CartLine cartLine);
    boolean delete(CartLine cartLine);
    List<CartLine> list(int cartId);

    List<CartLine> listAvailable(int cartId);
    CartLine getByCartAndProduct(int cartId, int productId);

    boolean updateCart(Cart cart);

    void addOrderDetail(OrderDetail orderDetail);
    void updateOrderDetail(OrderDetail orderDetail);
}
