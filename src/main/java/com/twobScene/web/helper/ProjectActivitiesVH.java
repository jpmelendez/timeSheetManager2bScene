package com.twobScene.web.helper;

import com.twobScene.web.dto.ProjectActivitiesDTO;
import com.twobScene.web.model.Activity;
import com.twobScene.web.model.C_Person_Charge;
import com.twobScene.web.model.Stages;
import com.twobScene.web.model.Tasks;

public class ProjectActivitiesVH {
	
	public ProjectActivitiesDTO getAllActivitiesChecked(Object[] obj){
		
		ProjectActivitiesDTO dto = new ProjectActivitiesDTO();
		Long tmpStage = new Long(obj[0]!= null ? obj[0].toString():"0");
		dto.setStage_id(tmpStage.longValue());
		Long tmpTask = new Long(obj[1]!= null ? obj[1].toString():"0");
		dto.setTask_id(tmpTask.longValue());
		Long tmpAct = new Long(obj[2]!= null ? obj[2].toString():"0");
		dto.setAct_id(tmpAct.longValue());
		dto.setAct_name(obj[3]!= null ? obj[3].toString():"(Oops. No activity name)");
		dto.setCatalogOrder(obj[4]!= null ? obj[4].toString():"0");
		dto.setChecked(obj[5].equals("1") ? true : false );
		
		
		return dto;
		
	}
	
public Stages getStageByProjectID(Object[] obj){
		
		Stages mod = new Stages();
		Long tmpStage = new Long(obj[0]!= null ? obj[0].toString():"0");
		mod.setStageID(tmpStage.longValue());
		mod.setStageName(obj[1]!= null ? obj[1].toString():" ");
		Integer tmpPriority = Integer.parseInt(obj[2]!= null ? obj[2].toString():"0");
		mod.setStagePriority(tmpPriority.intValue());
		
		return mod;
		
	}

public Tasks getTasksByProjectID(Object[] obj){
	
	Tasks mod = new Tasks();
	
	Long tmpTask = new Long(obj[0]!= null ? obj[0].toString():"0");
	mod.setTaskID(tmpTask.longValue());
	Long tmpStage = new Long(obj[1]!= null ? obj[1].toString():"0");
	mod.setStageID(tmpStage.longValue());
	mod.setTaskName(obj[2]!= null ? obj[2].toString():"/");
	Integer tmpPriority = Integer.parseInt(obj[3]!= null ? obj[3].toString():"0");
	mod.setTaskPriority(tmpPriority.intValue());
	
	
	return mod;
	
}
public ProjectActivitiesDTO getActivityByProjectID(Object[] obj){
	
	ProjectActivitiesDTO dto = new ProjectActivitiesDTO();
	
	Long tmpMapID = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setMapProjectActID(tmpMapID.longValue());
	Long tmpProjectID = new Long(obj[1]!= null ? obj[1].toString():"0");
	dto.setProjectId(tmpProjectID.longValue());
	Long tmpAct = new Long(obj[2]!= null ? obj[2].toString():"0");
	dto.setAct_id(tmpAct.longValue());
	Long tmpTask = new Long(obj[3]!= null ? obj[3].toString():"0");
	dto.setTask_id(tmpTask.longValue());
	Long tmpStage = new Long(obj[4]!= null ? obj[4].toString():"0");
	dto.setStage_id(tmpStage.longValue());
	dto.setAct_name(obj[5]!= null ? obj[5].toString():"(Oops. No activity name)");
	dto.setActDescription(obj[6]!= null ? obj[6].toString():"(There is no description for this activity)");
	dto.setMapActDesc(obj[7]!= null ? obj[7].toString():"n/d");
	dto.setProgress(obj[8]!= null ? obj[8].toString():"0%");
	dto.setCatalogOrder(obj[9]!= null ? obj[9].toString():"0");
	
	
	return dto;
	
}

public ProjectActivitiesDTO getProjectActivityDetail(Object[] obj){
	
	ProjectActivitiesDTO dto = new ProjectActivitiesDTO();
	
	Long tmpMapID = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setMapProjectActID(tmpMapID.longValue());
	dto.setPaStatus(obj[1]!= null ? obj[1].toString():" ");
	dto.setPaPriority(obj[2]!= null ? obj[2].toString():" ");
	dto.setMapActDesc(obj[3]!= null ? obj[3].toString():" ");
	dto.setStartDate(obj[4]!= null ? obj[4].toString():" ");
	dto.setDueDate(obj[5]!= null ? obj[5].toString():" ");
	dto.setEstimatedTime(obj[6]!= null ? obj[6].toString():" ");
	dto.setProgress(obj[7]!= null ? obj[7].toString() + "%" :"0%");
	dto.setNameStaffCharge(obj[8]!= null ? obj[8].toString():"(No staff in charge to this activity)");
	
	
	return dto;
}

public C_Person_Charge getProjectActivityStaff(Object[] obj){
	
	C_Person_Charge mod = new C_Person_Charge();
	
	mod.setInitials(obj[0]!= null ? obj[0].toString():" ");
	mod.setFirstName(obj[1]!= null ? obj[1].toString():" ");
	mod.setLastName(obj[2]!= null ? obj[2].toString():" ");
	
	
	
	return mod;
}

public ProjectActivitiesDTO getActByProjectStageTask(Object[] obj){
	
	ProjectActivitiesDTO mod = new ProjectActivitiesDTO();
	
	Long tmpMapActProject = new Long(obj[0]!= null ? obj[0].toString():"0");
	mod.setMapProjectActID(tmpMapActProject.longValue());
	Long tmpActID = new Long(obj[1]!= null ? obj[1].toString():"0");
	mod.setAct_id(tmpActID.longValue());
	Long tmpStage = new Long(obj[2]!= null ? obj[2].toString():"0");
	mod.setStage_id(tmpStage.longValue());
	Long tmpTask = new Long(obj[3]!= null ? obj[3].toString():"0");
	mod.setTask_id(tmpTask.longValue());
	mod.setAct_name(obj[4]!= null ? obj[4].toString():" ");
	mod.setPaPriority(obj[5]!= null ? obj[5].toString():"0");
	
	
	return mod;
	
}

}
