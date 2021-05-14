package com.example.customerrelationshipmanager.repository;

import com.example.customerrelationshipmanager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String email);

    List<Customer> findByFirstname(String firstName);

    @Query("select count(*) from Customer where email = :email and crmid != :crmid")
    int emailAlreadyRegisteredWhenUpdate(@Param("email") String email, @Param("crmid") int crmid);

}
