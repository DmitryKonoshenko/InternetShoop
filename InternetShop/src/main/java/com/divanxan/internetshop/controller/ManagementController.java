package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Category;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.util.FileUploadUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    private final CategoryDao categoryDao;

    private final ProductDao productDao;

    @Autowired
    public ManagementController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "Product Management");
        mv.addObject("userClickManageProducts", true);

        Product nProduct = new Product();

        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);

        if (operation != null) {
            if (operation.equals("product")) {
                mv.addObject("message", "Товар успешно добавлен");
            }
        }
        logger.info(mv.toString());
        return mv;
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String handleProductsSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result
            , Model model, HttpServletRequest request) {

        // проверка на ошибки
        if(result.hasErrors()){

            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Product Management");
            model.addAttribute("message", "Ошибка валидации для добавления товара!");

            return "page";// если тут заюзать redirect:, то ошибки не будут выведены
        }


        logger.info(mProduct.toString());

        // создание нового продукта
        productDao.add(mProduct);


        if(!mProduct.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/product?operation=product";
    }


    @ModelAttribute("categories")
    public List<Category> getCategories() {

        return categoryDao.list();

    }

}
