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
import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.service.BookService;

@RestController
@RequestMapping("/api/v1/")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("bookings")
	public Book addBooking(@RequestBody Book book) {
		return bookService.addBooking(book);
	}

	@GetMapping("bookings")
	public List<Book> viewAllBookings() {
		return bookService.viewAllBookings();
	}

	@GetMapping("bookings/{id}")
	public ResponseEntity<Book> viewBookingById(@PathVariable(value = "id") Integer bookId)
			throws ResourceNotFoundException {
		return bookService.viewBookingById(bookId);
	}

	@PutMapping("bookings/{id}")
	public ResponseEntity<Book> updateBooking(@PathVariable(value = "id") Integer bookId,
			@Validated @RequestBody Book bookDetails) throws ResourceNotFoundException {
		return bookService.updateBooking(bookId, bookDetails);
	}

	@DeleteMapping("bookings/{id}")
	public Map<String, Boolean> removeBooking(@PathVariable(value = "id") Integer bookId)
			throws ResourceNotFoundException {
		return bookService.removeBooking(bookId);
	}

}
