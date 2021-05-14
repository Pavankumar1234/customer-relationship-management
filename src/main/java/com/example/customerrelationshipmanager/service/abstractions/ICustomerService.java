package com.example.customerrelationshipmanager.service.abstractions;

import com.example.customerrelationshipmanager.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> findAll();

    Customer findByEmail(String email);

    List<Customer> findByFirstname(String firstname);

    Customer addNewCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    boolean isEmailExists(String email);

    boolean isCustomerExists(int crmid);

    boolean emailAlreadyTakenWhenUpdate(String email,int crmid);

}
