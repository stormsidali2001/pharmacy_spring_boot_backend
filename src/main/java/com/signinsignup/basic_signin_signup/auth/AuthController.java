package com.signinsignup.basic_signin_signup.auth;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.relation.RoleStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.signinsignup.basic_signin_signup.auth.models.AdminDTO;
import com.signinsignup.basic_signin_signup.auth.models.ClientDto;
import com.signinsignup.basic_signin_signup.models.Admin;
import com.signinsignup.basic_signin_signup.models.Client;
import com.signinsignup.basic_signin_signup.models.Role;
import com.signinsignup.basic_signin_signup.models.User;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="auth")
public class AuthController {
    @Autowired
    private AuthService auth;



    @PostMapping("/public/signup/client")
    public String register(@RequestBody ClientDto client){
        return auth.signUpClient(client);
    }
    @PostMapping("/public/signup/admin")
    public String singnUpAdmin(@RequestBody AdminDTO admin){
        return auth.singnUpAdmin(admin);
    }
    @PostMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws StreamWriteException, DatabindException, IOException{
        User user = auth.getUser();
      if(user.getRefreshToken() == null){
          return;
      }

      Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

      String access_token = JWT.create()
      .withSubject(user.getEmail())
      .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*1000))
      .withIssuer(request.getRequestURL().toString())
      .sign(algorithm);

      String refresh_token = JWT.create()
      .withSubject(user.getEmail())
      .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
      .withIssuer(request.getRequestURL().toString())
      .sign(algorithm);

  

      Map<String,String> tokens = new HashMap<>();
      tokens.put("access_token", access_token);
      tokens.put("refresh_token", refresh_token);
      response.setContentType("application/json");
      new ObjectMapper().writeValue(response.getOutputStream(), tokens);
   }

   @PostMapping("/public/logout")
   public String logout(){
      return  auth.logout();
   }
   @GetMapping("userInfos")
   public Object getUserInfos(){
      User user = this.auth.getUser();
    
    
      String userRoles = "";

      HashMap<Object,Object> res = new HashMap<>();

      res.put("email",user.getEmail());
      res.put("roles", user.getRoles());
     ArrayList<Role> arr = new ArrayList<>();
     arr.addAll(user.getRoles());
     for(int i=0;i<arr.size();i++){
       userRoles +=  arr.get(i).getRole() + " ";
     }
      if(userRoles.contains("CLIENT")){
        Client client = auth.getClientByUserId(user.getId());
        
          res.put("firstName", client.getFirstName());
          res.put("lastName", client.getLastName());
          res.put("address", client.getAddress());
          res.put("phoneNumber", client.getPhoneNumber());

       }else if(userRoles.contains("ADMIN")){
            Admin admin = auth.getAdminByUserId(user.getId());
            res.put("firstName", admin.getFirstName());
            res.put("lastName", admin.getLastName());
       }
     
        return res;
   }

    
}
