package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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
            e.printStackTrace();
            return null;
        }
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
    public Address getBillingAddress(int userId) {
        String selectQuerry = "FROM Address WHERE userId =:userId AND billing =:billing";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuerry, Address.class)
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
        String selectQuerry = "FROM Address WHERE userId =:userId AND shipping =:shipping";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuerry, Address.class)
                    .setParameter("userId", userId)
                    .setParameter("shipping", true)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateCart(Cart cart) {
        try {
            sessionFactory.getCurrentSession().update(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
