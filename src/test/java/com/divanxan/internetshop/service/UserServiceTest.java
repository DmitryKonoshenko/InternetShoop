package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.*;
import com.divanxan.internetshop.model.CheckoutModel;
import com.divanxan.internetshop.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private HttpSession session;

    private UserModel usModel;

    private Cart cart;

    private Product product;

    private CartLine cartLine;

    private User user;

    private List<CartLine> list;

    private List<Address> listA;

    private List<OrderDetail> listD;

    private Address address;

    private OrderDetail orderDetail;


    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        cart.setId(1);
        cart.setGrandTotal(new BigDecimal(0));
        cart.setCartLines(0);

        cartLine = new CartLine();
        cartLine.setId(3);
        cartLine.setProductCount(1);
        cartLine.setTotal(new BigDecimal(111));
        cartLine.setBuyingPrice(new BigDecimal(111));
        cartLine.setAvailable(true);
        cartLine.setCartId(1);
        cart.setGrandTotal(cartLine.getTotal());

        usModel = new UserModel();
        usModel.setId(1);
        usModel.setCart(cart);
        usModel.setEmail("!!");

        user = new User();
        user.setId(1);
        user.setFirstName("user");
        user.setCart(cart);
        cart.setUser(user);

        product = new Product();
        product.setId(1);
        product.setQuantity(6);
        product.setActive(true);
        product.setUnitPrice(new BigDecimal(50));
        cartLine.setProduct(product);

        list = new ArrayList<>();
        list.add(cartLine);

        address = new Address();
        address.setId(1);

        listA = new ArrayList<>();
        listA.add(address);

        orderDetail = new OrderDetail();
        orderDetail.setId(1);
        listD = new ArrayList<>();

        listD.add(orderDetail);
    }

    @Test
    public void getUser() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(userDao.getByEmail("!!")).thenReturn(user);
        User u = userService.getUser();
        verify(userDao, times(1)).getByEmail("!!");
        verify(session, times(1)).getAttribute("userModel");
        assertEquals(u, user);
    }

    @Test
    public void getAddresses() {
        when(userDao.listAddressess(1)).thenReturn(listA);
        assertEquals(userService.getAddresses(1), listA);
    }

    @Test
    public void getOrders() {
        when(userDao.listOrders(1)).thenReturn(listD);
        assertEquals(userService.getOrders(1), listD);
    }

    @Test
    public void getBillingAddress() {
        when(userDao.getBillingAddress(1)).thenReturn(address);
        assertEquals(userService.getBillingAddress(1), address);
    }

    @Test
    public void update() {
        userService.update(user);
        verify(userDao, times(1)).update(user);
    }

    @Test
    public void getEmail() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        assertEquals(userService.getEmail(), "!!");
        verify(session, times(1)).getAttribute("userModel");
    }

    @Test
    public void setAddressId() {
        userService.setAddressId(address.getId());
        verify(session, times(1)).setAttribute("addressId", address.getId());
    }
}