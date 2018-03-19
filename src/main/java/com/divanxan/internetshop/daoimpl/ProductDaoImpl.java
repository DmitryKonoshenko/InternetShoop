package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product get(int productId) {
        try {

            return sessionFactory.getCurrentSession().get(Product.class, productId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> list() {
        return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class)
                .getResultList();
    }

    @Override
    public boolean add(Product product) {
        try {

            sessionFactory.getCurrentSession().persist(product);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {

            sessionFactory.getCurrentSession().update(product);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try {

            product.setActive(false);
            return this.update(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setActiv(Product product) {
        try {

            product.setActive(true);
            return this.update(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteForTest(Product product) {
        try {

            sessionFactory.getCurrentSession().delete(product);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> listActiveProducts() {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listActiveProduct")
                .setParameter("active",true);
        return query.getResultList();

//        String selectActiveProducts = "FROM Product WHERE active = :active";
//        return sessionFactory.getCurrentSession()
//                .createQuery(selectActiveProducts, Product.class)
//                .setParameter("active", true).getResultList();
    }

    @Override
    public List<Product> listActiveProductsByCategory(int categoryId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listActiveProductsByCategory")
                .setParameter("active",true)
                .setParameter("categoryId",categoryId);
        return query.getResultList();
//        String selectActiveProducts = "FROM Product WHERE active = :active AND categoryId = :categoryId";
//        return sessionFactory.getCurrentSession()
//                .createQuery(selectActiveProducts, Product.class)
//                .setParameter("active", true)
//                .setParameter("categoryId", categoryId)
//                .getResultList();
    }

    @Override
    public List<Product> getLatestActiveProducts(int count) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("getLatestActiveProducts")
                .setParameter("active",true)
                .setFirstResult(0)
                .setMaxResults(count);
        return query.getResultList();
//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
//                .setParameter("active", true)
//                .setFirstResult(0)
//                .setMaxResults(count)
//                .getResultList();
    }

    @Override
    public List<Product> getTopProducts() {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("getTopProducts")
                .setMaxResults(10);
        return query.getResultList();

//        String selectQuery = "FROM Product ORDER BY purchases desc ";
//
//        List<Product> list = sessionFactory.getCurrentSession()
//                .createQuery(selectQuery, Product.class)
//                .setMaxResults(10)
//                .getResultList();
//        return list;
    }
}
