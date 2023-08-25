package com.wellsfargo.training.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.lms.model.EmployeeIssue;
import com.wellsfargo.training.lms.model.ItemMaster;

/**
 * 
 * @author rajgs
 * JPA Repository is mainly used for managing the data in a Spring Boot Application. 
 * JpaRepository is particularly a JPA specific extension for Repository.
 * Jpa Repository contains the APIs for basic CRUD operations, the APIS for 
 * pagination, and the APIs for sorting.
 * This Layer interacts with Database
 */

public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long> {
	  //Long is data type of id field of Product class
	/*
     * This interface has save(),findAll(),findById(),deleteById(),count()
       etc.. inbuilt methods of jpa repository for various database operations.
       This interface will be implemented by class automatically
    */

	    @Query("SELECT ei.id, im.itemDescription, im.itemMake, im.itemCategory, im.itemValuation " +
	           "FROM EmployeeIssue ei " +
	           "JOIN ei.itemMaster im " +
	           "JOIN ei.customer c " +
	           "WHERE c.empId = :empId")
	    Optional<List<Object[]>> findIssueDetailsByEmpId(@Param("empId") String empId);
	

}
