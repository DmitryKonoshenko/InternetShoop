package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.UserModel;
import com.divanxan.internetshop.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Global controller for displaying user's personal data and information about the shopping cart
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@ControllerAdvice
public class GlobalController {
    private final UserDao userDao;
    private final CartService cartService;
    private final HttpSession session;
    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    /**
     * Constructor initializing the service class CartService,  Dao class for take user information UserDao and class HttpSession
     *
     * @param userDao
     * @param session
     * @param cartService
     */
    @Autowired
    public GlobalController(UserDao userDao, HttpSession session, CartService cartService) {
        this.userDao = userDao;
        this.session = session;
        this.cartService = cartService;
    }

    /**
     * Method for getting user information
     *
     * @return UserModel - model class with all user information
     */
    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        UserModel userModel = ((UserModel) session.getAttribute("userModel"));
        boolean isUserModelExist = (userModel == null);
        if (userModel == null || userModel.getEmail() == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userDao.getByEmail(authentication.getName());
            Cart sessionCart = null;
            if (userModel != null) sessionCart = userModel.getCart();
            if (user != null) {
                userModel = new UserModel();
                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName() + " " + user.getLastName());
                Cart userCart = user.getCart();
                if (sessionCart.getCartLines() > 0) {
                    cartService.mergeCart(userCart);
                }
                userModel.setCart(userCart);
                if (userModel.getRole().equals("USER")) {
                    userModel.setCart(user.getCart());
                }
                session.setAttribute("userModel", userModel);
                logger.info("UserModel create for registered user: "+ userModel.toString());
                return userModel;
            } else if (isUserModelExist) {
                userModel = new UserModel();
                Cart cart = new Cart();
                cart.setGrandTotal(new BigDecimal(0));
                userModel.setCart(cart);
                System.out.println(cart);
                System.out.println(userModel);
                session.setAttribute("userModel", userModel);
                logger.info("UserModel create for anonymous: "+ userModel.toString());
                return userModel;
            }
        }
        logger.info("UserModel take from session: "+ userModel.toString());
        return (UserModel) session.getAttribute("userModel");
    }

}
