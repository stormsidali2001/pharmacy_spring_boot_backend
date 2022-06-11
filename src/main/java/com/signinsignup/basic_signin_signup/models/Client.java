package com.signinsignup.basic_signin_signup.models;

import javax.persistence.*;

import com.signinsignup.basic_signin_signup.auth.models.ClientDto;

@Table
@Entity(name = "client")
public class Client {
    @Id
    @SequenceGenerator(
        name ="client_generator",
        sequenceName = "client_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "client_generator"
    )
    private Long id;
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Client(ClientDto client){
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.phoneNumber = client.getPhoneNumber();
        this.address = client.getAddress();
    }
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
    @OneToOne()
    private User user;
    
}
