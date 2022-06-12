package com.signinsignup.basic_signin_signup.orders;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.signinsignup.basic_signin_signup.models.Client;
import com.signinsignup.basic_signin_signup.models.ClientRepository;
import com.signinsignup.basic_signin_signup.models.CustomUserDetails;
import com.signinsignup.basic_signin_signup.models.Order;
import com.signinsignup.basic_signin_signup.models.OrderRepository;
import com.signinsignup.basic_signin_signup.models.Product;
import com.signinsignup.basic_signin_signup.models.ProductRepository;
import com.signinsignup.basic_signin_signup.models.User;
import com.signinsignup.basic_signin_signup.models.enums.OrderStatus;
import com.signinsignup.basic_signin_signup.orders.dto.OrderProductDTO;

import org.slf4j.Logger;


@Service
public class OrdersService {
   
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;

    
   

    public String createOrder(ArrayList<OrderProductDTO> products) {
     
        User user = getUser();
        Long userId = user.getId();
        Client client = this.getClientByUserId(userId);      
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Long> productIds = new ArrayList<>();
      
        products.forEach(pr->{
            productIds.add(pr.getProductId());     
        });
        ArrayList<Product> productsDb = ( ArrayList<Product>) productRepository.findAllById(productIds);
        if(productIds.size() != productsDb.size()){
            throw new IllegalStateException("Action not permited: some products are not found");
        }
        for(int i = 0 ;i<productsDb.size();i++){
                Product product = productsDb.get(i);
                OrderProductDTO orderProductdto =  products.get(i);
                if(orderProductdto.getQuantity() >product.getQuantity()){
                    throw new IllegalStateException("Action not permited: the requested quantity of the product is not available");
                }
                Order order = new Order(client, product, orderProductdto.getQuantity(), OrderStatus.WAIT);
                orders.add(order);
        }
      
         orderRepository.saveAll(orders);
        return "success";
    }

    public void getClientOrders(){
        User user = getUser();
        Long userId = user.getId();
        Client client = this.getClientByUserId(userId);    

        
       

    }


    //utils
    private User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user  =(User)auth.getPrincipal();
        if(user == null){
            throw new IllegalStateException("user not found");      
        }

        return user;
    }

    private Client getClientByUserId(Long userId){
        Optional <Client> clientOptional = clientRepository.findByUserId(userId);
        if(!clientOptional.isPresent()){
            throw new IllegalStateException("client not found");
        }
        Client client = clientOptional.get();

        return client;
        
    }
    
}
