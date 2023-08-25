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
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.model.ViewLoan;
import com.wellsfargo.training.lms.service.LoanCardMasterService;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping(value = "/api")
public class LoanCardMasterController {

	@Autowired
	private LoanCardMasterService lservice;
	
	@PostMapping("/loan_card")
	public LoanCardMaster saveCard(@Validated @RequestBody LoanCardMaster newCard)
	{
		LoanCardMaster l = lservice.saveLoanCard(newCard);
		return l;
	}
	
	@GetMapping("/loan_card")
	public List<LoanCardMaster> getAllCards(){
		return lservice.listAll();
	}
	
	@DeleteMapping("/loan_card/{id}")
	public Map<String, Boolean> deleteLoanCard(@PathVariable(value="id") Long pId)
		throws ResourceNotFoundException{
			
			LoanCardMaster l = lservice.getSingleItem(pId).orElseThrow(() -> 
			new ResourceNotFoundException("Loan Card not found for this Id: "+ pId));
			
			lservice.deleteItem(pId);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			
			return response;
		}
	@PutMapping("/loan_card/{id}")
	public ResponseEntity<LoanCardMaster> updateLoanCard(@PathVariable(value="id") Long pId,
			@Validated @RequestBody LoanCardMaster p)
			throws ResourceNotFoundException{
				
				LoanCardMaster card = lservice.getSingleItem(pId).orElseThrow(() -> 
				new ResourceNotFoundException("Loan Card not found for this Id: "+ pId));
				System.out.println(pId);
				card.setDuration(p.getDuration());
				card.setLoanId(p.getLoanId());
				card.setLoanType(p.getLoanType());
				
				if(p.getCustomerCards()!=null) card.setCustomerCards(p.getCustomerCards());
				
				final LoanCardMaster updatedLoanCard = lservice.saveLoanCard(card);
				
				return ResponseEntity.ok().body(updatedLoanCard);
			}
	
	@GetMapping("/loans/{empId}")
	public ResponseEntity<List<Object[]>> getLoanByEmpId(@PathVariable(value="empId") String empId)
			throws ResourceNotFoundException{
				
				List<Object[]> p = lservice.getLoanDetailsForEmp(empId).orElseThrow(() -> 
				new ResourceNotFoundException("Loans not found for this Id: "+ empId));
				
				return ResponseEntity.ok().body(p);
			}
}
