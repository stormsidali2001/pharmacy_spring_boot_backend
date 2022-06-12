package com.signinsignup.basic_signin_signup.auth;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.signinsignup.basic_signin_signup.models.User;




@RestController
@RequestMapping(path="auth")
public class AuthController {
    @Autowired
    private AuthService auth;



    @PostMapping("signup/client")
    public String register(@RequestBody ClientDto client){
        return auth.signUpClient(client);
    }
    @PostMapping("signup/admin")
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

   @PostMapping("logout")
   public String logout(){
      return  auth.logout();
   }

    
}
