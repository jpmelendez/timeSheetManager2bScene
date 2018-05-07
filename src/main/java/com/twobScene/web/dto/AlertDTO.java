package com.twobScene.web.dto;

import java.io.Serializable;

public class AlertDTO implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long alertID;
	private Long projectID;
	private String alertCategoryID;
	private Long consultantListID;
	private Long mapProjectID;
	private String receivedDate;
	private String dueDate;
	private String alertDate;
	private String alertNote;
	private String timeAlert;
	private String alertStatus;
	private String createdDate;
	private Boolean alertStatusBoolean;
	private String vcatResult;
	private String vcatResultDate;
	public Long getAlertID() {
		return alertID;
	}
	public void setAlertID(Long alertID) {
		this.alertID = alertID;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public String getAlertCategoryID() {
		return alertCategoryID;
	}
	public void setAlertCategoryID(String alertCategoryID) {
		this.alertCategoryID = alertCategoryID;
	}
	public Long getConsultantListID() {
		return consultantListID;
	}
	public void setConsultantListID(Long consultantListID) {
		this.consultantListID = consultantListID;
	}
	public Long getMapProjectID() {
		return mapProjectID;
	}
	public void setMapProjectID(Long mapProjectID) {
		this.mapProjectID = mapProjectID;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}
	public String getAlertNote() {
		return alertNote;
	}
	public void setAlertNote(String alertNote) {
		this.alertNote = alertNote;
	}
	public String getTimeAlert() {
		return timeAlert;
	}
	public void setTimeAlert(String timeAlert) {
		this.timeAlert = timeAlert;
	}
	public String getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getAlertStatusBoolean() {
		return alertStatusBoolean;
	}
	public void setAlertStatusBoolean(Boolean alertStatusBoolean) {
		this.alertStatusBoolean = alertStatusBoolean;
	}
	
	public String getVcatResult() {
		return vcatResult;
	}
	public void setVcatResult(String vcatResult) {
		this.vcatResult = vcatResult;
	}
	public String getVcatResultDate() {
		return vcatResultDate;
	}
	public void setVcatResultDate(String vcatResultDate) {
		this.vcatResultDate = vcatResultDate;
	}
	@Override
	public String toString() {
		return "AlertDTO [alertID=" + alertID + ", projectID=" + projectID
				+ ", alertCategoryID=" + alertCategoryID
				+ ", consultantListID=" + consultantListID + ", mapProjectID="
				+ mapProjectID + ", receivedDate=" + receivedDate
				+ ", dueDate=" + dueDate + ", alertDate=" + alertDate
				+ ", alertNote=" + alertNote + ", timeAlert=" + timeAlert
				+ ", alertStatus=" + alertStatus + ", createdDate="
				+ createdDate + ", alertStatusBoolean=" + alertStatusBoolean
				+ "]";
	}
	
	
	
	
}

