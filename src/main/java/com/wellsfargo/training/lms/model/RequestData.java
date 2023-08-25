
package com.wellsfargo.training.lms.model;

public class RequestData {
	private String empId;
	private String loanId;
	private ItemMaster itemMaster;
	public RequestData() {
		// TODO Auto-generated constructor stub
	}
	public RequestData(String empId, String loanId, ItemMaster itemMaster) {
		this.empId = empId;
		this.loanId = loanId;
		this.itemMaster = itemMaster;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public ItemMaster getItemMaster() {
		return itemMaster;
	}
	public void setItemMaster(ItemMaster itemMaster) {
		this.itemMaster = itemMaster;
	}
	
	
}
