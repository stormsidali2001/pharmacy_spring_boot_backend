package com.signinsignup.basic_signin_signup.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.signinsignup.basic_signin_signup.auth.models.ClientDto;
import com.signinsignup.basic_signin_signup.auth.models.LoginRequest;
import com.signinsignup.basic_signin_signup.models.User;



@RestController
@RequestMapping(path="auth")
public class AuthController {
    @Autowired
    private AuthService auth;

    @PostMapping("signin")
    public String signIn(@RequestBody LoginRequest request){
        return auth.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("signup")
    public String register(@RequestBody ClientDto client){
        return auth.signUpClient(client);
    }
    
}
