package com.twobScene.web.action;

import com.opensymphony.xwork2.Action;

public class TimeSheetRedirectAction implements Action {

	private String userID;
	private String passID;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassID() {
		return passID;
	}
	public void setPassID(String passID) {
		this.passID = passID;
	}
	
	

}
