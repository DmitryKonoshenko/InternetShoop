package com.divanxan.internetshop.dto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = "userGetByEmail",
                query = "FROM User WHERE email=:email"
        ),
        @NamedQuery(
                name = "userGetById",
                query = "FROM User WHERE id=:id"
        )
})
@Entity
@Table(name = "user_detail")
public class User implements Serializable{

    private static final long serialVersionUID = 777777777L;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "Введите имя!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Введите фамилию!")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "Введите почту")
    private String email;

    @Column(name = "contact_number")
    @NotBlank(message = "Введите телефон")
    private String contactNumber;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    @NotBlank(message = "Введите пароль")
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

// поле для проверки пароля
    @Transient
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //------------------
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    //------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
