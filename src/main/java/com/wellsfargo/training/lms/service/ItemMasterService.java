package com.wellsfargo.training.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.lms.model.ItemMaster;
import com.wellsfargo.training.lms.repository.ItemMasterRepository;

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
public class ItemMasterService {
	

	/*@Autowired - marks a constructor, field, or setter method to be autowired by Spring dependency injection. */
	@Autowired
	private ItemMasterRepository irepo;
	
	public ItemMaster saveItem(ItemMaster p) {
		return irepo.save(p);   // Invokes save() method predefined in JPA repo
	}
	
	 public List<ItemMaster> listAll(){
 		
 		return irepo.findAll(); //Define in JPA repo.
 	}
	 
	public Optional<ItemMaster> getSingleItem(long id){
		return irepo.findById(id);
	}
	 
	public void deleteItem(long id) {
		irepo.deleteById(id);  //defined in JPA repo
	}
	
	
}
