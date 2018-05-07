package com.twobScene.web.helper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.twobScene.web.dto.MapProjectsPersonChargeDTO;
import com.twobScene.web.dto.projectStrDTO;
import com.twobScene.web.dto.projectsDTO;

public class ProjectVH {
	
	/**
	 * 
	 * @param Metodo para formatear monto
	 * @return
	 */
	public String formato_miles(String valor){
		double val = Double.parseDouble(valor);
		Locale.setDefault(Locale.US);
		DecimalFormat _num  = new DecimalFormat("###,##0.00");
		String strBudget = "$" + _num.format(val);
		return strBudget;
	}

	/**
	 * Metodo que se utiliza para setear unos parametros
	 * @return
	 */
public projectsDTO getAllProjects(Object[] obj, Integer consecutive){
		
	projectsDTO dto = new projectsDTO();
	Long tmp = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setProjectID(tmp.longValue());
	dto.setStage(obj[1]!= null ? obj[1].toString():"");
	dto.setStaffInCharge(obj[2]!= null ? obj[2].toString():"");
	dto.setProjectName(obj[3]!= null ? obj[3].toString():"");
	dto.setFinishJob(obj[4]!= null ? obj[4].toString() + " weeks":"");
	dto.setProjectTime(obj[5]!= null ? obj[5].toString():"");
	dto.setSpentTime(obj[6]!= null ? obj[6].toString():"");
	dto.setAvailableTime(obj[7]!= null ? obj[7].toString():"");
	dto.setOverdueTime(obj[8]!= null ? obj[8].toString():"");
	//**** color code target
	if (obj[8]!= null) {
		if (Double.parseDouble(obj[8].toString()) != 0.0) {
			if (Double.parseDouble(obj[8].toString()) > 0.0) {
				dto.setCdtptargetColorCode("#B61212");
			} else {
				dto.setCdtptargetColorCode("#E6F40F");
			}
		} else {
			dto.setCdtptargetColorCode("transparent");
		}
	} else {
		dto.setCdtptargetColorCode("transparent");
	}
	//dto.setNotes(obj[9]!= null ? obj[9].toString():"");
	dto.setRfiCondition(obj[10]!= null ? obj[10].toString():"");
	if (obj[11] != null) {
		dto.setStrPhotoSite(obj[11].toString().contains("1") ? "yes":"no");
	} else {
		dto.setStrPhotoSite("no");
	}
	dto.setProjecCat(obj[12]!= null ? obj[12].toString():"");
	dto.setPhaseID(obj[13]!= null ? Integer.parseInt(obj[13].toString()):1);
	dto.setPhaseName(obj[14]!= null ? obj[14].toString():"");
	dto.setClient(obj[15]!= null ? obj[15].toString():"");
	dto.setProjectStatusID(obj[16]!= null ? obj[16].toString():"");
	dto.setProjectStatus(obj[17]!= null ? obj[17].toString():"");
	dto.setProjectStatusColor(obj[18]!= null ? obj[18].toString():"transparent");
	dto.setNotes(obj[19]!= null ? obj[19].toString():"");
	dto.setWdFinishJob(obj[20]!= null ? obj[20].toString():"");
	dto.setWdTimeProject(obj[21]!= null ? obj[21].toString():"");
	dto.setWdTimeSpent(obj[22]!= null ? obj[22].toString():"");
	dto.setWdTarget(obj[23]!= null ? obj[23].toString():"");
	//**** color code target
			if (obj[23]!= null) {
				if (Double.parseDouble(obj[23].toString()) != 0.0) {
					if (Double.parseDouble(obj[23].toString()) > 0.0) {
						dto.setWdtargetColorCode("#B61212");
					} else {
						dto.setWdtargetColorCode("#E6F40F");
					}
				} else {
					dto.setWdtargetColorCode("transparent");
				}
			} else {
				dto.setWdtargetColorCode("transparent");
			}
	dto.setVcatFinishJob(obj[24]!= null ? obj[24].toString():"");
	dto.setVcatTimeProject(obj[25]!= null ? obj[25].toString():"");
	dto.setVcatTimeSpent(obj[26]!= null ? obj[26].toString():"");
	dto.setVcatTarget(obj[27]!= null ? obj[27].toString():"");
	//**** color code target
	if (obj[27]!= null) {
		if (Double.parseDouble(obj[27].toString()) != 0.0) {
			if (Double.parseDouble(obj[27].toString()) > 0.0) {
				dto.setVcattargetColorCode("#B61212");
			} else {
				dto.setVcattargetColorCode("#E6F40F");
			}
		} else {
			dto.setVcattargetColorCode("transparent");
		}
	} else {
		dto.setVcattargetColorCode("transparent");
	}
	dto.setRowIndexConsecutive(consecutive != null ? consecutive.toString():"");
	
	return dto;
	}
	
public projectStrDTO showProjectDetail(Object[] obj){
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	projectStrDTO dto = new projectStrDTO();
	
	Long tmp = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setProjectID(tmp.longValue());
	dto.setStaffInCharge(obj[1]!= null ? obj[1].toString():"");
	dto.setProjectName(obj[2]!= null ? obj[2].toString():"");
	dto.setProjectStatus(obj[3]!= null ? obj[3].toString():"");
	dto.setNumEA(obj[4]!= null ? obj[4].toString():"");
	dto.setProjecCat(obj[5]!= null ? obj[5].toString():"");
	String tmpImportemiles = (obj[6] != null ? this.formato_miles(String.valueOf(obj[6])) : "-" );
	dto.setBudget(tmpImportemiles != null ? tmpImportemiles.toString():"");
	dto.setPriority(obj[7]!= null ? obj[7].toString():"");
	dto.setJobStart(obj[8]!= null ? obj[8].toString():"");
	dto.setJobDueDate(obj[9]!= null ? obj[9].toString():"");
	dto.setClient(obj[10]!= null ? obj[10].toString():"");
	dto.setProjectTime(obj[11]!= null ? obj[11].toString():"");
	dto.setSpentTime(obj[12]!= null ? obj[12].toString():"");
	dto.setProjectDescription(obj[13]!= null ? obj[13].toString():"");
	dto.setShortName(obj[14]!= null ? obj[14].toString():"");
	if (obj[15] != null) {
		dto.setPhotoSite(obj[15].toString().contains("1") ? true:false);
	} else {
		dto.setPhotoSite(false);
	}
	
	dto.setPhaseID(obj[16]!= null ? obj[16].toString():"");
	dto.setRfiCondition(obj[17] != null ? sdf.format(obj[17]):"");
	dto.setRfiConditionWeeks(obj[18]!= null ? obj[18].toString():"");
	dto.setCouncil(obj[19]!= null ? obj[19].toString():"");
	dto.setCouncilReference(obj[20]!= null ? obj[20].toString():"");
	dto.setCouncilName(obj[21]!= null ? obj[21].toString():"");
	dto.setCouncilPhone(obj[22]!= null ? obj[22].toString():"");
	dto.setCouncilEmail(obj[23]!= null ? obj[23].toString():"");
	dto.setCouncilNote(obj[24]!= null ? obj[24].toString():"");
	dto.setBoxArchive(obj[25]!= null ? obj[25].toString():"");
	dto.setWdStartDate(obj[26]!= null ? obj[26].toString():"");
	dto.setWdDueDate(obj[27]!= null ? obj[27].toString():"");
	dto.setVcatStartDate(obj[28]!= null ? obj[28].toString():"");
	dto.setVcatDueDate(obj[29]!= null ? obj[29].toString():"");
	dto.setWdFinishJob(obj[30]!= null ? obj[30].toString() + " Weeks":"0 Weeks");
	dto.setWdProjectTime(obj[31]!= null ? obj[31].toString() + " Hrs":"0 Hrs");
	dto.setVcatFinishJob(obj[32]!= null ? obj[32].toString() + " Weeks":"0 Weeks");
	dto.setVcatProjectTime(obj[33]!= null ? obj[33].toString() + " Hrs":"0 Hrs");
	return dto;
}

public projectsDTO generalSearch(Object[] obj, Integer consecutive){
	
	projectsDTO dto = new projectsDTO();
	Long tmp = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setProjectID(tmp.longValue());
	dto.setStage(obj[1]!= null ? obj[1].toString():"");
	dto.setStaffInCharge(obj[2]!= null ? obj[2].toString():"");
	dto.setProjectName(obj[3]!= null ? obj[3].toString():"");
	dto.setFinishJob(obj[4]!= null ? obj[4].toString():"");
	dto.setProjectTime(obj[5]!= null ? obj[5].toString():"");
	dto.setSpentTime(obj[6]!= null ? obj[6].toString():"");
	dto.setAvailableTime(obj[7]!= null ? obj[7].toString():"");
	dto.setOverdueTime(obj[8]!= null ? obj[8].toString():"");
	//dto.setNotes(obj[9]!= null ? obj[9].toString():"");
	dto.setRfiCondition(obj[10]!= null ? obj[10].toString():"");
	if (obj[11] != null) {
		dto.setStrPhotoSite(obj[11].toString().contains("1") ? "yes":"no");
	} else {
		dto.setStrPhotoSite("no");
	}
	dto.setProjecCat(obj[12]!= null ? obj[12].toString():"");
	dto.setPhaseID(obj[13]!= null ? Integer.parseInt(obj[13].toString()):1);
	dto.setPhaseName(obj[14]!= null ? obj[14].toString():"");
	dto.setClient(obj[15]!= null ? obj[15].toString():"");
	dto.setProjectStatusID(obj[16]!= null ? obj[16].toString():"");
	dto.setProjectStatus(obj[17]!= null ? obj[17].toString():"");
	dto.setProjectStatusColor(obj[18]!= null ? obj[18].toString():"#FFFFFF");
	dto.setClientEmail(obj[19]!= null ? obj[19].toString():"");
	dto.setCouncilName(obj[20]!= null ? obj[20].toString():"");
	dto.setProjectPriority(obj[21]!= null ? obj[21].toString():"");
	dto.setNotes(obj[22]!= null ? obj[22].toString():"");
	dto.setRowIndexConsecutive(consecutive != null ? consecutive.toString():"");
	return dto;
}

public MapProjectsPersonChargeDTO personChargeInfo(Object[] obj){
	MapProjectsPersonChargeDTO dto = new MapProjectsPersonChargeDTO();
	
	
	Long tmpProjectID = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setProjectID(tmpProjectID);
	Long tmpPCharge = new Long(obj[1]!= null ? obj[1].toString():"0");
	dto.setpChargeID(tmpPCharge);
	dto.setpChargeName(obj[2]!= null ? obj[2].toString():"");
	dto.setNameInitials(obj[3]!= null ? obj[3].toString():"");
	dto.setpChargeEmail(obj[4]!= null ? obj[4].toString():"");
	dto.setProjectName(obj[5]!= null ? obj[5].toString():"");
	return dto;
	
}

public projectsDTO projectsByUser(Object[] obj){
	
	projectsDTO dto = new projectsDTO();
	Long tmp = new Long(obj[0]!= null ? obj[0].toString():"0");
	dto.setProjectID(tmp.longValue());
	dto.setStaffInCharge(obj[1]!= null ? obj[1].toString():"");
	dto.setColorStaff(obj[2]!= null ? obj[2].toString():"");
	dto.setProjectName(obj[3]!= null ? obj[3].toString():"");
	
	return dto;
}
	

}
