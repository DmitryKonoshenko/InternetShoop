package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.dto.PromoCode;
import com.divanxan.internetshop.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is a service class and serves to receive a cart, add the product to the cart, remove the product from the cart
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Service("cartService")
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CartLineDao cartLineDao;
    private final HttpSession session;
    private final ProductDao productDao;

    @Autowired
    public CartService(CartLineDao cartLineDao, HttpSession session, ProductDao productDao) {
        this.cartLineDao = cartLineDao;
        this.session = session;
        this.productDao = productDao;
    }


    /**
     * This method serves to retrieve or create a user's Cart
     *
     * @return Cart - getting or creating Cart
     */
    // возвращает корзину зарегестрированого покупателя
    public Cart getCart() {
        Cart cart;
        try {
            UserModel userModel = ((UserModel) session.getAttribute("userModel"));
            cart = userModel.getCart();
        } catch (Exception e) {
            UserModel userModel = (UserModel) session.getAttribute("userModel");
            cart = new Cart();
            cart.setGrandTotal(new BigDecimal(0));
            userModel.setCart(cart);
            session.setAttribute("userModel", userModel);
        }
        logger.info("getting cart:"+cart.toString());
        return cart;
    }

    private List<CartLine> getCartLinesList() {
        List<CartLine> list = (List<CartLine>) session.getAttribute("AnonymousCartLines");
        return list;
    }

    /**
     * This method serves to obtain a list of products added to the cart
     *
     * @return List<CartLine> - list of CartLines in Cart
     */
    //возвращает содержимое корзины
    public List<CartLine> getCartLines() {
        Cart cart = this.getCart();
        List<CartLine> cartLines = null;
        if (cart.getUser() != null) {
            cartLines = cartLineDao.list(cart.getId());
        } else {
            cartLines = this.getCartLinesList();
        }

        if (cartLines != null) {
            for (CartLine cartLine : cartLines) {
                int count = cartLine.getProductCount();
                int productCount = productDao.get(cartLine.getProduct().getId()).getQuantity();
                if (count > productCount) cartLine.setProductCount(productCount);
            }
        }
        logger.info("getting CartLines of cart: "+cart.toString());
        return cartLines;
    }

    private BigDecimal getDiscount(Cart cart, BigDecimal total){
        BigDecimal discount = null;
        if(cart.getPromoCode()!=null) discount = total.multiply((new BigDecimal(cart.getPromoCode().getDiscount())).divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        else discount = new BigDecimal(0);
        return  discount;
    }

    /**
     * This method serves to update the number of products of one type in the cart
     *
     * @param cartLineId
     * @param count
     * @return String - key of success
     */
    // обновим количество товара в корзине (используется в методе updateCart в CartController)
    public String updateCartLine(int cartLineId, int count) {
        Cart cart = this.getCart();
        CartLine cartLine;
        // если пользователь зарегестирован
        if (cart.getUser() != null) {
            // получим строку корзины
            cartLine = cartLineDao.get(cartLineId);
        }
        // если пользователь аноним
        else {
            cartLine = this.getCartLines().get(cartLineId);
        }

        if (cartLine == null) {
            logger.error("updating cartLine failed. CartLine=null");
            return "result=error";
        } else {
            Product product = cartLine.getProduct();

            BigDecimal oldTotal = cartLine.getTotal();

            // если покупатель хочет взять больше товара, чем у нас есть в наличии
            if (product.getQuantity() <= count) {
                count = product.getQuantity();
            }
            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setTotal(product.getUnitPrice().multiply(new BigDecimal(count)));
            // если пользователь зарегестирован
            if (cart.getUser() != null) {
                //обновляем сроку в корзине
                cartLineDao.update(cartLine);
            }

            // обновляем общую стоимость покупки
            BigDecimal total = cart.getGrandTotal().subtract(oldTotal).add(cartLine.getTotal());
            cart.setGrandTotal(total);
            // обновляем корзину в бд если пользователь зарегестирован
            if (cart.getUser() != null) {
                cartLineDao.updateCart(cart);
            }
            logger.info("cartLine update, cart: "+cart.toString());
            return "result=update";
        }

    }

     /**
     * This method is used to remove a product from the cart
     *
     * @param cartLineId - id of CartLine
     * @return String - key of success
     */
    // удалим товар из корзины (используется в методе deleteCart в CartController)
    public String deleteCartLine(int cartLineId) {
        // получим строку корзины
        Cart cart = this.getCart();
        if (cart.getUser() != null) {
            //если пользователь зарегестрирован
            CartLine cartLine = cartLineDao.get(cartLineId);
            if (cartLine == null) {
                logger.error("deleting cartLine failed. CartLine=null");
                return "result=error";
            } else {
                // удалим стоимость данной позиции
                cart.setGrandTotal(cart.getGrandTotal().subtract(cartLine.getTotal()));
                cart.setCartLines(cart.getCartLines() - 1);
                cartLineDao.updateCart(cart);
                // удалим позицию
                cartLineDao.delete(cartLine);
                logger.info("cartLine delete, cart: "+cart.toString());
                return "result=deleted";
            }
        } else {
            List<CartLine> list = this.getCartLines();
            CartLine cartLine = null;
            for (CartLine line: list) {
                if(line.getId() == cartLineId){
                    cartLine=line;
                    break;
                }
            }
            if (cartLine == null) {
                logger.error("deleting cartLine failed. CartLine=null");
                return "result=error";
            } else {
                cart.setGrandTotal(cart.getGrandTotal().subtract(cartLine.getTotal()));
                cart.setCartLines(cart.getCartLines() - 1);
                int i = 0;
                for (; i <list.size() ; i++) {
                    if(list.get(i).getId()==cartLineId){
                        break;
                    }
                }
                list.remove(i);
                session.setAttribute("AnonymousCartLines", list);
                logger.info("cartLine delete, cart: "+cart.toString());
                return "result=deleted";
            }
        }
    }

    /**
     * This method serves to add the product to the cart
     *
     * @return String - key of success
     */
    // добавим товар в корзину (используется в методе addCart в CartController)
    public String addCartLine(int productId) {
        String response = null;

        Cart cart = this.getCart();
        CartLine cartLine = null;

        try {
            cartLine = cartLineDao.getByCartAndProduct(cart.getId(), productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cartLine == null) {
            List<CartLine> list = this.getCartLines();
            if(list!=null && list.size()>0){
                for (CartLine line: list) {
                    if(line.getProduct().getId() == productId) {
                        cartLine=line;
                        break;
                    }
                }
            }
        }
        if (cartLine == null) {
            //добавим новую позицию
            cartLine = new CartLine();
            //найдем нужный товар
            Product product = productDao.get(productId);

            cartLine.setCartId(cart.getId());

            cartLine.setProduct(product);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setProductCount(1);

            cartLine.setTotal(product.getUnitPrice());
            cartLine.setAvailable(true);
            //если пользователь - зарегестрирован
            if (cart.getUser() != null) {
                cartLineDao.add(cartLine);
            } else {
                //если пользователь - Аноним
                List<CartLine> cartLines = this.getCartLines();
                if (cartLines != null) {
                    //TODO может все рухнет из-за этого
                    CartLine last = null;
                    if(cartLines.size()>0){
                        last = cartLines.get(cartLines.size() - 1);
                    cartLine.setId(last.getId() + 1);
                    }
                    cartLines.add(cartLine);
                } else {
                    cartLines = new ArrayList<>();
                    cartLines.add(cartLine);
                    session.setAttribute("AnonymousCartLines", cartLines);
                }
            }
            cart.setCartLines(cart.getCartLines() + 1);
            cart.setGrandTotal(cart.getGrandTotal().add(cartLine.getTotal()));
            //если пользователь - зарегестрирован внесем изменения в БД
            if (cart.getUser() != null) {
                cartLineDao.updateCart(cart);
            }
            response = "result=added";
        }

        return response;
    }

    /**
     * this method validate CartLines in Cart before creating order
     *
     * @return String - key of success
     */
    public String validateCartLine() {
        Cart cart = this.getCart();

        //TODO
        List<CartLine> cartLines = this.getCartLines();
        BigDecimal grandTotal = new BigDecimal(0.0);
        int lineCount = 0;
        String response = "result=success";
        boolean changed;
        Product product = null;
        for (CartLine cartLine : cartLines) {
            product = cartLine.getProduct();
            changed = false;
            // check if the product is active or not
            // if it is not active make the availability of cartLine as false
            if ((!product.isActive() && product.getQuantity() == 0) && cartLine.isAvailable()) {
                cartLine.setAvailable(false);
                changed = true;
            }
            // check if the cartLine is not available
            // check whether the product is active and has at least one quantity available
            if ((product.isActive() && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
                cartLine.setAvailable(true);
                changed = true;
            }

            // check if the buying price of product has been changed
            if (!cartLine.getBuyingPrice().equals(product.getUnitPrice())) {
                // set the buying price to the new price
                cartLine.setBuyingPrice(product.getUnitPrice());
                // calculate and set the new total
                cartLine.setTotal(product.getUnitPrice().multiply(new BigDecimal(cartLine.getProductCount())));
                changed = true;
            }

            // check if that much quantity of product is available or not
            if (cartLine.getProductCount() > product.getQuantity()) {
                cartLine.setProductCount(product.getQuantity());
                cartLine.setTotal(product.getUnitPrice().multiply(new BigDecimal(cartLine.getProductCount())));
                changed = true;

            }

            // changes has happened
            if (changed) {
                //update the cartLine
                cartLineDao.update(cartLine);
                // set the result as modified
                response = "result=modified";
            }

            grandTotal = grandTotal.add(cartLine.getTotal());
            lineCount++;
        }

        cart.setCartLines(lineCount);
        cart.setGrandTotal(grandTotal);
        cartLineDao.updateCart(cart);
        logger.info("validate CartLine, response: "+response);
        return response;
    }

    /**
     * This method combines the session basket with the user's database in the DB
     *
     * @param cart1 - merging cart
     */
    public void mergeCart(Cart cart1) {

        List<CartLine> cartLines1 =this.getCartLinesList();

        List<CartLine> cartLines2 =cartLineDao.listAvailable(cart1.getId());

        boolean isChanged = false;

        if(cartLines2.size()>0) {
            boolean isOneProduct = false;
            for (int i = 0; i < cartLines1.size(); i++) {
                for (int j = 0; j < cartLines2.size(); j++) {
                    if (cartLines1.get(i).getProduct().getId() == cartLines2.get(j).getProduct().getId()) {
                        isOneProduct = true;
                        break;
                    }
                }
                if (!isOneProduct) {
                    CartLine cartLine = cartLines1.get(i);
                    cartLine.setId(0);
                    cartLine.setCartId(cart1.getId());
                    cart1.setCartLines(cart1.getCartLines()+1);
                    cart1.setGrandTotal(cart1.getGrandTotal().add(cartLine.getTotal()));
                    cartLineDao.add(cartLine);
                    isChanged = true;
                }
                isOneProduct = false;
            }
        }
        else if(cartLines1.size()>0){
            for (CartLine cartLine:cartLines1) {
                cartLine.setId(0);
                cartLine.setCartId(cart1.getId());
                cart1.setCartLines(cart1.getCartLines()+1);
                cart1.setGrandTotal(cart1.getGrandTotal().add(cartLine.getTotal()));
                cartLineDao.add(cartLine);
            }
            isChanged = true;
        }
        if(isChanged) cartLineDao.updateCart(cart1);
        logger.info("carts merged");
    }

    public String checkProducts() {
        Cart cart = this.getCart();
        List<CartLine> cartLines = cartLineDao.listAvailable(cart.getId());
        for (CartLine cartLine: cartLines) {
            if(cartLine.getProductCount()==0 || cartLine.getProductCount()>cartLine.getProduct().getQuantity()) {
                int countBefore  = cartLine.getProductCount();
                cartLine.setProductCount(cartLine.getProduct().getQuantity());
                int countAfter  = cartLine.getProductCount();
                int rezult = countBefore - countAfter;
                cartLine.setTotal(cartLine.getProduct().getUnitPrice().multiply(new BigDecimal(cartLine.getProductCount())));
                cart.setGrandTotal(cart.getGrandTotal().subtract(cartLine.getProduct().getUnitPrice().multiply(new BigDecimal(rezult))));
                cartLineDao.update(cartLine);
                logger.info("check faled");
                return "false";
            }
        }
        logger.info("checked");
        return "true";
    }

    private List<PromoCode> getPromocode() {
        List<PromoCode> promoCodes = cartLineDao.listPromocodes();
        return promoCodes;
    }

    /**
     * Getting price of order with discount
     *
     * @return BigDecimal - price with discount
     */
    public BigDecimal getAltogether() {
        Cart cart = this.getCart();
        BigDecimal total = cart.getGrandTotal();
        BigDecimal discount = this.getDiscount(cart, total);
        logger.info("getting total price of order with discount");
        return total.subtract(discount);
    }

    /**
     * This method setting promocode in Cart
     *
     * @param promocode - String promocode
     * @return boolean (success of operation)
     */
    public boolean setPromocode(String promocode) {
        Cart cart = this.getCart();
        List<PromoCode> promoCodes = this.getPromocode();
        for (PromoCode promoCode : promoCodes){
            if(promoCode.getCode().equals(promocode)){
                cart.setPromoCode(promoCode);
                cartLineDao.updateCart(cart);
                logger.info("promocode added");
                return true;
            }
        }
        logger.info("promocode NOT added");
        return false;
    }

    /**
     * This method deleting promocode from cart
     */
    public void deletePromocode() {
        Cart cart = this.getCart();
        cart.setPromoCode(null);
        cartLineDao.updateCart(cart);
        logger.info("promocode deleted");
    }
}
