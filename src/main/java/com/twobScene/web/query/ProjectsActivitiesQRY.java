package com.twobScene.web.query;

public class ProjectsActivitiesQRY {
	
public String getActivityCheckedByProjectID(Long projectID){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("select mp.STAGE_ID, mp.TASK_ID, mp.ACT_ID, ca.act_name, ca.ACT_PRIORITY as 'catalogOrder', mp.PA_CHECKED as 'checked' ");
		qry.append("FROM ");
		qry.append("map_projects_activity mp ");
		qry.append("join "); 
		qry.append("c_activities ca ");
		qry.append("on ca.ACT_ID = mp.ACT_ID ");
		qry.append("where ");
		qry.append("mp.PROJECT_ID = ").append(projectID);
		qry.append(" union all ");
		qry.append("select x.stage_id, x.task_id, x.act_id, x.act_name, x.catalogOrder, '0' as 'checked' ");
		qry.append("from ");
		qry.append("(select a.STAGE_ID, a.TASK_ID, a.ACT_ID, a.act_name, a.ACT_PRIORITY as 'catalogOrder' ");
		qry.append("FROM "); 
		qry.append("c_activities a ");
		qry.append("order by a.STAGE_ID, a.TASK_ID, a.ACT_ID) x ");
		qry.append("where ( x.stage_id, x.task_id, x.act_id, x.act_name, x.catalogOrder) not in "); 
		qry.append("(select mp.STAGE_ID, mp.TASK_ID, mp.ACT_ID, ca.act_name, ca.ACT_PRIORITY as 'catalogOrder' ");
		qry.append("FROM ");
		qry.append("map_projects_activity mp ");
		qry.append("join "); 
		qry.append("c_activities ca ");
		qry.append("on ca.ACT_ID = mp.ACT_ID ");
		qry.append("where "); 
		qry.append("mp.PROJECT_ID = ").append(projectID);
		qry.append(" order by mp.STAGE_ID, mp.TASK_ID, mp.ACT_ID) ");
		qry.append("order by stage_id, task_id, catalogOrder ");
		
		return qry.toString();
		
	}

public String searchStageBYProjectID(Long projectID){
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select distinct s.STAGE_ID, s.STAGE_NAME, s.STAGE_PRIORITY ");
	qry.append("FROM ");
	qry.append("map_projects_activity mp ");
	qry.append("join ");
	qry.append("c_project_stages s ");
	qry.append("on mp.STAGE_ID = s.STAGE_ID ");
	qry.append("where ");
	qry.append("mp.PROJECT_ID = ").append(projectID);
	qry.append(" order by s.STAGE_PRIORITY ");
	
	return qry.toString();
}

public String searchTaskBYProjectID(Long projectID){
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select distinct t.TASK_ID, t.STAGE_ID, t.TASK_NAME, t.TASK_PRIORITY ");
	qry.append("FROM ");
	qry.append("map_projects_activity mp ");
	qry.append("join ");
	qry.append("c_tasks t ");
	qry.append("on mp.STAGE_ID = t.STAGE_ID ");
	qry.append("and mp.TASK_ID = t.TASK_ID ");
	qry.append("where "); 
	qry.append("mp.PROJECT_ID = ").append(projectID);
	qry.append(" order by t.STAGE_ID, t.TASK_PRIORITY ");
	
	return qry.toString();
}

public String searchTaskBYProjectIDandStageID(Long projectID, Long stageID){
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select distinct t.TASK_ID, t.STAGE_ID, t.TASK_NAME, t.TASK_PRIORITY ");
	qry.append("FROM ");
	qry.append("map_projects_activity mp ");
	qry.append("join ");
	qry.append("c_tasks t ");
	qry.append("on mp.STAGE_ID = t.STAGE_ID ");
	qry.append("and mp.TASK_ID = t.TASK_ID ");
	qry.append("where "); 
	qry.append("mp.PROJECT_ID = ").append(projectID);
	qry.append(" and mp.STAGE_ID = ").append(stageID);
	qry.append(" order by t.STAGE_ID, t.TASK_PRIORITY ");
	
	return qry.toString();
}

public String searchActivityBYProjectID(Long projectID){
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select distinct mp.MAP_PA_ID, mp.PROJECT_ID, a.ACT_ID, a.TASK_ID, a.STAGE_ID, ");
	qry.append("a.ACT_NAME, a.ACT_DESCRIPTON, mp.DESC_MAP_PA, mp.PROGRESS, a.ACT_PRIORITY ");
	qry.append("FROM "); 
	qry.append("map_projects_activity mp ");
	qry.append("join ");
	qry.append("c_activities a ");
	qry.append("on mp.STAGE_ID = a.STAGE_ID ");
	qry.append("and mp.TASK_ID = a.TASK_ID ");
	qry.append("and mp.ACT_ID = a.ACT_ID ");
	qry.append("where ");
	qry.append("mp.PROJECT_ID = ").append(projectID);
	qry.append(" order by a.STAGE_ID, a.TASK_ID, a.ACT_PRIORITY ");
	
	return qry.toString();
}

public String getProjectActDetails(Long selectedMapActID){
	StringBuffer qry = new StringBuffer("");
	qry.append("select distinct mp.MAP_PA_ID, s.PT_STATUS_NAME, mp.PRIORITY_MAP_PA, mp.DESC_MAP_PA, ");
	qry.append("DATE_FORMAT(mp.START_DATE,'%d/%m/%Y') as START_DATE, DATE_FORMAT(mp.DUE_DATE,'%d/%m/%Y') as DUE_DATE, CONCAT(mp.ESTIMATED_TIME_HRS, ' Hrs.') as ESTIMATED_TIME, mp.PROGRESS, ");
	qry.append("CONCAT(ch.INITIALS, ' - ', ch.FIRST_NAME, ' ', ch.LAST_NAME) AS STAFFINCHARGE ");
	qry.append("FROM ");
	qry.append("map_projects_activity mp ");
	qry.append("left outer join project_tasks_status s on mp.STATUS_TASK_ID = s.PT_STATUS_ID ");
	qry.append("left outer join C_PERSON_CHARGE ch on mp.P_CHARGE_ID = ch.P_CHARGE_ID ");
	qry.append("where mp.MAP_PA_ID = ").append(selectedMapActID);
	qry.append(" order by mp.MAP_PA_ID ");
	
	
	return qry.toString();
	
}

public String getProjectActStaff(Long selectedMapActID){
	StringBuffer qry = new StringBuffer("");
	qry.append("select cp.INITIALS, cp.FIRST_NAME, cp.LAST_NAME ");
	qry.append("FROM ");
	qry.append("map_staff_activity ms ");
	qry.append("join c_person_charge cp on ms.P_CHARGE_ID = cp.P_CHARGE_ID ");	
	qry.append("where ms.MAP_PA_ID = ").append(selectedMapActID);
	qry.append(" order by cp.INITIALS ");
	
	
	return qry.toString();
	
}

public String searchActByProjectStageTask(Long projectID, Long stageID, Long taskID){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select distinct mp.MAP_PA_ID, a.ACT_ID, a.STAGE_ID, ");
	qry.append("a.TASK_ID, a.ACT_NAME, a.ACT_PRIORITY ");
	qry.append("FROM map_projects_activity mp ");
	qry.append("join c_activities a on mp.STAGE_ID = a.STAGE_ID and mp.TASK_ID = a.TASK_ID and mp.ACT_ID = a.ACT_ID ");
	qry.append("where "); 
	qry.append("mp.PROJECT_ID = ").append(projectID);
	qry.append(" and mp.STAGE_ID = ").append(stageID);
	qry.append(" and mp.TASK_ID = ").append(taskID);
	qry.append(" order by a.ACT_PRIORITY ");
	
	
	return qry.toString();
	
	
	
}

public String searchActByProjectStage(Long projectID, Long stageID){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("select distinct mp.MAP_PA_ID, a.ACT_ID, a.STAGE_ID, ");
	qry.append("a.TASK_ID, a.ACT_NAME, a.ACT_PRIORITY ");
	qry.append("FROM map_projects_activity mp ");
	qry.append("join c_activities a on mp.STAGE_ID = a.STAGE_ID and mp.TASK_ID = a.TASK_ID and mp.ACT_ID = a.ACT_ID ");
	qry.append("where "); 
	qry.append("mp.PROJECT_ID = ").append(projectID);
	qry.append(" and mp.STAGE_ID = ").append(stageID);
	//qry.append(" and mp.TASK_ID = ").append(taskID);
	qry.append(" order by a.ACT_PRIORITY ");
	
	
	return qry.toString();
	
	
	
}

}
