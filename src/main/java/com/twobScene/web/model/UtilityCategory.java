package com.twobScene.web.model;

import java.io.Serializable;

public class UtilityCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Long utilityCatID;
	private String utilityCatName;
	private String utilinyCatShortName;
	public Long getUtilityCatID() {
		return utilityCatID;
	}
	public void setUtilityCatID(Long utilityCatID) {
		this.utilityCatID = utilityCatID;
	}
	public String getUtilityCatName() {
		return utilityCatName;
	}
	public void setUtilityCatName(String utilityCatName) {
		this.utilityCatName = utilityCatName;
	}
	public String getUtilinyCatShortName() {
		return utilinyCatShortName;
	}
	public void setUtilinyCatShortName(String utilinyCatShortName) {
		this.utilinyCatShortName = utilinyCatShortName;
	}
	
	

}

