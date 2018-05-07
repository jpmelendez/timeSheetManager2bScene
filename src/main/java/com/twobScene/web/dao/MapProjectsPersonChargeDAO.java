package com.twobScene.web.dao;




import java.util.List;



import com.twobScene.web.dto.MapProjectsPersonChargeDTO;


public interface MapProjectsPersonChargeDAO {

	MapProjectsPersonChargeDTO savePersonCharge(MapProjectsPersonChargeDTO mapPersonCharge);
	Integer deletePersonCharge(Long projectID, Long pChargeID);
	List<MapProjectsPersonChargeDTO> getPersonPerProject(Long projectID);
	Boolean findPerson(Long projectID, Long pChargeID);
	String getProjectsPerUserString(Long pChargeID);
}
 