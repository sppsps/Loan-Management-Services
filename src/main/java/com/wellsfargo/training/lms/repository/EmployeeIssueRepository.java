package com.wellsfargo.training.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.lms.model.CustomerCard;
import com.wellsfargo.training.lms.model.EmployeeIssue;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long> {
	
	@Query("SELECT ei FROM EmployeeIssue ei WHERE ei.customer.empId = :empId")
    List<EmployeeIssue> findEmployeeIssuesByEmpId(@Param("empId") String empId);
}
