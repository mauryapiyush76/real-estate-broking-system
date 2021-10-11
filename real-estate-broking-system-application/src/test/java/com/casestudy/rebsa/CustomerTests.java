package com.casestudy.rebsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.casestudy.rebsa.model.Customer;
import com.casestudy.rebsa.repository.CustomerRepository;
import com.casestudy.rebsa.service.CustomerService;

@SpringBootTest
class CustomerTests {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void createCustomerTest() {
		Customer customer = new Customer("Piyush", "Mourya", "9999999999", "piyush@gmail.com");
		Customer savedCustomer = customerService.addCustomer(customer);
		Assertions.assertEquals(customer, savedCustomer);
		customerRepository.delete(savedCustomer);
	}

	@Test
	void updateCustomerTest() {
		Customer customer = new Customer("Customer2", "User2", "9999999999", "customer@gmail.com");
		customerService.addCustomer(customer);
		customer.setEmail("piyushmourya@gmail.com");
		String emailBeforeSave = customer.getEmail();
		Customer savedCustomer = customerRepository.save(customer);
		String emailAfterSave = savedCustomer.getEmail();
		Assertions.assertNotEquals(customer, savedCustomer);
		Assertions.assertEquals(emailBeforeSave, emailAfterSave);
		customerRepository.delete(savedCustomer);
	}

	@Test
	void deleteCustomerTest() {
		Customer customer = new Customer("Customer2", "User2", "9999999999", "customer@gmail.com");
		customerService.addCustomer(customer);
		long beforeDelete = customerRepository.count();
		customerRepository.delete(customer);
		long afterDelete = customerRepository.count();
		Assertions.assertEquals(afterDelete, beforeDelete - 1);

	}

}
