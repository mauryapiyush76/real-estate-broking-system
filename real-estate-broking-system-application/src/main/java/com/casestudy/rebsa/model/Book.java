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
	@Column(name = "book_id")
	private int bookId;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "property_id", unique = true)
	private Property property;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "owner_id", unique = true)
	private Customer customer;

	public Book() {
		super();
	}

	public Book(Property property, Customer customer) {
		super();
		this.property = property;
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
}
