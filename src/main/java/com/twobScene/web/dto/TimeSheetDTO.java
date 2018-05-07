package com.twobScene.web.dto;

import java.io.Serializable;



public class TimeSheetDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long trackID;
	private Long mapPAID;
	private Long pChargeID;
	private Long projectID;
	private Long stageID;
	private Long taskID;
	private Long actID;
	private String nameCharge;
	private String trackingDate;
	private String trackingTime;
	private String trackingNote;
	private String trackingApproved;
	private String totalTime;
	private String mapActDesc;
	private String projectName;
	private String stageName;
	private String taskAndActName;
	private String monTime;
	private String tueTime;
	private String wedTime;
	private String thuTime;
	private String friTime;
	private String satTime;
	private String sunTime;
	private String taskName;
	private String actName;
	public Long getTrackID() {
		return trackID;
	}
	public void setTrackID(Long trackID) {
		this.trackID = trackID;
	}
	public Long getMapPAID() {
		return mapPAID;
	}
	public void setMapPAID(Long mapPAID) {
		this.mapPAID = mapPAID;
	}
	public Long getpChargeID() {
		return pChargeID;
	}
	public void setpChargeID(Long pChargeID) {
		this.pChargeID = pChargeID;
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
	public String getNameCharge() {
		return nameCharge;
	}
	public void setNameCharge(String nameCharge) {
		this.nameCharge = nameCharge;
	}
	public String getTrackingDate() {
		return trackingDate;
	}
	public void setTrackingDate(String trackingDate) {
		this.trackingDate = trackingDate;
	}
	public String getTrackingTime() {
		return trackingTime;
	}
	public void setTrackingTime(String trackingTime) {
		this.trackingTime = trackingTime;
	}
	public String getTrackingNote() {
		return trackingNote;
	}
	public void setTrackingNote(String trackingNote) {
		this.trackingNote = trackingNote;
	}
	public String getTrackingApproved() {
		return trackingApproved;
	}
	public void setTrackingApproved(String trackingApproved) {
		this.trackingApproved = trackingApproved;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getMapActDesc() {
		return mapActDesc;
	}
	public void setMapActDesc(String mapActDesc) {
		this.mapActDesc = mapActDesc;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getTaskAndActName() {
		return taskAndActName;
	}
	public void setTaskAndActName(String taskAndActName) {
		this.taskAndActName = taskAndActName;
	}
	public String getMonTime() {
		return monTime;
	}
	public void setMonTime(String monTime) {
		this.monTime = monTime;
	}
	public String getTueTime() {
		return tueTime;
	}
	public void setTueTime(String tueTime) {
		this.tueTime = tueTime;
	}
	public String getWedTime() {
		return wedTime;
	}
	public void setWedTime(String wedTime) {
		this.wedTime = wedTime;
	}
	public String getThuTime() {
		return thuTime;
	}
	public void setThuTime(String thuTime) {
		this.thuTime = thuTime;
	}
	public String getFriTime() {
		return friTime;
	}
	public void setFriTime(String friTime) {
		this.friTime = friTime;
	}
	public String getSatTime() {
		return satTime;
	}
	public void setSatTime(String satTime) {
		this.satTime = satTime;
	}
	public String getSunTime() {
		return sunTime;
	}
	public void setSunTime(String sunTime) {
		this.sunTime = sunTime;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
	
	@Override
	public String toString() {
		return "TimeSheetDTO [trackID=" + trackID + ", mapPAID=" + mapPAID
				+ ", pChargeID=" + pChargeID + ", projectID=" + projectID
				+ ", stageID=" + stageID + ", taskID=" + taskID + ", actID="
				+ actID + ", nameCharge=" + nameCharge + ", trackingDate="
				+ trackingDate + ", trackingTime=" + trackingTime
				+ ", trackingNote=" + trackingNote + ", trackingApproved="
				+ trackingApproved + ", totalTime=" + totalTime
				+ ", mapActDesc=" + mapActDesc + ", projectName=" + projectName
				+ ", stageName=" + stageName + ", taskAndActName="
				+ taskAndActName + ", monTime=" + monTime + ", tueTime="
				+ tueTime + ", wedTime=" + wedTime + ", thuTime=" + thuTime
				+ ", friTime=" + friTime + ", satTime=" + satTime
				+ ", sunTime=" + sunTime + "]";
	}

}
