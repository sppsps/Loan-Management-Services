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
import com.wellsfargo.training.lms.model.ItemMaster;
import com.wellsfargo.training.lms.service.ItemMasterService;

/*Spring RestController annotation is used to create RESTful web services using Spring MVC. 
 * Spring RestController takes care of mapping request data to the defined request handler method. 
 * Once response body is generated from the handler method, it converts it to JSON or XML response. 
 * 
 * @RequestMapping - maps HTTP request with a path to a controller 
 * */

@RestController
@RequestMapping(value="/api")
public class ItemMasterController {
	

	@Autowired
	private ItemMasterService pservice;
	
	/* ResponseEntity represents an HTTP response, including headers, body, and status.
	 *  @RequestBody annotation automatically deserializes the JSON into a Java type
	 *  */
	
	//Open PostMan, make a POST Request - http://localhost:8085/pms/api/products/
    //Select body -> raw -> JSON 
    //Insert JSON product object.
	@PostMapping("/item_master")
	public ItemMaster saveItemMaster(@Validated @RequestBody ItemMaster product) {
		ItemMaster p=pservice.saveItem(product);
		System.out.println(product.getItemDescription());
		return p;
	}
	
	// Postman/Browser --> Controller -->Service -> Repository -> DataBase
	// All layers will use Model when required
	//Open PostMan, make a GET Request - http://localhost:8085/pms/api/products/
	@GetMapping("/item_master")
	public List<ItemMaster> getAllItemMasters() {
		return pservice.listAll();   // Invokes service Method user defined listAll()
	}
	
	/* @PathVariable is a Spring annotation which indicates that a method parameter should be
     *  bound to a URI template variable.
       @PathVariable annotation is used to read an URL template variable.
     */
	//Open PostMan, make a GET Request - http://localhost:8085/pms/api/products/4
	
	@GetMapping("/item_master/{id}")
	public ResponseEntity<ItemMaster> getItemMasterById(@PathVariable(value="id") Long pId)
	throws ResourceNotFoundException{
		
		ItemMaster p = pservice.getSingleItem(pId).orElseThrow(() -> 
		new ResourceNotFoundException("ItemMaster not found for this Id: "+ pId));
		
		return ResponseEntity.ok().body(p);
	}

	@PutMapping("/item_master/{id}")
	public ResponseEntity<ItemMaster> updateItemMaster(@PathVariable(value="id") Long pId,
			@Validated @RequestBody ItemMaster p)
			throws ResourceNotFoundException{
				
				ItemMaster product = pservice.getSingleItem(pId).orElseThrow(() -> 
				new ResourceNotFoundException("ItemMaster not found for this Id: "+ pId));
				
//				product.set(p.getBrand());
//				product.setMadein(p.getMadein());
//				product.setName(p.getName());
//				product.setPrice(p.getPrice());
				
				product.setIssueStatus(p.getIssueStatus());
				product.setItemCategory(p.getItemCategory());
				product.setItemDescription(p.getItemDescription());
				product.setItemId(p.getItemId());
				product.setItemMake(p.getItemMake());
				product.setItemValuation(p.getItemValuation());
				
				final ItemMaster updatedItemMaster = pservice.saveItem(product);
				
				return ResponseEntity.ok().body(updatedItemMaster);
			}

		@DeleteMapping("/item_master/{id}")
		public Map<String, Boolean> deleteItemMaster(@PathVariable(value="id") Long pId)
			throws ResourceNotFoundException{
				
				ItemMaster p = pservice.getSingleItem(pId).orElseThrow(() -> 
				new ResourceNotFoundException("ItemMaster not found for this Id: "+ pId));
				
				pservice.deleteItem(pId);
				
				Map<String, Boolean> response = new HashMap<>();
				response.put("Deleted", Boolean.TRUE);
				
				return response;
			}
}
