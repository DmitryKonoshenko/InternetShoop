package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Category;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {

    @InjectMocks
    private ManagerService managerService;

    @Mock
    private ProductDao productDao;

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private UserDao userDao;

    @Mock
    private CartLineDao cartLineDao;

    private Product product;

    private Category category;

    private OrderDetail orderDetail;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId(1);
        product.setQuantity(2);
        product.setActive(true);
        product.setUnitPrice(new BigDecimal(50));

        category = new Category();
        category.setId(1);
        category.setActive(true);
        category.setName("one");

        orderDetail = new OrderDetail();
        orderDetail.setId(1);
        orderDetail.setOrderCount(1);
    }

    @Test
    public void getProductById() {
        managerService.getProductById(1);
        verify(productDao, times(1)).get(1);
    }

    @Test
    public void addProduct() {
        managerService.addProduct(product);
        verify(productDao, times(1)).add(product);
    }

    @Test
    public void updateProduct() {
        managerService.updateProduct(product);
        verify(productDao, times(1)).update(product);
    }

    @Test
    public void addCategory() {
        managerService.addCategory(category);
        verify(categoryDao, times(1)).add(category);
    }

    @Test
    public void updateCategory() {
        managerService.updateCategory(category);
        verify(categoryDao, times(1)).update(category);
    }

    @Test
    public void getListCategory() {
        managerService.getListCategory();
        verify(categoryDao, times(1)).list();
    }

    @Test
    public void getListAllOrders() {
        managerService.getListAllOrders();
        verify(userDao, times(1)).listAllOrders();
    }

    @Test
    public void updateOrderDetail() {
        managerService.updateOrderDetail(orderDetail);
        verify(cartLineDao, times(1)).updateOrderDetail(orderDetail);
    }

    @Test
    public void getTopProducts() {
        managerService.getTopProducts();
        verify(userDao, times(1)).getTopProducts();
    }

    @Test
    public void getTopUsers() {
        managerService.getTopUsers();
        verify(userDao, times(1)).listThisMonthOrders();
    }

    @Test
    public void getCashByWeek() {
        managerService.getCashByWeek();
        verify(userDao, times(1)).listThisWeekOrders();
    }

    @Test
    public void getCashByMonth() {
        managerService.getCashByMonth();
        verify(userDao, times(1)).listThisMonthOrders();
    }
}