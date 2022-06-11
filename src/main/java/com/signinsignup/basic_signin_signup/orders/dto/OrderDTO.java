package com.signinsignup.basic_signin_signup.orders.dto;

import java.util.Objects;
import java.util.Set;

public class OrderDTO {
    private Set<OrderProductDTO> products;


    public OrderDTO() {
    }

    public OrderDTO(Set<OrderProductDTO> products) {
        this.products = products;
    }

    public Set<OrderProductDTO> getProducts() {
        return this.products;
    }

    public void setProducts(Set<OrderProductDTO> products) {
        this.products = products;
    }

    public OrderDTO products(Set<OrderProductDTO> products) {
        setProducts(products);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDTO)) {
            return false;
        }
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(products, orderDTO.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "{" +
            " products='" + getProducts() + "'" +
            "}";
    }

}
