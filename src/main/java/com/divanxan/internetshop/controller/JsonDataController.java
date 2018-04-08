package com.divanxan.internetshop.controller;


import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * The controller for sending data in the JSON form
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller
@RequestMapping("/json/data")
public class JsonDataController {
    private final ProductDao productDao;
    private static final Logger logger = LoggerFactory.getLogger(JsonDataController.class);

    /**
     * Constructor initializing Dao class for take product information ProductDao
     * @param productDao
     */
    @Autowired
    public JsonDataController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * Method for sending all active products in database in JSON form
     *
     * @return List<Product>  - list of all active products in database
     */
    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts(){
        logger.info("Sending list of all active products");
        return productDao.listActiveProducts();
    }

    /**
     *  Method for sending all products in database in JSON form for administrator
     *
     * @return List<Product>  - list of all products in database for administrator
     */
    @RequestMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsForAdmin(){
        logger.info("Sending list of all active products for administrator");
        return productDao.list();
    }

    /**
     * Method for sending all products in category in JSON form
     *
     * @param id of category
     * @return List<Product> - list of all products in category
     */
    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable int id){
        logger.info("Sending list of all products in category");
        return productDao.listActiveProductsByCategory(id);
    }

    /**
     * Method for sending top 10 products in JSON form
     *
     * @return List<Product> - list top 10 products
     */
    @RequestMapping("/top")
    @ResponseBody
    public List<Product> getProductsTop(){
        logger.info("Sending list top 10 products");
        return productDao.getTopProducts();
    }

}
