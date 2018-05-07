package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class Stages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long stageID;
	private String stageName;
	private Integer stagePriority;
	private Date createdDate;
	
	
	public Long getStageID() {
		return stageID;
	}
	public void setStageID(Long stageID) {
		this.stageID = stageID;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Integer getStagePriority() {
		return stagePriority;
	}
	public void setStagePriority(Integer stagePriority) {
		this.stagePriority = stagePriority;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	

}
