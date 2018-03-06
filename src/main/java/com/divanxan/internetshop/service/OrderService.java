package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.*;
import com.divanxan.internetshop.model.CheckoutModel;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Данный класс является сервисным для контроллера OrderController.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Service("orderService")
public class OrderService {

    private final UserDao userDao;

    private final ProductDao productDao;

    private final CartLineDao cartLineDao;

    private final HttpSession session;

    @Autowired
    public OrderService(UserDao userDao, ProductDao productDao, CartLineDao cartLineDao, HttpSession session) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.cartLineDao = cartLineDao;
        this.session = session;
    }

    public User getUser(){
        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        return userDao.getByEmail(email);
    }

    private List<CartLine> getListAvailableCartLines(int cartId)
    {
        return cartLineDao.listAvailable(cartId);
    }

    public void prepareShowOrder() throws Exception {
        CheckoutModel checkoutModel = null;

        List<Address> addresses = null;

        User user = this.getUser();

        if (user != null) {
            checkoutModel = new CheckoutModel();
            checkoutModel.setUser(user);
            checkoutModel.setCart(user.getCart());

            double checkoutTotal = 0.0;
            List<CartLine> cartLines = this.getListAvailableCartLines(user.getCart().getId());

            if (cartLines.size() == 0) {
                throw new Exception("There are no products available for checkout now!");
            }

            for (CartLine cartLine : cartLines) {
                checkoutTotal += cartLine.getTotal();
            }

            checkoutModel.setCartLines(cartLines);
            checkoutModel.setCheckoutTotal(checkoutTotal);

            // найдем все адреса пользователя
            addresses = userDao.listShippingAddressess(checkoutModel.getUser().getId());

            if(addresses.size() == 0) {
                addresses = new ArrayList<>();
            }

            addresses.add(addresses.size(), userDao.getBillingAddress(checkoutModel.getUser().getId()));
        }

        session.setAttribute("checkoutModel", checkoutModel);
        session.setAttribute("addresses", addresses);
    }

    public void selectAddress(int addressId) {
        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");

//        List<Address> addresses = (List<Address>) session.getAttribute("addresses");

        Address address = userDao.getAddress(addressId);
        checkoutModel.setShipping(address);
//        for (Address  address:addresses) {
//            if(address.getId() == addressId) checkoutModel.setShipping(address);
//        }

        session.setAttribute("checkoutModel", checkoutModel);
    }

    public void addAddress(@Valid Address shipping) {
        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");

        //checkoutModel.setShipping(shipping);

        //session.setAttribute("checkoutModel", checkoutModel);

        if(shipping!=null){
            shipping.setUserId(checkoutModel.getUser().getId());
            shipping.setShipping(true);
            userDao.addAddress(shipping);
        }

    }

    public CheckoutModel getCheckoutModel() {
        return (CheckoutModel) session.getAttribute("checkoutModel");
    }

    public String saveOrder(Map<String, String> map) {

        String isPayByCArt= map.get("isPayByCArt");
        String cardNumber= map.get("cardNumber");
        String expityMonth= map.get("expityMonth");
        String expityYear= map.get("expityYear");
        String cvCode= map.get("cvCode");

        //сделаем гард кондишн для оплаты
        if(isPayByCArt.equals("cart") &&(cardNumber.equals("")||expityMonth.equals("")
                ||expityYear.equals("")||cvCode.equals(""))){
            return "redirect:/order/payment?operation=noCart";
        }

        //создадим новый заказ
        OrderDetail orderDetail = new OrderDetail();

        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");

        //установим пользхователя
        orderDetail.setUser(checkoutModel.getUser());

        //установим адресс оплаты
        orderDetail.setShipping(checkoutModel.getShipping());



        String delivery = map.get("delivery");
        if(isPayByCArt.equals("cart")) {
            orderDetail.setPay(true);
        }
        if(delivery.equals("byMail")){
            orderDetail.setDelivery(true);
        }

        //установим адрес доставки
        Address billing = userDao.getBillingAddress(checkoutModel.getUser().getId());
        orderDetail.setBilling(billing);

        List<CartLine> cartLines = checkoutModel.getCartLines();
        OrderItem orderItem = null;

        double orderTotal = 0.0;
        int orderCount = 0;
        Product product = null;

        for(CartLine cartLine : cartLines) {

            orderItem = new OrderItem();

            orderItem.setBuyingPrice(cartLine.getBuyingPrice());
            orderItem.setProduct(cartLine.getProduct());
            orderItem.setProductCount(cartLine.getProductCount());
            orderItem.setTotal(cartLine.getTotal());

            orderItem.setOrderDetail(orderDetail);
            orderDetail.getOrderItems().add(orderItem);

            orderTotal += orderItem.getTotal();
            orderCount++;

            // обновим информацию о продукте
            // обновим количество продукта в магазине
            product = cartLine.getProduct();
            product.setQuantity(product.getQuantity() - cartLine.getProductCount());
            product.setPurchases(product.getPurchases() + cartLine.getProductCount());
            productDao.update(product);

            // удалим поле покупки в корзине
            cartLineDao.delete(cartLine);
        }

        orderDetail.setOrderTotal(orderTotal);
        orderDetail.setOrderCount(orderCount);
        orderDetail.setOrderDate(new Date());

        // сохрании заказ в БД
        cartLineDao.addOrderDetail(orderDetail);

        // зададим заказ в модель
        checkoutModel.setOrderDetail(orderDetail);


        // обновим корзину и удалим из нее лишнее(весь заказ)
        Cart cart = checkoutModel.getCart();
        cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
        cart.setCartLines(cart.getCartLines() - orderCount);
        cartLineDao.updateCart(cart);

        // обновим модель покуупателя, чобы не отображать невернуб информацию в корзине
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        if (userModel != null) {
            userModel.setCart(cart);
        }
        session.setAttribute("checkoutModel", checkoutModel);
        return "redirect:/order/ordered";
    }
}

