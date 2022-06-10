package com.signinsignup.basic_signin_signup.auth;

import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.signinsignup.basic_signin_signup.PasswordEncoder;
import com.signinsignup.basic_signin_signup.models.User;
import com.signinsignup.basic_signin_signup.models.UserRepository;

@Component
public class AuthService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
   
  
    
    @Transactional
    public String register (User user){
        boolean exists =  this.userRepository.findUserByEmail(user.getEmail()).isPresent();
        if(exists){
            throw new IllegalStateException("email already taken");
        }
        boolean isValidEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$").matcher(user.getEmail()).matches();
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        String encryptedPassword = this.passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return "registered";
    }

    
    public String login(String email,String password){
        try{
            System.out.println("login/LoginService");
            User user = userRepository.findUserByEmail(email).orElseThrow(
                ()->new IllegalStateException("Email not found")
            );
            String encodedPassword = user.getPassword();
            boolean maches = passwordEncoder.bCryptPasswordEncoder().matches(password,encodedPassword);
            if(!maches){
                throw new IllegalStateException("wrong password!!");
            }
    
            return "login success";

        }catch(Exception err){
            
           throw new IllegalStateException("some error occured");
           
        }
      
       
    }
}
