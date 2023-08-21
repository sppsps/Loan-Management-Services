package com.wellsfargo.training.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.lms.model.EmployeeIssue;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long> {

}
