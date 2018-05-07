package com.twobScene.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class Projects implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	

	
	private Long projectID;
	private Long companyID;
	private String projectStatus;
	private Integer numberOfEandA;
	private Long projectCatID;
	private String projectName;
	private String projectDescription;
	private String shortName;
	private BigDecimal budget;
	private String priority;
	private Long staffID;
	private Integer projectTime;
	private String masurementTime;
	private Date startDate;
	private Date dueDate;
	private Integer hoursSpend;
	private Long clientID;
	private String councilName;
	private String councilPh;
	private String councilEmail;
	private Boolean photoSite;
	private Integer phaseId;
	private Date rfiCondition;
	private Integer rfiConditionWeeks;
	private String council;
	private String councilReference;
	private String councilNote;
	private String boxArchive;
	private Date wdStartDate;
	private Date wdDueDate;
	private Integer wdFinishJob;
	private Integer wdProjectTime;
	private Date vcatStartDate;
	private Date vcatDueDate;
	private Integer vcatFinishJob;
	private Integer vcatProjectTime;
	private Date insertTime;
	
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public Long getCompanyID() {
		return companyID;
	}
	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	public Integer getNumberOfEandA() {
		return numberOfEandA;
	}
	public void setNumberOfEandA(Integer numberOfEandA) {
		this.numberOfEandA = numberOfEandA;
	}
	public Long getProjectCatID() {
		return projectCatID;
	}
	public void setProjectCatID(Long projectCatID) {
		this.projectCatID = projectCatID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	public Integer getProjectTime() {
		return projectTime;
	}
	public void setProjectTime(Integer projectTime) {
		this.projectTime = projectTime;
	}
	public String getMasurementTime() {
		return masurementTime;
	}
	public void setMasurementTime(String masurementTime) {
		this.masurementTime = masurementTime;
	}
	public Integer getHoursSpend() {
		return hoursSpend;
	}
	public void setHoursSpend(Integer hoursSpend) {
		this.hoursSpend = hoursSpend;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	public Long getStaffID() {
		return staffID;
	}
	public void setStaffID(Long staffID) {
		this.staffID = staffID;
	}
	public Long getClientID() {
		return clientID;
	}
	public void setClientID(Long clientID) {
		this.clientID = clientID;
	}
	
	public String getCouncilName() {
		return councilName;
	}
	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}
	public String getCouncilPh() {
		return councilPh;
	}
	public void setCouncilPh(String councilPh) {
		this.councilPh = councilPh;
	}
	public String getCouncilEmail() {
		return councilEmail;
	}
	public void setCouncilEmail(String councilEmail) {
		this.councilEmail = councilEmail;
	}
	
	public Boolean getPhotoSite() {
		return photoSite;
	}
	public void setPhotoSite(Boolean photoSite) {
		this.photoSite = photoSite;
	}
	public Integer getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}
	
	
	public Date getRfiCondition() {
		return rfiCondition;
	}
	public void setRfiCondition(Date rfiCondition) {
		this.rfiCondition = rfiCondition;
	}
	public Integer getRfiConditionWeeks() {
		return rfiConditionWeeks;
	}
	public void setRfiConditionWeeks(Integer rfiConditionWeeks) {
		this.rfiConditionWeeks = rfiConditionWeeks;
	}
	
	public String getCouncil() {
		return council;
	}
	public void setCouncil(String council) {
		this.council = council;
	}
	public String getCouncilReference() {
		return councilReference;
	}
	public void setCouncilReference(String councilReference) {
		this.councilReference = councilReference;
	}
	
	
	public String getCouncilNote() {
		return councilNote;
	}
	public void setCouncilNote(String councilNote) {
		this.councilNote = councilNote;
	}
	public String getBoxArchive() {
		return boxArchive;
	}
	public void setBoxArchive(String boxArchive) {
		this.boxArchive = boxArchive;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getWdStartDate() {
		return wdStartDate;
	}
	public void setWdStartDate(Date wdStartDate) {
		this.wdStartDate = wdStartDate;
	}
	public Date getWdDueDate() {
		return wdDueDate;
	}
	public void setWdDueDate(Date wdDueDate) {
		this.wdDueDate = wdDueDate;
	}
	public Integer getWdFinishJob() {
		return wdFinishJob;
	}
	public void setWdFinishJob(Integer wdFinishJob) {
		this.wdFinishJob = wdFinishJob;
	}
	public Integer getWdProjectTime() {
		return wdProjectTime;
	}
	public void setWdProjectTime(Integer wdProjectTime) {
		this.wdProjectTime = wdProjectTime;
	}
	public Date getVcatStartDate() {
		return vcatStartDate;
	}
	public void setVcatStartDate(Date vcatStartDate) {
		this.vcatStartDate = vcatStartDate;
	}
	public Date getVcatDueDate() {
		return vcatDueDate;
	}
	public void setVcatDueDate(Date vcatDueDate) {
		this.vcatDueDate = vcatDueDate;
	}
	public Integer getVcatFinishJob() {
		return vcatFinishJob;
	}
	public void setVcatFinishJob(Integer vcatFinishJob) {
		this.vcatFinishJob = vcatFinishJob;
	}
	public Integer getVcatProjectTime() {
		return vcatProjectTime;
	}
	public void setVcatProjectTime(Integer vcatProjectTime) {
		this.vcatProjectTime = vcatProjectTime;
	}
	
	

}
