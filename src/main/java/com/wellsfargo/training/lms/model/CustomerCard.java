package com.wellsfargo.training.lms.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name="customer_card")

public class CustomerCard {

	@SequenceGenerator(name="product_seq",initialValue = 1000, allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ccid")
	private Long id;
	
	@Column(name="empId", length = 6)
	private String empId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date issue_date;
	
	@Column(name="loan_id",length=6, unique = true)
	private String loanId;

	public CustomerCard() {
		// TODO Auto-generated constructor stub
	}

	public CustomerCard(Long id, String empId, Date issue_date, String loanId) {
		this.id = id;
		this.empId = empId;
		this.issue_date = issue_date;
		this.loanId = loanId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
}
