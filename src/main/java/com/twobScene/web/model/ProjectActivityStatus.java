package com.twobScene.web.model;

import java.io.Serializable;

public class ProjectActivityStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long ptStatusID;
	private String ptStatusName;
	public Long getPtStatusID() {
		return ptStatusID;
	}
	public void setPtStatusID(Long ptStatusID) {
		this.ptStatusID = ptStatusID;
	}
	public String getPtStatusName() {
		return ptStatusName;
	}
	public void setPtStatusName(String ptStatusName) {
		this.ptStatusName = ptStatusName;
	}
	
	

}
