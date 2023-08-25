package com.wellsfargo.training.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.lms.model.ItemMaster;
import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.model.ViewLoan;
import com.wellsfargo.training.lms.repository.LoanCardMasterRepository;

@Service
public class LoanCardMasterService {

	@Autowired
	private LoanCardMasterRepository lrepo;
	
	public LoanCardMaster saveLoanCard(LoanCardMaster l)
	{
		return lrepo.save(l);
	}
	
	public List<LoanCardMaster> listAll(){
 		
 		return lrepo.findAll(); //Define in JPA repo.
 	}
	 
	public Optional<LoanCardMaster> getSingleItem(long id){
		return lrepo.findById(id);
	}
	 
	public void deleteItem(long id) {
		lrepo.deleteById(id);  //defined in JPA repo
	}
	
	public List<ViewLoan> getLoanDetailsForEmp(String empId){
		
		return lrepo.getLoanDetailsForEmp(empId);
	}
	
}
