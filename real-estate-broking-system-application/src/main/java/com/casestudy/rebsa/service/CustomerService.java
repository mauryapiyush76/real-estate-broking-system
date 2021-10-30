package com.casestudy.rebsa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.rebsa.exception.ResourceNotFoundException;
import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.model.Customer;
import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.repository.BookRepository;
import com.casestudy.rebsa.repository.CustomerRepository;
import com.casestudy.rebsa.repository.PropertyRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private BookRepository bookRepository;

	public CustomerService() {

	}

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> viewAllCustomers() {
		return this.customerRepository.findAll();
	}

	public ResponseEntity<Customer> viewCustomerById(Integer customerId) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	public ResponseEntity<Customer> updateCustomer(Integer customerId, Customer customerDetails)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		customer.setFirstName(customerDetails.getFirstName());
		customer.setLastName(customerDetails.getLastName());
		customer.setEmail(customerDetails.getEmail());
		customer.setMobileNumber(customerDetails.getMobileNumber());
		return ResponseEntity.ok(this.customerRepository.save(customer));
	}

	public ResponseEntity<Map<String, Boolean>> removeCustomer(Integer customerId) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		this.customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	public Book bookPropertyById(Integer customerId, Integer propertyId) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: " + propertyId));
		if(property.isAvailableStatus()) {
		property.setAvailableStatus(false);
		} else {
			throw new ResourceNotFoundException("Property not available for booking");
		}
		property.setCustomer(customer);
		this.propertyRepository.save(property);
		Book book = new Book(property, customer);
		return this.bookRepository.save(book);
	}
}
