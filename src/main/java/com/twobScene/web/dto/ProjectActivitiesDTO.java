
package com.twobScene.web.dto;

import java.io.Serializable;

public class ProjectActivitiesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long mapProjectActID;
	private Long projectId;
	private Long stage_id;
	private Long task_id;
	private Long act_id;
	private String act_name;
	private String actDescription;
	private String mapActDesc;
	private String progress;
	private String paStatus;
	private String paPriority;
	private String startDate;
	private String dueDate;
	private String estimatedTime;
	private String catalogOrder;
	private String nameStaffCharge;
	private Boolean checked;
	
	public Long getMapProjectActID() {
		return mapProjectActID;
	}
	public void setMapProjectActID(Long mapProjectActID) {
		this.mapProjectActID = mapProjectActID;
	}
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getStage_id() {
		return stage_id;
	}
	public void setStage_id(Long stage_id) {
		this.stage_id = stage_id;
	}
	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	public Long getAct_id() {
		return act_id;
	}
	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getActDescription() {
		return actDescription;
	}
	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}
	public String getMapActDesc() {
		return mapActDesc;
	}
	public void setMapActDesc(String mapActDesc) {
		this.mapActDesc = mapActDesc;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getPaStatus() {
		return paStatus;
	}
	public void setPaStatus(String paStatus) {
		this.paStatus = paStatus;
	}
	public String getPaPriority() {
		return paPriority;
	}
	public void setPaPriority(String paPriority) {
		this.paPriority = paPriority;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getCatalogOrder() {
		return catalogOrder;
	}
	public void setCatalogOrder(String catalogOrder) {
		this.catalogOrder = catalogOrder;
	}
	
	public String getNameStaffCharge() {
		return nameStaffCharge;
	}
	public void setNameStaffCharge(String nameStaffCharge) {
		this.nameStaffCharge = nameStaffCharge;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
	
	
	
	
}
