package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.Product;

import java.util.List;

public interface ProductDao {

    Product get(int productId);
    List<Product> list();
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(Product product);
    boolean setActiv(Product product);
    boolean deleteForTest(Product product);

    //buisness methods
    List<Product> listActiveProducts();
    List<Product> listActiveProductsByCategory(int categoryId);
    List<Product> getLatestActiveProducts(int count);

}
