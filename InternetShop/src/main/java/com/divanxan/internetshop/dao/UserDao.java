package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.User;

import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    boolean delleteForTestUser(User user);

    User getByEmail(String email);

    // добавление адреса
    boolean addAddress(Address address);

    Address getBillingAddress(int userId);
    List<Address> listShippingAddressess(int userId);

    boolean updateCart(Cart cart);
}
