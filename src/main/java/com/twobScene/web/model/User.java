package com.twobScene.web.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1172485768645699042L;
	
	private Long pChargeID;
    private String username;
    private String pwd;
    private String credentials;
    private String userStatus;
    private Date createdDate;
    
    
	
	public Long getpChargeID() {
		return pChargeID;
	}
	public void setpChargeID(Long pChargeID) {
		this.pChargeID = pChargeID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
	
	@Override
	public String toString() {
		return "User [pChargeID=" + pChargeID + ", username=" + username
				+ ", pwd=" + pwd + ", credentials=" + credentials
				+ ", userStatus=" + userStatus + ", createdDate=" + createdDate
				+ "]";
	}
    
	 
	
}
