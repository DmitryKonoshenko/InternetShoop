package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dto.Category;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(Category category) {
        if(category.getName().length()>50) category.setName(category.getName().substring(0,49));
        if(category.getDescription().length()>255) category.setDescription(category.getDescription().substring(0,254));
        try {
            sessionFactory.getCurrentSession().persist(category);
            logger.info("category added: "+category.toString());
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error("error category added: "+category.toString());
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        if(category.getName().length()>50) category.setName(category.getName().substring(0,49));
        if(category.getDescription().length()>255) category.setDescription(category.getDescription().substring(0,249));
        logger.info("category update: "+category.toString());
        return updateCode(category);
    }

    @Override
    public boolean delete(Category category) {
        logger.info("category deactivate: "+category.toString());
        category.setActive(false);
        return updateCode(category);
    }

    @Override
    public boolean setActive(Category category) {
        category.setActive(true);
        logger.info("category activate: "+category.toString());
        return updateCode(category);
    }

    @Override
    public boolean deleteForTest(Category category) {
        try{
            sessionFactory.getCurrentSession().delete(category);
            logger.info("category deleted: "+category.toString());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("error category deleted: "+category.toString());
            return false;
        }
    }

    private boolean updateCode(Category category) {
        try{
            sessionFactory.getCurrentSession().update(category);
            logger.info("category update: "+category.toString());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("error category update: "+category.toString());
            return false;
        }
    }

    @Override
    public List<Category> list() {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listCategory")
                .setParameter("active",true);
        logger.info("get categoru list");
        return query.getResultList();
    }

    @Override
    public Category get(int id) {
        logger.info("get category, id: " +id);
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }
}
