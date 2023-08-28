package com.wellsfargo.training.lms.controller;

import java.time.LocalDate;
import java.util.Calendar;
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
import com.wellsfargo.training.lms.model.CustomerCard;
import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.model.ItemMaster;
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.model.RequestData;
import com.wellsfargo.training.lms.service.CustomerCardService;
import com.wellsfargo.training.lms.service.CustomerService;
import com.wellsfargo.training.lms.service.EmployeeIssueService;
import com.wellsfargo.training.lms.service.ItemMasterService;
import com.wellsfargo.training.lms.service.LoanCardMasterService;

/*Spring RestController annotation is used to create RESTful web services using Spring MVC. 
 * Spring RestController takes care of mapping request data to the defined request handler method. 
 * Once response body is generated from the handler method, it converts it to JSON or XML response. 
 * 
 * @RequestMapping - maps HTTP request with a path to a controller 
 * */
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class ItemMasterController {
	

	@Autowired
	private ItemMasterService pservice;
	@Autowired
	private CustomerCardService ccservice;
	@Autowired
	private CustomerService cservice;
	@Autowired
	private LoanCardMasterService lservice;
	@Autowired
	private EmployeeIssueService eservice;
	
	/* ResponseEntity represents an HTTP response, including headers, body, and status.
	 *  @RequestBody annotation automatically deserializes the JSON into a Java type
	 *  */
	
	//Open PostMan, make a POST Request - http://localhost:8085/pms/api/products/
    //Select body -> raw -> JSON 
    //Insert JSON product object.
	@PostMapping("/item_master")
	public ResponseEntity<ItemMaster> saveItemMaster(@Validated @RequestBody RequestData data) throws ResourceNotFoundException {
//		CustomerCard c = new CustomerCard();
//		c.setCustomer(customer);
//		c.setLoanCard(loanCard);
//		c.setIssueDate(Calendar.getInstance().getTime());
		
//		ccservice.saveItem(c);
//		ItemMaster p=pservice.saveItem(product);
//		System.out.println(product.getItemDescription());
//		return p;
		Customer customer = cservice.getByEmpId(data.getEmpId()).orElseThrow(()->
		new ResourceNotFoundException("Customer Not Found for this id ::"));
		
		LoanCardMaster loanCard = lservice.getByLoanId(data.getLoanId());
		CustomerCard c = new CustomerCard();
		c.setCustomer(customer);
		c.setLoanCard(loanCard);
		c.setIssueDate(Calendar.getInstance().getTime());
		ccservice.saveItem(c);
		
		
		
		ItemMaster itemMaster = data.getItemMaster();
		ItemMaster p = pservice.saveItem(itemMaster);
		
		EmployeeIssue eI = new EmployeeIssue();
		eI.setCustomer(customer);
		eI.setIssueDate(Calendar.getInstance().getTime());
		eI.setItemMaster(data.getItemMaster());
		eservice.saveIssue(eI);
		if(p!=null) return ResponseEntity.ok(p);
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/item_master/admin")
	public ItemMaster saveForAdmin(@Validated @RequestBody ItemMaster itemMaster)throws ResourceNotFoundException
	{
		return pservice.saveItem(itemMaster);
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
		@GetMapping("/items_purchased/{id}")
		public ResponseEntity<List<Object[]>> getObjectById(@PathVariable(value="id") String empId)
				throws ResourceNotFoundException{
					
				List<Object[]> p = pservice.getObjectByEmpId(empId).orElseThrow(() -> 
					new ResourceNotFoundException("No Purchased items found for this Id: "+ empId));
					
					return ResponseEntity.ok().body(p);
				}
}
