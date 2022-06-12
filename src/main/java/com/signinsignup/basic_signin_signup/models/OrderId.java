package com.signinsignup.basic_signin_signup.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderId implements Serializable {

    @Column(name = "client_id")
    private Long client;

    @Column(name = "product_id")
    private Long product;

    // getters/setters and most importantly equals() and hashCode()

   

    public OrderId() {
    }

    public OrderId(Long client, Long product) {
        this.client = client;
        this.product = product;
    }

    public Long getClient() {
        return this.client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getProduct() {
        return this.product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public OrderId client(Long client) {
        setClient(client);
        return this;
    }

    public OrderId product(Long product) {
        setProduct(product);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderId)) {
            return false;
        }
        OrderId orderId = (OrderId) o;
        return Objects.equals(client, orderId.client) && Objects.equals(product, orderId.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, product);
    }

    @Override
    public String toString() {
        return "{" +
            " client='" + getClient() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }

}
