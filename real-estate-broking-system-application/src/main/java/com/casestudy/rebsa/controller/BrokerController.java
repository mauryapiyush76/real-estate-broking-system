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
import com.casestudy.rebsa.model.Broker;
import com.casestudy.rebsa.repository.BrokerRepository;

@RestController
@RequestMapping("/api/v1/")
public class BrokerController {

	@Autowired
	private BrokerRepository brokerRepository;

	// add Broker
	@PostMapping("brokers")
	public Broker addBroker(@RequestBody Broker broker) {
		return this.brokerRepository.save(broker);
	}

	// view all Brokers
	@GetMapping("brokers")
	public List<Broker> viewAllBrokers() {
		return this.brokerRepository.findAll();
	}

	// view Broker by id
	@GetMapping("brokers/{id}")
	public ResponseEntity<Broker> viewBrokerById(@PathVariable(value = "id") Integer brokerId)
			throws ResourceNotFoundException {
		Broker broker = brokerRepository.findById(brokerId)
				.orElseThrow(() -> new ResourceNotFoundException("Broker not found for this id :: " + brokerId));
		return ResponseEntity.ok().body(broker);

	}

	// update Broker
	@PutMapping("brokers/{id}")
	public ResponseEntity<Broker> updateBroker(@PathVariable(value = "id") Integer brokerId,
			@Validated @RequestBody Broker brokerDetails) throws ResourceNotFoundException {
		Broker broker = brokerRepository.findById(brokerId)
				.orElseThrow(() -> new ResourceNotFoundException("Broker not found for this id :: " + brokerId));
		broker.setFirstName(brokerDetails.getFirstName());
		broker.setLastName(brokerDetails.getLastName());
		broker.setEmail(brokerDetails.getEmail());
		broker.setMobileNumber(brokerDetails.getMobileNumber());

		return ResponseEntity.ok(this.brokerRepository.save(broker));
	}

	// remove Broker
	@DeleteMapping("brokers/{id}")
	public Map<String, Boolean> removeBroker(@PathVariable(value = "id") Integer brokerId)
			throws ResourceNotFoundException {
		Broker broker = brokerRepository.findById(brokerId)
				.orElseThrow(() -> new ResourceNotFoundException("Broker not found for this id :: " + brokerId));
		this.brokerRepository.delete(broker);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

}
