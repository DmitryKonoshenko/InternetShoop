package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Category;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.exception.ProductNotFoundException;
import com.divanxan.internetshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller for {@link com.divanxan.internetshop.dto.Category}'s pages.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    private final CategoryDao categoryDao;

    private final ProductDao productDao;

    private final UserService userService;

    @Autowired
    public PageController(CategoryDao categoryDao, ProductDao productDao, UserService userService) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.userService = userService;
    }

    /**
     * Функция возврата на домашнюю страницу
     *
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Home
     */
    @RequestMapping(value = {"/", "home"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "На главную");

        logger.info("Inside PageController index method - INFO");
        logger.debug("Inside PageController index method - DEBUG");


        //вставка листа категория
        mv.addObject("categories", categoryDao.list());


        mv.addObject("userClickHome", true);

        return mv;
    }

    /**
     * Функция перехода на страницу "О нас"
     *
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку About
     */
    @RequestMapping(value = {"/about"})
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "О нас");
        mv.addObject("userClickAbout", true);
        return mv;
    }

    /**
     * Функция перехода на страницу "Контакты"
     *
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Contact
     */
    @RequestMapping(value = {"/contact"})
    public ModelAndView contact() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Наши контакты");
        mv.addObject("userClickContact", true);
        return mv;
    }

    /**
     * Функция для загрузки всех товаров
     *
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Contact
     */
    @RequestMapping(value = {"/show/all/products"})
    public ModelAndView showAllProducts() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Наши продукты");

        //вставка листа категория
        mv.addObject("categories", categoryDao.list());

        mv.addObject("userClickAllProducts", true);
        return mv;
    }

    /**
     * Функция для загрузки всех товаров в категории
     *
     * @return Возвращает ModelAndView обьект с полями title и информацией о нажатии на вкладку Contact
     */
    @RequestMapping(value = {"/show/category/{id}/products"})
    public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
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

    // просмотр одного товара
    @RequestMapping(value = "/show/{id}/product")
    public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {

        ModelAndView mv = new ModelAndView("page");

        Product product = productDao.get(id);

        if (product == null) throw new ProductNotFoundException();

        //update the view count
        product.setViews(product.getViews() + 1);
        productDao.update(product);
        //done

        mv.addObject("title", product.getName());
        mv.addObject("product", product);

        mv.addObject("userClickShowProduct", true);

        return mv;
    }

// такой же запрос для нашено flow id
//    @RequestMapping(value = {"/register"})
//    public ModelAndView register(){
//        ModelAndView mv = new ModelAndView("page");
//        mv.addObject("title", "О нас");
//        return mv;
//    }


    //Login
    @RequestMapping(value = {"/login"})
    public ModelAndView login(@RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "logout", required = false) String logout) {
        ModelAndView mv = new ModelAndView("login");

        int count = userService.getLoginCount();
        if (error != null) {
            if (count < 3) mv.addObject("message", "Неправильный логин и пароль");
            else mv.addObject("message", "Слишком много попыток ввода");
            count++;
            userService.setLoginCount(count);
        }
        if (logout != null) {
            mv.addObject("logout", "Пользователь успешно вышел из магазина!");
        }
        if(count>=3) mv.addObject("message", "Слишком много попыток ввода");
        mv.addObject("count",count);
        mv.addObject("title", "login");
        return mv;
    }


    // страница ошибки доступа, как в spring-security.xml
    @RequestMapping(value = {"/access-denied"})
    public ModelAndView accessDenied() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("title", "403 - Access Denied");
        mv.addObject("errorTitle", "Вы не можете тут находится!");
        mv.addObject("errorDescription"
                , "У вас нет прав администратора, чтобы видеть содержимое данной страницы!");
        return mv;
    }


    //для logout
    @RequestMapping(value = "/perform-logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //удалим аутентификацию из SecurityContext. Он делает session.invalidate(); и SecurityContextHolder.clearContext();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }

}
