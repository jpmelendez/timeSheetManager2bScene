package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long actID;
	private Long stageID;
	private Long taskID;
	private String actName;
	private String actDescription;
	private Integer actPriority;
	private Boolean actChecked;
	private Boolean actAlert;
	private Integer alertDays;
	private Integer alertTrigger;
	private Date createdDate;
	
	
	public Long getActID() {
		return actID;
	}
	public void setActID(Long actID) {
		this.actID = actID;
	}
	public Long getStageID() {
		return stageID;
	}
	public void setStageID(Long stageID) {
		this.stageID = stageID;
	}
	public Long getTaskID() {
		return taskID;
	}
	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActDescription() {
		return actDescription;
	}
	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}
	public Integer getActPriority() {
		return actPriority;
	}
	public void setActPriority(Integer actPriority) {
		this.actPriority = actPriority;
	}
	
	public Boolean getActChecked() {
		return actChecked;
	}
	public void setActChecked(Boolean actChecked) {
		this.actChecked = actChecked;
	}
	public Boolean getActAlert() {
		return actAlert;
	}
	public void setActAlert(Boolean actAlert) {
		this.actAlert = actAlert;
	}
	public Integer getAlertDays() {
		return alertDays;
	}
	public void setAlertDays(Integer alertDays) {
		this.alertDays = alertDays;
	}
	
	public Integer getAlertTrigger() {
		return alertTrigger;
	}
	public void setAlertTrigger(Integer alertTrigger) {
		this.alertTrigger = alertTrigger;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

	
	
}
