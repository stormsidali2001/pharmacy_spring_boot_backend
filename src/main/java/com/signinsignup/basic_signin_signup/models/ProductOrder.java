package com.signinsignup.basic_signin_signup.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.signinsignup.basic_signin_signup.models.enums.OrderStatus;


@Entity(name = "order")
@Table(name =  "order")
public class ProductOrder {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "order_generator"
    )
    private Long id;

    private Long quantity;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    //relations
    @ManyToOne
    Client client;
    @ManyToOne
    Product product;


    
}
