package com.wellsfargo.training.lms.model;

import java.sql.Date;

public class ViewLoan {
	private String loanId;
    private String loanType;
    private int duration;
    private Date issueDate;
    
    

    public ViewLoan() {
		// TODO Auto-generated constructor stub
	}



	public ViewLoan(String loanId, String loanType, int duration, Date issueDate) {
		this.loanId = loanId;
		this.loanType = loanType;
		this.duration = duration;
		this.issueDate = issueDate;
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



	public Date getIssueDate() {
		return issueDate;
	}



	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}



	

}
