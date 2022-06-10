package com.signinsignup.basic_signin_signup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//add this in SpringBootApplication to exclud spring security exclude = SecurityAutoConfiguration.class
@SpringBootApplication()
public class PharmacyApp {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyApp.class, args);
	}

}
