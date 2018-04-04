package com.divanxan.internetshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Simple POJO class of Product
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version1.0
 */
@NamedQueries({
        @NamedQuery(
                name = "listActiveProduct",
                query = "FROM Product WHERE active=:active"
        ),
        @NamedQuery(
                name = "listActiveProductsByCategory",
                query = "FROM Product WHERE active=:active AND categoryId=:categoryId"
        ),
        @NamedQuery(
                name = "getLatestActiveProducts",
                query = "FROM Product WHERE active=:active ORDER BY id"
        ),
        @NamedQuery(
                name = "getTopProducts",
                query = "FROM Product WHERE active=:active ORDER BY purchases desc"
        ),
        @NamedQuery(
                name = "product",
                query = "FROM Product WHERE id=:id"
        )
})
@Component
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(name = "code")
    private String code;


    @Column(name = "name")
    @NotBlank(message ="Добавьте название товара!")
    private String name;

    @Getter
    @Setter
    @Column(name = "brand")
    @NotBlank(message ="Добавьте название бренда!")
    private String brand;

    @Getter
    @Setter
    @Column(name = "description")
    @NotBlank(message = "Введите значение для описания товара!")
    private String description;

    @Getter
    @Setter
    @Column(name = "unit_price")
    @Min(value = 1, message = "Выберите хотя бы 1 значение!")
    private BigDecimal unitPrice;

    @Getter
    @Setter
    @Column(name = "quantity")
    private int quantity;

    @Getter
    @Setter
    @Column(name = "is_active")
    private boolean active;

    @Getter
    @Setter
    @Column(name = "category_id")
    @JsonIgnore
    private int categoryId;

    @Getter
    @Setter
    @Column(name = "supplier_id")
    @JsonIgnore
    private int supplierId;

    @Getter
    @Setter
    @Column(name = "purchases")
    private int purchases;

    @Getter
    @Setter
    @Column(name = "views")
    private int views;

    @Getter
    @Setter
    @NotFound(action=NotFoundAction.IGNORE)
    @ManyToOne(optional = true)
    @JsonIgnore
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product productDis;

    @Getter
    @Setter
    @Column(name = "discount")
    private int discount;

    @Column(name = "product_id", nullable = false)
    @JsonIgnore
    private int productDisId;

//    @Column(name = "vishes")
//    private int vishes;


    @Transient
    @Getter
    @Setter
    @JsonIgnore
    private MultipartFile file;


    //default constructor
    public Product() {
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    public int getProductDisId() {
        return productDisId;
    }

    public void setProductDisId(int productDisId) {
        this.productDisId = productDisId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", active=" + active +
                ", categoryId=" + categoryId +
                ", supplierId=" + supplierId +
                ", purchases=" + purchases +
                ", views=" + views +
                ", productDis=" + productDis +
                ", discount=" + discount +
                ", productDisId=" + productDisId +
                '}';
    }
}
