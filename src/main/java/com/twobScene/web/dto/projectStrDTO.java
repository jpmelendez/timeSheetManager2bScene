package com.twobScene.web.dto;

import java.io.Serializable;

public class projectStrDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8284995730162540213L;
	
	private Long projectID;
	private String staffInCharge;
	private String projectName;
	private String projectStatus;
	private String numEA;
	private String projecCat;
	//private String stageWork;
	private String budget;
	private String priority;
	private String jobStart;
	private String jobDueDate;
	private String client;
	private String projectTime;
	private String spentTime;
	private String projectDescription;
	private String shortName;
	private Boolean photoSite;
	private String rfiCondition;
	private String rfiConditionWeeks;
	private String council;
	private String councilReference;
	private String phaseID;
	private String councilName;
	private String councilPhone;
	private String councilEmail;
	private String councilNote;
	private String boxArchive;
	private String wdStartDate;
	private String wdDueDate;
	private String wdFinishJob;
	private String wdProjectTime;
	private String vcatStartDate;
	private String vcatDueDate;
	private String vcatFinishJob;
	private String vcatProjectTime;
	//private String percentage;
	
	public String getStaffInCharge() {
		return staffInCharge;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public void setStaffInCharge(String staffInCharge) {
		this.staffInCharge = staffInCharge;
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
	public String getNumEA() {
		return numEA;
	}
	public void setNumEA(String numEA) {
		this.numEA = numEA;
	}
	public String getProjecCat() {
		return projecCat;
	}
	public void setProjecCat(String projecCat) {
		this.projecCat = projecCat;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getJobStart() {
		return jobStart;
	}
	public void setJobStart(String jobStart) {
		this.jobStart = jobStart;
	}
	public String getJobDueDate() {
		return jobDueDate;
	}
	public void setJobDueDate(String jobDueDate) {
		this.jobDueDate = jobDueDate;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getProjectTime() {
		return projectTime;
	}
	public void setProjectTime(String projectTime) {
		this.projectTime = projectTime;
	}
	public String getSpentTime() {
		return spentTime;
	}
	public void setSpentTime(String spentTime) {
		this.spentTime = spentTime;
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
	
	public Boolean getPhotoSite() {
		return photoSite;
	}
	public void setPhotoSite(Boolean photoSite) {
		this.photoSite = photoSite;
	}
	public String getRfiCondition() {
		return rfiCondition;
	}
	public void setRfiCondition(String rfiCondition) {
		this.rfiCondition = rfiCondition;
	}
	public String getRfiConditionWeeks() {
		return rfiConditionWeeks;
	}
	public void setRfiConditionWeeks(String rfiConditionWeeks) {
		this.rfiConditionWeeks = rfiConditionWeeks;
	}
	
	public String getPhaseID() {
		return phaseID;
	}
	public void setPhaseID(String phaseID) {
		this.phaseID = phaseID;
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
	
	public String getCouncilName() {
		return councilName;
	}
	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}
	public String getCouncilPhone() {
		return councilPhone;
	}
	public void setCouncilPhone(String councilPhone) {
		this.councilPhone = councilPhone;
	}
	public String getCouncilEmail() {
		return councilEmail;
	}
	public void setCouncilEmail(String councilEmail) {
		this.councilEmail = councilEmail;
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
	
	public String getWdStartDate() {
		return wdStartDate;
	}
	public void setWdStartDate(String wdStartDate) {
		this.wdStartDate = wdStartDate;
	}
	public String getWdDueDate() {
		return wdDueDate;
	}
	public void setWdDueDate(String wdDueDate) {
		this.wdDueDate = wdDueDate;
	}
	public String getWdFinishJob() {
		return wdFinishJob;
	}
	public void setWdFinishJob(String wdFinishJob) {
		this.wdFinishJob = wdFinishJob;
	}
	public String getWdProjectTime() {
		return wdProjectTime;
	}
	public void setWdProjectTime(String wdProjectTime) {
		this.wdProjectTime = wdProjectTime;
	}
	public String getVcatStartDate() {
		return vcatStartDate;
	}
	public void setVcatStartDate(String vcatStartDate) {
		this.vcatStartDate = vcatStartDate;
	}
	public String getVcatDueDate() {
		return vcatDueDate;
	}
	public void setVcatDueDate(String vcatDueDate) {
		this.vcatDueDate = vcatDueDate;
	}
	public String getVcatFinishJob() {
		return vcatFinishJob;
	}
	public void setVcatFinishJob(String vcatFinishJob) {
		this.vcatFinishJob = vcatFinishJob;
	}
	public String getVcatProjectTime() {
		return vcatProjectTime;
	}
	public void setVcatProjectTime(String vcatProjectTime) {
		this.vcatProjectTime = vcatProjectTime;
	}
	@Override
	public String toString() {
		return "projectStrDTO [projectID=" + projectID + ", staffInCharge="
				+ staffInCharge + ", projectName=" + projectName
				+ ", projectStatus=" + projectStatus + ", numEA=" + numEA
				+ ", projecCat=" + projecCat + ", budget=" + budget
				+ ", priority=" + priority + ", jobStart=" + jobStart
				+ ", jobDueDate=" + jobDueDate + ", client=" + client
				+ ", projectTime=" + projectTime + ", spentTime=" + spentTime
				+ ", projectDescription=" + projectDescription + ", shortName="
				+ shortName + ", photoSite=" + photoSite + ", rfiCondition="
				+ rfiCondition + ", rfiConditionWeeks=" + rfiConditionWeeks
				+ ", phaseID=" + phaseID + "]";
	}
	

	
	

}
