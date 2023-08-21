package com.wellsfargo.training.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name="loan_card_master")
public class LoanCardMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lcid")
	private Long id;
	
	@Column(name="loan_id", length = 6, unique=true)
	private String loanId;
	
	@Column(name="loan_type", length = 15)
	private String loanType;
	
	@Column(name="duration")
	private int duration;

	public LoanCardMaster() {
		// TODO Auto-generated constructor stub
	}

	public LoanCardMaster(Long id, String loanId, String loanType, int duration) {
		this.id = id;
		this.loanId = loanId;
		this.loanType = loanType;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
