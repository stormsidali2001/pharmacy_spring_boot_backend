package com.signinsignup.basic_signin_signup.models;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
  Optional<Client> findByUserId(Long userId);

 
 
  ArrayList<Client> findAll();
}
