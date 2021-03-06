package com.signinsignup.basic_signin_signup.auth;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.signinsignup.basic_signin_signup.PasswordEncoder;
import com.signinsignup.basic_signin_signup.auth.models.AdminDTO;
import com.signinsignup.basic_signin_signup.auth.models.ClientDto;
import com.signinsignup.basic_signin_signup.models.User;
import com.signinsignup.basic_signin_signup.models.UserRepository;
import com.signinsignup.basic_signin_signup.models.Admin;
import com.signinsignup.basic_signin_signup.models.AdminRepository;
import com.signinsignup.basic_signin_signup.models.Client;
import com.signinsignup.basic_signin_signup.models.ClientRepository;
import com.signinsignup.basic_signin_signup.models.Role;
import com.signinsignup.basic_signin_signup.models.RoleRepository;

@Component
public class AuthService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

  
   
  
    
    @Transactional
    public String signUpClient (ClientDto client){
        User user = getUser(client.getEmail(), client.getPassword());
        Client clientDb = new Client(client);

        Optional<Role> roleOptional =  roleRepository.findRoleByRole("CLIENT");
        Role role = null;
        if(!roleOptional.isPresent()){
            Role r = new Role();
            r.setRole("CLIENT");
               
            role =  roleRepository.save(r);
        }else{
            role = roleOptional.get();

        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        
        clientDb.setUser(user);
        userRepository.save(user);
        clientRepository.save(clientDb);
        return "registered";
    }

    
    public String singnUpAdmin(AdminDTO admin){
        User user = getUser(admin.getEmail(), admin.getPassword());
        Optional<Role> roleOptional =  roleRepository.findRoleByRole("ADMIN");
        Role role = null;
        if(!roleOptional.isPresent()){
            Role r = new Role();
            r.setRole("ADMIN");
               
            role =  roleRepository.save(r);
        }else{
            role = roleOptional.get();

        }
        
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        
        Admin adminDb = new Admin(admin);
        adminDb.setUser(user);
        userRepository.save(user);
        adminRepository.save(adminDb);
        
        return "done...";
    }
  

    //utils

    public User getUser(String email , String password){
        Optional<User> userOptional =  this.userRepository.findUserByEmail(email);

        if(userOptional.isPresent()){
            throw new IllegalStateException("email already taken");
        }
        boolean isValidEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$").matcher(email).matches();
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        String encryptedPassword = this.passwordEncoder.bCryptPasswordEncoder().encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptedPassword);

        return user;

    }

    public String logout(){
       User user = getUser();
       user.setRefreshToken(null);
       userRepository.save(user);
       return "logged out";
    }

    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user  =(User)auth.getPrincipal();
        if(user == null){
            throw new IllegalStateException("user not found");      
        }

        return user;
    }
    public Client getClientByUserId(Long userId){
        Optional <Client> clientOptional = clientRepository.findByUserId(userId);
        if(!clientOptional.isPresent()){
            throw new IllegalStateException("client not found");
        }
        Client client = clientOptional.get();

        return client;
        
    }
    public Admin getAdminByUserId(Long userId){
        Optional <Admin> adminOptional = adminRepository.findByUserId(userId);
        if(!adminOptional.isPresent()){
            throw new IllegalStateException("client not found");
        }
        Admin admin = adminOptional.get();

        return admin;
        
    }

}
