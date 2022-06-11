package com.signinsignup.basic_signin_signup.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.signinsignup.basic_signin_signup.products.dto.ProductDTO;


@EntityListeners(AuditingEntityListener.class)
@Entity(name="product")
@Table
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String name;
    private String description;
    private Long price;
    private String imageUrl;
    private Long quantity;

    //relations
    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Order> orders;
  
   
    private LocalDateTime createdAt;



    public Product(ProductDTO product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.imageUrl = product.getImageUrl();
        this.description = product.getDescription();
        this.createdAt = LocalDateTime.now();
        
        
    }
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

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(quantity, product.quantity) && Objects.equals(orders, product.orders) && Objects.equals(createdAt, product.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, imageUrl, quantity, orders, createdAt);
    }

    
}
