package com.signinsignup.basic_signin_signup.models;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService/loadUserByUsername");
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(()->new IllegalStateException("user not found"));
        return user.map(CustomUserDetails::new).get();
    }
    
}
