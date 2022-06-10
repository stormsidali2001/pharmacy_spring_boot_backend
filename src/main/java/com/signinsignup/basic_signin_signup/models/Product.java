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

    @CreatedDate
    private Date createdAt;

    
}
