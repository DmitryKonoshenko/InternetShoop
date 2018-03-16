package com.divanxan.internetshop.dto;

import com.divanxan.internetshop.config.HibernateConfig;
import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.daoimpl.CartLineDaoImpl;
import com.divanxan.internetshop.daoimpl.ProductDaoImpl;
import com.divanxan.internetshop.daoimpl.UserDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration({"classpath*:../webapp/WEB-INF/dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class CartLineTest {

    @Autowired
    private CartLineDao cartLineDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;


    private CartLine cartLine = null;


//	@BeforeClass
//	public static void init() {
//
//        HttpSession session = new MockHttpSession(null, "test-session-id");
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.setSession(session);
//        request.setRemoteAddr("1.2.3.4");
//
//		context = new AnnotationConfigApplicationContext();
//        context.scan("com.divanxan.internetshop");
//        context.register(HttpSession.class);
//		context.refresh();
//		cartLineDao = (CartLineDao)context.getBean("cartLineDAO");
//		productDao = (ProductDao)context.getBean("productDAO");
//		userDao = (UserDao)context.getBean("userDAO");
//	}

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

        assertEquals("Failed to add the CartLine!", true, cartLineDao.add(cartLine));
        assertEquals("Failed to update the cart!", true, cartLineDao.updateCart(cart));

    }


}

