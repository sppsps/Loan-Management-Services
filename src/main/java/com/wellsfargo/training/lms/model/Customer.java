package com.wellsfargo.training.lms.model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customers")
public class Customer {

	@SequenceGenerator(name="product_seq",initialValue = 1000, allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cid")
	private Long id;
	
	@Column(name="empId", length = 6, unique = true)
	private String empId;
	
	@Column(name="first_name", length = 20, nullable=false)
	private String fname;
	
	@Column(name="last_name")
	private String lname;
	
	@Column(name="designation", length = 25)
	private String desg;
	
	@Column(name="department", length = 25)
	private String dept;
	
	@Column(name="gender", length = 1)
	private char sex;
	
	private String password;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date doj;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerCard> customerCards = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeIssue> empIssues = new ArrayList<>();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String empId, String fname, String lname, String desg, String dept, char sex,
			String password, Date dob, Date doj, List<CustomerCard> customerCards,
			List<EmployeeIssue> empIssues) {
		super();
		this.id = id;
		this.empId = empId;
		this.fname = fname;
		this.lname = lname;
		this.desg = desg;
		this.dept = dept;
		this.sex = sex;
		this.password = password;
		this.dob = dob;
		this.doj = doj;
		this.customerCards = customerCards;
		this.empIssues = empIssues;
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

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
//		Base64.Encoder encoder = Base64.getEncoder();  
//        String normalString = password;
//        String encodedString = encoder.encodeToString(   // encrypt password in database field
//        normalString.getBytes(StandardCharsets.UTF_8) );
//        this.password = encodedString;
		this.password=password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public List<CustomerCard> getCustomerCards() {
		return customerCards;
	}

	public void setCustomerCards(List<CustomerCard> customerCards) {
		this.customerCards = customerCards;
	}

	public List<EmployeeIssue> getEmpIssues() {
		return empIssues;
	}

	public void setEmpIssues(List<EmployeeIssue> empIssues) {
		this.empIssues = empIssues;
	}
	
}
