package com.signinsignup.basic_signin_signup.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
        name ="user_generator",
        sequenceName = "user_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_generator"
    )  
    private Long id;
  
    private String email;
    private String password;

    @Column(name = "active")
    private int active;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //relations
    @ManyToMany
    private Set<Role> roles;

   
    public User(){
        
    }

    public User(User user) {
     
        this.id = user.getId();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
  


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{" +
        
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    


}
