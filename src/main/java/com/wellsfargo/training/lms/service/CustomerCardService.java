package com.wellsfargo.training.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.lms.model.CustomerCard;
import com.wellsfargo.training.lms.repository.CustomerCardRepository;

import jakarta.transaction.Transactional;

/*
 * A service layer is an additional layer in an MVC application that 
 * mediates communication between a controller and repository layer. 
 * The service layer contains business logic. 
 * In particular, it contains validation logic. */

/* @Service annotation allows developers to add business functionalities.
 *  @Transactional annotation allows to manage Database transactions efficiently */
@Service
@Transactional
public class CustomerCardService {
	

	/*@Autowired - marks a constructor, field, or setter method to be autowired by Spring dependency injection. */
	@Autowired
	private CustomerCardRepository irepo;
	
	public CustomerCard saveItem(CustomerCard product) {
		product.getCustomer().getCustomerCards().add(product);
		product.getLoanCard().getCustomerCards().add(product);
		System.out.println("Hi");
		return irepo.save(product);   // Invokes save() method predefined in JPA repo
	}
	
	 public List<CustomerCard> listAll(){
 		
 		return irepo.findAll(); //Define in JPA repo.
 	}
	 
//	 public List<CustomerCard> getById(String empId){
//		 return irepo.findCustomerCardsByEmpId(empId);
//	 }
}
