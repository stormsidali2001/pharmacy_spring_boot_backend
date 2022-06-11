package com.signinsignup.basic_signin_signup.models;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("select u from User u where u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("select u from User u left join u.roles r  where u.email = ?1")
    Optional<User> findUserByEmailWithRoles(String email);


}