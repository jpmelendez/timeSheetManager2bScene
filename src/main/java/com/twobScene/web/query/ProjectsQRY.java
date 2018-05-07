package com.twobScene.web.query;

public class ProjectsQRY {
	
	
public String getAllProjects(char phaseID, String module, String projectsByUser, String userCredentials){
		
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT xx.project_id, xx.stages, xx.PCHARGE, xx.project_name, 	");
	qry.append("xx.cdtp_finish_job, xx.cdtp_time_project, xx.cdtp_TIME_SPENT, (0) as available_time,	");
	qry.append("(if(xx.cdtp_TIME_SPENT>0,xx.cdtp_TIME_SPENT-xx.cdtp_time_project,xx.cdtp_time_project*-1)) as cdtp_time_overdue, xx.notes, ");
	qry.append("xx.RFI_CONDITION, xx.PHOTO_SITE, xx.category, xx.PHASE_ID, xx.PHASE_NAME, xx.CLIENT_NAME,	");
	qry.append("xx.STATE, xx.STATUS_NAME, xx.STATUS_COLOR_CODE, xx.DESCRIPTION, xx.wd_finish_job, xx.wd_time_project,	");
	qry.append("xx.wd_TIME_SPENT, (if(xx.wd_TIME_SPENT>0,xx.wd_TIME_SPENT-xx.wd_time_project,xx.wd_time_project*-1)) as wd_time_overdue,	");
	qry.append("xx.vcat_finish_job, xx.vcat_time_project, xx.vcat_TIME_SPENT, 	");
	qry.append("(if(xx.vcat_TIME_SPENT>0,xx.vcat_TIME_SPENT-xx.vcat_time_project,xx.vcat_time_project*-1)) as vcat_time_overdue	");
	qry.append("from (	");
	qry.append("select f.project_id, g.stages, h.PCHARGE, f.project_name,   	");
	qry.append("IFNULL(i.finish_job,0) as cdtp_finish_job, 	");
	qry.append("IFNULL(i.time_project,0) as cdtp_time_project, 	");
	qry.append("IFNULL(i.TIME_SPENT,0) as cdtp_TIME_SPENT,  	");
	qry.append("replace(j.notes,',','; ') as notes,   	");
	qry.append("f.RFI_CONDITION, f.PHOTO_SITE,   	");
	qry.append("concat(CAST(f.NUMBER_OF_EA AS CHAR), ' ', k.EXTENTION) as category, 	");
	qry.append("f.PHASE_ID, m.PHASE_NAME, o.CLIENT_NAME, f.STATE, p.STATUS_NAME, p.STATUS_COLOR_CODE, f.DESCRIPTION, 	");
	qry.append("IFNULL(r.wd_finish_job,0) as wd_finish_job, IFNULL(r.wd_time_project, 0) as wd_time_project, 	");
	qry.append("IFNULL(r.wd_TIME_SPENT, 0) as wd_TIME_SPENT, 	");
	qry.append("IFNULL(s.vcat_finish_job, 0) as vcat_finish_job, 	");
	qry.append("IFNULL(s.vcat_time_project,0) as vcat_time_project, 	");
	qry.append("IFNULL(s.vcat_TIME_SPENT, 0) as vcat_TIME_SPENT	");
	qry.append("from projects f  	");
	qry.append("left outer join c_categories k  	");
	qry.append("on f.CATEGORY_ID = k.CATEGORY_ID  	");
	qry.append("left outer join c_phases m  	");
	qry.append("on f.PHASE_ID = m.PHASE_ID  	");
	qry.append("left outer join clients o  	");
	qry.append("on f.CLIENT_ID = o.CLIENT_ID  	");
	qry.append("left outer join project_status p  	");
	qry.append("on f.STATE = p.STATUS_ID  	");
	qry.append("left outer join (  	");
	qry.append("SELECT aa.PROJECT_ID,  	");
	qry.append("GROUP_CONCAT(IFNULL(cc.TASK_NAME, '') SEPARATOR '/') as stages  	");
	qry.append("from (  ");
	qry.append("select distinct x.PROJECT_ID, x.TASK_ID  ");
	qry.append("from  map_projects_activity x  ");
	qry.append("where x.ACTIVITY_DONE = 0  ");
	qry.append("group by x.PROJECT_ID, x.TASK_ID  ");
	qry.append("order by x.PROJECT_ID, x.STAGE_ID, x.TASK_ID, x.ACT_ID) aa  ");
	qry.append("left outer join c_tasks cc  on aa.TASK_ID = cc.TASK_ID  ");
	qry.append("group by aa.PROJECT_ID  order by aa.PROJECT_ID) g  	");
	qry.append("on f.project_id = g.project_id  ");
	qry.append("left outer join (  	");
	qry.append("SELECT z.PROJECT_ID, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z  	");
	qry.append("left outer join c_person_charge r  on z.P_CHARGE_ID = r.P_CHARGE_ID  ");
	qry.append("group by z.PROJECT_ID  order by z.PROJECT_ID) h ");
	qry.append("on f.project_id = h.project_id  ");
	qry.append("left outer join (  	");
	qry.append("SELECT a.PROJECT_ID, IFNULL(a.PROJECT_TIME,0) as finish_job,  	");
	qry.append("IFNULL(a.HOURS_SPEND,0)  as time_project,   ");
	qry.append("SUM(IFNULL(c.TRACK_TIME_HRS,0)) as TIME_SPENT  ");
	qry.append("FROM  PROJECTS a  ");
	qry.append("LEFT OUTER JOIN map_projects_activity b  ");
	qry.append("on a.PROJECT_ID = b.PROJECT_ID  ");
	qry.append("left outer join time_tracking c  ");
	qry.append("on b.MAP_PA_ID = c.MAP_PA_ID  ");
	qry.append("where b.STAGE_ID = 1 	");
	qry.append("group by a.PROJECT_ID  order by a.PROJECT_ID) i  ");
	qry.append("on f.project_id = i.project_id 	");
	qry.append("left outer join (  	");
	qry.append("SELECT a.PROJECT_ID, IFNULL(a.WD_FINISHJOB_WEEKS,0) as wd_finish_job, ");
	qry.append("IFNULL(a.WD_PTIME_HRS,0) as wd_time_project, ");
	qry.append("SUM(IFNULL(c.TRACK_TIME_HRS,0)) as wd_TIME_SPENT  ");
	qry.append("FROM PROJECTS a  	");
	qry.append("LEFT OUTER JOIN map_projects_activity b  ");
	qry.append("on a.PROJECT_ID = b.PROJECT_ID  ");
	qry.append("left outer join time_tracking c  ");
	qry.append("on b.MAP_PA_ID = c.MAP_PA_ID ");
	qry.append("where b.STAGE_ID = 4	");
	qry.append("group by a.PROJECT_ID  	");
	qry.append("order by a.PROJECT_ID ) r	");
	qry.append("on f.project_id = r.project_id 	");
	qry.append("left outer join (  	");
	qry.append("SELECT a.PROJECT_ID, IFNULL(a.VCAT_FINISHJOB_WEEKS, 0) as vcat_finish_job,  ");
	qry.append("IFNULL(a.VCAT_PTIME_HRS,0) as vcat_time_project,   	");
	qry.append("SUM(IFNULL(c.TRACK_TIME_HRS,0)) as vcat_TIME_SPENT  	");
	qry.append("FROM  	");
	qry.append("PROJECTS a  	");
	qry.append("LEFT OUTER JOIN map_projects_activity b  	");
	qry.append("on a.PROJECT_ID = b.PROJECT_ID  	");
	qry.append("left outer join time_tracking c  	");
	qry.append("on b.MAP_PA_ID = c.MAP_PA_ID 	");
	qry.append("where b.STAGE_ID = 5	");
	qry.append("group by a.PROJECT_ID  order by a.PROJECT_ID) s	");
	qry.append("on f.project_id = s.project_id 	");
	qry.append("left outer join (  	");
	qry.append("SELECT y.PROJECT_ID,  GROUP_CONCAT(Nullif(y.DESC_MAP_PA,'')) as notes  	");
	qry.append("from  map_projects_activity y  group by y.PROJECT_ID  order by y.PROJECT_ID) j  	");
	qry.append("on f.project_id = j.project_id  ");
	qry.append("order by trim(f.PROJECT_NAME)  	");
	qry.append(") xx	");
	qry.append("where  ");
			qry.append("xx.STATE in ('1','2','3','6')  ");
			if (!userCredentials.contains("Manager")) {
				qry.append(" and xx.PROJECT_ID in ").append(projectsByUser); 
			}
			if (module.contains("overview")) {
				if (phaseID == '1') {
					qry.append("and (xx.PHASE_ID = ").append(phaseID);
					qry.append(" OR xx.phase_id is null) ");
		
				}else {
					qry.append("and xx.PHASE_ID = ").append(phaseID);
				}
				
			}
			qry.append(" order by trim(xx.PROJECT_NAME);  ");

	return qry.toString();
	
		
	}
	
public String ShowProjectById(Long projectID){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("SELECT DISTINCT p.PROJECT_ID as projectID, ");
		qry.append("CONCAT(s.FIRST_NAME, ' ', s.LAST_NAME) AS staffInCharge, ");
		qry.append("p.PROJECT_NAME as projectName, sta.STATUS_NAME AS projectStatus,  ");  
		qry.append("p.NUMBER_OF_EA as numEA, ");
		qry.append("c.EXTENTION as projecCat, "); 
		qry.append("p.BUDGET as budget, p.PRIORITY as priority, "); 
		qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as jobStart, DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as jobDueDate, ");  
		qry.append("CONCAT(cli.CLIENT_NAME, ' <', cli.CLIENT_EMAIL, '>') as client, "); 
		qry.append("CONCAT(CAST(p.PROJECT_TIME AS CHAR), ' ', p.MASUREMENT_TIME) as projectTime, ");  
		qry.append("CONCAT(CAST(p.HOURS_SPEND AS CHAR), ' Hours') as spentTime, p.DESCRIPTION as projectDescription, p.SHORT_NAME as shortName, ");
		qry.append("p.PHOTO_SITE, p.PHASE_ID, p.RFI_CONDITION, p.RFI_CONDITION_WEEKS, p.COUNCIL, p.COUNCIL_REFERENCE, p.COUNCIL_NAME, p.COUNCIL_PH, "); 
		qry.append("p.COUNCIL_EMAIL, p.COUNCIL_NOTE, p.BOX_ARCHIVE, "); 
		qry.append("DATE_FORMAT(p.WD_START_DATE,'%d/%m/%Y') as wdStart, DATE_FORMAT(p.WD_DUE_DATE,'%d/%m/%Y') as wdDueDate, "); 
		qry.append("DATE_FORMAT(p.VCAT_START_DATE,'%d/%m/%Y') as vcatStart, DATE_FORMAT(p.VCAT_DUE_DATE,'%d/%m/%Y') as vcatDueDate, "); 
		qry.append("p.WD_FINISHJOB_WEEKS, p.WD_PTIME_HRS, p.VCAT_FINISHJOB_WEEKS, p.VCAT_PTIME_HRS "); 
		qry.append("FROM PROJECTS p "); 
		qry.append("LEFT OUTER JOIN C_PERSON_CHARGE s ON p.P_CHARGE_ID = s.P_CHARGE_ID "); 
		qry.append("LEFT OUTER JOIN PROJECT_STATUS sta ON p.STATE = sta.STATUS_ID ");
		qry.append("LEFT OUTER JOIN C_CATEGORIES c ON p.CATEGORY_ID = c.CATEGORY_ID ");
		qry.append("LEFT OUTER JOIN CLIENTS cli ON p.CLIENT_ID = cli.CLIENT_ID ");
		qry.append("WHERE "); 
		qry.append("p.PROJECT_ID = ").append(projectID);
		qry.append(" ORDER BY p.START_DATE, p.PROJECT_ID ");   
		
		return qry.toString();
		
	}

public String getAllActiveProjectsByUser(String userProjects, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT DISTINCT p.PROJECT_ID, "); 
	qry.append("CONCAT(s.FIRST_NAME, ' ', s.LAST_NAME) AS STAFFINCHARGE, ");
	qry.append("s.COLOR_CODE, p.PROJECT_NAME, sta.STATUS_NAME AS PROJECTSTATUS, "); 
	qry.append("CONCAT(p.NUMBER_OF_EA, '-', c.EXTENTION) as PROJECTCAT, ");
	qry.append("p.BUDGET, p.PRIORITY, ");
	qry.append("DATE_FORMAT(p.START_DATE,'%d/%m/%Y') as START_DATE, DATE_FORMAT(p.DUE_DATE,'%d/%m/%Y') as DUE_DATE, "); 
	qry.append("CONCAT(cli.CLIENT_NAME, ' <', cli.CLIENT_EMAIL, '>') as CLIENT, ");
	qry.append("CONCAT(p.PROJECT_TIME, ' ', p.MASUREMENT_TIME) as PROJECTTIME, "); 
	qry.append("CONCAT(p.HOURS_SPEND, ' Hours') as SPENT_HOURS ");
	qry.append("FROM PROJECTS p ");
	qry.append("LEFT OUTER JOIN C_PERSON_CHARGE s ON p.P_CHARGE_ID = s.P_CHARGE_ID "); 
	qry.append("LEFT OUTER JOIN PROJECT_STATUS sta ON p.STATE = sta.STATUS_ID "); 
	qry.append("LEFT OUTER JOIN C_CATEGORIES c ON p.CATEGORY_ID = c.CATEGORY_ID "); 
	qry.append("LEFT OUTER JOIN CLIENTS cli ON p.CLIENT_ID = cli.CLIENT_ID ");
	qry.append("WHERE ");
	if (userCredentials.contains("Manager")) {
		qry.append("p.STATE in ('1','2','3','6') ");
		qry.append("ORDER BY trim(p.PROJECT_NAME) "); 
	}else {
		qry.append("p.PROJECT_ID in ").append(userProjects); 
		qry.append(" AND p.STATE in ('1','2','3','6') ");
		qry.append("ORDER BY trim(p.PROJECT_NAME) "); 
	}
	return qry.toString();
	
}

public String getGeneralSearch(String module, String projectsByUser, String userCredentials){
	
	StringBuffer qry = new StringBuffer("");

	qry.append("select f.project_id, g.stages, h.PCHARGE, f.project_name,   ");
	qry.append("i.finish_job, i.time_project, i.TIME_SPENT, if(i.TIME_SPENT<i.time_project,i.time_project-i.TIME_SPENT,0) as time_avaiable,  ");
	qry.append("(if(i.TIME_SPENT>0,i.TIME_SPENT-i.time_project,i.time_project*-1)) as time_overdue, replace(j.notes,',','; ') as notes,   ");
	qry.append("f.RFI_CONDITION, f.PHOTO_SITE,   ");
	qry.append("concat(CAST(f.NUMBER_OF_EA AS CHAR), ' ', k.EXTENTION) as category, ");
	qry.append("f.PHASE_ID, m.PHASE_NAME, o.CLIENT_NAME, f.STATE, p.STATUS_NAME, p.STATUS_COLOR_CODE, o.CLIENT_EMAIL, ");
	qry.append("f.COUNCIL_NAME, f.PRIORITY, f.DESCRIPTION, f.COUNCIL_EMAIL  ");
	qry.append("from projects f  ");
	qry.append("left outer join c_categories k  ");
	qry.append("on f.CATEGORY_ID = k.CATEGORY_ID  ");
	qry.append("left outer join c_phases m  ");
	qry.append("on f.PHASE_ID = m.PHASE_ID  ");
	qry.append("left outer join clients o  ");
	qry.append("on f.CLIENT_ID = o.CLIENT_ID  ");
	qry.append("left outer join project_status p  ");
	qry.append("on f.STATE = p.STATUS_ID  ");
	qry.append("left outer join (  ");
	qry.append("SELECT aa.PROJECT_ID,  ");
	qry.append("GROUP_CONCAT(IFNULL(cc.TASK_NAME, '') SEPARATOR '/') as stages  ");
	qry.append("from (  ");
	qry.append("select distinct x.PROJECT_ID, x.TASK_ID  ");
	qry.append("from  map_projects_activity x  ");
	qry.append("where x.ACTIVITY_DONE = 0  ");
	qry.append("group by x.PROJECT_ID, x.TASK_ID  ");
	qry.append("order by x.PROJECT_ID, x.STAGE_ID, x.TASK_ID, x.ACT_ID) aa  ");
	qry.append("left outer join c_tasks cc  ");
	qry.append("on aa.TASK_ID = cc.TASK_ID  ");
	qry.append("group by aa.PROJECT_ID  ");
	qry.append("order by aa.PROJECT_ID) g  ");
	qry.append("on f.project_id = g.project_id  ");
	qry.append("left outer join (  ");
	qry.append("SELECT z.PROJECT_ID, GROUP_CONCAT(concat(r.FIRST_NAME,' ',r.LAST_NAME) SEPARATOR ', ') as PCHARGE  ");
	qry.append("from map_project_person z  ");
	qry.append("left outer join c_person_charge r  ");
	qry.append("on z.P_CHARGE_ID = r.P_CHARGE_ID  ");
	qry.append("group by z.PROJECT_ID  ");
	qry.append("order by z.PROJECT_ID) h  ");
	qry.append("on f.project_id = h.project_id  ");
	qry.append("left outer join (  ");
	qry.append("SELECT a.PROJECT_ID, concat(CAST(a.PROJECT_TIME AS CHAR), ' weeks') as finish_job,  ");
	qry.append("concat(CAST((IFNULL(a.HOURS_SPEND,0)) AS CHAR), ' Hrs.') as time_project,   ");
	qry.append("SUM(IFNULL(c.TRACK_TIME_HRS,0)) as TIME_SPENT  ");
	qry.append("FROM  ");
	qry.append("PROJECTS a  ");
	qry.append("LEFT OUTER JOIN map_projects_activity b  ");
	qry.append("on a.PROJECT_ID = b.PROJECT_ID  ");
	qry.append("left outer join time_tracking c  ");
	qry.append("on b.MAP_PA_ID = c.MAP_PA_ID  ");
	qry.append("group by a.PROJECT_ID  ");
	qry.append("order by a.PROJECT_ID) i  ");
	qry.append("on f.project_id = i.project_id ");
	qry.append("left outer join (  ");
	qry.append("SELECT y.PROJECT_ID,  ");
	qry.append("GROUP_CONCAT(IFNULL(y.DESC_MAP_PA,'')) as notes  ");
	qry.append("from  map_projects_activity y  ");
	qry.append("group by y.PROJECT_ID  ");
	qry.append("order by y.PROJECT_ID) j  ");
	qry.append("on f.project_id = j.project_id  ");
	if (userCredentials.contains("Manager")) {
		qry.append(" order by trim(f.PROJECT_NAME);  ");
	}else {
		qry.append("WHERE ");
		qry.append("f.PROJECT_ID in ").append(projectsByUser); 
		qry.append(" ORDER BY trim(f.PROJECT_NAME) "); 
	}
	
	return qry.toString();
	
}

}
