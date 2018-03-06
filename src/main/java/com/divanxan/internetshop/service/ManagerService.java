package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Category;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service("managerService")
public class ManagerService {

    private final CategoryDao categoryDao;

    private final ProductDao productDao;

    private final UserDao userDao;

    private final CartLineDao cartLineDao;

    @Autowired
    public ManagerService(CategoryDao categoryDao, ProductDao productDao, UserDao userDao, CartLineDao cartLineDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.cartLineDao = cartLineDao;
    }

    public Product getProductById(int id) {
     return productDao.get(id);
    }

    public void addProduct(@Valid Product mProduct) {
        productDao.add(mProduct);
    }

    public void updateProduct(@Valid Product mProduct) {
        productDao.update(mProduct);
    }

    public void addCategory(@Valid Category category) {
        categoryDao.add(category);
    }

    public void updateCategory(@Valid Category category) {
        categoryDao.update(category);
    }

    public List<Category> getListCategory() {
       return categoryDao.list();
    }

    public List<OrderDetail> getListAllOrders() {
      return userDao.listAllOrders();
    }

    public void updateOrderDetail(OrderDetail orderDetail) {
        cartLineDao.updateOrderDetail(orderDetail);
    }

    public List<Product> getTopProducts() {
       return userDao.getTopProducts();
    }

    public Map<User, Double> getTopUsers() {

        List<OrderDetail> orders =  userDao.listThisMonthOrders();
       Map<Integer, Double> map = new TreeMap<Integer, Double>();
        for (OrderDetail order:orders) {
            int id = order.getUser().getId();
            if(!map.containsKey(id)) {
                map.put(id,order.getOrderTotal());
            }
            else{
                map.put(id, map.get(id)+order.getOrderTotal());
            }
        }

        Map<User, Double> userList = new HashMap();
        int count =1;
        for(Map.Entry<Integer,Double> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            userList.put(userDao.getById(key),value);
            count++;
            if(count ==11) break;
        }
        return userList;
    }

    public double getCashByWeek() {
        List<OrderDetail> orders =  userDao.listThisWeekOrders();
        double cash =0;
        for (OrderDetail order:orders) {
            cash+= order.getOrderTotal();
        }
        return cash;
    }
}
