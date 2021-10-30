package com.casestudy.rebsa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rebsa.exception.ResourceNotFoundException;
import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.service.PropertyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PropertyController {

	@Autowired
	PropertyService propertyService;

	@PostMapping("properties")
	public Property addProperty(@RequestBody Property property) {
		return propertyService.addProperty(property);
	}

	@GetMapping("properties")
	public List<Property> viewAllProperties() {
		return propertyService.viewAllProperties();
	}

	@GetMapping("properties/{id}")
	public ResponseEntity<Property> viewPropertyById(@PathVariable(value = "id") Integer propertyId)
			throws ResourceNotFoundException {
		return propertyService.viewPropertyById(propertyId);
	}

	@PutMapping("properties/{id}")
	public ResponseEntity<Property> updateProperty(@PathVariable(value = "id") Integer propertyId,
			@Validated @RequestBody Property propertyDetails) throws ResourceNotFoundException {
		return propertyService.updateProperty(propertyId, propertyDetails);
	}

	@DeleteMapping("properties/{id}")
	public ResponseEntity<Map<String, Boolean>> removeProperty(@PathVariable(value = "id") Integer propertyId)
			throws ResourceNotFoundException {
		return propertyService.removeProperty(propertyId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/properties/spec")
	@ResponseBody
	public List<Property> search(@RequestParam(value = "search") String search) {
		return propertyService.search(search);
	}
	
}
