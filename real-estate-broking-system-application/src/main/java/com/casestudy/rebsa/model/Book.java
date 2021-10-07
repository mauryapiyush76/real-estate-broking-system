package com.casestudy.rebsa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id") 
	private int bookId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "property_id")
	private Property property;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id")
	private Customer customer;
	
	public Book() {
		super();
	}
	
	public Book(int bookId, Property property, Customer customer) {
		super();
		this.bookId = bookId;
		this.property = property;
		this.customer = customer;
	}

	
}
