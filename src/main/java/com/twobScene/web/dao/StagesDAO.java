package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.model.Stages;

public interface StagesDAO {
	
	Stages addStages(Stages stages);
	Stages deleteStages(Long stageID);
	Stages updateStages(Stages stages);
	List<Stages> getStages();
	List<Stages> getStagesByID(Long selectedStageID);
	Stages showStageById(Long selectedStageID);
	

}
