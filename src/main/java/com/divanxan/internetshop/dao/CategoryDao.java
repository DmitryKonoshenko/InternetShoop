package com.divanxan.internetshop.dao;

import com.divanxan.internetshop.dto.Category;

import java.util.List;

/**
 * Dao interface for management Cart
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
public interface CategoryDao {

    /**
     * Getting CartLine list of Cart
     *
     * @return List<Category> - all category
     */
    List<Category> list();

    /**
     * Getting Category by id
     *
     * @param id - category id
     * @return Category
     */
    Category get(int id);

    /**
     * Adding Category
     *
     * @param category - adding Category
     * @return boolean (success information)
     */
    boolean add(Category category);

    /**
     * Updating Category
     *
     * @param category - updating Category
     * @return boolean (success information)
     */
    boolean update(Category category);

    /**
     * Deleting Category
     *
     * @param category - deleting Category
     * @return boolean (success information)
     */
    boolean delete(Category category);

    /**
     *
     * @param category - Category which set active
     * @return boolean (success information)
     */
    boolean setActive(Category category);

    /**
     *
     * @param category - deleting Category
     * @return boolean (success information)
     */
    boolean deleteForTest(Category category);
}
