package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.*;

import java.util.List;
import java.util.Map;

/**
 * Dao interface for management User
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
public interface UserDao {

    /**
     * Adding User
     *
     * @param user - adding User
     * @return boolean (success information)
     */
    boolean addUser(User user);

    /**
     * Updating user information
     *
     * @param user  - updating User
     * @return boolean (success information)
     */
    boolean update(User user);

    /**
     * Deleting user from BD
     *
     * @param user - deleting User from BD
     * @return boolean (success information)
     */
    boolean delleteForTestUser(User user);

    /**
     * Getting user by email
     *
     * @param email - User email
     * @return User - getting by email
     */
    User getByEmail(String email);

    /**
     * Getting user by id
     *
     * @param id - User id
     * @return User - getting by id
     */
    User getById(int id);

    /**
     * Adding address
     *
     * @param address - adding Address
     * @return boolean (success information)
     */
    // добавление адреса
    boolean addAddress(Address address);

    /**
     * Getting address by id
     *
     * @param addressId - id of Address
     * @return Address - getting by id
     */
    Address getAddress(int addressId);

    /**
     * Updating address
     *
     * @param address - updating Address
     * @return boolean (success information)
     */
    boolean updateAddress(Address address);

    /**
     * Getting billing address by id
     *
     * @param userId - id of User
     * @return Address - getting by id
     */
    Address getBillingAddress(int userId);

    /**
     * Getting list shipping addresses of user by user id
     *
     * @param userId - id of User
     * @return List - list shipping addresses getting by User id
     */
    List listShippingAddressess(int userId);

    /**
     * Getting list addresses of user by user id
     *
     * @param userId - id of User
     * @return List<Address> - list addresses of User getting by id
     */
    List<Address> listAddressess(int userId);

    /**
     * Getting list order detail by user id
     *
     * @param userId - id of User
     * @return List<OrderDetail> - list OrderDetail getting by User id
     */
    List<OrderDetail> listOrders(int userId);

    /**
     * Getting list of all order detail
     *
     * @return List<OrderDetail> - list all OrderDetail
     */
    List<OrderDetail> listAllOrders();

    /**
     * Getting list order detail of this month
     *
     * @return List<OrderDetail> - list OrderDetail of this month
     */
    List<OrderDetail> listThisMonthOrders();

    /**
     * Getting top 10 products
     *
     * @return List<Product> - list top 10 Products
     */
    List<Product> getTopProducts();

    /**
     * Getting list order detail of this week
     *
     * @return List<OrderDetail> - list OrderDetail of this week
     */
    List<OrderDetail> listThisWeekOrders();

     /**
     * Getting order detail by id
     *
     * @param id - id of order detail
     * @return OrderDetail - OrderDetail
     */
    OrderDetail getOrderDetail(int id);

    /**
     * Getting orders list by dates
     *
     * @param map - Map<String,String> with date information
     * @return
     */
    List<OrderDetail> listThisDateOrders(Map<String,String> map);
}
