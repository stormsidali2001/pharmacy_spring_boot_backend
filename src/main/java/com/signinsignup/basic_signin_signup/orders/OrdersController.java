package com.signinsignup.basic_signin_signup.orders;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.signinsignup.basic_signin_signup.models.Order;
import com.signinsignup.basic_signin_signup.orders.dto.OrderDTO;
import com.signinsignup.basic_signin_signup.orders.dto.OrderProductDTO;

import ch.qos.logback.core.net.server.Client;

@RestController
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("new")
    public String createOrder(@RequestBody OrderDTO data){
        ArrayList<OrderProductDTO> products =   data.getProducts();
        return ordersService.createOrder(products);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/client")
    public   ArrayList<Order> getClientOrders(){
        return this.ordersService.getClientOrders();
        
    }
   

    // @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public      ArrayList<com.signinsignup.basic_signin_signup.models.Client> getAllOrders(){
        return this.ordersService.getAllOrders();
    }
}
