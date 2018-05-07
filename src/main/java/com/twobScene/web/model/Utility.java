package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class Utility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long utilityID;
	private Long utilityCatID;
	private String utilityName;
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	private String contactMobile;
	private String contactFax;
	private String contactAddress;
	private String councilName;
	private Integer utilityStatus;
	private Date createdDate;
	
	public Long getUtilityID() {
		return utilityID;
	}
	public void setUtilityID(Long utilityID) {
		this.utilityID = utilityID;
	}
	public Long getUtilityCatID() {
		return utilityCatID;
	}
	public void setUtilityCatID(Long utilityCatID) {
		this.utilityCatID = utilityCatID;
	}
	public String getUtilityName() {
		return utilityName;
	}
	public void setUtilityName(String utilityName) {
		this.utilityName = utilityName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getContactFax() {
		return contactFax;
	}
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getCouncilName() {
		return councilName;
	}
	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}
	public Integer getUtilityStatus() {
		return utilityStatus;
	}
	public void setUtilityStatus(Integer utilityStatus) {
		this.utilityStatus = utilityStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "Utility [utilityID=" + utilityID + ", utilityCatID="
				+ utilityCatID + ", utilityName=" + utilityName
				+ ", contactName=" + contactName + ", contactEmail="
				+ contactEmail + ", contactPhone=" + contactPhone
				+ ", contactMobile=" + contactMobile + ", contactFax="
				+ contactFax + ", contactAddress=" + contactAddress
				+ ", councilName=" + councilName + ", utilityStatus="
				+ utilityStatus + ", createdDate=" + createdDate + "]";
	}
	
	
	
	
}

