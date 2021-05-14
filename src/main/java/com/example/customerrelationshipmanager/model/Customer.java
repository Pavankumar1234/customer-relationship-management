package com.example.customerrelationshipmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer", schema = "crm_admin")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crmid;

    private String firstname;
    private String lastname;
    private String email;

    public Customer() {}

    public Customer(int crmid,String firstname,String lastname,String email) {
        this.crmid = crmid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Customer(String firstname,String lastname,String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @Override
    public String toString() {
        return("User: crmid->"+crmid+" firstname->"+firstname+" lastname->"+lastname+" email->"+email);
    }
}
