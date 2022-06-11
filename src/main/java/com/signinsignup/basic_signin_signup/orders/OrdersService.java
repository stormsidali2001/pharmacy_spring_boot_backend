package com.signinsignup.basic_signin_signup.orders;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.signinsignup.basic_signin_signup.orders.dto.OrderProductDTO;

@Service
public class OrdersService {

    public String createOrder(Set<OrderProductDTO> products) {
        products.forEach(pr->{
            
        });
        return null;
    }
    
}
