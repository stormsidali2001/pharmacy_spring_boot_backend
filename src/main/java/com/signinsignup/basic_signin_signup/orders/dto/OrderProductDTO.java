package com.signinsignup.basic_signin_signup.orders.dto;

import java.util.Objects;

import antlr.collections.List;

public class OrderProductDTO {
    private Long productId;
    private Long quantity;

    public OrderProductDTO() {
    }

    public OrderProductDTO(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderProductDTO productId(Long productId) {
        setProductId(productId);
        return this;
    }

    public OrderProductDTO quantity(Long quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderProductDTO)) {
            return false;
        }
        OrderProductDTO orderProductDTO = (OrderProductDTO) o;
        return Objects.equals(productId, orderProductDTO.productId) && Objects.equals(quantity, orderProductDTO.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " productId='" + getProductId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }


}
