package com.casestudy.rebsa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rebsa.exception.ResourceNotFoundException;
import com.casestudy.rebsa.model.Customer;
import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.service.CustomerService;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("customers")
	public List<Customer> viewAllCustomers() {
		return customerService.viewAllCustomers();
	}

	@GetMapping("customers/{id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		return customerService.viewCustomerById(customerId);
	}

	@PutMapping("customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Integer customerId,
			@Validated @RequestBody Customer customerDetails) throws ResourceNotFoundException {
		return customerService.updateCustomer(customerId, customerDetails);
	}

	@DeleteMapping("customers/{id}")
	public Map<String, Boolean> removeCustomer(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		return customerService.removeCustomer(customerId);

	}

	@PostMapping("customers/{customerId}/properties/{propertyId}")
	public Book bookPropertyById(@PathVariable(value = "customerId") Integer customerId,
			@PathVariable(value = "propertyId") Integer propertyId) throws ResourceNotFoundException {
		return customerService.bookPropertyById(customerId, propertyId);
	}
}
