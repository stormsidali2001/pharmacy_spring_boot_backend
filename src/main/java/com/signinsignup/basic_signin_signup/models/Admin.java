package com.signinsignup.basic_signin_signup.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @SequenceGenerator(
        name ="admin_generator",
        sequenceName = "admin_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "admin_generator"
    )
    private Long id;
    
    private String firstName;
    private String lastName;
   
    @OneToOne()
    private User user;

    //relations


    
}
