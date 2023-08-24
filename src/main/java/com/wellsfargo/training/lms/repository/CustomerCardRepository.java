package com.wellsfargo.training.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.lms.model.CustomerCard;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
	
	//Custom Method to fetch record/object based on email field - non id field.
			//public List<Optional<CustomerCard>> findByEmpId(String empId);
	@Query("SELECT cc FROM CustomerCard cc WHERE cc.customer.empId = :empId")
    List<CustomerCard> findCustomerCardsByEmpId(@Param("empId") String empId);
}
