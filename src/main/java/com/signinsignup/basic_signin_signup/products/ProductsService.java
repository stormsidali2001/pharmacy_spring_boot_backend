package com.signinsignup.basic_signin_signup.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signinsignup.basic_signin_signup.models.Product;
import com.signinsignup.basic_signin_signup.models.ProductRepository;
import com.signinsignup.basic_signin_signup.products.dto.ProductDTO;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public String addProduct(ProductDTO product){
        try{
            Product p = new Product();
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setQuantity(product.getQuantity());
            p.setImageUrl(p.getImageUrl());
            productRepository.save(p);
        }catch(Exception err){
            System.err.println("ProductService/AddProduct Exception"+err.toString());
        }

        return "product added !!!";
    }
    
}
