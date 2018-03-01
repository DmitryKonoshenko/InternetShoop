package com.divanxan.internetshop.dto;

import com.divanxan.internetshop.dao.UserDao;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

public class UserTest {

    private static AnnotationConfigApplicationContext context;
    private static UserDao userDao;
    private User user = null;
    private Address address = null;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.divanxan.internetshop");
        context.refresh();

        userDao = (UserDao) context.getBean("userDao");
    }

/*    @Test
    public void testAdd(){

        user = new User();
        user.setFirstName("Баранов");
        user.setLastName("Дмитрий");
        user.setEmail("branovdp@gidrokor.ru");
        user.setContactNumber("+79551326411");
        user.setRole("USER");
        user.setPassword("123456");

        // add the user
        assertEquals("Failed to add the user!", true, userDao.addUser(user));

        address = new Address();
        address.setAddressLineOne("Проспект ветеранов д.394 кв556");
        address.setAddressLineTwo("рядом с аптекой Ромашка");
        address.setCity("Санкт-Петербург");
        address.setState("Ленинградская обасть");
        address.setCountry("Россия");
        address.setPostalСode("19855500");
        address.setBilling(true);


        // linked the address with the user
        address.setUserId(user.getId());

        // add the address
        assertEquals("Failed to add the addres!", true, userDao.addAddress(address));


        if(user.getRole().equals("USER")){
            //create a cart
            cart = new Cart();
            cart.setUser(user);

            //add the cart
            assertEquals("Failed to add the cart!", true, userDao.addCart(cart));

            //add shiping adres
            address = new Address();
            address.setAddressLineOne("Проспект обуховской обороны д 123 литера А");
            address.setAddressLineTwo("рядом с магазином Пятерочка");
            address.setCity("Санкт-Петербург");
            address.setState("Ленинградская обасть");
            address.setCountry("Россия");
            address.setPostalСode("19855500");
            // set shipping to true
            address.setShipping(true);

            //link with the user
            address.setUserId(user.getId());

            //add the shipping adress
            assertEquals("Failed to add the shiping address!", true, userDao.addAddress(address));
        }
    }
*/

/*    @Test
    public void testAdd() {
        user = new User();
        user.setFirstName("Баранов");
        user.setLastName("Дмитрий");
        user.setEmail("branovdp@gidrokor.ru");
        user.setContactNumber("+79551326411");
        user.setRole("USER");
        user.setPassword("123456");

        if (user.getRole().equals("USER")) {
            //create a cart
            cart = new Cart();

            cart.setUser(user);

            //attach cart whith the user
            user.setCart(cart);
        }
        // add the user
        assertEquals("Failed to add the user!", true, userDao.addUser(user));
    }


    @Test
    public void testUpdateCart(){

        user = userDao.getByEmail("branovdp@gidrokor.ru");

        cart = user.getCart();

        cart.setGrandTotal(5555);

        cart.setCartLines(2);

        assertEquals("Failed to update the cart", true, userDao.updateCart(cart));

        user = userDao.getByEmail("branovdp@gidrokor.ru");
        userDao.delleteForTestUser(user);
    }
*/

 /*
 @Test
    public void testAddAddress(){

        user = new User();
        user.setFirstName("Баранов");
        user.setLastName("Дмитрий");
        user.setEmail("branovdp@gidrokor.ru");
        user.setContactNumber("+79551326411");
        user.setRole("USER");
        user.setPassword("123456");

        // add the user
        assertEquals("Failed to add the user!", true, userDao.addUser(user));

        address = new Address();
        address.setAddressLineOne("Проспект ветеранов д.394 кв556");
        address.setAddressLineTwo("рядом с аптекой Ромашка");
        address.setCity("Санкт-Петербург");
        address.setState("Ленинградская обасть");
        address.setCountry("Россия");
        address.setPostalСode("19855500");
        address.setBilling(true);

       address.setUser(user);

       assertEquals("Failed to add address!", true, userDao.addAddress(address));

        //add shiping adres
        address = new Address();
        address.setAddressLineOne("Проспект обуховской обороны д 123 литера А");
        address.setAddressLineTwo("рядом с магазином Пятерочка");
        address.setCity("Санкт-Петербург");
        address.setState("Ленинградская обасть");
        address.setCountry("Россия");
        address.setPostalСode("19855500");
        // set shipping to true
        address.setShipping(true);

        address.setUser(user);

        assertEquals("Failed to add shipping address!", true, userDao.addAddress(address));
    }
    */


    @Test
    public void crudTestForUserAndAddress() {

        user = new User();
        user.setFirstName("Баранов");
        user.setLastName("Дмитрий");
        user.setEmail("branovdp@gidrokor.ru");
        user.setContactNumber("+79551326411");
        user.setRole("USER");
        user.setPassword("123456");

        // add the user
        assertEquals("Failed to add the user!", true, userDao.addUser(user));

        address = new Address();
        address.setAddressLineOne("Проспект обуховской обороны д 223 литера А");
        address.setAddressLineTwo("рядом с магазином Пятерочка");
        address.setCity("Москва");
        address.setState("Московская область");
        address.setCountry("Россия");
        address.setPostalCode("19855500");
        // set shipping to true
        address.setShipping(true);

        address.setUserId(user.getId());

        assertEquals("Failed to add shipping address!", true, userDao.addAddress(address));

        address = new Address();
        address.setAddressLineOne("Проспект ветеранов д.394 кв556");
        address.setAddressLineTwo("рядом с аптекой Ромашка");
        address.setCity("Санкт-Петербург");
        address.setState("Ленинградская обасть");
        address.setCountry("Россия");
        address.setPostalCode("19855500");
        address.setBilling(true);

        address.setUserId(user.getId());

        assertEquals("Failed to add address!", true, userDao.addAddress(address));

        user = null;

        user = userDao.getByEmail("branovdp@gidrokor.ru");

        assertEquals("Failed to fetch the list of address and size does not mutch!"
                , 1, userDao.listShippingAddressess(user.getId()).size());

        String str = userDao.getBillingAddress(user.getId()).getCity();

        assertEquals("Failed to fetch the list of address and size does not mutch!"
                , "Санкт-Петербург", userDao.getBillingAddress(user.getId()).getCity());

    }


    @AfterClass
    public static void reinit() {
        User user = userDao.getByEmail("branovdp@gidrokor.ru");
        userDao.delleteForTestUser(user);
    }
}