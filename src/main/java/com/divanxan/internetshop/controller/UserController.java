package com.divanxan.internetshop.controller;


import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpSession session;

    @RequestMapping("/show")
    public ModelAndView showUser(@RequestParam(name = "operation", required = false) String operation) {

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "User Data");
        mv.addObject("userClickShowUser", true);

        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        User user = userDao.getByEmail(email);

        mv.addObject("userData", user);
        mv.addObject("userAddress", userDao.getBillingAddress(user.getId()));

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
    public ModelAndView changeUser() {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Data");
        mv.addObject("userClickShowUserName", true);

        User user = new User();
        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        user.setId(userDao.getByEmail(email).getId());
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String submit(@RequestParam Map<String, String> map, Model model) {

        String firstName = map.get("firstName");
        String lastName = map.get("lastName");
        String email = map.get("email");
        String contactNumber = map.get("contactNumber");

        String password1 = map.get("password1");
        String password2 = map.get("password2");
        String password3 = map.get("password3");

        String addressLineOne = map.get("addressLineOne");
        String addressLineTwo = map.get("addressLineTwo");
        String city = map.get("city");
        String state = map.get("region");
        String country = map.get("country");
        String postalCode = map.get("postalCode");


        String emailOfUser = ((UserModel) session.getAttribute("userModel")).getEmail();
        User user = userDao.getByEmail(emailOfUser);
        Address address = userDao.getBillingAddress(user.getId());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        // boolean whatIf = BCrypt.checkpw(password3, user.getPassword());

        if (addressLineOne != null) {
            if (addressLineOne.equals("") && addressLineTwo.equals("") && city.equals("")
                    && state.equals("") && country.equals("") && postalCode.equals("")) {
                model.addAttribute("userClickShowUserName", true);
                model.addAttribute("title", "User Data");
                model.addAttribute("message", "Ошибка валидации для изменения данных!");

                return "redirect:/user/show?operation=noAddress";
            }

        }
        if (firstName != null) {
            if (firstName.equals("") && lastName.equals("") && email.equals("") && contactNumber.equals("")) {

                model.addAttribute("userClickShowUserName", true);
                model.addAttribute("title", "User Data");
                model.addAttribute("message", "Ошибка валидации для изменения данных!");

                return "redirect:/user/show?operation=noUser";
            }
        }
        if (password1 != null) {
            if (password2.equals("") || password3.equals("") || !password1.equals(password2) || !BCrypt.checkpw(password3, user.getPassword())) {
                model.addAttribute("userClickShowUserName", true);
                model.addAttribute("title", "User Data");
                model.addAttribute("message", "Ошибка валидации для изменения данных!");

                return "redirect:/user/show?operation=noPassword";
            }
        }


        if (firstName != null && !firstName.equals("")) user.setFirstName(firstName);
        if (lastName != null && !lastName.equals("")) user.setLastName(lastName);
        if (email != null && !email.equals("")) user.setEmail(email);
        if (contactNumber != null && !contactNumber.equals("")) user.setContactNumber(contactNumber);

        if (password1 != null && !password1.equals("")) user.setPassword(passwordEncoder.encode(password1));

        if (addressLineOne != null && !addressLineOne.equals("")) address.setAddressLineOne(addressLineOne);
        if (addressLineTwo != null && !addressLineTwo.equals("")) address.setAddressLineTwo(addressLineTwo);
        if (city != null && !city.equals("")) address.setCity(city);
        if (state != null && !state.equals("")) address.setState(state);
        if (country != null && !country.equals("")) address.setCountry(country);
        if (postalCode != null && !postalCode.equals("")) address.setPostalCode(postalCode);

        if (firstName != null || password1 != null) {
            userDao.update(user);
            UserModel userModel = ((UserModel) session.getAttribute("userModel"));
            userModel.setEmail(user.getEmail());
            userModel.setFullName(user.getFirstName() + " " + user.getLastName());

            session.setAttribute("userModel", userModel);
        }

        if (addressLineOne != null) {
            userDao.updateAddress(address);
        }

        return "redirect:/user/show?operation=user";

    }


    @RequestMapping(value = "/userPassword", method = RequestMethod.GET)
    public ModelAndView changePassword() {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Password");
        mv.addObject("userClickShowUserPassword", true);

        return mv;
    }

    @RequestMapping(value = "/userAddress", method = RequestMethod.GET)
    public ModelAndView changeAddress() {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Address");
        mv.addObject("userClickShowUserAddress", true);

        return mv;
    }

}
