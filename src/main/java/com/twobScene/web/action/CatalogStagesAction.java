package com.twobScene.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.StaffDAO;
import com.twobScene.web.dao.StaffDAOImpl;
import com.twobScene.web.dao.StagesDAO;
import com.twobScene.web.dao.StagesDAOImpl;
import com.twobScene.web.model.Stages;

public class CatalogStagesAction implements Action, ModelDriven<Stages>, ServletContextAware{
	
	private ServletContext ctx;
	private Stages stages = new Stages();
	private Stages stageByID = new Stages();
	private List<Stages> ListStages;
	private Long stageIdSelected;

	
	
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}
	
	@Override
	public Stages getModel() {
		
		return stages;
	}
	

	@Override
	public String execute() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        StagesDAO stagesDAO = new StagesDAOImpl(sf);
        this.ListStages = stagesDAO.getStages();
       
		
        		return SUCCESS;
	}
	
	public String newStage(){
		return SUCCESS;
		
	}
	
	public String add(){

		System.out.println("--------->>>> public class CatalogStagesAction add() : ");
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StagesDAO stagesDAO = new StagesDAOImpl(sf);
		
		try {
			stagesDAO.addStages(stages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ListStages = stagesDAO.getStages();
		return SUCCESS;
		
	}
	
	public String delete(){
		
		System.out.println("--------->>>> public class CatalogStagesAction delete() : ");
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StagesDAO stagesDAO = new StagesDAOImpl(sf);
		
		try {
			stagesDAO.deleteStages(stages.getStageID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.ListStages = stagesDAO.getStages();
		return SUCCESS;
		
	}
	
	public String editStage(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        StagesDAO stagesDAO = new StagesDAOImpl(sf);
        stageByID = stagesDAO.showStageById(stageIdSelected);
       
		
        		return SUCCESS;
		
	}
	
	public String update(){
		System.out.println("--------->>>> public class CatalogStagesAction update() : ");
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StagesDAO stagesDAO = new StagesDAOImpl(sf);
		
		try {
			stagesDAO.updateStages(stages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ListStages = stagesDAO.getStages();
		return SUCCESS;
	}

	public List<Stages> getListStages() {
		return ListStages;
	}

	public void setListStages(List<Stages> listStages) {
		ListStages = listStages;
	}

	public Long getStageIdSelected() {
		return stageIdSelected;
	}

	public void setStageIdSelected(Long stageIdSelected) {
		this.stageIdSelected = stageIdSelected;
	}

	public Stages getStageByID() {
		return stageByID;
	}

	public void setStageByID(Stages stageByID) {
		this.stageByID = stageByID;
	}



	

}
