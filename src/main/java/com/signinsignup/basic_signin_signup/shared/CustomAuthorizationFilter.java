package com.signinsignup.basic_signin_signup.shared;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.signinsignup.basic_signin_signup.models.Role;
import com.signinsignup.basic_signin_signup.models.User;
import com.signinsignup.basic_signin_signup.models.UserRepository;

import antlr.collections.List;


public class CustomAuthorizationFilter extends OncePerRequestFilter{

 
    private UserDetailsService userDetailsService;

    public CustomAuthorizationFilter(UserDetailsService userDetailsService){
          this.userDetailsService = userDetailsService;
    }
    

   
  

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
          if(request.getServletPath() == "/login" || request.getServletPath().startsWith("/auth")){
            filterChain.doFilter(request, response);
          }else{  
            String authorizationHeader = request.getHeader("Authorization");
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
              try{
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject(); // its the email
               
                UserDetails user =   userDetailsService.loadUserByUsername(username);
                
                ArrayList<SimpleGrantedAuthority> s = (ArrayList<SimpleGrantedAuthority>) user.getAuthorities();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,s);
            
             
                SecurityContext  context =  SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
         
                filterChain.doFilter(request, response);

                
              }catch(Exception err){
                System.out.println("/CustomAuthorizationFilter"+err.getMessage());
                response.setHeader("error", err.getMessage());
                response.sendError(403); // 403 forbiden
              }

            }else{
              filterChain.doFilter(request, response);
            }
            
          }
        
    }
    
}
