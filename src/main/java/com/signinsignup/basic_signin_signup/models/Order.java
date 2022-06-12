package com.signinsignup.basic_signin_signup.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.signinsignup.basic_signin_signup.models.enums.OrderStatus;


@Entity(name = "Order")
@Table(name =  "client_order_product")
public class Order implements Serializable {
   
  
    //relations
    @EmbeddedId
    private OrderId orderId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("clientId")
    Client client;

     
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    Product product;

    private Long quantity;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;


   


    public Order() {
    }

    public Order(OrderId orderId, Client client, Product product, Long quantity, LocalDateTime createdAt, OrderStatus status) {
        this.orderId = orderId;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.status = status;
    }
    public Order( Client client, Product product, Long quantity, OrderStatus status) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
        this.status = status;
        this.orderId = new OrderId();
        this.orderId.setClient(client.getId());
        this.orderId.setProduct(product.getId());
    }

    public OrderId getOrderId() {
        return this.orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order orderId(OrderId orderId) {
        setOrderId(orderId);
        return this;
    }

    public Order client(Client client) {
        setClient(client);
        return this;
    }

    public Order product(Product product) {
        setProduct(product);
        return this;
    }

    public Order quantity(Long quantity) {
        setQuantity(quantity);
        return this;
    }

    public Order createdAt(LocalDateTime createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Order status(OrderStatus status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(client, order.client) && Objects.equals(product, order.product) && Objects.equals(quantity, order.quantity) && Objects.equals(createdAt, order.createdAt) && Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, client, product, quantity, createdAt, status);
    }

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", client='" + getClient() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

    
}
