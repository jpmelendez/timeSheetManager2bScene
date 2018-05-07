package com.twobScene.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.CategoriesDAO;
import com.twobScene.web.dao.CategoriesDAOImpl;
import com.twobScene.web.dao.ClientsDAO;
import com.twobScene.web.dao.ClientsDAOImpl;
import com.twobScene.web.dao.MapProjectsPersonChargeDAO;
import com.twobScene.web.dao.MapProjectsPersonChargeDAOImpl;
import com.twobScene.web.dao.ProjectsDAO;
import com.twobScene.web.dao.ProjectsDAOImpl;
import com.twobScene.web.dao.StaffDAO;
import com.twobScene.web.dao.StaffDAOImpl;
import com.twobScene.web.dto.MapProjectsPersonChargeDTO;
import com.twobScene.web.dto.projectStrDTO;
import com.twobScene.web.dto.projectsDTO;
import com.twobScene.web.model.C_Person_Charge;
import com.twobScene.web.model.Categories;
import com.twobScene.web.model.Clients;
import com.twobScene.web.model.MapProjectPersonCharge;
import com.twobScene.web.model.Projects;
import com.twobScene.web.model.User;

public class CaptureProjectAction extends ActionSupport implements Action, ModelDriven<Projects>, ServletContextAware, SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2244714765939538004L;
	private Map<String, Object> sessionAttributes;
	private ServletContext ctx;
	private Projects projects = new Projects();
	private Projects projectById = new Projects();
	private List<projectStrDTO> showProject;
	
	//variables for overview tables
		private List<projectsDTO> projectStagesList;
		private List<projectsDTO> councilList;
		private List<projectsDTO> vcatList;
		private List<projectsDTO> amendPlansList;
		private List<projectsDTO> buildingPermitList;
		private List<projectsDTO> underConstructionList;
		private List<projectsDTO> tenderList;
		
		
	
	private List<Clients> clientsList;
	private List<Categories> categoList;
	private List<C_Person_Charge> staffList;
	private List<Projects> projectList;
	private List<projectsDTO> allProjects;
	private String startDateSTR;
	private String dueDateSTR;
	private Long projIDSelected;
	private String currentBudget;
	private Long sessionUserID;
	private String userCredentials;
	private String tabSelected;
	private String tabFromProject;
	
	
	//variables for overview tables

	private List<projectsDTO> workingDrawingList;
	private List<projectsDTO> archiveList;
	private List<MapProjectsPersonChargeDTO> pChargeList;
	private List<MapProjectPersonCharge> personChargeDataList = new ArrayList<MapProjectPersonCharge>();
	MapProjectPersonCharge mapPersonCharge = new MapProjectPersonCharge();
	
	private String wdStartDateSTR;
	private String wdDueDateSTR;
	private String vcatStartDateSTR;
	private String vcatDueDateSTR;
	
	private Long staffIDTmp;
	private String staffCredentials = "";
	
	/*Temporal Variables for theedit/add customer link */
	private String tmPprojectStatus;
	private String tmPnumberOfEandA;
	private String tmPprojectCatID;
	private String tmPprojectName;
	private String tmPprojectDescription;
	private String tmPshortName;
	private String tmPcurrentBudget;
	private String tmPpriority;
	private String tmPstaffID;
	private String tmPprojectTime;
	private String tmPstartDateSTR;
	private String tmPdueDateSTR;
	private String tmPhoursSpend;
	private Long customerIdSelected;
	private String moduleID;
	
	
	
	public Projects getModel(){
		return projects;
		
	}
	
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}

	@Override
	public String execute() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        CategoriesDAO categories = new CategoriesDAOImpl(sf);
        ClientsDAO clients = new ClientsDAOImpl(sf);
        StaffDAO staffDAO = new StaffDAOImpl(sf);
        this.categoList = categories.listCategories();
        this.clientsList = clients.listClients();
        this.staffList = staffDAO.listStaff();
       
        
        System.out.println("++++++ categoList SIZE: " + categoList.size());
        System.out.println("++++++ clientsList SIZE: " + clientsList.size());
        System.out.println("++++++ staffList SIZE: " + staffList.size());
		return SUCCESS;
	}
	
