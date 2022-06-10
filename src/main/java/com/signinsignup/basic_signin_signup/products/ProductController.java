
package com.signinsignup.basic_signin_signup.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.signinsignup.basic_signin_signup.products.dto.ProductDTO;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    
    @Autowired
    private ProductsService productsService;

   
    @GetMapping("/secured/alternate")
    public String getProducts(){
        return "working";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("secured/new")
    public String createProduct(ProductDTO product){
        return productsService.addProduct(product);
    }
    
}
