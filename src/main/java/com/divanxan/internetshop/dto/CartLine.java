package com.divanxan.internetshop.dto;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = "getByCartAndProduct",
                query = "FROM cart_line Where cartId=:cartId AND product.id=:productId"
        ),
        @NamedQuery(
                name = "listAvailable",
                query = "FROM cart_line Where cartId=:cartId AND available=:available"
        ),
        @NamedQuery(
                name = "listCartLine",
                query = "FROM cart_line where cartId=:cartId"
        )
})
@Entity(name = "cart_line")
public class CartLine implements Serializable {

    private static final long serialVersionUID = 444444444L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Product product;

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "product_count")
    private int productCount;

    @Column(name = "total")
    private double total;

    @Column(name = "buying_price")
    private double buyingPrice;

    @Column(name = "is_available")
    private boolean available =true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    @Override
    public String toString() {
        return "CartLine{" +
                "id=" + id +
                ", product=" + product +
                ", cartId=" + cartId +
                ", productCount=" + productCount +
                ", total=" + total +
                ", buyingPrice=" + buyingPrice +
                ", available=" + available +
                '}';
    }
}
