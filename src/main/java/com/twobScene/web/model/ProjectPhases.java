package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class ProjectPhases implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long projectPhaseID;
	private Long phaseID;
	private Long projectID;
	private Date phaseLapseDate;
	private Integer timeLapseWeeks;
	private String phaseNotes;
	private Boolean phaseDone;
	private Date createdDate;
	
	
	public Long getProjectPhaseID() {
		return projectPhaseID;
	}
	public void setProjectPhaseID(Long projectPhaseID) {
		this.projectPhaseID = projectPhaseID;
	}
	public Long getPhaseID() {
		return phaseID;
	}
	public void setPhaseID(Long phaseID) {
		this.phaseID = phaseID;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public Date getPhaseLapseDate() {
		return phaseLapseDate;
	}
	public void setPhaseLapseDate(Date phaseLapseDate) {
		this.phaseLapseDate = phaseLapseDate;
	}
	public Integer getTimeLapseWeeks() {
		return timeLapseWeeks;
	}
	public void setTimeLapseWeeks(Integer timeLapseWeeks) {
		this.timeLapseWeeks = timeLapseWeeks;
	}
	public String getPhaseNotes() {
		return phaseNotes;
	}
	public void setPhaseNotes(String phaseNotes) {
		this.phaseNotes = phaseNotes;
	}
	public Boolean getPhaseDone() {
		return phaseDone;
	}
	public void setPhaseDone(Boolean phaseDone) {
		this.phaseDone = phaseDone;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "projectPhases [projectPhaseID=" + projectPhaseID + ", phaseID="
				+ phaseID + ", projectID=" + projectID + ", phaseLapseDate="
				+ phaseLapseDate + ", timeLapseWeeks=" + timeLapseWeeks
				+ ", phaseNotes=" + phaseNotes + ", phaseDone=" + phaseDone
				+ ", createdDate=" + createdDate + "]";
	}
	
	
	
	
}
