package com.divanxan.internetshop.dto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = "getBillingAddress",
                query = "FROM Address WHERE userId=:userId AND billing=:billing"
        ),
        @NamedQuery(
                name = "listShippingAddresses",
                query = "FROM Address WHERE userId=:userId AND shipping=:shipping"
        ),
        @NamedQuery(
                name = "listAddresses",
                query = "FROM Address WHERE userId=:userId"
        )
})
@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 888888888L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address_line_one")
    @NotBlank(message = "Введите адрес")
    private String addressLineOne;

    @Column(name = "address_line_two")
    private String addressLineTwo;

    @Column(name = "city")
    @NotBlank(message = "Введите город")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "Введите область")
    private String state;

    @Column(name = "country")
    @NotBlank(message = "Введите страну")
    private String country;

    @Column(name = "postal_code")
    @NotBlank(message = "Введите почтовый индекс")
    private String postalCode;

    @Column(name = "is_shipping")
    private boolean shipping;

    @Column(name = "is_billing")
    private boolean billing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public boolean isBilling() {
        return billing;
    }

    public void setBilling(boolean billing) {
        this.billing = billing;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", shipping=" + shipping +
                ", billing=" + billing +
                ", userId=" + userId +
                '}';
    }

    @Column(name = "user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}

