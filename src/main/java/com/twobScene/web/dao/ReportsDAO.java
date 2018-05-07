package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.dto.ReportsDTO;

import com.twobScene.web.dto.projectsDTO;

public interface ReportsDAO {

	
	List<ReportsDTO> getTownPlannerListReport(char phaseID);
	List<projectsDTO> generalSearch(String module);
}
