package com.twobScene.web.dao;

import com.twobScene.web.dto.AlertDTO;
import com.twobScene.web.model.Alerts;

public interface AlertsDAO {
	
	AlertDTO getAlertByProject(Long projectID, Long alertCategoryID);
	Alerts getConsultantAlert(Long projectID, Long alertCategoryID, Long consultantListID);
	Alerts saveAlert(Alerts alert);
	Alerts updateAlert(Alerts alert);
	Alerts deleteAlert(Long alertSelectedID);
}
