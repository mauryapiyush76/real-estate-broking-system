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
import com.casestudy.rebsa.model.Broker;
import com.casestudy.rebsa.service.BrokerService;

@RestController
@RequestMapping("/api/v1/")
public class BrokerController {

	@Autowired
	private BrokerService brokerService;

	@PostMapping("brokers")
	public Broker addBroker(@RequestBody Broker broker) {
		return brokerService.addBroker(broker);
	}

	@GetMapping("brokers")
	public List<Broker> viewAllBrokers() {
		return brokerService.viewAllBrokers();
	}

	@GetMapping("brokers/{id}")
	public ResponseEntity<Broker> viewBrokerById(@PathVariable(value = "id") Integer brokerId)
			throws ResourceNotFoundException {
		return brokerService.viewBrokerById(brokerId);
	}

	@PutMapping("brokers/{id}")
	public ResponseEntity<Broker> updateBroker(@PathVariable(value = "id") Integer brokerId,
			@Validated @RequestBody Broker brokerDetails) throws ResourceNotFoundException {
		return brokerService.updateBroker(brokerId, brokerDetails);
	}

	@DeleteMapping("brokers/{id}")
	public Map<String, Boolean> removeBroker(@PathVariable(value = "id") Integer brokerId)
			throws ResourceNotFoundException {
		return brokerService.removeBroker(brokerId);
	}

}
