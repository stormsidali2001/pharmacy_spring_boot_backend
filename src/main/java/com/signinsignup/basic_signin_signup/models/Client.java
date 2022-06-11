package com.signinsignup.basic_signin_signup.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.signinsignup.basic_signin_signup.auth.models.ClientDto;

@Table
@Entity(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

   

    //relations
    @OneToOne()
    private User user;

    @OneToMany(
        mappedBy = "client",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Order> orders;


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


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(address, client.address) && Objects.equals(user, client.user) && Objects.equals(orders, client.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, address, user, orders);
    }


    
}


