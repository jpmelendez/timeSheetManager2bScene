package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.model.Tasks;

public interface TasksDAO {
	
	
	Tasks addTasks(Long selectedStageID, Tasks tasks);
	Tasks deleteTasks(Long selectedStageID, Long TaskID);
	Tasks updateStage(Long selectedStageID, Tasks tasks);
	List<Tasks> getTasks(Long selectedStageID);
	List<Tasks> getTaskByIDTask(Long selectedStageID, Long selectedTaskID);
	Tasks showTaskByID(Long selectedStageID, Long selectedTaskID);

}
