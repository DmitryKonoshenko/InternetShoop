package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
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
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private UserDao userDao;

    @Mock
    private ChangeInTop changeInTop;

    @Mock
    private ProductDao productDao;

    @Mock
    private CartLineDao cartLineDao;

    @Mock
    private HttpSession session;

    private UserModel usModel;

    private Cart cart;

    private Product product;

    private CartLine cartLine;

    private User user;

    private List<CartLine> list;

    private List<Address> listA;

    private Address address;

    private CheckoutModel checkoutModel;

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
        usModel.setEmail("!!!");

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

        checkoutModel = new CheckoutModel();
        checkoutModel.setUser(user);
        checkoutModel.setCart(user.getCart());
        checkoutModel.setCartLines(list);
        checkoutModel.setCheckoutTotal(cart.getGrandTotal());
    }

    @Test
    public void getUser() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(userDao.getByEmail("!!!")).thenReturn(user);
        User u = orderService.getUser();
        verify(userDao, times(1)).getByEmail("!!!");
        verify(session, times(1)).getAttribute("userModel");
        assertEquals(u, user);
    }

    @Test
    public void prepareShowOrder() {
        when(cartLineDao.listAvailable(1)).thenReturn(list);
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(userDao.listShippingAddressess(1)).thenReturn(listA);
        when(userDao.getByEmail("!!!")).thenReturn(user);
        try {
            orderService.prepareShowOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(cartLineDao, times(1)).listAvailable(1);
        verify(session, times(1)).setAttribute("addresses", listA);
    }

    @Test
    public void selectAddress() {
        when(session.getAttribute("checkoutModel")).thenReturn(checkoutModel);
        when(userDao.getAddress(1)).thenReturn(address);
        orderService.selectAddress(1);
        verify(session, times(1)).getAttribute("checkoutModel");
        verify(userDao, times(1)).getAddress(1);
        verify(session, times(1)).setAttribute("checkoutModel", checkoutModel);
    }

    @Test
    public void addAddress() {
        when(session.getAttribute("checkoutModel")).thenReturn(checkoutModel);
        orderService.addAddress(address);
        verify(userDao, times(1)).addAddress(address);
    }

    @Test
    public void getCheckoutModel() {
        when(session.getAttribute("checkoutModel")).thenReturn(checkoutModel);
        assertEquals(orderService.getCheckoutModel(), checkoutModel);

        verify(session, times(1)).getAttribute("checkoutModel");
    }
}