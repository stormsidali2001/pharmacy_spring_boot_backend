package com.signinsignup.basic_signin_signup.models;

import java.sql.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;


@Entity(name="product")
@Table
public class Product {
    @Id
    @SequenceGenerator(
        name ="product_generator",
        sequenceName = "product_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "product_generator"
    )
    private Long id;
    
    private String name;
    private String description;
    private Long price;
    private String imageUrl;
    private Long quantity;
    @CreatedDate
    private Date createdAt;




    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    
}
