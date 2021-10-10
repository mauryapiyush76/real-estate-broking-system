package com.casestudy.rebsa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.rebsa.exception.ResourceNotFoundException;
import com.casestudy.rebsa.model.Book;
import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.repository.BookRepository;
import com.casestudy.rebsa.repository.PropertyRepository;
import com.casestudy.rebsa.specification.PropertySpecificationBuilder;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private BookRepository bookRepository;

	public PropertyService() {

	}

	public Property addProperty(Property property) {
		return this.propertyRepository.save(property);
	}

	public List<Property> viewAllProperties() {
		return this.propertyRepository.findAll();
	}

	public ResponseEntity<Property> viewPropertyById(Integer propertyId) throws ResourceNotFoundException {

		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: " + propertyId));
		return ResponseEntity.ok().body(property);

	}

	public ResponseEntity<Property> updateProperty(Integer propertyId, Property propertyDetails)
			throws ResourceNotFoundException {
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: " + propertyId));

		property.setAddress(propertyDetails.getAddress());
		property.setCity(propertyDetails.getCity());
		property.setFloorSpace(propertyDetails.getFloorSpace());
		property.setOfferType(propertyDetails.getOfferType());
		property.setPrice(propertyDetails.getPrice());
		property.setPropertyType(propertyDetails.getPropertyType());

		return ResponseEntity.ok(this.propertyRepository.save(property));
	}

	public Map<String, Boolean> removeProperty(Integer propertyId) throws ResourceNotFoundException {
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: " + propertyId));
		if (property.getCustomer() != null) {
			Book book = new Book(property, property.getCustomer());
			this.bookRepository.delete(book);
		}
		this.propertyRepository.delete(property);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	public List<Property> search(String search) {
		PropertySpecificationBuilder builder = new PropertySpecificationBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}

		Specification<Property> spec = builder.build();
		return this.propertyRepository.findAll(spec);
	}

}
