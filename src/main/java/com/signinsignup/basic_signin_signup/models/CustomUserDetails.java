package com.signinsignup.basic_signin_signup.models;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User  implements UserDetails{

    public CustomUserDetails(User user){
       super(user);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
        .stream()
        .map(role->(GrantedAuthority) new SimpleGrantedAuthority("ROLE_"+role.getRole())
        )
        .collect(Collectors.toList());
       
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
