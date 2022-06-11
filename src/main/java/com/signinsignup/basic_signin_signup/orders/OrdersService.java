package com.signinsignup.basic_signin_signup.orders;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.signinsignup.basic_signin_signup.models.Order;
import com.signinsignup.basic_signin_signup.models.OrderRepository;
import com.signinsignup.basic_signin_signup.models.Product;
import com.signinsignup.basic_signin_signup.models.ProductRepository;
import com.signinsignup.basic_signin_signup.models.User;
import com.signinsignup.basic_signin_signup.models.enums.OrderStatus;
import com.signinsignup.basic_signin_signup.orders.dto.OrderProductDTO;

@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public String createOrder(Set<OrderProductDTO> products) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user  = (User) auth.getPrincipal();
      

        // ArrayList<Order> orders = new ArrayList<>();
        // products.forEach(pr->{
        //     Product product = productRepository.getById(pr.getProductId());
        //     Order order = new Order(client, product, pr.getQuantity(), OrderStatus.WAIT);
          
        // });
        return null;
    }
    
}
