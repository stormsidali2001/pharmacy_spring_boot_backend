package com.signinsignup.basic_signin_signup.models;

import java.sql.Date;
import java.time.LocalDateTime;
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
    
    @Column(unique = true)
    private String name;
    private String description;
    private Long price;
    private String imageUrl;
    private Long quantity;

    //relations
    @OneToMany()
    private Set<Client> clients;
  
   
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


    
}
