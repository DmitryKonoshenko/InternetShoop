package com.divanxan.internetshop.controller;


import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

    private final ProductDao productDao;

    @Autowired
    public JsonDataController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts(){

        return productDao.listActiveProducts();
    }


    @RequestMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsForAdmin(){

        return productDao.list();
    }


    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable int id){

        return productDao.listActiveProductsByCategory(id);
    }

}
