package com.wellsfargo.training.lms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.lms.model.Customer;
import com.wellsfargo.training.lms.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository arepo;
	
	public Customer registerCustomer(Customer a) {
		return arepo.save(a);
	}
	
	public Optional<Customer> loginCustomer(String email)
	{
		//System.out.println("Inservice");
		//System.out.println(arepo.findByEmail(email));
		return arepo.findByEmpId(email);
	}
}
