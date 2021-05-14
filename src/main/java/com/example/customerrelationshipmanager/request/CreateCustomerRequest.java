package com.example.customerrelationshipmanager.request;

import lombok.Data;

@Data
public class CreateCustomerRequest {

    String firstname;
    String lastname;
    String email;

}
