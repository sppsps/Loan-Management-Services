package com.wellsfargo.training.lms.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name="emp_issue")
public class EmployeeIssue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="eiid")
	private Long id;
	
	@Column(name="issue_id", length = 6)
	private String issueId;
	
	@Column(name="emp_id", length=6)
	private String empId;
	
	@Column(name="item_id", length=6)
	private String itemId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date issueDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date returnDate;

	public EmployeeIssue() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeIssue(Long id, String issueId, String empId, String itemId, Date issueDate, Date returnDate) {
		this.id = id;
		this.issueId = issueId;
		this.empId = empId;
		this.itemId = itemId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
}
