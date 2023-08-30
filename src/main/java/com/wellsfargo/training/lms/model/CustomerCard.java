package com.wellsfargo.training.lms.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "emp_Id")
    private Customer customer;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanCardMaster loanCard;

	public CustomerCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerCard(Long id, Date issueDate, Customer customer, LoanCardMaster loanCard) {
		this.id = id;
		this.issueDate = issueDate;
		this.customer = customer;
		this.loanCard = loanCard;
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

	public LoanCardMaster getLoanCard() {
		return loanCard;
	}

	public void setLoanCard(LoanCardMaster loanCard) {
		this.loanCard = loanCard;
	}

	
	
}
