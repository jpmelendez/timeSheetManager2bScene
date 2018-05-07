package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class ProjectActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long projectActivityID;
	private Long projectID;
	private Long stageID;
	private Long taskID;
	private Long actID;
	private Long statusID;
	private String descPA;
	private String priorityPA;
	private Integer progress;
	private Date startDate;
	private Date dueDate;
	private Integer estimatedHrs;
	private Integer estimatedMin;
	private Long dependancyID;
	private Long staffInCharge;
	private Boolean checkedPA;
	private Boolean activityDone;
	private Date createdDate;
	
	
	public Long getProjectActivityID() {
		return projectActivityID;
	}
	public void setProjectActivityID(Long projectActivityID) {
		this.projectActivityID = projectActivityID;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
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
	public Long getActID() {
		return actID;
	}
	public void setActID(Long actID) {
		this.actID = actID;
	}
	public Long getStatusID() {
		return statusID;
	}
	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}
	public String getDescPA() {
		return descPA;
	}
	public void setDescPA(String descPA) {
		this.descPA = descPA;
	}
	public String getPriorityPA() {
		return priorityPA;
	}
	public void setPriorityPA(String priorityPA) {
		this.priorityPA = priorityPA;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getEstimatedHrs() {
		return estimatedHrs;
	}
	public void setEstimatedHrs(Integer estimatedHrs) {
		this.estimatedHrs = estimatedHrs;
	}
	public Integer getEstimatedMin() {
		return estimatedMin;
	}
	public void setEstimatedMin(Integer estimatedMin) {
		this.estimatedMin = estimatedMin;
	}
	
	
	public Long getDependancyID() {
		return dependancyID;
	}
	public void setDependancyID(Long dependancyID) {
		this.dependancyID = dependancyID;
	}
	public Long getStaffInCharge() {
		return staffInCharge;
	}
	public void setStaffInCharge(Long staffInCharge) {
		this.staffInCharge = staffInCharge;
	}
	public Boolean getCheckedPA() {
		return checkedPA;
	}
	public void setCheckedPA(Boolean checkedPA) {
		this.checkedPA = checkedPA;
	}
	
	
	public Boolean getActivityDone() {
		return activityDone;
	}
	public void setActivityDone(Boolean activityDone) {
		this.activityDone = activityDone;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
}
