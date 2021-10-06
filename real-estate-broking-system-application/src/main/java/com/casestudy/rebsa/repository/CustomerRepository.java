package com.casestudy.rebsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.rebsa.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
