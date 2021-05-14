package com.example.customerrelationshipmanager;

import com.example.customerrelationshipmanager.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CustomerRelationshipManagerApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	private static final Logger log = LoggerFactory.getLogger(CustomerRelationshipManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerRelationshipManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(String.valueOf(customerRepository.findById(1)));
	}
}
