package com.divanxan.internetshop.controller;


import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.exception.UserAccessException;
import com.divanxan.internetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


/**
 * Данный коласс является контроллером. Служит для изменения персональных данных пользователя.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/show")
    public ModelAndView showUser(@RequestParam(name = "operation", required = false) String operation) throws UserAccessException {

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "User Data");
        mv.addObject("userClickShowUser", true);

        User user = userService.getUser();

        List<Address> addresses = userService.getAddresses(user.getId());

        List<OrderDetail> orderDetails = userService.getOrders(user.getId());

        mv.addObject("userData", user);

        mv.addObject("addresses", addresses);
        mv.addObject("orderDetails", orderDetails);
        try {
            mv.addObject("userAddress", userService.getBillingAddress(user.getId()));
        }
        catch (Exception e){
            throw new UserAccessException();
        }
        if (operation != null) {
            if (operation.equals("user")) {
                mv.addObject("message", "Персональные данные успешно изменены!");
            }
            if (operation.equals("noUser")) {
                mv.addObject("message", "Персональные данные НЕ ИЗМЕНЕНЫ. Введите нужные поля!");
            }
            if (operation.equals("noPassword")) {
                mv.addObject("message", "Пароль НЕ ИЗМЕНЕН. Введите все поля!");
            }
            if (operation.equals("noAddress")) {
                mv.addObject("message", "Адрес НЕ ИЗМЕНЕН. Введите нужные поля!");
            }
        }
        return mv;
    }

    @RequestMapping(value = "/userName", method = RequestMethod.GET)
    public ModelAndView changeUser() throws UserAccessException {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Data");
        mv.addObject("userClickShowUserName", true);

        User user = new User();
        try {
        user.setId(userService.getUser().getId());
        }
        catch (Exception e){
            throw new UserAccessException();
        }
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String submit(@RequestParam Map<String, String> map, Model model) {

        return userService.ValidateUserInformation(map,model);

    }


    @RequestMapping(value = "/userPassword", method = RequestMethod.GET)
    public ModelAndView changePassword() throws UserAccessException {

        String email = userService.getEmail();

        if(email==null){
            throw new UserAccessException();
        }

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Password");
        mv.addObject("userClickShowUserPassword", true);

        return mv;
    }

    @RequestMapping(value = "/{userAddressId}/userAddress", method = RequestMethod.GET)
    public ModelAndView changeAddress(@PathVariable int userAddressId) throws UserAccessException {

        String email = userService.getEmail();

        if(email==null){
            throw new UserAccessException();
        }

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Address");
        mv.addObject("userClickShowUserAddress", true);

        userService.setAddressId(userAddressId);

        return mv;
    }

}
