package com.example.customerrelationshipmanager.controller;

import com.example.customerrelationshipmanager.configuration.Constants;
import com.example.customerrelationshipmanager.exception.BadRequestException;
import com.example.customerrelationshipmanager.exception.CustomerNotFoundException;
import com.example.customerrelationshipmanager.model.Customer;
import com.example.customerrelationshipmanager.request.CreateCustomerRequest;
import com.example.customerrelationshipmanager.request.UpdateCustomerRequest;
import com.example.customerrelationshipmanager.response.CreateCustomerResponse;
import com.example.customerrelationshipmanager.response.DeleteCustomerResponse;
import com.example.customerrelationshipmanager.service.abstractions.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping(value="/find/all")
    public List<Customer> findAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping(value = "/create")
    public CreateCustomerResponse addNewCustomer(@RequestBody CreateCustomerRequest request) {

        if(request.getFirstname() == null || request.getFirstname().equals("")) {
            throw new BadRequestException(Constants.MESSAGE_INVALID_FIRSTNAME);
        }

        if(request.getEmail() == null || request.getEmail().equals("")) {
            throw new BadRequestException(Constants.MESSAGE_INVALID_EMAIL);
        }

        if(customerService.isEmailExists(request.getEmail())) {
            throw new BadRequestException(Constants.MESSAGE_EMAIL_ALREADY_REGISTERED);
        }

        Customer customer = customerService.addNewCustomer(new Customer(request.getFirstname(), request.getLastname(), request.getEmail()));
        CreateCustomerResponse response = new CreateCustomerResponse(customer);
        return response;
    }

    @PostMapping(value="/update")
    public CreateCustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest request) {

        if(customerService.isCustomerExists(request.getCrmid())) {
            throw new BadRequestException(Constants.MESSAGE_CUSTOMER_NOT_FOUND);
        }

        if(customerService.emailAlreadyTakenWhenUpdate(request.getEmail(), request.getCrmid())) {
            throw new BadRequestException(Constants.MESSAGE_EMAIL_ALREADY_REGISTERED);
        }

        if(request.getFirstname() == null || request.getFirstname().equals("")) {
            throw new BadRequestException(Constants.MESSAGE_INVALID_FIRSTNAME);
        }

        if(request.getEmail() == null || request.getEmail().equals("")) {
            throw new BadRequestException(Constants.MESSAGE_INVALID_EMAIL);
        }

        Customer customer = customerService.updateCustomer(new Customer(request.getCrmid(), request.getFirstname(), request.getLastname(), request.getEmail()));
        CreateCustomerResponse response = new CreateCustomerResponse(customer);
        return response;
    }

    @PostMapping(value = "/delete")
    public DeleteCustomerResponse deleteCustomer(@RequestBody UpdateCustomerRequest request) {

        if(customerService.isCustomerExists(request.getCrmid())) {
            throw new CustomerNotFoundException(request.getCrmid());
        }

        Customer customer = new Customer(request.getCrmid(), request.getFirstname(), request.getLastname(), request.getEmail());
        customerService.deleteCustomer(customer);
        return new DeleteCustomerResponse(request.getCrmid(), Constants.MESSAGE_CUSTOMER_DELETED);
    }

}
