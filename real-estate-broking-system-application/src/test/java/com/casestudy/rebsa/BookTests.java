package com.casestudy.rebsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.model.Customer;
import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.repository.BookRepository;
import com.casestudy.rebsa.repository.CustomerRepository;
import com.casestudy.rebsa.repository.PropertyRepository;
import com.casestudy.rebsa.service.BookService;
import com.casestudy.rebsa.service.CustomerService;
import com.casestudy.rebsa.service.PropertyService;

@SpringBootTest
public class BookTests {

	@Autowired
	private BookService bookService;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@Test
	void addBookingTest() {
		Property property = new Property("colony", "plot", 100.0, "delhi", "sell", 10000.0);
		propertyService.addProperty(property);
		Customer customer = new Customer("Piyush", "Mourya", "9999999999", "piyush@gmail.com");
		customerService.addCustomer(customer);

		Book book = new Book(property, customer);
		Book savedBook = bookService.addBooking(book);

		Assertions.assertEquals(book, savedBook);
		bookRepository.delete(savedBook);
		propertyRepository.delete(property);
		customerRepository.delete(customer);
	}

}
