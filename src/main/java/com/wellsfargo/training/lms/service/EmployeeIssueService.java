package com.wellsfargo.training.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.repository.EmployeeIssueRepository;

@Service
public class EmployeeIssueService {

	@Autowired
	private EmployeeIssueRepository esrepo;
	
	public EmployeeIssue saveIssue(EmployeeIssue e)
	{
//		e.getCustomer().getEmpIssues().add(e);
//		e.getItemMaster().getEmpIssues().add(e);
		return esrepo.save(e);
	}
	
	public List<EmployeeIssue> getAll(){
		return esrepo.findAll();
	}
	
	public List<EmployeeIssue> getById(String empId)
	{
		return esrepo.findEmployeeIssuesByEmpId(empId);
	}
	public Optional<EmployeeIssue> getSingleItem(long id){
		return esrepo.findById(id);
	}
	 
	public void deleteItem(long id) {
		esrepo.deleteById(id);  //defined in JPA repo
	}
}
