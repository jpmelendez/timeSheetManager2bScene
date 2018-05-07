package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class Tasks implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long taskID;
	private Long stageID;
	private String taskName;
	private String taskDescription;
	private Integer taskPriority;
	private Date createdDate;
	public Long getTaskID() {
		return taskID;
	}
	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}
	public Long getStageID() {
		return stageID;
	}
	public void setStageID(Long stageID) {
		this.stageID = stageID;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Integer getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(Integer taskPriority) {
		this.taskPriority = taskPriority;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	
}
