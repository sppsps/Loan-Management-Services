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
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date issueDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date returnDate;
	
	@ManyToOne
    @JoinColumn(name = "emp_Id")
    private Customer customer;

	@ManyToOne
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;
	
	public EmployeeIssue() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeIssue(Long id, String issueId, Date issueDate, Date returnDate, Customer customer,
			ItemMaster itemMaster) {
		this.id = id;
		this.issueId = issueId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.customer = customer;
		this.itemMaster = itemMaster;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ItemMaster getItemMaster() {
		return itemMaster;
	}

	public void setItemMaster(ItemMaster itemMaster) {
		this.itemMaster = itemMaster;
	}

	
}
