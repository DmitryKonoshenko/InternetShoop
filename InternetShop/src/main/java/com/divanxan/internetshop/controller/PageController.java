package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for {@link com.divanxan.internetshop.dto.Category}'s pages.
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version 1.0
 */
@Controller
public class PageController {

    private final CategoryDao categoryDao;

    @Autowired
    public PageController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * Функция возврата на домашнюю страницу
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Home
     */
    @RequestMapping(value = {"/","home"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "На главную");

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

    /**
     * Функция для загрузки всех товаров
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Contact
     */
    @RequestMapping(value = {"/show/all/products"})
    public ModelAndView showAllProducts(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Наши продукты");

        //вставка листа категория
        mv.addObject("categories", categoryDao.list());

        mv.addObject("userClickAllProducts", true);
        return mv;
    }

    /**
     * Функция для загрузки всех товаров в категории
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Contact
     */
    @RequestMapping(value = {"/show/category/{id}/products"})
    public ModelAndView showCategoryProducts(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("page");

        Category category = categoryDao.get(id);

        mv.addObject("title", category.getName());

        //вставка листа категория
        mv.addObject("categories", categoryDao.list());
        //вставка самой категории
        mv.addObject("category", category);

        mv.addObject("userClickCategoryProducts", true);
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
