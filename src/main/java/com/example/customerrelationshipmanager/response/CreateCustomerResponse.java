package com.example.customerrelationshipmanager.response;

import com.example.customerrelationshipmanager.model.Customer;
import lombok.Data;

@Data
public class CreateCustomerResponse {

    private int crmid;
    private String firstname;
    private String lastname;
    private String email;

    public CreateCustomerResponse(Customer customer) {
        crmid = customer.getCrmid();
        firstname = customer.getFirstname();
        lastname = customer.getLastname();
        email = customer.getEmail();
    }

}
