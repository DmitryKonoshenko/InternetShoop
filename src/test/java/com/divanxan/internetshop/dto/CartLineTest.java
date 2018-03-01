package com.divanxan.internetshop.dto;

import static org.junit.Assert.assertEquals;

import com.divanxan.internetshop.controller.CartController;
import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;


public class CartLineTest {



    private static AnnotationConfigApplicationContext context;


    private static CartLineDao cartLineDao;
    private static ProductDao productDao;
    private static UserDao userDao;


    private CartLine cartLine = null;


    @BeforeClass
    public static void init() {
		HttpSession session = mock(HttpSession.class);
		CartController cartController = mock(CartController.class);
        context = new AnnotationConfigApplicationContext();
        context.scan("com.divanxan.internetshop");
        context.refresh();
        cartLineDao = (CartLineDao)context.getBean("cartLineDao");
        productDao = (ProductDao)context.getBean("productDao");
        userDao= (UserDao)context.getBean("userDao");
    }

	@Test
	public void testAddCartLine() {

		// fetch the user and then cart of that user
		User user = userDao.getByEmail("ivan@gmail.com");
		Cart cart = user.getCart();

		// fetch the product
		Product product = productDao.get(2);

		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);

		double oldTotal = cartLine.getTotal();

		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());

		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));

		assertEquals("Failed to add the CartLine!",true, cartLineDao.add(cartLine));
		assertEquals("Failed to update the cart!",true, cartLineDao.updateCart(cart));

	}





}
