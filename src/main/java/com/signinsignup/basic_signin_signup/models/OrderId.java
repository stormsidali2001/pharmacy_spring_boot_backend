package com.signinsignup.basic_signin_signup.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderId implements Serializable {

    @Column(name = "client_id")
    private int client;

    @Column(name = "product_id")
    private int product;

    // getters/setters and most importantly equals() and hashCode()

    public OrderId() {
    }

    public OrderId(int client, int product) {
        this.client = client;
        this.product = product;
    }

    public int getClient() {
        return this.client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getProduct() {
        return this.product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public OrderId client(int client) {
        setClient(client);
        return this;
    }

    public OrderId product(int product) {
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
        OrderId commandId = (OrderId) o;
        return client == commandId.client && product == commandId.product;
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
