package com.wellsfargo.training.lms.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.lms.model.LoanCardMaster;
import com.wellsfargo.training.lms.model.ViewLoan;

public interface LoanCardMasterRepository extends JpaRepository<LoanCardMaster, Long> {


	    @Query("SELECT lc.loanId, lc.loanType, lc.duration, cc.issueDate " +
	           "FROM CustomerCard cc " +
	           "JOIN cc.loanCard lc " +
	           "JOIN cc.customer c " +
	           "WHERE c.empId = :empId")
	    Optional<List<Object[]>> findLoanDetailsByEmpId(@Param("empId") String empId);
	


	public LoanCardMaster findByLoanId(String empId);
}
