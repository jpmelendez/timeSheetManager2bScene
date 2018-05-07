package com.twobScene.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.StagesDAO;
import com.twobScene.web.dao.StagesDAOImpl;
import com.twobScene.web.dao.TasksDAO;
import com.twobScene.web.dao.TasksDAOImpl;
import com.twobScene.web.model.Stages;
import com.twobScene.web.model.Tasks;

public class CatalogTasksAction implements Action, ModelDriven<Tasks>, ServletContextAware {
	
	private ServletContext ctx;
	private Stages stages = new Stages();
	private Tasks tasks = new Tasks();
	private Tasks taskById = new Tasks();
	private List<Stages> ListStages;
	private List<Tasks> ListTasks;
	private Long selectedStageID;
	private String selectedStageName;
	private Long selectedTaskId;
	

	private Long taskID;
	
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}
	
	@Override
	public Tasks getModel() {
		
		return tasks;
	}
	

	@Override
	public String execute() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        StagesDAO stagesDAO = new StagesDAOImpl(sf);
        TasksDAO tasksDAO = new TasksDAOImpl(sf);
        this.ListStages = stagesDAO.getStagesByID(selectedStageID);
        this.ListTasks = tasksDAO.getTasks(selectedStageID);
       
		
        		return SUCCESS;
	}
	
	public String newTask(){
		System.out.println("<-------- TASKS: newTask() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedStageName : " + selectedStageName);
		
		return SUCCESS;
		
	}
	
	public String getTasksByIDStage(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TasksDAO tasksDAO = new TasksDAOImpl(sf);
		
		System.out.println("<-------- TASKS: LIST getTasksByIDStage() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedStageName : " + selectedStageName);
		
		this.ListTasks = tasksDAO.getTasks(selectedStageID);
		return SUCCESS;
		
	}
	
	public String add(){
		
		System.out.println("<-------- TASKS: ADD TAsks add() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedStageName : " + selectedStageName);
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TasksDAO tasksDAO = new TasksDAOImpl(sf);
		
		try {
			tasksDAO.addTasks(selectedStageID, tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ListTasks = tasksDAO.getTasks(selectedStageID);
		return SUCCESS;
		
	}
	
	public String delete(){
		
		System.out.println("<-------- TASKS: DELETE Tasks delete() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("taskID : " + tasks.getTaskID());
		System.out.println("selectedStageName : " + selectedStageName);
		
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TasksDAO tasksDAO = new TasksDAOImpl(sf);
		
		try {
			
			tasksDAO.deleteTasks(selectedStageID, tasks.getTaskID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ListTasks = tasksDAO.getTasks(selectedStageID);
		return SUCCESS;
		
	}
	
	public String editTask(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        
        TasksDAO tasksDAO = new TasksDAOImpl(sf);
        
        taskById = tasksDAO.showTaskByID(selectedStageID, selectedTaskId);
       
		
        		return SUCCESS;
	}
	
	public String update(){
		System.out.println("<-------- TASKS: ADD TAsks update() --------> ");
		System.out.println("selectedStageID : " + selectedStageID);
		System.out.println("selectedStageName : " + selectedStageName);
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TasksDAO tasksDAO = new TasksDAOImpl(sf);
		
		try {
			tasksDAO.updateStage(selectedStageID, tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ListTasks = tasksDAO.getTasks(selectedStageID);
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

	public Long getSelectedStageID() {
		return selectedStageID;
	}

	public void setSelectedStageID(Long selectedStageID) {
		this.selectedStageID = selectedStageID;
	}

	public Long getTaskID() {
		return taskID;
	}

	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}
	public String getSelectedStageName() {
		return selectedStageName;
	}

	public void setSelectedStageName(String selectedStageName) {
		this.selectedStageName = selectedStageName;
	}

	public Long getSelectedTaskId() {
		return selectedTaskId;
	}

	public void setSelectedTaskId(Long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

	public Tasks getTaskById() {
		return taskById;
	}

	public void setTaskById(Tasks taskById) {
		this.taskById = taskById;
	}
	
	

}
