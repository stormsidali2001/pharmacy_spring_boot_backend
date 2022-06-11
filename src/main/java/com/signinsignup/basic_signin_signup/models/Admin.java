package com.signinsignup.basic_signin_signup.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.signinsignup.basic_signin_signup.auth.models.AdminDTO;

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
   

    public Admin(AdminDTO admin) {
        
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
    }

    @OneToOne()
    private User user;

    //relations

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    
}
