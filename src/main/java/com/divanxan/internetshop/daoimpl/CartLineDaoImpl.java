package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.OrderDetail;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("cartLineDao")
@Transactional
public class CartLineDaoImpl implements CartLineDao {

    private final SessionFactory sessionFactory;


    @Autowired
    public CartLineDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public CartLine get(int id) {
        return sessionFactory.getCurrentSession().get(CartLine.class, id);
    }

    @Override
    public boolean add(CartLine cartLine) {
        try{
            sessionFactory.getCurrentSession().persist(cartLine);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(CartLine cartLine) {
        try{
            sessionFactory.getCurrentSession().update(cartLine);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(CartLine cartLine) {
        try{
            sessionFactory.getCurrentSession().delete(cartLine);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CartLine> list(int cartId) {
        Query query = sessionFactory.getCurrentSession()
                .getNamedQuery("listCartLine")
                .setParameter("cartId", cartId);
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
                .setParameter("available",true);
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
            sessionFactory.getCurrentSession().persist(orderDetail);
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
            sessionFactory.getCurrentSession().update(orderDetail);
    }
}
