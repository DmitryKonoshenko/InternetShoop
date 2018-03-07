package com.divanxan.internetshop.dto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * This is simple POJO class. For matching table Category in database.
 *
 * @autor Dmitry Konoshenko
 * @version 1.0
 * @since version 1.0
 */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message ="Добавьте категории!")
    private String name;

    @Column(name = "description")
    @NotBlank(message ="Добавьте описание!")
    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "is_active")
    private boolean active = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", active=" + active +
                '}';
    }
}
