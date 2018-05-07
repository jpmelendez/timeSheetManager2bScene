package com.twobScene.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.ActivitiesDAO;
import com.twobScene.web.dao.ActivitiesDAOImpl;
import com.twobScene.web.dao.StagesDAO;
import com.twobScene.web.dao.StagesDAOImpl;
import com.twobScene.web.dao.TasksDAO;
import com.twobScene.web.dao.TasksDAOImpl;
import com.twobScene.web.model.Activity;
import com.twobScene.web.model.Stages;
import com.twobScene.web.model.Tasks;

public class CatalogActivitiesAction implements Action, ModelDriven<Activity>, ServletContextAware {
	
	private ServletContext ctx;
	private Stages stages = new Stages();
	private Tasks tasks = new Tasks();
	private Activity activity = new Activity();
	private Activity activityById = new Activity();
	private List<Stages> ListStages;
	private List<Tasks> ListTasks;
	private List<Activity> listActivities;
	private Long selectedStageID;
	private Long selectedTaskID;
	private Long selectedActivityID;
	private Long activityID;
	private String selectedStageName;
	private String selectedTaskName;
	
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}

	@Override
	public Activity getModel() {
		
		return activity;
	}

	@Override
	public String execute() throws Exception {
		
		System.out.println("<-------- Activities: get Activities execute() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedTaskID : " + selectedTaskID);
		System.out.println("selectedStageName : " + selectedStageName);
		System.out.println("selectedTaskName : " + selectedTaskName);
		
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        ActivitiesDAO activitiesDAO = new ActivitiesDAOImpl(sf);
        this.listActivities = activitiesDAO.getActivities(selectedStageID, selectedTaskID);
       
		
        		return SUCCESS;
	}
	
	public String newActivity(){
		
		return SUCCESS;
		
	}
	
	public String add(){
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ActivitiesDAO activitiesDAO = new ActivitiesDAOImpl(sf);
		
		System.out.println("<-------- Activities: ADD Activities add() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedTaskID : " + selectedTaskID);
		System.out.println("selectedStageName : " + selectedStageName);
		System.out.println("selectedTaskName : " + selectedTaskName);
		
		try {
			
			activitiesDAO.addActivity(selectedStageID, selectedTaskID, activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.listActivities = activitiesDAO.getActivities(selectedStageID, selectedTaskID);
		return SUCCESS;
		
		
	}
	
	public String delete(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ActivitiesDAO activitiesDAO = new ActivitiesDAOImpl(sf);
		
		System.out.println("<-------- Activities: DELETE Activities delete() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedTaskID : " + selectedTaskID);
		System.out.println("selectedStageName : " + selectedStageName);
		System.out.println("selectedTaskName : " + selectedTaskName);
		System.out.println("ACTIVITY ID : " + activity.getActID());
		
		try {
			
			activitiesDAO.deleteActivity(selectedStageID, selectedTaskID, activity.getActID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.listActivities = activitiesDAO.getActivities(selectedStageID, selectedTaskID);
		return SUCCESS;
	}
	
	public String editActivity(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        ActivitiesDAO activitiesDAO = new ActivitiesDAOImpl(sf);
        activityById = activitiesDAO.showActivityById(selectedActivityID);
       
		
        		return SUCCESS;
	}
	
	public String update(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ActivitiesDAO activitiesDAO = new ActivitiesDAOImpl(sf);
		
		System.out.println("<-------- Activities: ADD Activities update() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedTaskID : " + selectedTaskID);
		System.out.println("selectedStageName : " + selectedStageName);
		System.out.println("selectedTaskName : " + selectedTaskName);
		
		try {
			
			activitiesDAO.updateActivity(selectedStageID, selectedTaskID, activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.listActivities = activitiesDAO.getActivities(selectedStageID, selectedTaskID);
		return SUCCESS;
	}

	public List<Stages> getListStages() {
		return ListStages;
	}

	public void setListStages(List<Stages> listStages) {
		ListStages = listStages;
	}

	public List<Tasks> getListTasks() {
		return ListTasks;
	}

	public void setListTasks(List<Tasks> listTasks) {
		ListTasks = listTasks;
	}

	public List<Activity> getListActivities() {
		return listActivities;
	}

	public void setListActivities(List<Activity> listActivities) {
		this.listActivities = listActivities;
	}

	public Long getSelectedStageID() {
		return selectedStageID;
	}

	public void setSelectedStageID(Long selectedStageID) {
		this.selectedStageID = selectedStageID;
	}

	

	public Long getSelectedTaskID() {
		return selectedTaskID;
	}

	public void setSelectedTaskID(Long selectedTaskID) {
		this.selectedTaskID = selectedTaskID;
	}

	public Long getActivityID() {
		return activityID;
	}

	public void setActivityID(Long activityID) {
		this.activityID = activityID;
	}

	public String getSelectedStageName() {
		return selectedStageName;
	}

	public void setSelectedStageName(String selectedStageName) {
		this.selectedStageName = selectedStageName;
	}

	public String getSelectedTaskName() {
		return selectedTaskName;
	}

	public void setSelectedTaskName(String selectedTaskName) {
		this.selectedTaskName = selectedTaskName;
	}

	public Activity getActivityById() {
		return activityById;
	}

	public void setActivityById(Activity activityById) {
		this.activityById = activityById;
	}

	public Long getSelectedActivityID() {
		return selectedActivityID;
	}

	public void setSelectedActivityID(Long selectedActivityID) {
		this.selectedActivityID = selectedActivityID;
	}
	
	
	
	

}
