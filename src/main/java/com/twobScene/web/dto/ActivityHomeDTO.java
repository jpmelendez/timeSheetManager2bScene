package com.twobScene.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ActivityHomeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mapProjectActID;
	private String mapStatus;
	private String mapActDesc;
	private String mapActPriority;
	private String progress;
	private String mapStartDate;
	private String mapDueDate;
	private Integer mapOverdue;
	private String mapEstimatedHrs;
	private String mapPCharge;
	private String mapColorCode;
	private String mapPChargeIni;
	private Long stageID;
	private String stageName;
	private Long taskID;
	private String taskName;
	private String taskDesc;
	private Long actID;
	private String actName;
	private String actDesc;
	private Boolean actAlert;
	private Integer actAlerDays;
	private Integer actAlertTrigger;
	private Integer actFlagAlert;
	private Long projectID;
	private String projectName;
	private String projectStatus;
	private String projectExtensionAleration;
	private String strBudget; 
	private BigDecimal budget;
	private String projectTime;
	private String projectStartDate;
	private String projectDueDate;
	private Integer projectOverdue;
	private String client;
	private String councilName;
	private String councilPH;
	private Long alertCatID;
	private String alertName;
	private String alertDate;
	private String alertTime;
	private String councilEmail;
	
	
	public Long getMapProjectActID() {
		return mapProjectActID;
	}
	public void setMapProjectActID(Long mapProjectActID) {
		this.mapProjectActID = mapProjectActID;
	}
	public String getMapStatus() {
		return mapStatus;
	}
	public void setMapStatus(String mapStatus) {
		this.mapStatus = mapStatus;
	}
	public String getMapActDesc() {
		return mapActDesc;
	}
	public void setMapActDesc(String mapActDesc) {
		this.mapActDesc = mapActDesc;
	}
	public String getMapActPriority() {
		return mapActPriority;
	}
	public void setMapActPriority(String mapActPriority) {
		this.mapActPriority = mapActPriority;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getMapStartDate() {
		return mapStartDate;
	}
	public void setMapStartDate(String mapStartDate) {
		this.mapStartDate = mapStartDate;
	}
	public String getMapDueDate() {
		return mapDueDate;
	}
	public void setMapDueDate(String mapDueDate) {
		this.mapDueDate = mapDueDate;
	}
	public Integer getMapOverdue() {
		return mapOverdue;
	}
	public void setMapOverdue(Integer mapOverdue) {
		this.mapOverdue = mapOverdue;
	}
	public String getMapEstimatedHrs() {
		return mapEstimatedHrs;
	}
	public void setMapEstimatedHrs(String mapEstimatedHrs) {
		this.mapEstimatedHrs = mapEstimatedHrs;
	}
	public String getMapPCharge() {
		return mapPCharge;
	}
	public void setMapPCharge(String mapPCharge) {
		this.mapPCharge = mapPCharge;
	}
	public String getMapColorCode() {
		return mapColorCode;
	}
	public void setMapColorCode(String mapColorCode) {
		this.mapColorCode = mapColorCode;
	}
	public String getMapPChargeIni() {
		return mapPChargeIni;
	}
	public void setMapPChargeIni(String mapPChargeIni) {
		this.mapPChargeIni = mapPChargeIni;
	}
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
	public Long getTaskID() {
		return taskID;
	}
	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public Long getActID() {
		return actID;
	}
	public void setActID(Long actID) {
		this.actID = actID;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActDesc() {
		return actDesc;
	}
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}
	public Boolean getActAlert() {
		return actAlert;
	}
	public void setActAlert(Boolean actAlert) {
		this.actAlert = actAlert;
	}
	public Integer getActAlerDays() {
		return actAlerDays;
	}
	public void setActAlerDays(Integer actAlerDays) {
		this.actAlerDays = actAlerDays;
	}
	public Integer getActAlertTrigger() {
		return actAlertTrigger;
	}
	public void setActAlertTrigger(Integer actAlertTrigger) {
		this.actAlertTrigger = actAlertTrigger;
	}
	public Integer getActFlagAlert() {
		return actFlagAlert;
	}
	public void setActFlagAlert(Integer actFlagAlert) {
		this.actFlagAlert = actFlagAlert;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getProjectExtensionAleration() {
		return projectExtensionAleration;
	}
	public void setProjectExtensionAleration(String projectExtensionAleration) {
		this.projectExtensionAleration = projectExtensionAleration;
	}
	public String getStrBudget() {
		return strBudget;
	}
	public void setStrBudget(String strBudget) {
		this.strBudget = strBudget;
	}
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	public String getProjectTime() {
		return projectTime;
	}
	public void setProjectTime(String projectTime) {
		this.projectTime = projectTime;
	}
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectDueDate() {
		return projectDueDate;
	}
	public void setProjectDueDate(String projectDueDate) {
		this.projectDueDate = projectDueDate;
	}
	public Integer getProjectOverdue() {
		return projectOverdue;
	}
	public void setProjectOverdue(Integer projectOverdue) {
		this.projectOverdue = projectOverdue;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getCouncilName() {
		return councilName;
	}
	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}
	public String getCouncilPH() {
		return councilPH;
	}
	public void setCouncilPH(String councilPH) {
		this.councilPH = councilPH;
	}
	public Long getAlertCatID() {
		return alertCatID;
	}
	public void setAlertCatID(Long alertCatID) {
		this.alertCatID = alertCatID;
	}
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public String getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}
	public String getAlertTime() {
		return alertTime;
	}
	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}
	public String getCouncilEmail() {
		return councilEmail;
	}
	public void setCouncilEmail(String councilEmail) {
		this.councilEmail = councilEmail;
	}
	@Override
	public String toString() {
		return "ActivityHomeDTO [mapProjectActID=" + mapProjectActID
				+ ", mapStatus=" + mapStatus + ", mapActDesc=" + mapActDesc
				+ ", mapActPriority=" + mapActPriority + ", progress="
				+ progress + ", mapStartDate=" + mapStartDate + ", mapDueDate="
				+ mapDueDate + ", mapOverdue=" + mapOverdue
				+ ", mapEstimatedHrs=" + mapEstimatedHrs + ", mapPCharge="
				+ mapPCharge + ", mapColorCode=" + mapColorCode
				+ ", mapPChargeIni=" + mapPChargeIni + ", stageID=" + stageID
				+ ", stageName=" + stageName + ", taskID=" + taskID
				+ ", taskName=" + taskName + ", taskDesc=" + taskDesc
				+ ", actID=" + actID + ", actName=" + actName + ", actDesc="
				+ actDesc + ", actAlert=" + actAlert + ", actAlerDays="
				+ actAlerDays + ", actAlertTrigger=" + actAlertTrigger
				+ ", actFlagAlert=" + actFlagAlert + ", projectID=" + projectID
				+ ", projectName=" + projectName + ", projectStatus="
				+ projectStatus + ", projectExtensionAleration="
				+ projectExtensionAleration + ", strBudget=" + strBudget
				+ ", budget=" + budget + ", projectTime=" + projectTime
				+ ", projectStartDate=" + projectStartDate
				+ ", projectDueDate=" + projectDueDate + ", projectOverdue="
				+ projectOverdue + ", client=" + client + ", councilName="
				+ councilName + ", councilPH=" + councilPH + ", alertCatID="
				+ alertCatID + ", alertName=" + alertName + ", alertDate="
				+ alertDate + ", alertTime=" + alertTime + ", councilEmail="
				+ councilEmail + "]";
	}
	
	
	
	
		
	
	

}
