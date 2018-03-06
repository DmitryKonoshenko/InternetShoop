package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.dto.Address;
import com.divanxan.internetshop.dto.OrderDetail;
import com.divanxan.internetshop.model.CheckoutModel;
import com.divanxan.internetshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/show")
    public ModelAndView showOrder() throws Exception {


        ModelAndView mv = new ModelAndView("order");

        orderService.prepareShowOrder();

        Address shipping = new Address();

        mv.addObject("shipping", shipping);


        return mv;
    }

    @RequestMapping("/{addressId}/select")
    public String selectAddress(@PathVariable int addressId) {

        orderService.selectAddress(addressId);

        return "redirect:/order/payment";

    }

    @RequestMapping(value = "/show", method = RequestMethod.POST )
    public String addAddress(@Valid @ModelAttribute("shipping") Address shipping, BindingResult result) {

        if(result.hasErrors()){
            return "order";
        }
        orderService.addAddress(shipping);
        return "redirect:/order/show";
    }


    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ModelAndView showPayment(){

        ModelAndView mv = new ModelAndView("orderPayment");
        mv.addObject("title", "Оплата");

        return mv;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String saveOrder(@RequestParam Map<String,String> map){

        return orderService.saveOrder(map);
    }


    @RequestMapping("/ordered")
    public ModelAndView ordered()  {
        ModelAndView mv = new ModelAndView("orderOrdered");
        CheckoutModel checkoutModel = orderService.getCheckoutModel();
        OrderDetail orderDetail = checkoutModel.getOrderDetail();
        mv.addObject("orderDetail", orderDetail);
        return mv;
    }
}

