package com.casestudy.rebsa.controller;

import java.util.HashMap;
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
import com.casestudy.rebsa.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	// add Customer
	@PostMapping("customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		return this.customerRepository.save(customer);
	}

	// view all Customers
	@GetMapping("customers")
	public List<Customer> viewAllCustomers() {
		return this.customerRepository.findAll();
	}

	// view Customer by id
	@GetMapping("customers/{id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);

	}

	// update Customer
	@PutMapping("customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Integer customerId,
			@Validated @RequestBody Customer customerDetails) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		customer.setFirstName(customerDetails.getFirstName());
		customer.setLastName(customerDetails.getLastName());
		customer.setEmail(customerDetails.getEmail());
		customer.setMobileNumber(customerDetails.getMobileNumber());

		return ResponseEntity.ok(this.customerRepository.save(customer));
	}

	// remove Customer
	@DeleteMapping("customers/{id}")
	public Map<String, Boolean> removeCustomer(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		this.customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

}
