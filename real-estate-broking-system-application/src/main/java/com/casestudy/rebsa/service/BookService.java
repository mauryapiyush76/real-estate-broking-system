package com.casestudy.rebsa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.rebsa.exception.ResourceNotFoundException;
import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.repository.BookRepository;
import com.casestudy.rebsa.repository.PropertyRepository;

@Service
public class BookService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private BookRepository bookRepository;

	public BookService() {

	}

	public Book addBooking(Book book) {
		return this.bookRepository.save(book);
	}

	public List<Book> viewAllBookings() {
		return this.bookRepository.findAll();
	}

	public ResponseEntity<Book> viewBookingById(Integer bookId) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
		return ResponseEntity.ok().body(book);

	}

	public ResponseEntity<Book> updateBooking(Integer bookId, Book bookDetails) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookId));
		book.setCustomer(bookDetails.getCustomer());
		book.setProperty(bookDetails.getProperty());
		return ResponseEntity.ok(this.bookRepository.save(book));
	}

	public ResponseEntity<Map<String, Boolean>> removeBooking(Integer bookId) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookId));
		if (book.getProperty() != null) {
			Property property = book.getProperty();
			property.setCustomer(null);
			this.propertyRepository.save(property);

		}
		this.bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}
