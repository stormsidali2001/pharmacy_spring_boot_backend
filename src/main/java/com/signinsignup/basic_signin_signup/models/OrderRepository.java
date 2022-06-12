package com.signinsignup.basic_signin_signup.models;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Long>{
  @Query(value = "select  o from Order o where o.client.id = ?1")
  ArrayList<Order> findAllById(Long clientId);
}
