package com.signinsignup.basic_signin_signup.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findUserByEmail(String email);
}