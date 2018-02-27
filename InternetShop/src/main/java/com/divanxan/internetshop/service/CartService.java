package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("cartService")
public class CartService {

    @Autowired
    private CartLineDao cartLineDao;

    @Autowired
    private HttpSession session;


    // возвращает корзину зарегестрированого покупателя
    private Cart getCart(){
        return ((UserModel)session.getAttribute("userModel")).getCart();
    }

    //возвращает содержимое корзины
    public List<CartLine> getCartLines(){
        Cart cart = this.getCart();
        return cartLineDao.list(cart.getId());
    }

}
