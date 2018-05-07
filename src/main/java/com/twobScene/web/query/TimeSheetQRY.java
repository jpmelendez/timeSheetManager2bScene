package com.twobScene.web.query;

import java.util.Date;

public class TimeSheetQRY {

	public String getTimesByMapActID(Long mapActIDSelected){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("SELECT ");
		qry.append("tt.TRACK_ID, tt.MAP_PA_ID, ");
		qry.append("tt.P_CHARGE_ID, CONCAT(pc.FIRST_NAME, ' ', pc.LAST_NAME) AS STAFFINCHARGE, ");
		qry.append("DATE_FORMAT(tt.TRACKING_DATE,'%d/%m/%Y') as 'TRACKING_DATE', TIME_FORMAT(tt.TRACKING_TIME, '%H:%i') as TRACKING_TIME, ");
		qry.append("tt.	TRACKING_NOTE, tt.TRACK_APPROVED ");
		qry.append("FROM ");
		qry.append("time_tracking tt ");
		qry.append("left outer join c_person_charge pc on tt.P_CHARGE_ID = pc.P_CHARGE_ID ");
		qry.append("left outer join map_projects_activity mpa on tt.MAP_PA_ID = mpa.MAP_PA_ID ");
		qry.append("WHERE ");
		qry.append("tt.MAP_PA_ID = ").append(mapActIDSelected);
		qry.append(" order by tt.TRACKING_DATE, tt.TRACKING_TIME, tt.P_CHARGE_ID ");
		
		
		return qry.toString();

	}
	
public String getTotalTimePerActivity(Long mapActIDSelected){
		
		StringBuffer qry = new StringBuffer("");
		
		qry.append("SELECT ");
		qry.append("SUM( TRACK_TIME_HRS )  AS total_time "); 
		qry.append("FROM ");
		qry.append("time_tracking ");
		qry.append("where ");
		qry.append("MAP_PA_ID = ").append(mapActIDSelected);
		
		
		return qry.toString();
	}

public String getTimesAfterSaved(String monStartDate, String tueDate, String wedDate, String thuDate, String friDate, String satDate, String sunEndDate, Long pCharge, String activitiesIn){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT y.MAP_PA_ID, aa.PROJECT_NAME, bb.STAGE_NAME, concat(cc.TASK_NAME, '-', dd.ACT_NAME) as ACT_NAME, ");
	qry.append("concat(ee.FIRST_NAME, ' ', ee.LAST_NAME) as STAFF_NAME, z.DESC_MAP_PA, sum(y.TIME_MON) as 'MON', sum(y.TIME_TUE) as 'TUE', ");
	qry.append("sum(y.TIME_WED) as 'WED', sum(y.TIME_THU) as 'THU', sum(y.TIME_FRI) as 'FRI', ");
	qry.append("sum(y.TIME_SAT) as 'SAT', sum(y.TIME_SUN) as 'SUN',  ");
	qry.append("((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))) as TotalWeek, ");
	qry.append("concat(CAST((LPAD((floor(((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))))), 2, '0')) AS CHAR), ':', CAST((LPAD((floor((((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))) - floor(((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))))) * 60)), 2, '0')) AS CHAR)) as totalWeekComplete ");
	qry.append("FROM ( ");
	qry.append("SELECT x.TRACK_ID, x.MAP_PA_ID, x.P_CHARGE_ID, o.TRACK_TIME_HRS as 'TIME_MON', p.TRACK_TIME_HRS as 'TIME_TUE',  ");
	qry.append("q.TRACK_TIME_HRS as 'TIME_WED', r.TRACK_TIME_HRS as 'TIME_THU',  ");
	qry.append("s.TRACK_TIME_HRS as 'TIME_FRI', t.TRACK_TIME_HRS as 'TIME_SAT',  ");
	qry.append("u.TRACK_TIME_HRS as 'TIME_SUN' ");
	qry.append("FROM ( ");
	qry.append("SELECT a.TRACK_ID, a.MAP_PA_ID, a.P_CHARGE_ID ");
	qry.append("from time_tracking a ");
	qry.append("where  ");
	qry.append("a.TRACKING_DATE between '").append(monStartDate);
    qry.append("' and '").append(sunEndDate);
	qry.append("' order by a.TRACK_ID) x ");
	qry.append("left outer join ( ");
	qry.append("SELECT b.TRACK_ID, b.MAP_PA_ID, b.P_CHARGE_ID, b.TRACK_TIME_HRS ");
	qry.append("from time_tracking b ");
	qry.append("where  ");
	qry.append("b.TRACKING_DATE = '").append(monStartDate);
	qry.append("' order by b.TRACK_ID ) o ");
	qry.append("on x.TRACK_ID = o.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT c.TRACK_ID, c.MAP_PA_ID, c.P_CHARGE_ID, c.TRACK_TIME_HRS ");
	qry.append("from time_tracking c ");
	qry.append("where  ");
	qry.append("c.TRACKING_DATE = '").append(tueDate);
	qry.append("' order by c.TRACK_ID ) p ");
	qry.append("on x.TRACK_ID = p.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT d.TRACK_ID, d.MAP_PA_ID, d.P_CHARGE_ID, d.TRACK_TIME_HRS ");
	qry.append("from time_tracking d ");
	qry.append("where  ");
	qry.append("d.TRACKING_DATE = '").append(wedDate);
	qry.append("' order by d.TRACK_ID ) q ");
	qry.append("on x.TRACK_ID = q.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT e.TRACK_ID, e.MAP_PA_ID, e.P_CHARGE_ID, e.TRACK_TIME_HRS ");
	qry.append("from time_tracking e ");
	qry.append("where  ");
	qry.append("e.TRACKING_DATE = '").append(thuDate);
	qry.append("' order by e.TRACK_ID ) r ");
	qry.append("on x.TRACK_ID = r.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT f.TRACK_ID, f.MAP_PA_ID, f.P_CHARGE_ID, f.TRACK_TIME_HRS ");
	qry.append("from time_tracking f ");
	qry.append("where  ");
	qry.append("f.TRACKING_DATE = '").append(friDate);
	qry.append("' order by f.TRACK_ID ) s ");
	qry.append("on x.TRACK_ID = s.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT g.TRACK_ID, g.MAP_PA_ID, g.P_CHARGE_ID, g.TRACK_TIME_HRS ");
	qry.append("from time_tracking g ");
	qry.append("where  ");
	qry.append("g.TRACKING_DATE = '").append(satDate);
	qry.append("' order by g.TRACK_ID ) t ");
	qry.append("on x.TRACK_ID = t.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT h.TRACK_ID, h.MAP_PA_ID, h.P_CHARGE_ID, h.TRACK_TIME_HRS ");
	qry.append("from time_tracking h ");
	qry.append("where  ");
	qry.append("h.TRACKING_DATE = '").append(sunEndDate);
	qry.append("' order by h.TRACK_ID ) u ");
	qry.append("on x.TRACK_ID = u.TRACK_ID ) y ");
	qry.append("left outer join map_projects_activity z ");
	qry.append("on y.MAP_PA_ID = z.MAP_PA_ID ");
	qry.append("left outer join projects aa ");
	qry.append("on z.PROJECT_ID = aa.PROJECT_ID ");
	qry.append("left outer join c_project_stages bb ");
	qry.append("on z.STAGE_ID = bb.STAGE_ID ");
	qry.append("left outer join c_tasks cc ");
	qry.append("on z.TASK_ID = cc.TASK_ID ");
	qry.append("left outer join c_activities dd ");
	qry.append("on z.ACT_ID = dd.ACT_ID ");
	qry.append("left outer join c_person_charge ee ");
	qry.append("on y.P_CHARGE_ID = ee.P_CHARGE_ID ");
	qry.append("WHERE ");
	qry.append(" y.P_CHARGE_ID = ").append(pCharge);
	qry.append(" and y.MAP_PA_ID in ").append(activitiesIn);
	qry.append(" group by y.MAP_PA_ID ");
	qry.append(" order by y.MAP_PA_ID; ");

	
	return qry.toString();
	

}

public String getAllTimesPerWeek(String monStartDate, String tueDate, String wedDate, String thuDate, String friDate, String satDate, String sunEndDate, Long pCharge){
	
	StringBuffer qry = new StringBuffer("");
	
	qry.append("SELECT y.MAP_PA_ID, aa.PROJECT_NAME, bb.STAGE_NAME, concat(cc.TASK_NAME, '-', dd.ACT_NAME) as ACT_NAME, ");
	qry.append("concat(ee.FIRST_NAME, ' ', ee.LAST_NAME) as STAFF_NAME, z.DESC_MAP_PA, sum(y.TIME_MON) as 'MON', sum(y.TIME_TUE) as 'TUE', ");
	qry.append("sum(y.TIME_WED) as 'WED', sum(y.TIME_THU) as 'THU', sum(y.TIME_FRI) as 'FRI', ");
	qry.append("sum(y.TIME_SAT) as 'SAT', sum(y.TIME_SUN) as 'SUN',  ");
	qry.append("((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))) as TotalWeek, ");
	qry.append("concat(CAST((LPAD((floor(((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))))), 2, '0')) as CHAR), ':', CAST((LPAD((floor((((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))) - floor(((SUM(IFNULL(y.TIME_MON,0))) + (SUM(IFNULL(y.TIME_TUE,0))) + (SUM(IFNULL(y.TIME_WED,0))) + (SUM(IFNULL(y.TIME_THU,0))) + (SUM(IFNULL(y.TIME_FRI,0))) + (SUM(IFNULL(y.TIME_SAT,0))) + (SUM(IFNULL(y.TIME_SUN,0)))))) * 60)), 2, '0')) as CHAR)) as totalWeekComplete, " );
	qry.append("z.PROJECT_ID, z.STAGE_ID, z.TASK_ID, z.ACT_ID " );
	qry.append("FROM ( ");
	qry.append("SELECT x.TRACK_ID, x.MAP_PA_ID, x.P_CHARGE_ID, o.TRACK_TIME_HRS as 'TIME_MON', p.TRACK_TIME_HRS as 'TIME_TUE',  ");
	qry.append("q.TRACK_TIME_HRS as 'TIME_WED', r.TRACK_TIME_HRS as 'TIME_THU',  ");
	qry.append("s.TRACK_TIME_HRS as 'TIME_FRI', t.TRACK_TIME_HRS as 'TIME_SAT',  ");
	qry.append("u.TRACK_TIME_HRS as 'TIME_SUN' ");
	qry.append("FROM ( ");
	qry.append("SELECT a.TRACK_ID, a.MAP_PA_ID, a.P_CHARGE_ID ");
	qry.append("from time_tracking a ");
	qry.append("where  ");
	qry.append("a.TRACKING_DATE between '").append(monStartDate);
    qry.append("' and '").append(sunEndDate);
	qry.append("' order by a.TRACK_ID) x ");
	qry.append("left outer join ( ");
	qry.append("SELECT b.TRACK_ID, b.MAP_PA_ID, b.P_CHARGE_ID, b.TRACK_TIME_HRS ");
	qry.append("from time_tracking b ");
	qry.append("where  ");
	qry.append("b.TRACKING_DATE = '").append(monStartDate);
	qry.append("' order by b.TRACK_ID ) o ");
	qry.append("on x.TRACK_ID = o.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT c.TRACK_ID, c.MAP_PA_ID, c.P_CHARGE_ID, c.TRACK_TIME_HRS ");
	qry.append("from time_tracking c ");
	qry.append("where  ");
	qry.append("c.TRACKING_DATE = '").append(tueDate);
	qry.append("' order by c.TRACK_ID ) p ");
	qry.append("on x.TRACK_ID = p.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT d.TRACK_ID, d.MAP_PA_ID, d.P_CHARGE_ID, d.TRACK_TIME_HRS ");
	qry.append("from time_tracking d ");
	qry.append("where  ");
	qry.append("d.TRACKING_DATE = '").append(wedDate);
	qry.append("' order by d.TRACK_ID ) q ");
	qry.append("on x.TRACK_ID = q.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT e.TRACK_ID, e.MAP_PA_ID, e.P_CHARGE_ID, e.TRACK_TIME_HRS ");
	qry.append("from time_tracking e ");
	qry.append("where  ");
	qry.append("e.TRACKING_DATE = '").append(thuDate);
	qry.append("' order by e.TRACK_ID ) r ");
	qry.append("on x.TRACK_ID = r.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT f.TRACK_ID, f.MAP_PA_ID, f.P_CHARGE_ID, f.TRACK_TIME_HRS ");
	qry.append("from time_tracking f ");
	qry.append("where  ");
	qry.append("f.TRACKING_DATE = '").append(friDate);
	qry.append("' order by f.TRACK_ID ) s ");
	qry.append("on x.TRACK_ID = s.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT g.TRACK_ID, g.MAP_PA_ID, g.P_CHARGE_ID, g.TRACK_TIME_HRS ");
	qry.append("from time_tracking g ");
	qry.append("where  ");
	qry.append("g.TRACKING_DATE = '").append(satDate);
	qry.append("' order by g.TRACK_ID ) t ");
	qry.append("on x.TRACK_ID = t.TRACK_ID ");
	qry.append("left outer join ( ");
	qry.append("SELECT h.TRACK_ID, h.MAP_PA_ID, h.P_CHARGE_ID, h.TRACK_TIME_HRS ");
	qry.append("from time_tracking h ");
	qry.append("where  ");
	qry.append("h.TRACKING_DATE = '").append(sunEndDate);
	qry.append("' order by h.TRACK_ID ) u ");
	qry.append("on x.TRACK_ID = u.TRACK_ID ) y ");
	qry.append("left outer join map_projects_activity z ");
	qry.append("on y.MAP_PA_ID = z.MAP_PA_ID ");
	qry.append("left outer join projects aa ");
	qry.append("on z.PROJECT_ID = aa.PROJECT_ID ");
	qry.append("left outer join c_project_stages bb ");
	qry.append("on z.STAGE_ID = bb.STAGE_ID ");
	qry.append("left outer join c_tasks cc ");
	qry.append("on z.TASK_ID = cc.TASK_ID ");
	qry.append("left outer join c_activities dd ");
	qry.append("on z.ACT_ID = dd.ACT_ID ");
	qry.append("left outer join c_person_charge ee ");
	qry.append("on y.P_CHARGE_ID = ee.P_CHARGE_ID ");
	qry.append("WHERE ");
	qry.append(" y.P_CHARGE_ID = ").append(pCharge);
	qry.append(" group by y.MAP_PA_ID ");
	qry.append(" order by y.MAP_PA_ID; ");

	
	return qry.toString();
	

}

public String getTimesperDay(Long userid, String priviledge, String datetime, Long mapActivity, Boolean searchbyactivity){
	StringBuffer qry = new StringBuffer("");
	qry.append("select a.track_id, a.map_pa_id, a.p_charge_id, a.TRACKING_DATE, a.TRACK_TIME_HRS, a.tracking_note, g.first_name, ");
	qry.append("c.project_name, d.stage_name, e.task_name, f.act_name, b.project_id, b.stage_id, b.task_id, b.act_id ");
	qry.append("from time_tracking a ");
	qry.append("left outer join map_projects_activity b ");
	qry.append("on  a.map_pa_id = b.map_pa_id ");
	qry.append("left outer join projects c ");
	qry.append("on b.project_id = c.project_id ");
	qry.append("left outer join c_project_stages d ");
	qry.append("on b.stage_id = d.stage_id ");
	qry.append("left outer join c_tasks e ");
	qry.append("on b.task_id = e.task_id ");
	qry.append("left outer join c_activities f ");
	qry.append("on b.act_id = f.act_id ");
	qry.append("left outer join c_person_charge g ");
	qry.append("on a.p_charge_id = g.p_charge_id ");
	qry.append("where  ");
	qry.append("a.TRACKING_DATE = '").append(datetime);
	qry.append("' and a.p_charge_id = ").append(userid);
	if (searchbyactivity) {
		qry.append(" and a.map_pa_id = ").append(mapActivity);
	}

	return qry.toString();
	
	
	
}
	
}
