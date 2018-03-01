package com.divanxan.internetshop.controller;


import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
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


/**
 * Данный коласс является контроллером. Служит для изменения персональных данных пользователя.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller
@RequestMapping("/userr")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpSession session;

    @RequestMapping("/show")
    public ModelAndView showUser() {

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "User Data");
        mv.addObject("userClickShowUser", true);

        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        User user = userDao.getByEmail(email);

        mv.addObject("userData", user);
        mv.addObject("userAddress", userDao.getBillingAddress(user.getId()));

        return mv;
    }

    @RequestMapping(value = "/userName", method = RequestMethod.GET)
    public ModelAndView changeUser() {

//        ModelAndView mv = new ModelAndView("page");
//
//        mv.addObject("title", "User Data");
//        mv.addObject("userClickShowUserName", true);
//
//        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
//        User user = userDao.getByEmail(email);
//
//        mv.addObject("userData", user);

        ModelAndView mv = new ModelAndView("userPageName");
        return mv;
    }

    @RequestMapping(value = "/newName", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("firstName") String name) {

        ModelAndView mv = new ModelAndView("newName");
        //mv.addObject("userClickNewName", true);
        mv.addObject("msg", "NewFirstName: "+name);
        return mv;
    }
}
