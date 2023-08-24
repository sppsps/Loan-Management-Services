package com.wellsfargo.training.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Customer;
import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.service.CustomerService;


@CrossOrigin(origins="http://localhost:3000")
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
		String empId = customer.getEmpId();
		String password  =customer.getPassword();
		Customer d = aservice.loginCustomer(empId).orElseThrow(()->
		new ResourceNotFoundException("Customer Not Found for this id ::"));
		//System.out.println(d.getPassword()+password);
		if(empId.equals(d.getEmpId()) && password.equals(d.getPassword()))
		{
			a = true;
		}
		return a;
	}
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return aservice.getAll();
	}
	
	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value="id") Long pId)
		throws ResourceNotFoundException{
			
			Customer l = aservice.getOne(pId).orElseThrow(() -> 
			new ResourceNotFoundException("Customer not found for this Id: "+ pId));
			
			aservice.deleteId(pId);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			
			return response;
		}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value="id") Long pId,
			@Validated @RequestBody Customer e)
			throws ResourceNotFoundException{
				
				Customer c = aservice.getOne(pId).orElseThrow(() -> 
				new ResourceNotFoundException("Customer not found for this Id: "+ pId));
				//System.out.println(pId);
				c.setDept(e.getDept());
				c.setDesg(e.getDesg());
				c.setDob(e.getDob());
				c.setDoj(e.getDoj());
				c.setFname(e.getFname());
				c.setCustomerCards(e.getCustomerCards());
				
				final Customer updatedCustomer = aservice.registerCustomer(c);
				
				return ResponseEntity.ok().body(updatedCustomer);
			}
}
