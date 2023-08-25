package com.wellsfargo.training.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.CustomerCard;
import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.service.CustomerCardService;

/*
 * Controller for Registration & Login process of a Dealer
 */

/*

Spring MVC provides @CrossOrigin annotation that marks the annotated method or type as permitting cross-origin requests.
The CORS (Cross-Origin Resource Sharing) allows a webpage to request additional resources into the browser from other domains
such as API data using AJAX, font files, style sheets etc. 

*/

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class CustomerCardController {
	

	@Autowired
	private CustomerCardService pservice;
	
	/* ResponseEntity represents an HTTP response, including headers, body, and status.
	 *  @RequestBody annotation automatically deserializes the JSON into a Java type
	 *  */
	
	//Open PostMan, make a POST Request - http://localhost:8085/pms/api/products/
    //Select body -> raw -> JSON 
    //Insert JSON product object.
	@PostMapping("/customer_card")
	public CustomerCard saveCustomerCard(@Validated @RequestBody CustomerCard product) {
		System.out.println(product.getCustomer());
		CustomerCard p=pservice.saveItem(product);
		
		return p;
	}
	
//	@GetMapping("/customer_card/{emp_id}")
//	public List<CustomerCard> getCustomerCardByEmp(@PathVariable(value="id") String eId)
//			throws ResourceNotFoundException{
//		
//				List<CustomerCard> c = pservice.getById(eId);
//		
//		return c;
//	}
//	// Postman/Browser --> Controller -->Service -> Repository -> DataBase
//	// All layers will use Model when required
//	//Open PostMan, make a GET Request - http://localhost:8085/pms/api/products/
	@GetMapping("/customer_card")
	public List<CustomerCard> getAllCustomerCards() {
		return pservice.listAll();   // Invokes service Method user defined listAll()
	}
}
