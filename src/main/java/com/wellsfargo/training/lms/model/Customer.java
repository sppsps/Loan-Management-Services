package com.wellsfargo.training.lms.model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.wellsfargo.training.pms.model.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cid")
	private Long id;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="first_name", nullable=false)
	private String fname;
	
	@Column(name="last_name")
	private String lname;
	
	private String password;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	@Column(name="phone")
	private String phoneno;
	
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String email, String fname, String lname, String password, Date dob, String phoneno) {
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();  
        String normalString = password;
        String encodedString = encoder.encodeToString(   // encrypt password in database field
        normalString.getBytes(StandardCharsets.UTF_8) );
        this.password = encodedString;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	
	

}
