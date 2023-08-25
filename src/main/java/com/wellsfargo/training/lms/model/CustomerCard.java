package com.wellsfargo.training.lms.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customer_card")
public class CustomerCard {

	@SequenceGenerator(name="product_seq",initialValue = 1000, allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ccid")
	private Long id;
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date issueDate;

	@ManyToOne
    @JoinColumn(name = "emp_Id")
    private Customer customer;
	
	@ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanCardMaster loanCard;

	public CustomerCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerCard(Long id, Date issueDate, String loanId, Customer customer) {
		super();
		this.id = id;
		this.issueDate = issueDate;
		//this.loanId = loanId;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
