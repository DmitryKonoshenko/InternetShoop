package com.divanxan.internetshop.handler;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.RegisterModel;
import com.divanxan.internetshop.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * This class is used to register a user in the application.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Component
@Scope("session")
public class RegisterHandler {
    private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);
    private final UserDao userDao;
    private final CartLineDao cartLineDao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    @Autowired
    public RegisterHandler(UserDao userDao, CartLineDao cartLineDao, BCryptPasswordEncoder passwordEncoder, HttpSession session) {
        this.userDao = userDao;
        this.cartLineDao = cartLineDao;
        this.passwordEncoder = passwordEncoder;
        this.session = session;
    }

    /**
     * This method returns the registration model.
     *
     * @return RegisterModel
     */
    public RegisterModel init() {
        logger.info("make new registration model");
        return new RegisterModel();
    }

    /**
     * This method adds the user to the registration model.
     *
     * @param registerModel - model for registration users
     * @param user          - registration user
     */
    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
        logger.info("adding user in registration model, user: " + user.toString());
    }

    /**
     * This method returns the model of the new user.
     *
     * @param registerModel - model for registration users
     * @param billing       - billing Address
     */
    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
        logger.info("adding billing address in registration model, address: " + billing.toString());
    }

    /**
     * This method saves user data in DB
     *
     * @param model - RegisterModel model for registration users
     * @return String - key of success operation
     */
    public String saveAll(RegisterModel model) {
        String transitionValue = "success";
        User user = model.getUser();
        if (user.getRole().equals("USER")) {
            Cart cart = ((UserModel) (session.getAttribute("userModel"))).getCart();
            cart.setUser(user);
            user.setCart(cart);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
        Address billing = model.getBilling();
        billing.setUserId(user.getId());
        billing.setBilling(true);
        userDao.addAddress(billing);
        List<CartLine> list = (List<CartLine>) session.getAttribute("AnonymousCartLines");
        if (list != null && list.size() > 0) {
            User user1 = userDao.getByEmail(user.getEmail());
            Cart cart = user1.getCart();
            for (CartLine line : list) {
                line.setId(0);
                line.setCartId(cart.getId());
                cartLineDao.add(line);
            }
        }
        logger.info("Saving registration model in DB, value: " + transitionValue);
        return transitionValue;
    }


    /**
     * This method validate user registration data
     *
     * @param user  - validation User
     * @param error - error of operation
     * @return String - key of success operation
     */
    public String validateUser(User user, MessageContext error) {
        String transitionalValue = "success";
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Пароли не совпадает")
                    .build());
            transitionalValue = "failure";
        }
        if (userDao.getByEmail(user.getEmail()) != null) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Такая почта уже зарегестрирована!")
                    .build());
            transitionalValue = "failure";
        }
        logger.info("Validating user data, value: " + transitionalValue);
        return transitionalValue;
    }


}
