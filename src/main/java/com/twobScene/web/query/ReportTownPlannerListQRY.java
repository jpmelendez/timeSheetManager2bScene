package com.twobScene.web.query;

public class ReportTownPlannerListQRY {
	
	
	public String getTownPlannerReport(){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("select * from (  ");
		qry.append("SELECT a.project_id, b.alert_cat_id, a.council, a.project_name,   ");
		qry.append("a.council_name, a.council_email,   ");
		qry.append("a.council_ph, ifnull(a.council_note, '') as council_note,  ");
		qry.append("ifnull(b.ALERT_ESTATUS, '0') as alert_status,  ");
		qry.append("ifnull(c.received_date, '') as received_date,  ");
		qry.append("d.PCHARGE, a.COUNCIL_REFERENCE  ");
		qry.append("FROM  ");
		qry.append("projects a   ");
		qry.append("left outer join (  ");
		qry.append("SELECT x.* FROM project_alerts x where x.alert_cat_id = 9   ");
		qry.append(") b  ");
		qry.append("on a.project_id = b.project_id  ");
		qry.append("left outer join (    ");
		qry.append("SELECT aa.PROJECT_ID, GROUP_CONCAT(concat(bb.ALERT_CAT_NAME,': ',DATE_FORMAT(aa.RECEIVED_DATE,'%d/%m/%Y')) SEPARATOR ', ') as received_date  "); 
		qry.append("from project_alerts aa  ");
		qry.append("left outer join c_alert_category bb  ");
		qry.append("on aa.ALERT_CAT_ID = bb.ALERT_CAT_ID  ");
		qry.append("where aa.ALERT_CAT_ID in (1, 2, 3, 4, 5)  ");
		qry.append("group by aa.PROJECT_ID  ");
		qry.append("order by aa.PROJECT_ID) c   ");
		qry.append("on a.project_id = c.project_id  ");
		qry.append("left outer join (    ");
		qry.append("SELECT z.PROJECT_ID, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE    ");
		qry.append("from map_project_person z  ");
		qry.append("left outer join c_person_charge r  ");
		qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID  ");
		qry.append("group by z.PROJECT_ID    ");
		qry.append("order by z.PROJECT_ID) d  ");
		qry.append("on a.project_id = d.project_id  ");
		qry.append("order by a.project_id ) xx  ");
		qry.append("where   ");
		qry.append("xx.alert_status = 0  ");
		qry.append("and xx.project_id NOT IN ( 1 )  ");
		
		return qry.toString();
		
	}

}
