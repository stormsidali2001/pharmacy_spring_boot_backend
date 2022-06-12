package com.signinsignup.basic_signin_signup.shared;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.signinsignup.basic_signin_signup.models.User;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;
  
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username is {}:"+email);
        System.out.println("password is {}:"+password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authenticationToken);
     
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,HttpServletResponse response,FilterChain chain,Authentication authResult) throws IOException, ServletException{
        User user = (User) authResult.getPrincipal();
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
    
   
}
