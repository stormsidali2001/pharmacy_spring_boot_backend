package com.signinsignup.basic_signin_signup.auth;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.signinsignup.basic_signin_signup.PasswordEncoder;
import com.signinsignup.basic_signin_signup.auth.models.ClientDto;
import com.signinsignup.basic_signin_signup.models.Role;
import com.signinsignup.basic_signin_signup.models.User;
import com.signinsignup.basic_signin_signup.models.UserRepository;
import com.signinsignup.basic_signin_signup.models.Client;
import com.signinsignup.basic_signin_signup.models.ClientRepository;

@Component
public class AuthService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;
   
  
    
    @Transactional
    public String signUpClient (ClientDto client){
        Optional<User> userOptional =  this.userRepository.findUserByEmail(client.getEmail());

        if(userOptional.isPresent()){
            throw new IllegalStateException("email already taken");
        }
        boolean isValidEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$").matcher(client.getEmail()).matches();
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        String encryptedPassword = this.passwordEncoder.bCryptPasswordEncoder().encode(client.getPassword());
        User user = new User();
        user.setEmail(client.getEmail());
        user.setPassword(encryptedPassword);
        Client clientDb = new Client();
        clientDb.setFirstName(client.getFirstName());
        clientDb.setLastName(client.getLastName());
        clientDb.setPhoneNumber(client.getPhoneNumber());
        clientDb.setAddress(client.getAddress());
        clientDb.setUser(user);
        userRepository.save(user);
        clientRepository.save(clientDb);
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
