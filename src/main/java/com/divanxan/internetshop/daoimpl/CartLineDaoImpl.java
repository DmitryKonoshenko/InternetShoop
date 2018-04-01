package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.PromoCode;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("cartLineDao")
@Transactional
public class CartLineDaoImpl implements CartLineDao {

    private static final Logger logger = LoggerFactory.getLogger(CartLineDaoImpl.class);

    private final SessionFactory sessionFactory;


    @Autowired
    public CartLineDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public CartLine get(int id) {
        logger.info("getting CartLine, id: " + id);
        return sessionFactory.getCurrentSession().get(CartLine.class, id);
    }

    @Override
    public boolean add(CartLine cartLine) {
        try {
            sessionFactory.getCurrentSession().persist(cartLine);
            logger.info("adding CartLine: " + cartLine.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error adding CartLine: " + cartLine.toString());
            return false;
        }
    }

    @Override
    public boolean update(CartLine cartLine) {
        try {
            sessionFactory.getCurrentSession().update(cartLine);
            logger.info("updating CartLine: " + cartLine.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error updating CartLine: " + cartLine.toString());
            return false;
        }
    }

    @Override
    public boolean delete(CartLine cartLine) {
        try {
            sessionFactory.getCurrentSession().delete(cartLine);
            logger.info("delete CartLine: " + cartLine.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error delete CartLine: " + cartLine.toString());
            return false;
        }
    }

    @Override
    public List<CartLine> list(int cartId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listCartLine")
                .setParameter("cartId", cartId);
        logger.info("getting CartLine list of Cart id: " + cartId);
        return query.getResultList();
        //String query = "FROM CartLine WHERE cartId =:cartId";
//        String query = "FROM cart_line where cartId=:cartId";
//        return sessionFactory.getCurrentSession()
//                .createQuery(query, CartLine.class)
//                .setParameter("cartId",cartId)
//                .getResultList();
    }

    @Override
    public List<CartLine> listAvailable(int cartId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listAvailable")
                .setParameter("cartId", cartId)
                .setParameter("available", true);
        logger.info("getting available  CartLine list of Cart id: " + cartId);
        return query.getResultList();
//        String query = "FROM cart_line Where cartId =:cartId AND available =:available";
//        return sessionFactory.getCurrentSession()
//                .createQuery(query, CartLine.class)
//                .setParameter("cartId",cartId)
//                .setParameter("available",true)
//                .getResultList();
    }

    @Override
    public CartLine getByCartAndProduct(int cartId, int productId) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getByCartAndProduct");
        CartLine cartLine = (CartLine) query.getSingleResult();
        logger.info("getting CartLine by cart id: " + cartId + "and product id: " + productId);
        return cartLine;
//        String query = "FROM cart_line Where cartId =:cartId AND product.id =: productId";
//        try {
//            return sessionFactory.getCurrentSession()
//                    .createQuery(query, CartLine.class)
//                    .setParameter("cartId", cartId)
//                    .setParameter("productId", productId)
//                    .getSingleResult();
//        }
//        catch (Exception e){
//            //e.printStackTrace();
//            return null;
//        }
    }

    @Override
    public boolean updateCart(Cart cart) {
        try {
            sessionFactory.getCurrentSession().update(cart);
            logger.info("update Cart: " + cart.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        logger.info("add OrderDetail: " + orderDetail.toString());
        sessionFactory.getCurrentSession().persist(orderDetail);
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        logger.info("update OrderDetail: " + orderDetail.toString());
        sessionFactory.getCurrentSession().update(orderDetail);
    }

    @Override
    public List<PromoCode> listPromocodes() {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("getPromocode");
        logger.info("get promocode list");
        return query.getResultList();
    }
}
