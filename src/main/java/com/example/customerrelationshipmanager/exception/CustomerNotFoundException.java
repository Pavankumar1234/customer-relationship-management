package com.example.customerrelationshipmanager.exception;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException() {
        super("Customer not found");
    }

    public CustomerNotFoundException(int crmid) {
        super("Customer not found with id - "+crmid);
    }

}
