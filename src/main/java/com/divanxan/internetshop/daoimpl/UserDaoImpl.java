package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delleteForTestUser(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public User getByEmail(String email) {
        String selectQuery = "FROM User WHERE email =:email";

        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getById(int id) {
        String selectQuery = "FROM User WHERE id =:id";
        return sessionFactory.getCurrentSession()
                .createQuery(selectQuery, User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public boolean addAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().persist(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Address getAddress(int addressId) {
        try {
            return sessionFactory.getCurrentSession().get(Address.class, addressId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().update(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Address getBillingAddress(int userId) {
        String selectQuery = "FROM Address WHERE userId =:userId AND billing =:billing";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Address.class)
                    .setParameter("userId", userId)
                    .setParameter("billing", true)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Address> listShippingAddressess(int userId) {
        String selectQuery = "FROM Address WHERE userId =:userId AND shipping =:shipping";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Address.class)
                    .setParameter("userId", userId)
                    .setParameter("shipping", true)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Address> listAddressess(int userId) {
        String selectQuery = "FROM Address WHERE userId =:userId";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Address.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderDetail> listOrders(int userId) {
        String selectQuery = "FROM OrderDetail WHERE user.id =:userId";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, OrderDetail.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderDetail> listAllOrders() {
        String selectQuery = "FROM OrderDetail";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, OrderDetail.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderDetail> listThisMonthOrders() {
        String selectQuery = "FROM OrderDetail WHERE orderDate BETWEEN :date1 AND :date2";

        Calendar date1 = Calendar.getInstance();   // this takes current date
        date1.set(Calendar.DAY_OF_MONTH, 1);
        Calendar date2 = (Calendar) date1.clone();
        date2.add(Calendar.MONTH, 1);

            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, OrderDetail.class)
                    .setParameter("date1", date1.getTime())
                    .setParameter("date2", date2.getTime())
                    .getResultList();

    }

    @Override
    public List<Product> getTopProducts() {
        String selectQuery = "FROM Product ORDER BY purchases desc ";

            List<Product> list = sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Product.class)
                    .setMaxResults(10)
                    .getResultList();
            return  list;
    }

    @Override
    public List<OrderDetail> listThisWeekOrders() {
        String selectQuery = "FROM OrderDetail WHERE orderDate BETWEEN :date1 AND :date2";

        Calendar date1 = Calendar.getInstance();   // this takes current date
        date1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Calendar date2 = (Calendar) date1.clone();
        date2.add(Calendar.DAY_OF_WEEK, 7);

        return sessionFactory.getCurrentSession()
                .createQuery(selectQuery, OrderDetail.class)
                .setParameter("date1", date1.getTime())
                .setParameter("date2", date2.getTime())
                .getResultList();
    }

}
