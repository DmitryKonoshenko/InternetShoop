package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private HttpSession session;

    @Mock
    private CartLineDao cartLineDao;

    @Mock
    private ProductDao productDao;

    private CartLine cartLine;
    private CartLine cartLine2;

    private UserModel usModel;

    private Cart cart;
    private Cart cart2;

    private Product product;

    private List<CartLine> list;
    private List<CartLine> list2;

    private User user;

    @Before
    public void setData() {

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

        product = new Product();
        product.setId(1);
        product.setQuantity(6);
        product.setActive(true);
        product.setUnitPrice(new BigDecimal(50));
        cartLine.setProduct(product);

        user = new User();
        user.setId(1);
        user.setFirstName("user");
        cart.setUser(user);

        usModel = new UserModel();
        usModel.setId(1);
        usModel.setCart(cart);


        list = new ArrayList<CartLine>();
        list.add(cartLine);

        cart2 = new Cart();
        cart2.setId(2);
        cart2.setGrandTotal(new BigDecimal(0));
        cart2.setCartLines(1);

        cartLine2 = new CartLine();
        cartLine2.setId(3);
        cartLine2.setProductCount(2);
        cartLine2.setTotal(new BigDecimal(222));
        cartLine2.setBuyingPrice(new BigDecimal(111));
        cartLine2.setAvailable(true);
        cartLine2.setCartId(2);
        cartLine2.setProduct(product);

        list2 = new ArrayList<CartLine>();
        list2.add(cartLine2);
    }


    @Test
    public void getCartLines() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(cartLineDao.list(1)).thenReturn(list);
        when(productDao.get(1)).thenReturn(product);
        List<CartLine> list1 = cartService.getCartLines();
        verify(cartLineDao, times(1)).list(1);
        assertEquals(list1, list);
    }

    @Test
    public void updateCartLine1() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        String str = cartService.updateCartLine(3, 3);
        assertEquals(str, "result=error");
    }

    @Test
    public void updateCartLine2() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(cartLineDao.get(3)).thenReturn(cartLine);
        String str = cartService.updateCartLine(3, 3);
        assertEquals(str, "result=update");
    }

    @Test
    public void deleteCartLine() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(cartLineDao.get(3)).thenReturn(cartLine);
        String str = cartService.deleteCartLine(3);
        verify(cartLineDao, times(1)).updateCart(cart);
        verify(cartLineDao, times(1)).delete(cartLine);
        assertEquals(str, "result=deleted");
    }

    @Test
    public void addCartLine() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(productDao.get(1)).thenReturn(product);
        String str = cartService.addCartLine(1);
        verify(cartLineDao, times(1)).getByCartAndProduct(cart.getId(), 1);
        verify(productDao, times(1)).get(1);
        assertEquals(str, "result=added");
    }

    @Test
    public void validateCartLine() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(cartLineDao.list(cart.getId())).thenReturn(list);
        when(productDao.get(1)).thenReturn(product);
        String str = cartService.validateCartLine();
        verify(productDao, times(1)).get(1);
        verify(cartLineDao, times(1)).list(cart.getId());
        assertEquals(str, "result=modified");
    }

    @Test
    public void mergeCart() {
        when(cartLineDao.listAvailable(2)).thenReturn(list);
        when(session.getAttribute("AnonymousCartLines")).thenReturn(list2);
        cartService.mergeCart(cart2);
        verify(cartLineDao, times(0)).updateCart(cart2);
    }

    @Test
    public void checkProducts() {
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(cartLineDao.listAvailable(1)).thenReturn(list);
        String str = cartService.checkProducts();
        assertEquals(str, "true");
    }

    @Test
    public void checkProducts2() {
        product.setQuantity(0);
        when(session.getAttribute("userModel")).thenReturn(usModel);
        when(cartLineDao.listAvailable(1)).thenReturn(list);
        String str = cartService.checkProducts();
        assertEquals(str, "false");
    }
}