package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.Product;

import java.util.List;

/**
 * Dao interface for management Product
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
public interface ProductDao {

    /**
     * Getting product by id
     *
     * @param productId - id of getting product
     * @return Product
     */
    Product get(int productId);

    /**
     * Getting all products
     *
     * @return List<Product> - list of all products
     */
    List<Product> list();

    /**
     * Adding product
     *
     * @param product - adding product
     * @return boolean (success information)
     */
    boolean add(Product product);

    /**
     * Updating product
     *
     * @param product - updating product
     * @return boolean (success information)
     */
    boolean update(Product product);

    /**
     * Deleting product
     *
     * @param product - deleting Product
     * @return boolean (success information)
     */
    boolean delete(Product product);

    /**
     * Setting product - active
     *
     * @param product - activation product
     * @return boolean (success information)
     */
    boolean setActiv(Product product);

    /**
     * Deleting product from DB
     *
     * @param product - deleting product
     * @return boolean (success information)
     */
    boolean deleteForTest(Product product);

    /**
     * Getting list of active products
     *
     * @return List<Product> - list active products
     */
    //buisness methods
    List<Product> listActiveProducts();

    /**
     * Getting list of active products by Category
     *
     * @param categoryId
     * @return List<Product> - list active products by Category
     */
    List<Product> listActiveProductsByCategory(int categoryId);

    /**
     * Getting count list of active products
     *
     * @param count - count of active products
     * @return  List<Product> - list of active products (count of products = @param count)
     */
    List<Product> getLatestActiveProducts(int count);

    /**
     * Getting top 10 products
     *
     * @return List<Product> - list top 10 products
     */
    List<Product> getTopProducts();
}
