package com.divanxan.internetshop.controller;

import com.divanxan.internetshop.service.CartService;
import com.divanxan.internetshop.util.FileUploadUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Controller for management cart of supplier
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Controller("cartController")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    /**
     * Constructor initializing the service class
     *
     * @param  cartService
     */
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Controller showing the cart information prior to purchase
     *
     * @param result
     * @return ModelAndView with information about cart
     */
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
        mv.addObject("altogether", cartService.getAltogether());
        mv.addObject("cart", cartService.getCart());

        logger.info(mv.toString());
        return mv;

    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String submit(@RequestParam Map<String, String> map, Model model) {
        String promocode = map.get("promocode");
        boolean addingPromocode = true;
        if(promocode!=null) addingPromocode = cartService.setPromocode(promocode);
        else cartService.deletePromocode();
        return "redirect:/cart/show";
    }

    /**
     * Controller for updating count products in cartLine
     *
     * @param cartLineId
     * @param count of product
     * @return String with redirect information
     */
// обновляем содержимое корзины в графе количество
    @RequestMapping("/{cartLineId}/update")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {

        String response = cartService.updateCartLine(cartLineId, count);
        logger.info("Cart line id: "+cartLineId+" update");
        return "redirect:/cart/show?"+response;

    }

    /**
     * Controller for delete cartLine
     *
     * @param cartLineId
     * @return String with redirect information
     */
    // удаляем стоку в корзине
    @RequestMapping("/{cartLineId}/delete")
    public String deleteCart(@PathVariable int cartLineId) {

        String response = cartService.deleteCartLine(cartLineId);
        logger.info("Cart line id: "+cartLineId+" deleted");
        return "redirect:/cart/show?"+response;

    }

    /**
     * Controller for adding cartLine
     *
     * @param productId
     * @return String with redirect information
     */
    // добавляем позицию в корзине
    @RequestMapping("/add/{productId}/product")
    public String addCartLine(@PathVariable int productId) {
        String response = cartService.addCartLine(productId);
        logger.info("Cart line added");
        return "redirect:/cart/show?"+response;
    }

    /**
     * Controller for checking the number of products in the warehouse and in the cart
     *
     * @return String with redirect information
     */
    @RequestMapping("/validate")
    public String validateCart() {
        String response = cartService.validateCartLine();
        if(!response.equals("result=success")) {
            logger.info("Products count modified");
            return "redirect:/cart/show?"+response;
        }
        else {
            logger.info("Products ready to ordered");
            return "redirect:/cart/checkout";
        }
    }

    /**
     * Controller for checking the availability of goods in the warehouse before placing an order
     *
     * @return String with redirect information
     */
    @RequestMapping("/checkProducts")
    public String checkProduct() {
        String response = cartService.checkProducts();
        if(response.equals("true")) {
            logger.info("Order is processed");
            return "redirect:/order/show";
        }
        else {
            logger.info("Order is not processed");
            return "redirect:/cart/show?"+response;
        }
    }
}
