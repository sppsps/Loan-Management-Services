package com.wellsfargo.training.lms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.model.ViewLoan;

public interface LoanCardMasterRepository extends JpaRepository<LoanCardMaster, Long> {

	@Query("SELECT new com.wellsfargo.training.lms.model.ViewLoan(lcm.loanId, lcm.loanType, lcm.duration, cc.issueDate) " +
	           "FROM LoanCardMaster lcm " +
	           "JOIN lcm.customerCards cc " +
	           "WHERE cc.customer.empId = :empId")
	    List<ViewLoan> getLoanDetailsForEmp(@Param("empId") String empId);
}
