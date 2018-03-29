package com.divanxan.internetshop.controller;


import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.UserModel;
import com.divanxan.internetshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;


//данный контроллер будет участвовать в каждом запросе. Существует для отображения информации о юзере. использует UserModel

@ControllerAdvice
public class GlobalController {

    private final UserDao userDao;

    private final CartService cartService;

    private final HttpSession session;

    @Autowired
    public GlobalController(UserDao userDao, HttpSession session, CartService cartService) {
        this.userDao = userDao;
        this.session = session;
        this.cartService = cartService;
    }

    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        UserModel userModel = ((UserModel) session.getAttribute("userModel"));
        boolean isUserModelExist = (userModel == null);

        if (userModel == null || userModel.getEmail() == null) {
            //добавим покупателя
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            User user = userDao.getByEmail(authentication.getName());
            Cart sessionCart = null;
            if (userModel != null) sessionCart = userModel.getCart();
            if (user != null) {
                //создаем модель покупателя для отображения покупателя на странице

                userModel = new UserModel();

                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName() + " " + user.getLastName());

                // тут мерджим сессионную корзину с той что в БД
                Cart userCart = user.getCart();
                if (sessionCart.getCartLines() > 0) {
                    cartService.mergeCart(userCart);
                }


                userModel.setCart(userCart);
                // TODO если что - посмотри тут. с корзиной надо разобраться
                if (userModel.getRole().equals("USER")) {
                    //добавляем корзину только в случае если юзер - покупатель
                    userModel.setCart(user.getCart());
                }

                //добавляем модель покупателя в сессию
                session.setAttribute("userModel", userModel);
                return userModel;
            } else if (isUserModelExist) {
                // если юзер аноним, то создаем корзину
                userModel = new UserModel();
                Cart cart = new Cart();
                cart.setGrandTotal(new BigDecimal(0));
                userModel.setCart(cart);
                System.out.println(cart);
                System.out.println(userModel);
                //добавляем модель покупателя в сессию
                session.setAttribute("userModel", userModel);
                return userModel;
            }
        }

        return (UserModel) session.getAttribute("userModel");
    }

}
