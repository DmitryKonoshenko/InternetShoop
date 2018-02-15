package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер навигации по веб сайту.
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version 1.0
 */
@Controller
public class PageController {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * Функция возврата на домашнюю страницу
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Home
     */
    @RequestMapping(value = {"/","home", "index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "");

        //вставка листа категория
        mv.addObject("categories", categoryDao.list());

        mv.addObject("userClickHome", true);
        return mv;
    }

    /**
     * Функция перехода на страницу "О нас"
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку About
     */
    @RequestMapping(value = {"/about"})
    public ModelAndView about(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "О нас");
        mv.addObject("userClickAbout", true);
        return mv;
    }

    /**
     * Функция перехода на страницу "Контакты"
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Contact
     */
    @RequestMapping(value = {"/contact"})
    public ModelAndView contact(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Наши контакты");
        mv.addObject("userClickContact", true);
        return mv;
    }

//
//    @RequestMapping(value = "/test")
//    public ModelAndView test(@RequestParam("greeting")String greeting){
//        if(greeting==null){
//            greeting="Привет";
//        }
//        ModelAndView mv = new ModelAndView("page");
//        mv.addObject("greeting", greeting);
//        return mv;
//    }

//    @RequestMapping(value = "/test/{greeting}")
//    public ModelAndView test(@RequestParam("greeting")String greeting){
//        if(greeting==null){
//            greeting="Привет";
//        }
//        ModelAndView mv = new ModelAndView("page");
//        mv.addObject("greeting", greeting);
//        return mv;
//    }
}
