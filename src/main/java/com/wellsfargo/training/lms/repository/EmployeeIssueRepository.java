package com.wellsfargo.training.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.lms.model.EmployeeIssue;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long> {
	
	public List<Optional<EmployeeIssue>> findByEmpId(String empId);
}
