package com.wellsfargo.training.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public Integer loginCustomer(@Validated @RequestBody Customer customer) throws ResourceNotFoundException
	{
		
		String username = "000000";
		String pwd = "111111";
		
		String empId = customer.getEmpId();
		String password  =customer.getPassword();
//		System.out.println(empId);

		if(empId.equals(username)) {
//			System.out.println(empId);
			if(password.equals(pwd)) {
//				System.out.println(empId);
					return 0;
			}
			return 2;
		}
		
		Customer d = aservice.loginCustomer(empId).orElseThrow(()->
		new ResourceNotFoundException("Customer Not Found for this id ::"));
		//System.out.println(d.getPassword()+password);
		if(empId.equals(d.getEmpId()) && password.equals(d.getPassword()))
		{
			return 1;
		}
		return 2;
	}
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return aservice.getAll();
	}
	
	@GetMapping("customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable(value="id") String empId)
			throws ResourceNotFoundException{
		
		Optional<Customer> customer = aservice.getByEmpId(empId);
	    if (customer.isPresent()) {
	        return ResponseEntity.ok(customer.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
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
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value="id") String empId,
			@Validated @RequestBody Customer e)
			throws ResourceNotFoundException{
				
				Customer c = aservice.getByEmpId(empId).orElseThrow(() -> 
				new ResourceNotFoundException("Customer not found for this Id: "+ empId));
				//System.out.println(pId);
				c.setDept(e.getDept());
				c.setDesg(e.getDesg());
				c.setDob(e.getDob());
				c.setDoj(e.getDoj());
				c.setFname(e.getFname());
				c.setLname(e.getLname());
				c.setSex(e.getSex());
//				if(e.getCustomerCards()!=null) c.setCustomerCards(e.getCustomerCards());
//				if(e.getEmpIssues()!=null) c.setEmpIssues(e.getEmpIssues());
				
				final Customer updatedCustomer = aservice.registerCustomer(c);
				
				return ResponseEntity.ok().body(updatedCustomer);
			}
	
}
