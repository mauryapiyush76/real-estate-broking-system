package com.casestudy.rebsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.casestudy.rebsa.model.Property;
import com.casestudy.rebsa.repository.PropertyRepository;
import com.casestudy.rebsa.service.PropertyService;

@SpringBootTest
public class PropertyTests {

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private PropertyRepository propertyRepository;

	@Test
	void createPropertyTest() {
		Property property = new Property("colony", "plot", 100.0, "delhi", "sell", 10000.0);
		Property savedProperty = propertyService.addProperty(property);
		Assertions.assertEquals(property, savedProperty);
		propertyRepository.delete(savedProperty);
	}

	@Test
	void updatePropertyTest() {
		Property property = new Property("market", "shop", 1000.0, "mumbai", "rent", 15000.0);
		propertyService.addProperty(property);
		property.setCity("bangalore");
		String cityBeforeSave = property.getCity();
		Property savedProperty = propertyRepository.save(property);
		String cityAfterSave = savedProperty.getCity();
		Assertions.assertEquals(cityBeforeSave, cityAfterSave);
		propertyRepository.delete(savedProperty);
	}

	@Test
	void deletePropertyTest() {
		Property property = new Property("township", "flat", 1000.0, "mumbai", "rent", 15000.0);
		propertyService.addProperty(property);
		long beforeDelete = propertyRepository.count();
		propertyRepository.delete(property);
		long afterDelete = propertyRepository.count();
		Assertions.assertEquals(afterDelete, beforeDelete - 1);

	}

}
