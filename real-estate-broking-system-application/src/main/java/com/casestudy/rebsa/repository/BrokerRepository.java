package com.casestudy.rebsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.rebsa.model.Broker;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Integer> {

}
