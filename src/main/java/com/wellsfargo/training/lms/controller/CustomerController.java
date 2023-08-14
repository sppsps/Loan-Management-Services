package com.wellsfargo.training.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Customer;
import com.wellsfargo.training.lms.service.CustomerService;

@RestController
@RequestMapping(value="/api")
public class CustomerController {
	
	@Autowired
	private CustomerService aservice;
	
	@PostMapping("/register")
	public ResponseEntity<String> createCustomer(@Validated @RequestBody Customer customer){
		
		
		Customer registerCustomer = aservice.registerCustomer(customer);
		if(registerCustomer!=null)
		{
			return ResponseEntity.ok("Registration Successful");
		}
		else
		{
			return ResponseEntity.badRequest().body("Registration Failed");
		}
		
	}
	
	@PostMapping("/login")
	public Boolean loginCustomer(@Validated @RequestBody Customer customer) throws ResourceNotFoundException
	{
		Boolean a = false;
		String email = customer.getEmail();
		String password  =customer.getPassword();
		Customer d = aservice.loginCustomer(email).orElseThrow(()->
		new ResourceNotFoundException("Customer Not Found for this id ::"));
		//System.out.println(d.getPassword()+password);
		if(email.equals(d.getEmail()) && password.equals(d.getPassword()))
		{
			a = true;
		}
		return a;
	}
	
}
