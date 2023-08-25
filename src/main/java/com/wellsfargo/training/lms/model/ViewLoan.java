package com.wellsfargo.training.lms.model;

import java.sql.Date;

public class ViewLoan {
	private String loan_id;
    private String loan_type;
    private int loan_duration;
    private Date issue_date;
    
    

    public ViewLoan() {
		// TODO Auto-generated constructor stub
	}



	public ViewLoan(String loan_id, String loan_type, int loan_duration, Date issue_date) {
        this.loan_id = loan_id;
        this.loan_type = loan_type;
        this.loan_duration = loan_duration;
        this.issue_date = issue_date;
    }



	public String getLoan_id() {
		return loan_id;
	}



	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}



	public String getLoan_type() {
		return loan_type;
	}



	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}



	public int getLoan_duration() {
		return loan_duration;
	}



	public void setLoan_duration(int loan_duration) {
		this.loan_duration = loan_duration;
	}



	public Date getIssue_date() {
		return issue_date;
	}



	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	
	

}
