package com.wellsfargo.training.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.lms.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Optional<Customer> findByEmail(String email);
}
