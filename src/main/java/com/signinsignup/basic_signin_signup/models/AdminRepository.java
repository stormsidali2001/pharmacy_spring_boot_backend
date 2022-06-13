package com.signinsignup.basic_signin_signup.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    Optional<Admin> findByUserId(Long userId);
}
