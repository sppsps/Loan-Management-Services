package com.wellsfargo.training.lms.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="item_master")
public class ItemMaster {

	@SequenceGenerator(name="product_seq",initialValue = 1000, allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iid")
	private Long id;
	
	@Column(name="item_id", length = 6)
	private String itemId;
	
	@Column(name="item_description", length = 25)
	private String itemDescription;
	
	@Column(name="issue_status")
	private Boolean issueStatus;
	
	@Column(name="item_make", length = 25)
	private String itemMake;
	
	@Column(name="item_category", length = 20)
	private String itemCategory;
	
	@Column(name="item_valuation")
	private int itemValuation;
	
	@OneToMany(mappedBy = "itemMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeIssue> empIssues = new ArrayList<>();

	public ItemMaster() {
		// TODO Auto-generated constructor stub
	}

	public ItemMaster(Long id, String itemId, String itemDescription, Boolean issueStatus, String itemMake,
			String itemCategory, int itemValuation) {
		this.id = id;
		this.itemId = itemId;
		this.itemDescription = itemDescription;
		this.issueStatus = issueStatus;
		this.itemMake = itemMake;
		this.itemCategory = itemCategory;
		this.itemValuation = itemValuation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Boolean getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Boolean issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getItemMake() {
		return itemMake;
	}

	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getItemValuation() {
		return itemValuation;
	}

	public void setItemValuation(int itemValuation) {
		this.itemValuation = itemValuation;
	}
	
}
