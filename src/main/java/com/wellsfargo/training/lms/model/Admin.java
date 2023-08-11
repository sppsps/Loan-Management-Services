package com.wellsfargo.training.lms.model;

import java.sql.Date;

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
@Table(name="Admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="aid")
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
	

}
