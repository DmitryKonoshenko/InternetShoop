package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dto.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static List<Category> categories = new ArrayList<>();

    @Override
    public boolean add(Category category) {
        try{
            //добавление категорию в таблицу БД
            sessionFactory.getCurrentSession().persist(category);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        return updateCode(category);
    }

    @Override
    public boolean delete(Category category) {

        category.setActive(false);

        return updateCode(category);
    }

    @Override
    public boolean setActive(Category category) {
        category.setActive(true);

        return updateCode(category);
    }

    @Override
    public boolean deleteForTest(Category category) {
        try{
            //добавление категорию в таблицу БД
            sessionFactory.getCurrentSession().delete(category);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean updateCode(Category category) {
        try{
            //изменение категории в таблице БД
            sessionFactory.getCurrentSession().update(category);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Category> list() {

        String selecteActiveCategory = "FROM Category WHERE active = :active";

        Query query = sessionFactory.getCurrentSession().createQuery(selecteActiveCategory);

        query.setParameter("active", true);

        return query.getResultList();
    }

    @Override
    public Category get(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }
}
