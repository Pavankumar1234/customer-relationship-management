package com.example.customerrelationshipmanager.request;

import lombok.Data;

@Data
public class UpdateCustomerRequest {

    int crmid;
    String firstname;
    String lastname;
    String email;

}
