package com.divanxan.internetshop.handler;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class RegisterHandler {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Cart cart;

    public RegisterModel init() {
        return new RegisterModel();
    }


    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel model) {
        String transitionValue = "success";

        User user = model.getUser();
        // создаем корзину для пользователя
        if (user.getRole().equals("USER")) {
            cart.setUser(user);
            user.setCart(cart);
        }

        // кодируем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        //добавляем пользователя
        userDao.addUser(user);

        //получаем адрес
        Address billing = model.getBilling();
        billing.setUserId(user.getId());
        billing.setBilling(true);
        //добавляем адрес
        userDao.addAddress(billing);


        return transitionValue;
    }


    public String validateUser(User user, MessageContext error) {
        String transitionalValue = "success";
//проверка пароля на совпадение
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Пароли не совпадает")
                    .build());

            transitionalValue = "failure";
        }

        //проверка на уникальность почты
        if(userDao.getByEmail(user.getEmail())!=null){

            error.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Такая почта уже зарегестрирована!")
                    .build());

            transitionalValue = "failure";
        }

        return transitionalValue;
    }


}
