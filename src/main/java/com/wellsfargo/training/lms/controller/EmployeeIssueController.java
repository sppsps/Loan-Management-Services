package com.wellsfargo.training.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.lms.exceptions.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.model.ItemMaster;
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.service.EmployeeIssueService;
import com.wellsfargo.training.lms.service.LoanCardMasterService;

@RestController
@RequestMapping(value = "/api")
public class EmployeeIssueController {
	@Autowired
	private EmployeeIssueService eservice;
	
	@PostMapping("/emp_issue")
	public EmployeeIssue saveIssue(@Validated @RequestBody EmployeeIssue newIssue)
	{
		EmployeeIssue e = eservice.saveIssue(newIssue);
		return e;
	}
	
	@GetMapping("/emp_issues/{id}")
	public ResponseEntity<EmployeeIssue> getEmployeeIssueById(@PathVariable(value="id") Long pId)
			throws ResourceNotFoundException{
				
				EmployeeIssue p = eservice.getSingleItem(pId).orElseThrow(() -> 
				new ResourceNotFoundException("Employee Issue not found for this Id: "+ pId));
				
				return ResponseEntity.ok().body(p);
			}
	
	@GetMapping("/emp_issues/emp/{id}")
	public List<EmployeeIssue> getEmployeeIssueByEmp(@PathVariable(value="id") String eId)
			throws ResourceNotFoundException{
		
				List<EmployeeIssue> p = eservice.getById(eId);
		
		return p;
	}
	
	@DeleteMapping("/emp_issue/{id}")
	public Map<String, Boolean> deleteEmployeeIssue(@PathVariable(value="id") Long pId)
		throws ResourceNotFoundException{
			
			EmployeeIssue l = eservice.getSingleItem(pId).orElseThrow(() -> 
			new ResourceNotFoundException("Loan Card not found for this Id: "+ pId));
			
			eservice.deleteItem(pId);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			
			return response;
		}
	@PutMapping("/emp_issue/{id}")
	public ResponseEntity<EmployeeIssue> updateLoanCard(@PathVariable(value="id") Long pId,
			@Validated @RequestBody EmployeeIssue e)
			throws ResourceNotFoundException{
				
				EmployeeIssue issue = eservice.getSingleItem(pId).orElseThrow(() -> 
				new ResourceNotFoundException("Loan Card not found for this Id: "+ pId));
				//System.out.println(pId);
				issue.setIssueDate(e.getIssueDate());
				issue.setIssueId(e.getIssueId());
				issue.setItemId(e.getItemId());
				issue.setReturnDate(e.getReturnDate());
				
				final EmployeeIssue updatedIssue = eservice.saveIssue(issue);
				
				return ResponseEntity.ok().body(updatedIssue);
			}
}
