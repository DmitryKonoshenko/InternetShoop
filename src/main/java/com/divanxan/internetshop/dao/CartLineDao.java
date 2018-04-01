package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.PromoCode;

import java.util.List;

/**
 * Dao interface for management Cart
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
public interface CartLineDao {

    /**
     * Get CartLine function
     *
     * @param id - id of cart
     * @return CartLine
     */
    CartLine get(int id);

    /**
     * Adding CartLine
     *
     * @param cartLine - adding CartLine
     * @return boolean (success information)
     */
    boolean add(CartLine cartLine);

    /**
     * Updating CartLine
     *
     * @param cartLine - updating CartLine
     * @return boolean (success information)
     */
    boolean update(CartLine cartLine);

    /**
     * Delete CartLine
     *
     * @param cartLine - deleting CartLine
     * @return boolean (success information)
     */
    boolean delete(CartLine cartLine);

    /**
     * Getting CartLine list of Cart
     *
     * @param cartId - id of Cart
     * @return List<CartLine> - CartLine list of Cart
     */
    List<CartLine> list(int cartId);

    /**
     * Getting available CartLine list of Cart
     *
     * @param cartId - id Cart
     * @return List<CartLine>  - Available CartLine of Cart
     */
    List<CartLine> listAvailable(int cartId);

    /**
     * Getting CartLine of Cart and with product in it
     *
     * @param cartId - id Cart
     * @param productId - id of product
     * @return CartLine
     */
    CartLine getByCartAndProduct(int cartId, int productId);

    /**
     * Updating Cart
     *
     * @param cart - updating Cart
     * @return boolean (success information)
     */
    boolean updateCart(Cart cart);

    /**
     * Add Order Detail
     *
     * @param orderDetail - adding OrderDetail
     */
    void addOrderDetail(OrderDetail orderDetail);

    /**
     * Update OrderDetail
     *
     * @param orderDetail - updating OrderDetail
     */
    void updateOrderDetail(OrderDetail orderDetail);

    /**
     * Getting promocode list
     *
     * @return List<PromoCode> - list of promocods
     */
    List<PromoCode> listPromocodes();
}
