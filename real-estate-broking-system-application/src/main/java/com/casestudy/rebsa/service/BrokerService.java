package com.casestudy.rebsa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.rebsa.exception.ResourceNotFoundException;
import com.casestudy.rebsa.model.Broker;

import com.casestudy.rebsa.repository.BrokerRepository;

@Service
public class BrokerService {
	
	@Autowired
	private BrokerRepository brokerRepository;
	
	public BrokerService() {

	}
	
	public Broker addBroker(Broker broker) {
		return this.brokerRepository.save(broker);
	}
	
	public List<Broker> viewAllBrokers() {
		return this.brokerRepository.findAll();
	}
	
	public ResponseEntity<Broker> viewBrokerById(Integer brokerId)
			throws ResourceNotFoundException {
		Broker broker = brokerRepository.findById(brokerId)
				.orElseThrow(() -> new ResourceNotFoundException("Broker not found for this id :: " + brokerId));
		return ResponseEntity.ok().body(broker);

	}
	
	public ResponseEntity<Broker> updateBroker(Integer brokerId,Broker brokerDetails) throws ResourceNotFoundException {
		Broker broker = brokerRepository.findById(brokerId)
				.orElseThrow(() -> new ResourceNotFoundException("Broker not found for this id :: " + brokerId));
		broker.setFirstName(brokerDetails.getFirstName());
		broker.setLastName(brokerDetails.getLastName());
		broker.setEmail(brokerDetails.getEmail());
		broker.setMobileNumber(brokerDetails.getMobileNumber());

		return ResponseEntity.ok(this.brokerRepository.save(broker));
	}
	
	public Map<String, Boolean> removeBroker(Integer brokerId)
			throws ResourceNotFoundException {
		Broker broker = brokerRepository.findById(brokerId)
				.orElseThrow(() -> new ResourceNotFoundException("Broker not found for this id :: " + brokerId));
		this.brokerRepository.delete(broker);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}
}
