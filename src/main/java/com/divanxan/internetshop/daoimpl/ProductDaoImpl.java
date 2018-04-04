package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product get(int productId) {
        try {
            logger.info("getting product, id:"+ productId);
            Query query = sessionFactory.getCurrentSession()
                    .getNamedQuery("product")
                    .setParameter("id",productId);
            Product product = (Product) query.getSingleResult();
            return sessionFactory.getCurrentSession().get(Product.class, productId);

        } catch (Exception e) {
            logger.error("error getting product, id:"+ productId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> list() {
        logger.info("getting product list");
        return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class)
                .getResultList();
    }

    @Override
    public boolean add(Product product) {
        try {

            sessionFactory.getCurrentSession().persist(product);
            logger.info("add product: " + product.toString());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error adding product: " + product.toString());
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {

            sessionFactory.getCurrentSession().update(product);
            logger.info("update product: " + product.toString());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error updating product: " + product.toString());
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try {

            product.setActive(false);
            logger.info("deactivate product: " + product.toString());
            return this.update(product);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error deactivate product: " + product.toString());
        }
        return false;
    }

    @Override
    public boolean setActiv(Product product) {
        try {

            product.setActive(true);
            logger.info("activate product: " + product.toString());
            return this.update(product);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error activate product: " + product.toString());
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
        logger.info("getting list active product");
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
        logger.info("getting product by category");
        List<Product> list = query.getResultList();
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
        logger.info("getting latest active product, count: "+count);
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
                .setParameter("active",true)
                .setMaxResults(10);
        logger.info("getting top products");
        return query.getResultList();

//        String selectQuery = "FROM Product WHERE active=:active ORDER BY purchases desc";
//
//        List<Product> list = sessionFactory.getCurrentSession()
//                .createQuery(selectQuery, Product.class)
//                .setParameter("active",true)
//                .setMaxResults(10)
//                .getResultList();
//        return list;
    }
}
