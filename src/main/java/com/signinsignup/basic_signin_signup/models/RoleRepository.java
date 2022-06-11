package com.signinsignup.basic_signin_signup.models;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.signinsignup.basic_signin_signup.models.Role;;

public interface RoleRepository extends JpaRepository<Role,Long>{
    @Query("select r from Role r where r.role = ?1")
    public Optional<Role> findRoleByRole(String role);
}