public String add(){
		
		System.out.println("FECHAS RECIBIDAS : START " + startDateSTR + " END: " + dueDateSTR);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		BigDecimal bigCurrentBudget;
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
		Date startdate = null;
		Date duedate = null;
		
		
		
		try {
			
			if (startDateSTR.isEmpty() == false) {
				startdate = sdf.parse(startDateSTR);
			}else {
				startdate = null;
			}
			if (dueDateSTR.isEmpty() == false) {
				duedate = sdf.parse(dueDateSTR);
			} else {
				duedate = null;
			}
			
			
			System.out.println("FECHAS entregadas : START " + startdate + " END: " + duedate);
			projects.setStartDate(startdate);
			projects.setDueDate(duedate);
			
			if (currentBudget.isEmpty() == false) {
				bigCurrentBudget = new BigDecimal(currentBudget);
			}else {
				bigCurrentBudget = BigDecimal.ZERO;
			}
			
			
			System.out.println("xxxxxxxxx----> Budget before evaluation : " + currentBudget);
			projects.setBudget(bigCurrentBudget);
			System.out.println("xxxxxxxxx----> Budget AFTER evaluation : " + projects.getBudget());
			System.out.println("Model que viaja al DAO ::::: " + projects.toString());
			projectsDAO.addProject(projects);
			System.out.println("-----------> Data saved success add() : projects Project ID : " + projects.getProjectID());
			this.showProject =  projectsDAO.projectInfoById(projects.getProjectID());
			 addActionMessage("Data saved successfully");
			 System.out.println("-----------> Message success add() : Data saved successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Data not saved");
		}
	
		return SUCCESS;
		
	}

