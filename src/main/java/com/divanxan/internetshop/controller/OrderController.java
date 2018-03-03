package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dao.UserDao;
import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.User;
import com.divanxan.internetshop.model.CheckoutModel;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public ModelAndView showPayment(){
        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        User user = userDao.getByEmail(email);

        ModelAndView mv = new ModelAndView("orderPayment");
        CheckoutModel checkoutModel = (CheckoutModel) session.getAttribute("checkoutModel");

        mv.addObject("checkoutModel" ,checkoutModel);
        return mv;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String endPayment(@RequestParam CheckoutModel checkoutModel){

        return "redirect:/order/ordered";
    }
}
