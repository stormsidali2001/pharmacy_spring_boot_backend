package com.signinsignup.basic_signin_signup.models;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order,Long>{
  
  ArrayList<Order> findAllByClientId(Long clientId);
  
  ArrayList<Order> findAll();

}

