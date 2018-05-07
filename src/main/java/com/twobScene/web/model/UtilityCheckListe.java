package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class UtilityCheckListe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long utCheckListID;
	private Long utilityID;
	private Long projectID;
	private Integer utRequired;
	private Float price;
	private Integer paidBy;
	private Date dateOne;
	private Integer utReceived;
	private String utNote;
	private Date dateTwo;
	private Integer invoiceClient;
	private Date createdDate;
	public Long getUtCheckListID() {
		return utCheckListID;
	}
	public void setUtCheckListID(Long utCheckListID) {
		this.utCheckListID = utCheckListID;
	}
	public Long getUtilityID() {
		return utilityID;
	}
	public void setUtilityID(Long utilityID) {
		this.utilityID = utilityID;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public Integer getUtRequired() {
		return utRequired;
	}
	public void setUtRequired(Integer utRequired) {
		this.utRequired = utRequired;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(Integer paidBy) {
		this.paidBy = paidBy;
	}
	public Date getDateOne() {
		return dateOne;
	}
	public void setDateOne(Date dateOne) {
		this.dateOne = dateOne;
	}
	public Integer getUtReceived() {
		return utReceived;
	}
	public void setUtReceived(Integer utReceived) {
		this.utReceived = utReceived;
	}
	public String getUtNote() {
		return utNote;
	}
	public void setUtNote(String utNote) {
		this.utNote = utNote;
	}
	public Date getDateTwo() {
		return dateTwo;
	}
	public void setDateTwo(Date dateTwo) {
		this.dateTwo = dateTwo;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getInvoiceClient() {
		return invoiceClient;
	}
	public void setInvoiceClient(Integer invoiceClient) {
		this.invoiceClient = invoiceClient;
	}
	

}

