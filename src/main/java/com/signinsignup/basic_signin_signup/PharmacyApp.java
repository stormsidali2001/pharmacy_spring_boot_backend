package com.signinsignup.basic_signin_signup;

import java.util.Arrays;
import java.util.Collections;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//add this in SpringBootApplication to exclud spring security exclude = SecurityAutoConfiguration.class
@SpringBootApplication()

public class PharmacyApp {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyApp.class, args);
	}




}
