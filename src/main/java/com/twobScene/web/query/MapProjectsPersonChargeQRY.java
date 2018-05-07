package com.twobScene.web.query;

public class MapProjectsPersonChargeQRY {
	
public String getPersonChargePerProject(Long projectID){
		
		StringBuffer qry = new StringBuffer("");
		qry.append("select mpp.PROJECT_ID, mpp.P_CHARGE_ID, ");
		qry.append("CONCAT(cp.FIRST_NAME, ' ', cp.LAST_NAME) AS STAFFINCHARGE, cp.INITIALS, ");
		qry.append("cp.EMAIL, p.PROJECT_NAME ");
		qry.append("FROM map_project_person mpp ");
		qry.append("join c_person_charge cp on mpp.P_CHARGE_ID = cp.P_CHARGE_ID ");
		qry.append("join projects p on mpp.PROJECT_ID = p.PROJECT_ID ");				
		qry.append("where mpp.PROJECT_ID = ").append(projectID);
		qry.append(" order by mpp.P_CHARGE_ID");
		
		return qry.toString();
		
}

}
