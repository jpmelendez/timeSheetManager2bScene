package com.twobScene.web.query;

import java.util.Date;

public class ActivityHomeQRY {
	
	public String getStartTodayActivities(String sysdate){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("SELECT "); 
		qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
		qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
		qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
		qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
		qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
		qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
		qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
		qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
		qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
		qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
		qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
		qry.append("a.ALERT_TRIGGER, ");  
		qry.append("CASE a.ALERT_TRIGGER ");  
		qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
		qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
		qry.append("    ELSE null ");  
		qry.append("END as 'FLAG_ALERT', "); 
		qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
		qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
		qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
		qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
		qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
		qry.append("FROM "); 
		qry.append("map_projects_activity mpa "); 
		qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
		qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
		qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
		qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
		qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
		qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
		qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
		qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
		qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
		qry.append("WHERE "); 
		qry.append("mpa.START_DATE = '").append(sysdate);
		qry.append("' ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
		
		System.out.println("**** QUERY getStartTodayActivities: " + qry.toString());
	
		return qry.toString();
	}
	
public String getDueTodayActivities(String sysdate){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("SELECT "); 
		qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
		qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
		qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
		qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
		qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
		qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
		qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
		qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
		qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
		qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
		qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
		qry.append("a.ALERT_TRIGGER, ");  
		qry.append("CASE a.ALERT_TRIGGER ");  
		qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
		qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
		qry.append("    ELSE null ");  
		qry.append("END as 'FLAG_ALERT', "); 
		qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
		qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
		qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
		qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
		qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
		qry.append("FROM "); 
		qry.append("map_projects_activity mpa "); 
		qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
		qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
		qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
		qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
		qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
		qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
		qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
		qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
		qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
		qry.append("WHERE "); 
		qry.append("mpa.DUE_DATE = '").append(sysdate);
		qry.append("' ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
		
		System.out.println("**** QUERY getDueTodayActivities: " + qry.toString());
	
		return qry.toString();
	}

public String getStartThisWeekActivities(String firstDayWeek,
		String lastDayWeek){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("(mpa.START_DATE BETWEEN '").append(firstDayWeek);
	qry.append("' AND '").append(lastDayWeek);
	qry.append("') ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getStartThisWeekActivities: " + qry.toString());

	return qry.toString();
}

public String getDueThisWeekActivities(String firstDayWeek,
		String lastDayWeek){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("(mpa.DUE_DATE BETWEEN '").append(firstDayWeek);
	qry.append("' AND '").append(lastDayWeek);
	qry.append("') ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getDueThisWeekActivities: " + qry.toString());

	return qry.toString();
}

public String getStartNextWeekActivities(String firstDayNextWeek,
		String lastDayNextWeek){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("(mpa.START_DATE BETWEEN '").append(firstDayNextWeek);
	qry.append("' AND '").append(lastDayNextWeek);
	qry.append("') ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getStartNextWeekActivities: " + qry.toString());

	return qry.toString();
}

public String getDueNextWeekActivities(String firstDayNextWeek,
		String lastDayNextWeek){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("(mpa.DUE_DATE BETWEEN '").append(firstDayNextWeek);
	qry.append("' AND '").append(lastDayNextWeek);
	qry.append("') ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getDueNextWeekActivities: " + qry.toString());

	return qry.toString();
}

public String getStartMonthActivities(String firstDayMonth,
		String lastDayMonth){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("(mpa.START_DATE BETWEEN '").append(firstDayMonth);
	qry.append("' AND '").append(lastDayMonth);
	qry.append("') ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getStartMonthActivities: " + qry.toString());

	return qry.toString();
}

public String getDueMonthActivities(String firstDayMonth,
		String lastDayMonth){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("(mpa.DUE_DATE BETWEEN '").append(firstDayMonth);
	qry.append("' AND '").append(lastDayMonth);
	qry.append("') ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getDueMonthActivities: " + qry.toString());

	return qry.toString();
}

public String getUnscheduledActivities(){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select * from "); 
	qry.append("( "); 
	qry.append("SELECT ");  
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("mpa.START_DATE is null ");  
	qry.append("OR mpa.DUE_DATE is null "); 
	qry.append("OR mpa.P_CHARGE_ID is null "); 
	qry.append("ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME "); 
	qry.append(") x "); 
	qry.append("WHERE ");  
	qry.append("x.ACTIVITY_STATUS not in  ('Cancelled', 'Completed') ");  
	qry.append("or x.ACTIVITY_STATUS is null "); 
	
	System.out.println("**** QUERY getUnscheduledActivities: " + qry.toString());

	return qry.toString();
}

public String getOverdueActivities(){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select * from "); 
	qry.append("( "); 
	qry.append("SELECT ");  
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("mpa.STATUS_TASK_ID not in  ('5', '8') "); 
	qry.append("or mpa.STATUS_TASK_ID is null "); 
	qry.append("ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME "); 
	qry.append(") x "); 
	qry.append("WHERE ");  
	qry.append("x.ACT_OVERDUE > 0 "); 
	
	System.out.println("**** QUERY getOverdueActivities: " + qry.toString());

	return qry.toString();
}

public String getAlertedActivities(){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select * from "); 
	qry.append("( "); 
	qry.append("SELECT ");  
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("a.ALERT_ACT = 1 "); 
	qry.append("and mpa.START_DATE is not null "); 
	qry.append("and mpa.DUE_DATE is not null "); 
	qry.append("ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");  
	qry.append(") x "); 
	qry.append("WHERE ");  
	qry.append("x.ACTIVITY_STATUS not in  ('Cancelled', 'Completed') ");  
	qry.append("or x.ACTIVITY_STATUS is null ");
	
	System.out.println("**** QUERY getAlertedActivities(): " + qry.toString());

	return qry.toString();
}

public String getActivityById(Long mapIdAct){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT "); 
	qry.append("mpa.MAP_PA_ID, ts.PT_STATUS_NAME as 'ACTIVITY_STATUS', ");  
	qry.append("mpa.DESC_MAP_PA as 'MAP_ACT_DESC', mpa.PRIORITY_MAP_PA, ");  
	qry.append("mpa.PROGRESS, DATE_FORMAT(mpa.START_DATE,'%d/%m/%Y') as 'ACT_STARTDATE', ");  
	qry.append("DATE_FORMAT(mpa.DUE_DATE,'%d/%m/%Y') as 'ACT_DUEDATE', DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) as 'ACT_OVERDUE', "); 
	qry.append("mpa.ESTIMATED_TIME_HRS as 'ACT_ESTIMATED_HRS', CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS ACT_STAFF_NAME, ");  
	qry.append("pc.COLOR_CODE as 'ACT_COLOR_STAFF', pc.INITIALS as 'ACT_STAFF_INITIALS', "); 
	qry.append("mpa.STAGE_ID, s.STAGE_NAME, "); 
	qry.append("mpa.TASK_ID, t.TASK_NAME, ");  
	qry.append("t.TASK_DESCRIPTON, mpa.ACT_ID, ");  
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, ");  
	qry.append("a.ALERT_ACT, a.ALERT_DAYS, ");  
	qry.append("a.ALERT_TRIGGER, ");  
	qry.append("CASE a.ALERT_TRIGGER ");  
	qry.append("    WHEN '1' THEN DATEDIFF(CURRENT_DATE(), mpa.START_DATE) "); 
	qry.append("    WHEN '2' THEN DATEDIFF(CURRENT_DATE(), mpa.DUE_DATE) "); 
	qry.append("    ELSE null ");  
	qry.append("END as 'FLAG_ALERT', "); 
	qry.append("mpa.PROJECT_ID, p.PROJECT_NAME, ");  
	qry.append("ps.STATUS_NAME, CONCAT(p.NUMBER_OF_EA, '-', cat.EXTENTION) as 'PROJECTCAT', "); 
	qry.append("p.BUDGET, CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as 'PROJECTTIME', ");  
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as 'START_DATE', DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as 'DUE_DATE', "); 
	qry.append("DATEDIFF(CURRENT_DATE(), p.DUE_DATE) as 'PROJECT_OVERDUE', CONCAT(c.CLIENT_NAME, ' <', c.CLIENT_EMAIL, '>') as 'CLIENT' "); 
	qry.append("FROM "); 
	qry.append("map_projects_activity mpa "); 
	qry.append("LEFT OUTER JOIN projects p on mpa.PROJECT_ID = p.PROJECT_ID "); 
	qry.append("LEFT OUTER JOIN c_project_stages s on mpa.STAGE_ID = s.STAGE_ID "); 
	qry.append("LEFT OUTER JOIN c_tasks t on mpa.TASK_ID = t.TASK_ID "); 
	qry.append("LEFT OUTER JOIN c_activities a on mpa.ACT_ID = a.ACT_ID "); 
	qry.append("LEFT OUTER JOIN c_person_charge pc on mpa.P_CHARGE_ID = pc.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN project_status ps on p.STATE = ps.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN project_tasks_status ts on mpa.STATUS_TASK_ID = ts.PT_STATUS_ID "); 
	qry.append("LEFT OUTER JOIN clients c on p.CLIENT_ID = c.CLIENT_ID "); 
	qry.append("LEFT OUTER JOIN c_categories cat on p.CATEGORY_ID = cat.CATEGORY_ID "); 
	qry.append("WHERE "); 
	qry.append("mpa.MAP_PA_ID = ").append(mapIdAct);
	qry.append(" ORDER BY mpa.START_DATE, mpa.PRIORITY_MAP_PA, ts.PT_STATUS_NAME ");
	
	System.out.println("**** QUERY getActivityById: " + qry.toString());

	return qry.toString();
}

public String getNewAlertsInbox(String sysdate, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT a.PROJECT_ID, b.PCHARGE, b.INITIALS, b.COLOR_CODE, c.PROJECT_NAME, ");
	qry.append("c.COUNCIL_NAME, c.COUNCIL_PH, c.COUNCIL_EMAIL, ");
	qry.append("a.ALERT_CAT_ID, b.ALERT_CAT_NAME, ");
	qry.append("a.ALERT_DATE, a.TIME_FOR_ALERT ");
	qry.append("FROM project_alerts a ");
	qry.append("left outer join c_alert_category b ");
	qry.append("on a.ALERT_CAT_ID = b.ALERT_CAT_ID ");
	qry.append("left outer join (   ");
	qry.append("SELECT z.PROJECT_ID, r.INITIALS, r.COLOR_CODE, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z   ");
	qry.append("left outer join c_person_charge r  ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID   ");
	qry.append("group by z.PROJECT_ID   ");
	qry.append("order by z.PROJECT_ID) b  ");
	qry.append("on a.project_id = b.project_id  ");
	qry.append("left outer join projects c ");
	qry.append("on a.project_id = c.project_id  ");
	qry.append("where  ");
	qry.append("a.ALERT_ESTATUS = 0 ");
	if (!userCredentials.contains("Manager")) {
		qry.append(" and a.PROJECT_ID in ").append(projectsByUser); 
	}
	qry.append(" and c.state in ('1','2','3','6') ");
	qry.append(" and a.ALERT_DATE < '").append(sysdate);
	qry.append("' order by a.ALERT_DATE ");
	
	System.out.println("*********** Alert INBOX QRY: " + qry.toString());

	return qry.toString();
}

public String getNewAlertsToday(String sysdate, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT a.PROJECT_ID, b.PCHARGE, b.INITIALS, b.COLOR_CODE, c.PROJECT_NAME, ");
	qry.append("c.COUNCIL_NAME, c.COUNCIL_PH, c.COUNCIL_EMAIL, ");
	qry.append("a.ALERT_CAT_ID, b.ALERT_CAT_NAME, ");
	qry.append("a.ALERT_DATE, a.TIME_FOR_ALERT ");
	qry.append("FROM project_alerts a ");
	qry.append("left outer join c_alert_category b ");
	qry.append("on a.ALERT_CAT_ID = b.ALERT_CAT_ID ");
	qry.append("left outer join (   ");
	qry.append("SELECT z.PROJECT_ID, r.INITIALS, r.COLOR_CODE, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z   ");
	qry.append("left outer join c_person_charge r  ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID   ");
	qry.append("group by z.PROJECT_ID   ");
	qry.append("order by z.PROJECT_ID) b  ");
	qry.append("on a.project_id = b.project_id  ");
	qry.append("left outer join projects c ");
	qry.append("on a.project_id = c.project_id  ");
	qry.append("where  ");
	qry.append("a.ALERT_ESTATUS = 0 ");
	if (!userCredentials.contains("Manager")) {
		qry.append(" and a.PROJECT_ID in ").append(projectsByUser); 
	}
	qry.append(" and c.state in ('1','2','3','6') ");
	qry.append(" and a.ALERT_DATE = '").append(sysdate);
	qry.append("' order by a.ALERT_DATE ");
	
	System.out.println("*********** Alert INBOX QRY: " + qry.toString());

	return qry.toString();
}

public String getNewAlertsThisWeek(String firstDayWeek,
		String lastDayWeek, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT a.PROJECT_ID, b.PCHARGE, b.INITIALS, b.COLOR_CODE, c.PROJECT_NAME, ");
	qry.append("c.COUNCIL_NAME, c.COUNCIL_PH, c.COUNCIL_EMAIL, ");
	qry.append("a.ALERT_CAT_ID, b.ALERT_CAT_NAME, ");
	qry.append("a.ALERT_DATE, a.TIME_FOR_ALERT ");
	qry.append("FROM project_alerts a ");
	qry.append("left outer join c_alert_category b ");
	qry.append("on a.ALERT_CAT_ID = b.ALERT_CAT_ID ");
	qry.append("left outer join (   ");
	qry.append("SELECT z.PROJECT_ID, r.INITIALS, r.COLOR_CODE, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z   ");
	qry.append("left outer join c_person_charge r  ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID   ");
	qry.append("group by z.PROJECT_ID   ");
	qry.append("order by z.PROJECT_ID) b  ");
	qry.append("on a.project_id = b.project_id  ");
	qry.append("left outer join projects c ");
	qry.append("on a.project_id = c.project_id  ");
	qry.append("where  ");
	qry.append("a.ALERT_ESTATUS = 0 ");
	if (!userCredentials.contains("Manager")) {
		qry.append(" and a.PROJECT_ID in ").append(projectsByUser); 
	}
	qry.append(" and c.state in ('1','2','3','6') ");
	qry.append(" and a.ALERT_DATE BETWEEN '").append(firstDayWeek);
	qry.append("' AND '").append(lastDayWeek);
	qry.append("' order by a.ALERT_DATE ");
	
	System.out.println("*********** Alert INBOX QRY: " + qry.toString());

	return qry.toString();
}

public String getNewAlertsNextWeek(String firstDayNextWeek,
		String lastDayNextWeek, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT a.PROJECT_ID, b.PCHARGE, b.INITIALS, b.COLOR_CODE, c.PROJECT_NAME, ");
	qry.append("c.COUNCIL_NAME, c.COUNCIL_PH, c.COUNCIL_EMAIL, ");
	qry.append("a.ALERT_CAT_ID, b.ALERT_CAT_NAME, ");
	qry.append("a.ALERT_DATE, a.TIME_FOR_ALERT ");
	qry.append("FROM project_alerts a ");
	qry.append("left outer join c_alert_category b ");
	qry.append("on a.ALERT_CAT_ID = b.ALERT_CAT_ID ");
	qry.append("left outer join (   ");
	qry.append("SELECT z.PROJECT_ID, r.INITIALS, r.COLOR_CODE, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z   ");
	qry.append("left outer join c_person_charge r  ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID   ");
	qry.append("group by z.PROJECT_ID   ");
	qry.append("order by z.PROJECT_ID) b  ");
	qry.append("on a.project_id = b.project_id  ");
	qry.append("left outer join projects c ");
	qry.append("on a.project_id = c.project_id  ");
	qry.append("where  ");
	qry.append("a.ALERT_ESTATUS = 0 ");
	if (!userCredentials.contains("Manager")) {
		qry.append(" and a.PROJECT_ID in ").append(projectsByUser); 
	}
	qry.append(" and c.state in ('1','2','3','6') ");
	qry.append(" and a.ALERT_DATE BETWEEN '").append(firstDayNextWeek);
	qry.append("' AND '").append(lastDayNextWeek);
	qry.append("' order by a.ALERT_DATE ");
	
	System.out.println("*********** Alert INBOX QRY: " + qry.toString());

	return qry.toString();
}

public String getNewAlertsThisMonth(String firstDayMonth,
		String lastDayMonth, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT a.PROJECT_ID, b.PCHARGE, b.INITIALS, b.COLOR_CODE, c.PROJECT_NAME, ");
	qry.append("c.COUNCIL_NAME, c.COUNCIL_PH, c.COUNCIL_EMAIL, ");
	qry.append("a.ALERT_CAT_ID, b.ALERT_CAT_NAME, ");
	qry.append("a.ALERT_DATE, a.TIME_FOR_ALERT ");
	qry.append("FROM project_alerts a ");
	qry.append("left outer join c_alert_category b ");
	qry.append("on a.ALERT_CAT_ID = b.ALERT_CAT_ID ");
	qry.append("left outer join (   ");
	qry.append("SELECT z.PROJECT_ID, r.INITIALS, r.COLOR_CODE, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z   ");
	qry.append("left outer join c_person_charge r  ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID   ");
	qry.append("group by z.PROJECT_ID   ");
	qry.append("order by z.PROJECT_ID) b  ");
	qry.append("on a.project_id = b.project_id  ");
	qry.append("left outer join projects c ");
	qry.append("on a.project_id = c.project_id  ");
	qry.append("where  ");
	qry.append("a.ALERT_ESTATUS = 0 ");
	if (!userCredentials.contains("Manager")) {
		qry.append(" and a.PROJECT_ID in ").append(projectsByUser); 
	}
	qry.append(" and c.state in ('1','2','3','6') ");
	qry.append(" and a.ALERT_DATE BETWEEN '").append(firstDayMonth);
	qry.append("' AND '").append(lastDayMonth);
	qry.append("' order by a.ALERT_DATE ");
	
	System.out.println("*********** Alert INBOX QRY: " + qry.toString());

	return qry.toString();
}

public String getNewAlertsInactivity(String inactivityDate, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT a.PROJECT_ID, b.PCHARGE, b.INITIALS, b.COLOR_CODE, a.PROJECT_NAME, ");
	qry.append("a.COUNCIL_NAME, a.COUNCIL_PH, a.COUNCIL_EMAIL, ");
	qry.append("'8' as 'ALERT_CAT_ID', 'Project Inactivity' as 'ALERT_CAT_NAME', ");
	qry.append("a.INSERT_TIME, DATEDIFF(CURRENT_DATE(), a.INSERT_TIME) as 'TIME_FOR_ALERT' ");
	qry.append("FROM projects a ");
	qry.append("left outer join ( ");
	qry.append("SELECT z.PROJECT_ID, r.INITIALS, r.COLOR_CODE, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE ");
	qry.append("from map_project_person z ");
	qry.append("left outer join c_person_charge r ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID ");
	qry.append("group by z.PROJECT_ID ");
	qry.append("order by z.PROJECT_ID) b ");
	qry.append("on a.project_id = b.project_id ");
	qry.append("where ");
	qry.append("a.state in ('1','2','3','6') ");
	if (!userCredentials.contains("Manager")) {
		qry.append(" and a.PROJECT_ID in ").append(projectsByUser); 
	}
	qry.append("and a.PHASE_ID = '2' ");
	qry.append("and a.INSERT_TIME < '").append(inactivityDate);
	qry.append("' and a.PROJECT_ID not in (SELECT y.PROJECT_ID FROM project_alerts y)");
	qry.append(" order by a.INSERT_TIME ");
	
	System.out.println("*********** Alert INBOX QRY: " + qry.toString());

	return qry.toString();
}

}
