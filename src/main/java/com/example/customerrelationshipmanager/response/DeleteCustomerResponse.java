package com.example.customerrelationshipmanager.response;

import lombok.Data;

@Data
public class DeleteCustomerResponse {
    private int crmid;
    private String message;

    public DeleteCustomerResponse(int crmid,String message) {
        this.crmid = crmid;
        this.message = message;
    }

}
