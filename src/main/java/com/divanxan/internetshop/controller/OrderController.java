package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.*;
import com.divanxan.internetshop.model.CheckoutModel;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartLineDao cartLineDao;

    @Autowired
    private HttpSession session;

    @RequestMapping("/show")
    public ModelAndView showOrder() throws Exception {

        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        User user = userDao.getByEmail(email);

        ModelAndView mv = new ModelAndView("order");

        CheckoutModel checkoutModel = null;

        List<Address> addresses = null;

        if (user != null) {
            checkoutModel = new CheckoutModel();
            checkoutModel.setUser(user);
            checkoutModel.setCart(user.getCart());

            double checkoutTotal = 0.0;
            List<CartLine> cartLines = cartLineDao.listAvailable(user.getCart().getId());

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

        Address shipping = new Address();

        session.setAttribute("checkoutModel", checkoutModel);
        session.setAttribute("addresses", addresses);
        mv.addObject("shipping", shipping);


        return mv;
    }

    @RequestMapping("/{addressId}/select")
    public String selectAddress(@PathVariable int addressId) {

        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");

        List<Address> addresses = (List<Address>) session.getAttribute("addresses");

        for (Address  address:addresses) {
            if(address.getId() == addressId) checkoutModel.setShipping(address);
        }

        session.setAttribute("checkoutModel", checkoutModel);



        return "redirect:/order/payment";

    }

    @RequestMapping(value = "/show", method = RequestMethod.POST )
    public String addAddress(@ModelAttribute("shipping") Address shipping ) {

        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");



        //checkoutModel.setShipping(shipping);

        //session.setAttribute("checkoutModel", checkoutModel);

        if(shipping!=null){
            shipping.setUserId(checkoutModel.getUser().getId());
            shipping.setShipping(true);
            userDao.addAddress(shipping);
        }

        return "redirect:/order/show";
    }


    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ModelAndView showPayment(@RequestParam(name = "operation", required = false) String operation){

        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        User user = userDao.getByEmail(email);

        ModelAndView mv = new ModelAndView("orderPayment");
//        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");

        mv.addObject("isPayByCArt" ,"");
        if (operation != null) {
            if (operation.equals("noCart")) {
                mv.addObject("message", "Введите данные карты!");
            }
        }
        return mv;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String saveOrder(@RequestParam Map<String,String> map){
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


    @RequestMapping("/ordered")
    public ModelAndView ordered()  {
        ModelAndView mv = new ModelAndView("orderOrdered");
        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");
        OrderDetail orderDetail = checkoutModel.getOrderDetail();
        mv.addObject("orderDetail", orderDetail);
        return mv;
    }
}

