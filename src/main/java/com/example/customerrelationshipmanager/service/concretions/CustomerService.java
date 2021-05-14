package com.example.customerrelationshipmanager.service.concretions;

import com.example.customerrelationshipmanager.model.Customer;
import com.example.customerrelationshipmanager.repository.CustomerRepository;
import com.example.customerrelationshipmanager.service.abstractions.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findByFirstname(String firstname) {
        return customerRepository.findByFirstname(firstname);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.deleteById(customer.getCrmid());
    }

    @Override
    public boolean isEmailExists(String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customer != null;
    }

    @Override
    public boolean isCustomerExists(int crmid) {
        Optional<Customer> customer = customerRepository.findById(crmid);
        return customer == null;
    }

    @Override
    public boolean emailAlreadyTakenWhenUpdate(String email, int crmid) {
        int customerCount = customerRepository.emailAlreadyRegisteredWhenUpdate(email,crmid);
        return customerCount != 0;
    }
}
