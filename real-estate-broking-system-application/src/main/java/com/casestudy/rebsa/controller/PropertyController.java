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
import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.repository.PropertyRepository;

@RestController
@RequestMapping("/api/v1/")
public class PropertyController {

	@Autowired
	private PropertyRepository propertyRepository;

	@PostMapping("properties")
	public Property addProperty(@RequestBody Property property) {
		return this.propertyRepository.save(property);
	}

	@GetMapping("properties")
	public List<Property> viewAllProperties() {
		return this.propertyRepository.findAll();
	}

	@GetMapping("properties/{id}")
	public ResponseEntity<Property> viewPropertyById(@PathVariable(value = "id") Integer propertyId)
			throws ResourceNotFoundException {

		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: " + propertyId));
		return ResponseEntity.ok().body(property);

	}

	@PutMapping("properties/{id}")
	public ResponseEntity<Property> updateProperty(@PathVariable(value = "id") Integer propertyId,
			@Validated @RequestBody Property propertyDetails) throws ResourceNotFoundException {
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

	@DeleteMapping("properties/{id}")
	public Map<String, Boolean> removeProperty(@PathVariable(value = "id") Integer propertyId)
			throws ResourceNotFoundException {
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found for this id :: " + propertyId));
		this.propertyRepository.delete(property);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
