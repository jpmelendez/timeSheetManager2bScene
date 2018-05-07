package com.twobScene.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.ClientsDAO;
import com.twobScene.web.dao.ClientsDAOImpl;
import com.twobScene.web.dao.StaffDAO;
import com.twobScene.web.dao.StaffDAOImpl;
import com.twobScene.web.model.C_Person_Charge;


public class CatalogStaffAction implements Action, ModelDriven<C_Person_Charge>, ServletContextAware {

	private C_Person_Charge c_person_charge = new C_Person_Charge();
	

	private ServletContext ctx;
	private List<C_Person_Charge> personChargeList;
	private Long staffIdSelected;
	
	private C_Person_Charge staffById = new C_Person_Charge();

	public C_Person_Charge getModel(){
		return c_person_charge;
	}

	
	@Override
	public String execute() {
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        StaffDAO staffDAO = new StaffDAOImpl(sf);
        this.personChargeList = staffDAO.listStaff();
       
		
        		return SUCCESS;
		
	}
	
	public String newStaff(){
		return SUCCESS;
		
	}

public String add(){
		
		System.out.println("--------->>>> ADD ACTION MODEL: " + c_person_charge.getFirstName());
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StaffDAO staffDAO = new StaffDAOImpl(sf);
		try {
			System.out.println("Model que viaja al DAO ::::: " + c_person_charge.toString());
			staffDAO.addStaff(c_person_charge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.personChargeList = staffDAO.listStaff();
		return SUCCESS;
		
	}
	
	public String delete(){
		System.out.println(c_person_charge);
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StaffDAO staffDAO = new StaffDAOImpl(sf);
		try {
			
			staffDAO.deleteStaff(c_person_charge.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.personChargeList = staffDAO.listStaff();
		return SUCCESS;
	}
	
	public String editStaff(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	    StaffDAO staffDAO = new StaffDAOImpl(sf);
	    
	    System.out.println("---> public String editStaff():  staffIdSelected: " + staffIdSelected);
	    staffById = staffDAO.getStaffById(staffIdSelected);
	    System.out.println("---> public String editStaff():  staffNAME: " + staffById.getFirstName());
		
	    		return SUCCESS;
	}
	
	
	public String update(){
		System.out.println("--------->>>> update ACTION MODEL: " + c_person_charge.getFirstName());
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StaffDAO staffDAO = new StaffDAOImpl(sf);
		try {
			System.out.println("Model que viaja al DAO ::::: " + c_person_charge.toString());
			staffDAO.updateStaff(c_person_charge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.personChargeList = staffDAO.listStaff();
		return SUCCESS;
		
	}
	
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}

	public C_Person_Charge getC_person_charge() {
		return c_person_charge;
	}

	public void setC_person_charge(C_Person_Charge c_person_charge) {
		this.c_person_charge = c_person_charge;
	}

	public List<C_Person_Charge> getPersonChargeList() {
		return personChargeList;
	}

	public void setPersonChargeList(List<C_Person_Charge> personChargeList) {
		this.personChargeList = personChargeList;
	}


	


	public C_Person_Charge getStaffById() {
		return staffById;
	}


	public void setStaffById(C_Person_Charge staffById) {
		this.staffById = staffById;
	}


	public Long getStaffIdSelected() {
		return staffIdSelected;
	}


	public void setStaffIdSelected(Long staffIdSelected) {
		this.staffIdSelected = staffIdSelected;
	}


	
	
	
}
