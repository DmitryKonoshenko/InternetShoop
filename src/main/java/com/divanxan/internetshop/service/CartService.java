package com.divanxan.internetshop.service;

import com.divanxan.internetshop.dao.CartLineDao;
import com.divanxan.internetshop.dao.ProductDao;
import com.divanxan.internetshop.dto.Cart;
import com.divanxan.internetshop.dto.CartLine;
import com.divanxan.internetshop.dto.Product;
import com.divanxan.internetshop.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Данный класс является сервисным и служит для получения корзины, добавления продукта в корзину, удаления продукта из корзины.
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Service("cartService")
public class CartService {

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
     * Данный метод служит для получения или создания корзины пользователя.
     *
     * @return Cart
     */
    // возвращает корзину зарегестрированого покупателя
    private Cart getCart() {
        Cart cart;
        try {
            cart = ((UserModel) session.getAttribute("userModel")).getCart();
        } catch (Exception e) {
            UserModel userModel = (UserModel) session.getAttribute("userModel");
            cart = new Cart();
            userModel.setCart(cart);
            session.setAttribute("userModel", userModel);
        }
        return cart;
    }

    private List<CartLine> getCartLinesList() {
        List<CartLine> list = (List<CartLine>) session.getAttribute("AnonymousCartLines");
        return list;
    }

    /**
     * Данный метод служит для получения списка продуктов, добавленных в корзину.
     *
     * @return List<CartLine>
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

        return cartLines;
    }

    /**
     * Данный метод служит обновления колличества продукта одного вида в корзине.
     *
     * @return String
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
            return "result=error";
        } else {
            Product product = cartLine.getProduct();

            double oldTotal = cartLine.getTotal();

            // если покупатель хочет взять больше товара, чем у нас есть в наличии
            if (product.getQuantity() <= count) {
                count = product.getQuantity();
            }
            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setTotal(product.getUnitPrice() * count);
            // если пользователь зарегестирован
            if (cart.getUser() != null) {
                //обновляем сроку в корзине
                cartLineDao.update(cartLine);
            }

            // обновляем общую стоимость покупки
            cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
            // обновляем корзину в бд если пользователь зарегестирован
            if (cart.getUser() != null) {
                cartLineDao.updateCart(cart);
            }
            return "result=update";
        }

    }

    /**
     * Данный метод служит для удаления продукта из корзины.
     *
     * @return String
     */
    // удалим товар из корзины (используется в методе deleteCart в CartController)
    public String deleteCartLine(int cartLineId) {
        // получим строку корзины
        Cart cart = this.getCart();
        if (cart.getUser() != null) {
            //если пользователь зарегестрирован
            CartLine cartLine = cartLineDao.get(cartLineId);
            if (cartLine == null) {
                return "result=error";
            } else {
                // удалим стоимость данной позиции
                cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
                cart.setCartLines(cart.getCartLines() - 1);
                cartLineDao.updateCart(cart);
                // удалим позицию
                cartLineDao.delete(cartLine);

                return "result=deleted";
            }
        } else {
            List<CartLine> list = this.getCartLines();
            CartLine cartLine = list.get(cartLineId);
            if (cartLine == null) {
                return "result=error";
            } else {
                cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
                cart.setCartLines(cart.getCartLines() - 1);
                list.remove(cartLineId);
                session.setAttribute("AnonymousCartLines", list);
                return "result=deleted";
            }
        }
    }

    /**
     * Данный метод служит для добавления продукта в корзину.
     *
     * @return String
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
                    CartLine last = cartLines.get(cartLines.size() - 1);
                    cartLine.setId(last.getId() + 1);
                    cartLines.add(cartLine);
                } else {
                    cartLines = new ArrayList<>();
                    cartLines.add(cartLine);
                    session.setAttribute("AnonymousCartLines", cartLines);
                }
            }
            cart.setCartLines(cart.getCartLines() + 1);
            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
            //если пользователь - зарегестрирован внесем изменения в БД
            if (cart.getUser() != null) {
                cartLineDao.updateCart(cart);
            }
            response = "result=added";
        }

        return response;
    }

    public String validateCartLine() {
        Cart cart = this.getCart();
        String email = ((UserModel) session.getAttribute("userModel")).getEmail();
        //TODO
        List<CartLine> cartLines = this.getCartLines();
        double grandTotal = 0.0;
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
            if (cartLine.getBuyingPrice() != product.getUnitPrice()) {
                // set the buying price to the new price
                cartLine.setBuyingPrice(product.getUnitPrice());
                // calculate and set the new total
                cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
                changed = true;
            }

            // check if that much quantity of product is available or not
            if (cartLine.getProductCount() > product.getQuantity()) {
                cartLine.setProductCount(product.getQuantity());
                cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
                changed = true;

            }

            // changes has happened
            if (changed) {
                //update the cartLine
                cartLineDao.update(cartLine);
                // set the result as modified
                response = "result=modified";
            }

            grandTotal += cartLine.getTotal();
            lineCount++;
        }

        cart.setCartLines(lineCount++);
        cart.setGrandTotal(grandTotal);
        cartLineDao.updateCart(cart);

        return response;
    }
}
