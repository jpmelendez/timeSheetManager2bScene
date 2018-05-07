package com.twobScene.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class TimeTracking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1963681348928222720L;
	
	private Long trackID;
	private Long mapPAID;
	private Long pChargeID;
	private Date trackingDate;
	private Time trackingTime;
	private Double trackTimeDec;
	private Integer trackingTimeMin;
	private String trackingNote;
	private Boolean trackingApproved;
	private Date createdDate;
	private String createdUser;
	
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
	public Date getTrackingDate() {
		return trackingDate;
	}
	public void setTrackingDate(Date trackingDate) {
		this.trackingDate = trackingDate;
	}
	public Time getTrackingTime() {
		return trackingTime;
	}
	public void setTrackingTime(Time trackingTime) {
		this.trackingTime = trackingTime;
	}
	
	public Double getTrackTimeDec() {
		return trackTimeDec;
	}
	public void setTrackTimeDec(Double trackTimeDec) {
		this.trackTimeDec = trackTimeDec;
	}
	public Integer getTrackingTimeMin() {
		return trackingTimeMin;
	}
	public void setTrackingTimeMin(Integer trackingTimeMin) {
		this.trackingTimeMin = trackingTimeMin;
	}
	public String getTrackingNote() {
		return trackingNote;
	}
	public void setTrackingNote(String trackingNote) {
		this.trackingNote = trackingNote;
	}
	public Boolean getTrackingApproved() {
		return trackingApproved;
	}
	public void setTrackingApproved(Boolean trackingApproved) {
		this.trackingApproved = trackingApproved;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
	@Override
	public String toString() {
		return "TimeTracking [trackID=" + trackID + ", mapPAID=" + mapPAID
				+ ", pChargeID=" + pChargeID + ", trackingDate=" + trackingDate
				+ ", trackingTime=" + trackingTime + ", trackTimeDec="
				+ trackTimeDec + ", trackingTimeMin=" + trackingTimeMin
				+ ", trackingNote=" + trackingNote + ", trackingApproved="
				+ trackingApproved + ", createdDate=" + createdDate
				+ ", createdUser=" + createdUser + "]";
	}
	
	
	

}
