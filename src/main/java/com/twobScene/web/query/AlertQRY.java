package com.twobScene.web.query;

public class AlertQRY {

	
	public String getAlertByProjectIDandCatID(Long projectID, Long alertCatID){
		StringBuffer qry = new StringBuffer("");
		qry.append("SELECT * FROM project_alerts p "); 
		qry.append("WHERE "); 
		qry.append("p.PROJECT_ID = ").append(projectID);
		qry.append(" and p.ALERT_CAT_ID = ").append(alertCatID);
		qry.append(" ORDER BY p.ALERT_ID");   
		System.out.println("******* Query getAlert: " + qry.toString());
		return qry.toString();
		
		
		
	}
	
}
