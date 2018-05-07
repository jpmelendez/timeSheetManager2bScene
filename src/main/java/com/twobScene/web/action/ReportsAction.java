package com.twobScene.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.twobScene.web.dao.ClientsDAO;
import com.twobScene.web.dao.ClientsDAOImpl;
import com.twobScene.web.dao.ReportsDAO;
import com.twobScene.web.dao.ReportsDAOImpl;
import com.twobScene.web.dto.ReportsDTO;
import com.twobScene.web.model.Clients;
import com.twobScene.web.model.User;

public class ReportsAction extends ActionSupport implements Action, ServletContextAware,  SessionAware, ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext ctx;
	private Map<String, Object> sessionAttributes;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<ReportsDTO> townPlannerList;
	private List<ReportsDTO> projectStagesList;
	private List<ReportsDTO> councilList;
	private List<ReportsDTO> vcatList;
	private List<ReportsDTO> amendPlansList;
	private List<ReportsDTO> workingDrawingList;
	private List<ReportsDTO> buildingPermitList;
	private List<ReportsDTO> underConstructionList;
	private List<ReportsDTO> tenderList;
	private List<ReportsDTO> archiveList;
	private InputStream fileStream;
	private List<String> projectidSTR;
	private String idModuleTMP = "";
	private String projectidTMP = "";
	private String pchargeidTMP = "";
	
	//Customers Report
	private List<Clients> clientsList;

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}
	
	
	
	
	
	
	
	public String getCustomers(){
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        ClientsDAO clientsDAO = new ClientsDAOImpl(sf);
        this.clientsList = clientsDAO.listClients();
       
		
        return SUCCESS;
		
	}
	
	
	
	
	
	
	private String getStringBuilderProjectIDs(List<String> projectIDSList){
		String strprojectIDs = "(0)";
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			Integer sizeindex = projectIDSList.size();
			Integer index = 0;
			
			Iterator<String> iter = projectIDSList.listIterator();
			
			while(iter.hasNext()){
				String obj = iter.next();
				sb.append(obj);
				index ++;
			    if (index < sizeindex) {
			    	 sb.append(",");
				}
			}
			sb.append(")");
			strprojectIDs = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 	strprojectIDs;
	}
	
	

	public List<ReportsDTO> getTownPlannerList() {
		return townPlannerList;
	}

	public void setTownPlannerList(List<ReportsDTO> townPlannerList) {
		this.townPlannerList = townPlannerList;
	}

	public List<ReportsDTO> getProjectStagesList() {
		return projectStagesList;
	}

	public void setProjectStagesList(List<ReportsDTO> projectStagesList) {
		this.projectStagesList = projectStagesList;
	}

	public List<ReportsDTO> getCouncilList() {
		return councilList;
	}

	public void setCouncilList(List<ReportsDTO> councilList) {
		this.councilList = councilList;
	}

	public List<ReportsDTO> getVcatList() {
		return vcatList;
	}

	public void setVcatList(List<ReportsDTO> vcatList) {
		this.vcatList = vcatList;
	}

	public List<ReportsDTO> getAmendPlansList() {
		return amendPlansList;
	}

	public void setAmendPlansList(List<ReportsDTO> amendPlansList) {
		this.amendPlansList = amendPlansList;
	}

	public List<ReportsDTO> getWorkingDrawingList() {
		return workingDrawingList;
	}

	public void setWorkingDrawingList(List<ReportsDTO> workingDrawingList) {
		this.workingDrawingList = workingDrawingList;
	}

	public List<ReportsDTO> getBuildingPermitList() {
		return buildingPermitList;
	}

	public void setBuildingPermitList(List<ReportsDTO> buildingPermitList) {
		this.buildingPermitList = buildingPermitList;
	}

	public List<ReportsDTO> getUnderConstructionList() {
		return underConstructionList;
	}

	public void setUnderConstructionList(List<ReportsDTO> underConstructionList) {
		this.underConstructionList = underConstructionList;
	}

	public List<ReportsDTO> getTenderList() {
		return tenderList;
	}

	public void setTenderList(List<ReportsDTO> tenderList) {
		this.tenderList = tenderList;
	}

	public List<ReportsDTO> getArchiveList() {
		return archiveList;
	}

	public void setArchiveList(List<ReportsDTO> archiveList) {
		this.archiveList = archiveList;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionAttributes = session;
		
	}

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public List<String> getProjectidSTR() {
		return projectidSTR;
	}

	public void setProjectidSTR(List<String> projectidSTR) {
		this.projectidSTR = projectidSTR;
	}


	public String getProjectidTMP() {
		return projectidTMP;
	}

	public void setProjectidTMP(String projectidTMP) {
		this.projectidTMP = projectidTMP;
	}

	public String getPchargeidTMP() {
		return pchargeidTMP;
	}

	public void setPchargeidTMP(String pchargeidTMP) {
		this.pchargeidTMP = pchargeidTMP;
	}

	public String getIdModuleTMP() {
		return idModuleTMP;
	}

	public void setIdModuleTMP(String idModuleTMP) {
		this.idModuleTMP = idModuleTMP;
	}

	public List<Clients> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<Clients> clientsList) {
		this.clientsList = clientsList;
	}

	

	
	
	
	
	

}