public String showAllProjects(){
	
	try {
		User sessionUser = (User) sessionAttributes.get("USER");
		final String module = "overview";
		final char cdAndTp = '1';
		final char councilLodgmentPhase = '2';
		final char vcatPhase = '3';
		final char workingDrawing = '4';
		final char buldingPermitPhase = '5';
		final char tenderProjectPhase = '6';
		final char constructionPhase = '7';
		final char archivePhase = '8';
		sessionUserID = sessionUser.getpChargeID();
		userCredentials = sessionUser.getCredentials();
		String projectsByUser = getUserProjects(sessionUserID);
		projectStagesList = getProjectsByPhase(cdAndTp, module, projectsByUser, userCredentials);
		councilList = getProjectsByPhase(councilLodgmentPhase, module, projectsByUser, userCredentials);
		vcatList = getProjectsByPhase(vcatPhase, module, projectsByUser, userCredentials);
		workingDrawingList = getProjectsByPhase(workingDrawing, module, projectsByUser, userCredentials);
		buildingPermitList = getProjectsByPhase(buldingPermitPhase, module, projectsByUser, userCredentials);
		underConstructionList = getProjectsByPhase(constructionPhase, module, projectsByUser, userCredentials);
		tenderList = getProjectsByPhase(tenderProjectPhase, module, projectsByUser, userCredentials);
		archiveList = getProjectsByPhase(archivePhase, module, projectsByUser, userCredentials);
		
		if (tabFromProject == null || tabFromProject == "") {
			tabSelected = "1";
		} else {
			tabSelected = tabFromProject;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return SUCCESS;
	
}

public String delete(){
	
	SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	
	ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
	try {
		System.out.println("---->> ID Project to delete: " + projIDSelected);
		projectsDAO.deleteProject(projIDSelected);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return SUCCESS;
	
	
}

public String update(){
	
	System.out.println("FECHAS RECIBIDAS editProject() : START " + startDateSTR + " END: " + dueDateSTR);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	BigDecimal bigCurrentBudget;
	SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
	Date startdate = null;
	Date duedate = null;
	
	try {
		if (startDateSTR.isEmpty() == false) {
			startdate = sdf.parse(startDateSTR);
		}else {
			startdate = null;
		}
		if (dueDateSTR.isEmpty() == false) {
			duedate = sdf.parse(dueDateSTR);
		} else {
			duedate = null;
		}
		System.out.println("FECHAS entregadas : START " + startdate + " END: " + duedate);
		projects.setStartDate(startdate);
		projects.setDueDate(duedate);
		
		System.out.println("xxxxx editProject() xxxxx currentBudget: " + currentBudget);
		if (currentBudget.isEmpty() == false) {
			bigCurrentBudget = new BigDecimal(currentBudget);
		}else {
			bigCurrentBudget = BigDecimal.ZERO;
		}
		
		
		System.out.println("xxxxxxxxx----> Budget before evaluation : " + currentBudget);
		projects.setBudget(bigCurrentBudget);
		System.out.println("xxx editProject() xxxxxx----> Budget AFTER evaluation : " + projects.getBudget());
		System.out.println("Model que viaja al DAO ::::: " + projects.toString());
		projectsDAO.updateProject(projects);
		showProject =  projectsDAO.projectInfoById(projects.getProjectID());
		 addActionMessage("Data edited successfully");
		 System.out.println("-----------> Message success update() : Data saved successfully");
		 
	} catch (Exception e) {
		e.printStackTrace();
		addActionError("Data not saved");
	}

	return SUCCESS;
}

public String getProject(){
	
SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	
	ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
	CategoriesDAO categories = new CategoriesDAOImpl(sf);
    ClientsDAO clients = new ClientsDAOImpl(sf);
    StaffDAO staffDAO = new StaffDAOImpl(sf);
    
	try {
		System.out.println("---->> ID Project to Serch : " + projIDSelected);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		this.categoList = categories.listCategories();
	    this.clientsList = clients.listClients();
	    this.staffList = staffDAO.listStaff();
		projectById = projectsDAO.getProjectById(projIDSelected);
		
		
		
		if (projectById.getStartDate() == null) {
			startDateSTR = null;
		}else {
			startDateSTR = sdf.format(projectById.getStartDate());
		}
		if (projectById.getDueDate() == null) {
			dueDateSTR = null;
		} else {
			dueDateSTR = sdf.format(projectById.getDueDate());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return SUCCESS;
	
}

public String searchGeneralProject(){
	
	try {
		User sessionUser = (User) sessionAttributes.get("USER");
		final String module = "search";
		sessionUserID = sessionUser.getpChargeID();
		staffCredentials = sessionUser.getCredentials(); 
		String projectsByUser = getUserProjects(sessionUser.getpChargeID());
		System.out.println("************ SEARCH GENERAL PROJECTS BY USER: " + String.valueOf(projectsByUser));
		userCredentials = sessionUser.getCredentials();
		projectStagesList = getSearchGeneral(module, projectsByUser, userCredentials);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return SUCCESS;
	
	
}
private List<projectsDTO> getProjectsByPhase(char phaseID, String module, String projectsByUser, String userCredentials){
	List<projectsDTO> listProjectByPhase = new ArrayList<projectsDTO>();
	
	try {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
		listProjectByPhase = projectsDAO.listAllProjects(phaseID, module, projectsByUser, userCredentials);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return listProjectByPhase;
	
}

private List<projectsDTO> getSearchGeneral(String module, String projectsByUser, String userCredentials){
	List<projectsDTO> listProjectGeneral = new ArrayList<projectsDTO>();
	
	try {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
		listProjectGeneral = projectsDAO.generalSearch(module, projectsByUser, userCredentials);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return listProjectGeneral;
	
}


public void setSession(Map<String, Object> session) {
	this.sessionAttributes = session;
	
}


private String getUserProjects(Long userID){
	
	
	String projectsStr = "";
	try {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		MapProjectsPersonChargeDAO mapDao = new MapProjectsPersonChargeDAOImpl(sf);
		projectsStr = mapDao.getProjectsPerUserString(userID);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return projectsStr;
	
	
}

	public List<Clients> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<Clients> clientsList) {
		this.clientsList = clientsList;
	}

	public List<Categories> getCategoList() {
		return categoList;
	}

	public void setCategoList(List<Categories> categoList) {
		this.categoList = categoList;
	}

	public List<C_Person_Charge> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<C_Person_Charge> staffList) {
		this.staffList = staffList;
	}

	public List<Projects> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Projects> projectList) {
		this.projectList = projectList;
	}

	public String getStartDateSTR() {
		return startDateSTR;
	}

	public void setStartDateSTR(String startDateSTR) {
		this.startDateSTR = startDateSTR;
	}

	public String getDueDateSTR() {
		return dueDateSTR;
	}

	public void setDueDateSTR(String dueDateSTR) {
		this.dueDateSTR = dueDateSTR;
	}

	public List<projectsDTO> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(List<projectsDTO> allProjects) {
		this.allProjects = allProjects;
	}

	public Long getProjIDSelected() {
		return projIDSelected;
	}

	public void setProjIDSelected(Long projIDSelected) {
		this.projIDSelected = projIDSelected;
	}

	public String getCurrentBudget() {
		return currentBudget;
	}

	public void setCurrentBudget(String currentBudget) {
		this.currentBudget = currentBudget;
	}

	public Projects getProjectById() {
		return projectById;
	}

	public void setProjectById(Projects projectById) {
		this.projectById = projectById;
	}

	public List<projectStrDTO> getShowProject() {
		return showProject;
	}

	public void setShowProject(List<projectStrDTO> showProject) {
		this.showProject = showProject;
	}

	public List<projectsDTO> getProjectStagesList() {
		return projectStagesList;
	}

	public void setProjectStagesList(List<projectsDTO> projectStagesList) {
		this.projectStagesList = projectStagesList;
	}

	public List<projectsDTO> getCouncilList() {
		return councilList;
	}

	public void setCouncilList(List<projectsDTO> councilList) {
		this.councilList = councilList;
	}

	public List<projectsDTO> getVcatList() {
		return vcatList;
	}

	public void setVcatList(List<projectsDTO> vcatList) {
		this.vcatList = vcatList;
	}

	public List<projectsDTO> getAmendPlansList() {
		return amendPlansList;
	}

	public void setAmendPlansList(List<projectsDTO> amendPlansList) {
		this.amendPlansList = amendPlansList;
	}

	public List<projectsDTO> getBuildingPermitList() {
		return buildingPermitList;
	}

	public void setBuildingPermitList(List<projectsDTO> buildingPermitList) {
		this.buildingPermitList = buildingPermitList;
	}

	public List<projectsDTO> getUnderConstructionList() {
		return underConstructionList;
	}

	public void setUnderConstructionList(List<projectsDTO> underConstructionList) {
		this.underConstructionList = underConstructionList;
	}

	public List<projectsDTO> getTenderList() {
		return tenderList;
	}

	public void setTenderList(List<projectsDTO> tenderList) {
		this.tenderList = tenderList;
	}

	public Long getSessionUserID() {
		return sessionUserID;
	}

	public void setSessionUserID(Long sessionUserID) {
		this.sessionUserID = sessionUserID;
	}

	public String getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(String userCredentials) {
		this.userCredentials = userCredentials;
	}

	public String getTabSelected() {
		return tabSelected;
	}

	public void setTabSelected(String tabSelected) {
		this.tabSelected = tabSelected;
	}

	public String getTabFromProject() {
		return tabFromProject;
	}

	public void setTabFromProject(String tabFromProject) {
		this.tabFromProject = tabFromProject;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	public List<projectsDTO> getWorkingDrawingList() {
		return workingDrawingList;
	}

	public void setWorkingDrawingList(List<projectsDTO> workingDrawingList) {
		this.workingDrawingList = workingDrawingList;
	}

	public List<projectsDTO> getArchiveList() {
		return archiveList;
	}

	public void setArchiveList(List<projectsDTO> archiveList) {
		this.archiveList = archiveList;
	}

	public List<MapProjectsPersonChargeDTO> getpChargeList() {
		return pChargeList;
	}

	public void setpChargeList(List<MapProjectsPersonChargeDTO> pChargeList) {
		this.pChargeList = pChargeList;
	}

	public List<MapProjectPersonCharge> getPersonChargeDataList() {
		return personChargeDataList;
	}

	public void setPersonChargeDataList(
			List<MapProjectPersonCharge> personChargeDataList) {
		this.personChargeDataList = personChargeDataList;
	}

	public MapProjectPersonCharge getMapPersonCharge() {
		return mapPersonCharge;
	}

	public void setMapPersonCharge(MapProjectPersonCharge mapPersonCharge) {
		this.mapPersonCharge = mapPersonCharge;
	}

	public String getWdStartDateSTR() {
		return wdStartDateSTR;
	}

	public void setWdStartDateSTR(String wdStartDateSTR) {
		this.wdStartDateSTR = wdStartDateSTR;
	}

	public String getWdDueDateSTR() {
		return wdDueDateSTR;
	}

	public void setWdDueDateSTR(String wdDueDateSTR) {
		this.wdDueDateSTR = wdDueDateSTR;
	}

	public String getVcatStartDateSTR() {
		return vcatStartDateSTR;
	}

	public void setVcatStartDateSTR(String vcatStartDateSTR) {
		this.vcatStartDateSTR = vcatStartDateSTR;
	}

	public String getVcatDueDateSTR() {
		return vcatDueDateSTR;
	}

	public void setVcatDueDateSTR(String vcatDueDateSTR) {
		this.vcatDueDateSTR = vcatDueDateSTR;
	}

	public Long getStaffIDTmp() {
		return staffIDTmp;
	}

	public void setStaffIDTmp(Long staffIDTmp) {
		this.staffIDTmp = staffIDTmp;
	}

	public String getTmPprojectStatus() {
		return tmPprojectStatus;
	}

	public void setTmPprojectStatus(String tmPprojectStatus) {
		this.tmPprojectStatus = tmPprojectStatus;
	}

	public String getTmPnumberOfEandA() {
		return tmPnumberOfEandA;
	}

	public void setTmPnumberOfEandA(String tmPnumberOfEandA) {
		this.tmPnumberOfEandA = tmPnumberOfEandA;
	}

	public String getTmPprojectCatID() {
		return tmPprojectCatID;
	}

	public void setTmPprojectCatID(String tmPprojectCatID) {
		this.tmPprojectCatID = tmPprojectCatID;
	}

	public String getTmPprojectName() {
		return tmPprojectName;
	}

	public void setTmPprojectName(String tmPprojectName) {
		this.tmPprojectName = tmPprojectName;
	}

	public String getTmPprojectDescription() {
		return tmPprojectDescription;
	}

	public void setTmPprojectDescription(String tmPprojectDescription) {
		this.tmPprojectDescription = tmPprojectDescription;
	}

	public String getTmPshortName() {
		return tmPshortName;
	}

	public void setTmPshortName(String tmPshortName) {
		this.tmPshortName = tmPshortName;
	}

	public String getTmPcurrentBudget() {
		return tmPcurrentBudget;
	}

	public void setTmPcurrentBudget(String tmPcurrentBudget) {
		this.tmPcurrentBudget = tmPcurrentBudget;
	}

	public String getTmPpriority() {
		return tmPpriority;
	}

	public void setTmPpriority(String tmPpriority) {
		this.tmPpriority = tmPpriority;
	}

	public String getTmPstaffID() {
		return tmPstaffID;
	}

	public void setTmPstaffID(String tmPstaffID) {
		this.tmPstaffID = tmPstaffID;
	}

	public String getTmPprojectTime() {
		return tmPprojectTime;
	}

	public void setTmPprojectTime(String tmPprojectTime) {
		this.tmPprojectTime = tmPprojectTime;
	}

	public String getTmPstartDateSTR() {
		return tmPstartDateSTR;
	}

	public void setTmPstartDateSTR(String tmPstartDateSTR) {
		this.tmPstartDateSTR = tmPstartDateSTR;
	}

	public String getTmPdueDateSTR() {
		return tmPdueDateSTR;
	}

	public void setTmPdueDateSTR(String tmPdueDateSTR) {
		this.tmPdueDateSTR = tmPdueDateSTR;
	}

	public String getTmPhoursSpend() {
		return tmPhoursSpend;
	}

	public void setTmPhoursSpend(String tmPhoursSpend) {
		this.tmPhoursSpend = tmPhoursSpend;
	}

	public Long getCustomerIdSelected() {
		return customerIdSelected;
	}

	public void setCustomerIdSelected(Long customerIdSelected) {
		this.customerIdSelected = customerIdSelected;
	}

	public String getModuleID() {
		return moduleID;
	}

	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}

	public String getStaffCredentials() {
		return staffCredentials;
	}

	public void setStaffCredentials(String staffCredentials) {
		this.staffCredentials = staffCredentials;
	}

	

}

