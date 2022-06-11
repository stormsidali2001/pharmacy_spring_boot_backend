package com.signinsignup.basic_signin_signup.orders;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.signinsignup.basic_signin_signup.orders.dto.OrderDTO;
import com.signinsignup.basic_signin_signup.orders.dto.OrderProductDTO;

@RestController
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("new")
    public String createOrder(@RequestBody OrderDTO data){
        Set<OrderProductDTO> products =   data.getProducts();
        return ordersService.createOrder(products);
    }
}
