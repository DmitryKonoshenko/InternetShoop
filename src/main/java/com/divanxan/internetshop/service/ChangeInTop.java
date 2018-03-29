package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("changeInTop")
@Scope(scopeName = "singleton")
public class ChangeInTop {

    private final ProductDao productDao;

    private List<Product> productList;

    @Autowired
    public ChangeInTop(ProductDao productDao) {
        this.productDao = productDao;
        this.productList =productDao.getTopProducts();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
