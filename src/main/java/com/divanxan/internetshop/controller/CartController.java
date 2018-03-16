package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("cartController")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView mv = new ModelAndView("page");

        if(result!=null){

            switch (result){
                case "false":
                    mv.addObject("message","Данное количество товара не доступно");
                    break;
            }

        }

        mv.addObject("title", "Корзина");
        mv.addObject("userClickShowCart", true);
        mv.addObject("cartLines", cartService.getCartLines());

        return mv;

    }

// обновляем содержимое корзины в графе количество
    @RequestMapping("/{cartLineId}/update")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {

        String response = cartService.updateCartLine(cartLineId, count);

        return "redirect:/cart/show?"+response;

    }

    // удаляем стоку в корзине
    @RequestMapping("/{cartLineId}/delete")
    public String deleteCart(@PathVariable int cartLineId) {

        String response = cartService.deleteCartLine(cartLineId);

        return "redirect:/cart/show?"+response;

    }

    // добавляем позицию в корзине
    @RequestMapping("/add/{productId}/product")
    public String addCartLine(@PathVariable int productId) {
        String response = cartService.addCartLine(productId);
        return "redirect:/cart/show?"+response;
    }


    @RequestMapping("/validate")
    public String validateCart() {
        String response = cartService.validateCartLine();
        if(!response.equals("result=success")) {
            return "redirect:/cart/show?"+response;
        }
        else {
            return "redirect:/cart/checkout";
        }
    }


    @RequestMapping("/checkProducts")
    public String checkProduct() {
        String response = cartService.checkProducts();
        if(response.equals("true")) {
            return "redirect:/order/show";
        }
        else {
            return "redirect:/cart/show?"+response;
        }
    }
}
