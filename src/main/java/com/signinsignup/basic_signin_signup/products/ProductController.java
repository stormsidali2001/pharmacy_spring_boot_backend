package com.signinsignup.basic_signin_signup.products;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "products")
public class ProductController {
    

    @GetMapping("secured/5")
    public String getProducts(){
        return "working";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("secured/new")
    public String createProduct(){
        return "product created";
    }
}
