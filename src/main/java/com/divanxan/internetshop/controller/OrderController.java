package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.exception.UserAccessException;
import com.divanxan.internetshop.model.CheckoutModel;
import com.divanxan.internetshop.service.CartService;
import com.divanxan.internetshop.service.MailService;
import com.divanxan.internetshop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Controller for orders
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final CartService cartService;
    private final MailService mailService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService, MailService mailService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.mailService = mailService;
    }

    /**
     * Show provisional information about order
     *
     * @return ModelAndView
     * @throws UserAccessException
     */
    @RequestMapping("/show")
    public ModelAndView showOrder() throws UserAccessException {
        ModelAndView mv = new ModelAndView("order");
        orderService.prepareShowOrder();
        Address shipping = new Address();
        mv.addObject("shipping", shipping);
        logger.info(mv.toString());
        return mv;
    }

    /**
     * Select shipping address
     *
     * @param addressId
     * @return String with redirect information
     */
    @RequestMapping("/{addressId}/select")
    public String selectAddress(@PathVariable int addressId) {
        orderService.selectAddress(addressId);
        logger.info("Address with id: " +addressId+" selected like shipping");
        return "redirect:/order/payment";
    }

    /**
     * Managed address information
     *
     * @param shipping address of shipping
     * @param result error
     * @return String with redirect information
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String addAddress(@Valid @ModelAttribute("shipping") Address shipping, BindingResult result) {
        if (result.hasErrors()) {
            return "order";
        }
        orderService.addAddress(shipping);
        logger.info("Address added: " +shipping.toString());
        return "redirect:/order/show";
    }


    /**
     * Managed payment and delivered information
     *
     * @param operation - index of modal message
     * @return ModelAndView
     * @throws UserAccessException
     */
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ModelAndView showPayment(@RequestParam(name = "operation", required = false) String operation) throws UserAccessException {
        ModelAndView mv = new ModelAndView("orderPayment");
        mv.addObject("title", "Оплата");
        if (operation != null) {
            if (operation.equals("noCart")) {
                mv.addObject("message", "Введите данные карты!");
            }
        }
        BigDecimal altogether = cartService.getAltogether();
        CheckoutModel checkoutModel = orderService.getCheckoutModel();
        if (altogether.intValue() == 0 || checkoutModel == null) throw new UserAccessException();
        mv.addObject("altogether", altogether);
        logger.info(mv.toString());
        return mv;
    }

    /**
     * Save order
     *
     * @param map with order information
     * @return String with redirect information
     */
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String saveOrder(@RequestParam Map<String, String> map) {
        return orderService.saveOrder(map);
    }


    /**
     * Show order information
     *
     * @return ModelAndView
     * @throws UserAccessException
     */
    @RequestMapping("/ordered")
    public ModelAndView ordered() throws UserAccessException {
        ModelAndView mv = new ModelAndView("orderOrdered");
        CheckoutModel checkoutModel = orderService.getCheckoutModel();
        if (checkoutModel == null) throw new UserAccessException();
        OrderDetail orderDetail = checkoutModel.getOrderDetail();
        mv.addObject("orderDetail", orderDetail);
        mailService.send(orderDetail);
        orderService.ListCompare();
        logger.info(mv.toString());
        return mv;
    }
}

