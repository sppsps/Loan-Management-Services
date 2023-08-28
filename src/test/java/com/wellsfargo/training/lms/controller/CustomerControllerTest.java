package com.wellsfargo.training.lms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Customer;
import com.wellsfargo.training.lms.service.CustomerService;

@SpringBootTest
class CustomerControllerTest {
		
		Customer customer;
		
		Optional<Customer> c;
		@MockBean
		private CustomerService cservice;
		
		
		
		@Autowired
		private CustomerController customerController;
		
		@BeforeEach
		void setUp() throws Exception {
			customer = new Customer();
		}

		@AfterEach
		void tearDown() throws Exception {
			customer = null;
		}
		
		@Test
		void testCreateCustomer() throws ParseException {
			String date_string = "2001-01-15";
		       //Instantiating the SimpleDateFormat class
			Date date_dob = Date.valueOf(date_string);
			date_string = "2023-06-24";
			Date date_doj = Date.valueOf(date_string);
			customer.setEmpId("123");
			customer.setFname("John");
			customer.setLname("Doe");
			customer.setDesg("Program Associate");
			customer.setDept("CLT");
			customer.setDob(date_dob);
			customer.setDoj(date_doj);
			customer.setSex('M');
			customer.setPassword("password");
			
			when(cservice.registerCustomer(any(Customer.class))).thenReturn(customer);
			
			ResponseEntity<String> cus = customerController.createCustomer(customer);
			assertEquals(HttpStatus.OK, cus.getStatusCode());
			assertEquals("Registration Successful", cus.getBody());
			
			verify(cservice, times(1)).registerCustomer(any(Customer.class));
		}
		
		@Test
		void testLoginCustomer() throws ParseException, ResourceNotFoundException {
			customer.setEmpId("123");
			customer.setPassword("password");
			
			when(cservice.loginCustomer("123")).thenReturn(Optional.of(customer));
			Customer x = cservice.loginCustomer("123").get();
			assertEquals(x.getEmpId(), customer.getEmpId());
			assertEquals(x.getPassword(), customer.getPassword());
			Integer result=customerController.loginCustomer(customer);
			assertEquals(result, 1);
		}
		
		@Test 
		void testGetCustomer() throws ParseException, ResourceNotFoundException {
			String date_string = "2001-01-15";
		       //Instantiating the SimpleDateFormat class
			Date date_dob = Date.valueOf(date_string);
			date_string = "2023-06-24";
			Date date_doj = Date.valueOf(date_string);
			customer.setEmpId("123");
			customer.setFname("John");
			customer.setLname("Doe");
			customer.setDesg("Program Associate");
			customer.setDept("CLT");
			customer.setDob(date_dob);
			customer.setDoj(date_doj);
			customer.setSex('M');
			customer.setPassword("password");
			
			when(cservice.getByEmpId(any(String.class))).thenReturn(Optional.of(customer));
			
			String s = "123";
			ResponseEntity<Customer> c = customerController.getCustomer(s);
			assertEquals(HttpStatus.OK, c.getStatusCode());
			assertEquals(customer, c.getBody());
			
			verify(cservice, times(1)).getByEmpId(s);
			
		}
		
		@Test
		void testDeleteCustomer() throws ParseException, ResourceNotFoundException {
			Long customerIdToDelete = 1L;
	        
	        // Mock the behavior of the service
	        Customer customer = new Customer();
	        when(cservice.getOne(customerIdToDelete)).thenReturn(Optional.of(customer));
	        
	        // Perform the delete operation
	        Map<String, Boolean> response= customerController.deleteCustomer(customerIdToDelete);
	        
	        // Verify that the service methods were called
	        verify(cservice, times(1)).getOne(customerIdToDelete);
	        verify(cservice, times(1)).deleteId(customerIdToDelete);
	        
	        // Verify the response
	        assertTrue(response.containsKey("Deleted"));
	        assertTrue(response.get("Deleted"));
		}
		
		@Test
	    void testUpdateCustomer() throws ResourceNotFoundException {
	        String customerIdToUpdate = "123";
	        
	        // Mock the behavior of the service
	        Customer existingCustomer = new Customer();
	        existingCustomer.setEmpId("123");
	        existingCustomer.setFname("John");
	        existingCustomer.setLname("Doe");
	        existingCustomer.setDept("IT");
	        
	        Customer updatedCustomer = new Customer();
	        updatedCustomer.setEmpId("123");
	        updatedCustomer.setFname("John");
	        updatedCustomer.setLname("Doe");
	        updatedCustomer.setDept("HR"); // Updated department
	        
	        when(cservice.getByEmpId(customerIdToUpdate)).thenReturn(Optional.of(existingCustomer));
	        when(cservice.registerCustomer(any(Customer.class))).thenReturn(updatedCustomer);
	        ResponseEntity<Customer> response = customerController.updateCustomer(customerIdToUpdate, updatedCustomer);
	        
	        // Verify that the service methods were called
	        verify(cservice, times(1)).getByEmpId(customerIdToUpdate);
	        verify(cservice, times(1)).registerCustomer(any(Customer.class));
	        
	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(updatedCustomer, response.getBody());
	    }
		
		@Test
	    void testGetAllCustomers() {
	        // Mock the behavior of the service
	        List<Customer> customers = new ArrayList<>();
	        Customer c1 = new Customer();
	        c1.setEmpId("123");
			c1.setFname("John");
			c1.setLname("Doe");
			
			Customer c2 = new Customer();
			c2.setEmpId("124");
			c2.setFname("Jane");
			c2.setLname("Smith");
			customers.add(c2);
			customers.add(c1);
	        
	        when(cservice.getAll()).thenReturn(customers);
	        
	        // Perform the get all customers operation
	        List<Customer> response = customerController.getAllCustomers();
	        
	        // Verify that the service method was called
	        verify(cservice, times(1)).getAll();
	        
	        // Verify the response
	        assertEquals(customers.size(), response.size());
	        assertEquals(customers, response);
	    }

	}

