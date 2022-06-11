package com.signinsignup.basic_signin_signup.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.signinsignup.basic_signin_signup.auth.models.AdminDTO;
import com.signinsignup.basic_signin_signup.auth.models.ClientDto;




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
    public String singnUpAdmin(AdminDTO admin){
        return auth.singnUpAdmin(admin);
    }
    
}
