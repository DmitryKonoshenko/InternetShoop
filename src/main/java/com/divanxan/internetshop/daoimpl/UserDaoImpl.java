package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
            logger.info("adding user:"+ user.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error adding user:"+ user.toString());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            logger.info("updating user:"+ user.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error updating user:"+ user.toString());
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
        if (email.equals("anonymousUser")) return null;
        try {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("userGetByEmail")
                .setParameter("email", email);
            logger.info("getting user by email:"+ email);
        return (User) query.getSingleResult();
        } catch (Exception e) {
            logger.error("error getting user by email:"+ email);
            return null;
        }
    }

    @Override
    public User getById(int id) {

        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("userGetById")
                .setParameter("id", id);
        logger.info("getting user by id:"+ id);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean addAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().persist(address);
            logger.info("adding address:"+ address.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error adding address:"+ address.toString());
            return false;
        }
    }

    @Override
    public Address getAddress(int addressId) {
        try {
            logger.info("getting address by id:"+ addressId);
            return sessionFactory.getCurrentSession().get(Address.class, addressId);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error getting address by id:"+ addressId);
            return null;
        }
    }

    @Override
    public boolean updateAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().update(address);
            logger.info("updating address:"+ address.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error updating address:"+ address.toString());
            return false;
        }
    }

    @Override
    public Address getBillingAddress(int userId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("getBillingAddress")
                .setParameter("userId", userId)
                .setParameter("billing", true);
        Address address = (Address) query.getSingleResult();
        logger.info("getting billing address by user id:"+ userId);
        return address;
    }

    @Override
    public List<Address> listShippingAddressess(int userId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listShippingAddresses")
                .setParameter("userId", userId)
                .setParameter("shipping", true);

        logger.info("getting list shipping addresses user by id:"+ userId);
        return query.getResultList();
    }

    @Override
    public List<Address> listAddressess(int userId) {

        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listAddresses")
                .setParameter("userId", userId);
        logger.info("getting list addresses user by id:"+ userId);
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> listOrders(int userId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listOrders")
                .setParameter("userId", userId);
        logger.info("getting list OrderDetail user by id:"+ userId);
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> listAllOrders() {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listAllOrders");
        logger.info("getting list all OrderDetail");
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> listThisMonthOrders() {
        Calendar date1 = Calendar.getInstance();   // this takes current date
        date1.set(Calendar.DAY_OF_MONTH, 1);
        date1.set(Calendar.HOUR_OF_DAY, 0);
        date1.set(Calendar.MINUTE, 0);
        date1.set(Calendar.SECOND, 0);
        Calendar date2 = (Calendar) date1.clone();
        date2.add(Calendar.MONTH, 1);
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listDateOrders")
                .setParameter("date1", date1.getTime())
                .setParameter("date2", date2.getTime());
        logger.info("getting list month OrderDetail");
        return query.getResultList();

    }

    @Override
    public List<Product> getTopProducts() {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("getTopProducts")
                .setParameter("active",true)
                .setMaxResults(10);
        logger.info("getting list top products");
        return query.getResultList();
    }

    @Override
    public List<OrderDetail> listThisWeekOrders() {
        Calendar date1 = Calendar.getInstance();   // this takes current date
        date1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        date1.set(Calendar.HOUR_OF_DAY, 0);
        date1.set(Calendar.MINUTE, 0);
        date1.set(Calendar.SECOND, 0);
        Calendar date2 = (Calendar) date1.clone();
        date2.add(Calendar.DAY_OF_WEEK, 7);
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listDateOrders")
                .setParameter("date1", date1.getTime())
                .setParameter("date2", date2.getTime());
       logger.info("getting list weak OrderDetail");
        return query.getResultList();
    }

    @Override
    public OrderDetail getOrderDetail(int id) {
        try {
            logger.info("getting OrderDetail");
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("getOrders")
                .setParameter("id", id);
            logger.info("success getting OrderDetail");
            return (OrderDetail) query.getSingleResult();
        }
        catch (Exception e){
            logger.error("exeption getting orderDetail id:"+ id);
            return null;
        }
    }

    @Override
    public List<OrderDetail> listThisDateOrders(Map<String, String> map) {
        String[] date1 = map.get("calendar1").split("-");
        String[] date2 = map.get("calendar2").split("-");
        int month = Integer.parseInt(date1[1])-1;
        Calendar cal1 = new GregorianCalendar(Integer.parseInt(date1[0]),month,Integer.parseInt(date1[2]));
        logger.info(cal1.getTime().toString());
        month = Integer.parseInt(date2[1])-1;
        Calendar cal2 = new GregorianCalendar(Integer.parseInt(date2[0]),month,Integer.parseInt(date2[2]));
        logger.info(cal2.getTime().toString());
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listDateOrders")
                .setParameter("date1", cal1.getTime())
                .setParameter("date2", cal2.getTime());
        logger.info("getting list dates OrderDetail");
        return query.getResultList();
    }

}
