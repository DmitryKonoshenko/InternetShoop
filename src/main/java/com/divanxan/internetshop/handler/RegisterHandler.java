package com.divanxan.internetshop.handler;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.RegisterModel;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Данный класс служит для регистрации пользоватля в приложении.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Component
@Scope("session")
public class RegisterHandler {

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
     * Данный метод возвращает регистрационную модель.
     *
     * @return RegisterModel
     */
    public RegisterModel init() {
        return new RegisterModel();
    }

    /**
     * Данный метод добавляет пользователя в регистрационную модель.
     */
    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    /**
     * Данный метод возвращает модель нового пользователя.
     */
    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel model) {
        String transitionValue = "success";

        User user = model.getUser();
        // создаем корзину для пользователя
        if (user.getRole().equals("USER")) {

            Cart cart = ((UserModel) (session.getAttribute("userModel"))).getCart();
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

        //если аноним добавил покупки в корзину
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
        if (userDao.getByEmail(user.getEmail()) != null) {

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
