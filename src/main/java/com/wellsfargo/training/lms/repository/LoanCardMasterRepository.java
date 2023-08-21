package com.wellsfargo.training.lms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.training.lms.model.LoanCardMaster;

public interface LoanCardMasterRepository extends JpaRepository<LoanCardMaster, Long> {
	
}
