package com.wellsfargo.training.lms.service;

import java.util.List;
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
	
	public List<Customer> getAll(){
		return arepo.findAll();
	}
	
	public void deleteId(Long Id) {
		arepo.deleteById(Id);
	}
	
	public Optional<Customer> getOne(Long id) {
		return arepo.findById(id);
	}
	
	public Optional<Customer> loginCustomer(String email)
	{
		//System.out.println("Inservice");
		//System.out.println(arepo.findByEmail(email));
		return arepo.findByEmpId(email);
	}
	
	public Optional<Customer> getByEmpId(String empId)
	{
		return arepo.findByEmpId(empId);
	}
}
