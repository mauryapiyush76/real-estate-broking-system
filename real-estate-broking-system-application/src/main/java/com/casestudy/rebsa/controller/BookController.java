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
import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.repository.BookRepository;

@RestController
@RequestMapping("/api/v1/")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	// add Booking
	@PostMapping("bookings")
	public Book addBooking(@RequestBody Book book) {
		return this.bookRepository.save(book);
	}

	// view all Bookings
	@GetMapping("bookings")
	public List<Book> viewAllBookings() {
		return this.bookRepository.findAll();
	}

	// view Booking by id
	@GetMapping("bookings/{id}")
	public ResponseEntity<Book> viewBookingById(@PathVariable(value = "id") Integer bookId)
			throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
		return ResponseEntity.ok().body(book);

	}

	// update Booking
	@PutMapping("bookings/{id}")
	public ResponseEntity<Book> updateBooking(@PathVariable(value = "id") Integer bookId,
			@Validated @RequestBody Book bookDetails) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookId));
		book.setCustomer(bookDetails.getCustomer());
		book.setProperty(bookDetails.getProperty());
		return ResponseEntity.ok(this.bookRepository.save(book));
	}

	// remove Booking
	@DeleteMapping("bookings/{id}")
	public Map<String, Boolean> removeBroker(@PathVariable(value = "id") Integer bookId)
			throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookId));
		this.bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

}
