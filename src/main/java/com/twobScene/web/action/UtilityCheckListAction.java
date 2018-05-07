package com.twobScene.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.ProjectsDAO;
import com.twobScene.web.dao.ProjectsDAOImpl;
import com.twobScene.web.dao.UtilityCheckLIstDAOImpl;
import com.twobScene.web.dao.UtilityCheckListDAO;
import com.twobScene.web.dto.UtilityCheckListDTO;
import com.twobScene.web.model.Projects;
import com.twobScene.web.model.UtilityCategory;
import com.twobScene.web.model.UtilityCheckListe;

public class UtilityCheckListAction extends ActionSupport implements Action, ServletContextAware, ModelDriven<UtilityCheckListe> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext ctx;
	public UtilityCheckListe ucModel = new UtilityCheckListe();
	public List<UtilityCheckListDTO> utilityCheckList = new ArrayList<UtilityCheckListDTO>();
	public List<UtilityCheckListDTO> utilityList = new ArrayList<UtilityCheckListDTO>(); 
	public Projects project = new Projects();
	public Long projectIDSelected;
	public Long utilityCheckListIDSelected;
	public List<UtilityCategory> utilityCategory = new ArrayList<UtilityCategory>();
	@Override 
	
	public UtilityCheckListe getModel() {
		
		return ucModel;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}

	@Override
	public String execute() throws Exception {
		
		try {
			utilityList = getUtilitiesByIdProject(projectIDSelected);
			project = getProjectInfo(projectIDSelected);
			utilityCategory = getUtilityCategories();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	
	public String saveUpdateCheckList(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		//Integer totalSaved = 0;
		UtilityCheckListe newUtilityList = new UtilityCheckListe();
		try {
			if (utilityCheckList != null) {
				Iterator<UtilityCheckListDTO> iter = utilityCheckList.listIterator();
				
				while (iter.hasNext()) {
					UtilityCheckListDTO checklist = (UtilityCheckListDTO) iter.next();
					//System.out.println("-------- Utility checkList: " + checklist.toString());
					//This block is used to identify if we need to update or save a new checklist
					if (checklist.getUtCheckListID() != null) {
						//update Consultant
						System.out.println("@@@ Utility need to be updated  ");
						UtilityCheckListe utCheckTMP = new UtilityCheckListe();
						utCheckTMP.setUtCheckListID(checklist.getUtCheckListID());
						utCheckTMP.setUtilityID(checklist.getUtilityID());
						utCheckTMP.setProjectID(checklist.getProjectID());
						
						if (checklist.getUtRequiredSTR() != null ) {
							if ("".equals(checklist.getUtRequiredSTR())) {
								utCheckTMP.setUtRequired(null);
							} else {
								utCheckTMP.setUtRequired(Integer.parseInt(checklist.getUtRequiredSTR()));
							}
						} else {
							utCheckTMP.setUtRequired(null);
						}
						if (checklist.getDateOneSTR() != null) {
							if ("".equals(checklist.getDateOneSTR())) {
								utCheckTMP.setDateOne(null);
							} else {
								utCheckTMP.setDateOne(sdf.parse(checklist.getDateOneSTR()));
							}
						} else {
							utCheckTMP.setDateOne(null);
						}
						if (checklist.getPriceSTR() != null) {
							if ("".equals(checklist.getPriceSTR())) {
								utCheckTMP.setPrice(null);
							} else {
								String quoteStr = checklist.getPriceSTR().toString();
								quoteStr = quoteStr.replace("$","");
								quoteStr = quoteStr.replace(",", "");
								System.out.println("####### QUOTE: " + quoteStr);
								utCheckTMP.setPrice(Float.valueOf(quoteStr));
							}
						} else {
							utCheckTMP.setPrice(null);
						}
						if (checklist.getUtilityPaidBy() != null ) {
							if ("".equals(checklist.getUtilityPaidBy())) {
								utCheckTMP.setPaidBy(0);
							} else {
								utCheckTMP.setPaidBy(Integer.parseInt(checklist.getUtilityPaidBy()));
							}
						} else {
							utCheckTMP.setPaidBy(0);
						}
						if (checklist.getDateTwoSTR() != null) {
							if ("".equals(checklist.getDateTwoSTR())) {
								utCheckTMP.setDateTwo(null);
							} else {
								utCheckTMP.setDateTwo(sdf.parse(checklist.getDateTwoSTR()));
							}
						} else {
							utCheckTMP.setDateTwo(null);
						}
						if (checklist.getUtReceivedSTR() != null) {
							if ("".equals(checklist.getUtReceivedSTR())) {
								utCheckTMP.setUtReceived(0);
							} else {
								utCheckTMP.setUtReceived(Integer.parseInt(checklist.getUtReceivedSTR()));
							}
						} else {
							utCheckTMP.setUtReceived(0);
						}
						if (checklist.getUtNoteSTR() != null) {
							if ("".equals(checklist.getUtNoteSTR())) {
								utCheckTMP.setUtNote(null);
							} else {
								utCheckTMP.setUtNote(checklist.getUtNoteSTR());
							}
						} else {
							utCheckTMP.setUtNote(null);
						}
						if (checklist.getUtilityInvoiceClient() != null ) {
							if ("".equals(checklist.getUtilityInvoiceClient())) {
								utCheckTMP.setInvoiceClient(0);
							} else {
								utCheckTMP.setInvoiceClient(Integer.parseInt(checklist.getUtilityInvoiceClient()));
							}
						} else {
							utCheckTMP.setInvoiceClient(0);
						}
						//UPDATING Utility CheckList
						newUtilityList = updateUtility(utCheckTMP);
					
						
					} else {
						//evaluate if the data is empty
						if (checklist.getUtRequiredSTR() != null || !"".equals(checklist.getPriceSTR())  ||  checklist.getUtilityPaidBy() != null  || 
								!"".equals(checklist.getDateOneSTR()) || checklist.getUtReceivedSTR() != null || !"".equals(checklist.getDateTwoSTR())  || !"".equals(checklist.getUtNoteSTR())) {
							
							
							UtilityCheckListe utCheckTMP = new UtilityCheckListe();
							utCheckTMP.setUtCheckListID(null);
							utCheckTMP.setUtilityID(checklist.getUtilityID());
							utCheckTMP.setProjectID(checklist.getProjectID());
							
							if (checklist.getUtRequiredSTR() != null ) {
								if ("".equals(checklist.getUtRequiredSTR())) {
									utCheckTMP.setUtRequired(null);
								} else {
									utCheckTMP.setUtRequired(Integer.parseInt(checklist.getUtRequiredSTR()));
								}
							} else {
								utCheckTMP.setUtRequired(null);
							}
							if (checklist.getDateOneSTR() != null) {
								if ("".equals(checklist.getDateOneSTR())) {
									utCheckTMP.setDateOne(null);
								} else {
									utCheckTMP.setDateOne(sdf.parse(checklist.getDateOneSTR()));
								}
							} else {
								utCheckTMP.setDateOne(null);
							}
							if (checklist.getPriceSTR() != null) {
								if ("".equals(checklist.getPriceSTR())) {
									utCheckTMP.setPrice(null);
								} else {
									String quoteStr = checklist.getPriceSTR().toString();
									quoteStr = quoteStr.replace("$","");
									quoteStr = quoteStr.replace(",", "");
									System.out.println("####### QUOTE: " + quoteStr);
									utCheckTMP.setPrice(Float.valueOf(quoteStr));
								}
							} else {
								utCheckTMP.setPrice(null);
							}
							if (checklist.getUtilityPaidBy() != null ) {
								if ("".equals(checklist.getUtilityPaidBy())) {
									utCheckTMP.setPaidBy(0);
								} else {
									utCheckTMP.setPaidBy(Integer.parseInt(checklist.getUtilityPaidBy()));
								}
							} else {
								utCheckTMP.setPaidBy(0);
							}
							if (checklist.getDateTwoSTR() != null) {
								if ("".equals(checklist.getDateTwoSTR())) {
									utCheckTMP.setDateTwo(null);
								} else {
									utCheckTMP.setDateTwo(sdf.parse(checklist.getDateTwoSTR()));
								}
							} else {
								utCheckTMP.setDateTwo(null);
							}
							if (checklist.getUtReceivedSTR() != null) {
								if ("".equals(checklist.getUtReceivedSTR())) {
									utCheckTMP.setUtReceived(0);
								} else {
									utCheckTMP.setUtReceived(Integer.parseInt(checklist.getUtReceivedSTR()));
								}
							} else {
								utCheckTMP.setUtReceived(0);
							}
							if (checklist.getUtNoteSTR() != null) {
								if ("".equals(checklist.getUtNoteSTR())) {
									utCheckTMP.setUtNote(null);
								} else {
									utCheckTMP.setUtNote(checklist.getUtNoteSTR());
								}
							} else {
								utCheckTMP.setUtNote(null);
							}
							if (checklist.getUtilityInvoiceClient() != null ) {
								if ("".equals(checklist.getUtilityInvoiceClient())) {
									utCheckTMP.setInvoiceClient(0);
								} else {
									utCheckTMP.setInvoiceClient(Integer.parseInt(checklist.getUtilityInvoiceClient()));
								}
							} else {
								utCheckTMP.setInvoiceClient(0);
							}
							
							newUtilityList = saveOrUpdateUtility(utCheckTMP);
							
							
						} else {
							System.out.println("----------- Nothing to do -----------");
						}

					}
					
				}
			} else {
				addActionError("Data not saved");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Data not saved");
		}
		addActionMessage("Utility saved successfully ");
		
		return SUCCESS;
		
	}
	
public String deleteUtilityCheckList(){
		
		try {
			deleteUtilityChecklist(utilityCheckListIDSelected);
			addActionMessage("Consultant deleted successfully ");
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Data not deleted");
		}
		
		return SUCCESS;
		
		
	}
	
	private List<UtilityCheckListDTO> getUtilitiesByIdProject(Long projectID){
		List<UtilityCheckListDTO> utilityByProject = new ArrayList<UtilityCheckListDTO>();
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			UtilityCheckListDAO checklist = new UtilityCheckLIstDAOImpl(sf);
			utilityByProject = checklist.getUtilitiesByProjectID(projectID);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return utilityByProject;
	}
	
	private Projects getProjectInfo(Long projectID){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		Projects projectTmp = new Projects();
		try {
			ProjectsDAO projectsDao = new ProjectsDAOImpl(sf);
			projectTmp = projectsDao.getProjectById(projectID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return projectTmp;
	
	}
	
	private List<UtilityCategory> getUtilityCategories(){
		List<UtilityCategory> catTmp = new ArrayList<UtilityCategory>();
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			UtilityCheckListDAO checklist = new UtilityCheckLIstDAOImpl(sf);
			catTmp = checklist.getAllUtilityCategories();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return catTmp;
	}
	
	private UtilityCheckListe saveOrUpdateUtility(UtilityCheckListe checkList){
		
		UtilityCheckListe utilityChecklistSaved = null;
		//List<UtilityCheckListe> listSaved = new ArrayList<UtilityCheckListe>();
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			UtilityCheckListDAO checklistDao = new UtilityCheckLIstDAOImpl(sf);
			utilityChecklistSaved = checklistDao.saveOrUpdateUtilityCheckList(checkList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return utilityChecklistSaved;
		
	}
	
	private UtilityCheckListe updateUtility(UtilityCheckListe checkList){
		
		UtilityCheckListe utilityChecklistSaved = null;
		//List<ConsultantCheckList> listSaved = new ArrayList<ConsultantCheckList>();
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			UtilityCheckListDAO checklistDao = new UtilityCheckLIstDAOImpl(sf);
			utilityChecklistSaved = checklistDao.UpdateUtilityCheckList(checkList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//listSaved.add(consultantChecklistSaved);
		
		return utilityChecklistSaved;
		
	}
	
private String deleteUtilityChecklist(Long conCheckListID){
		
		UtilityCheckListe utilityChecklistdeleted = new UtilityCheckListe();
		String msg = "";
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			UtilityCheckListDAO checklistDao = new UtilityCheckLIstDAOImpl(sf);
			utilityChecklistdeleted = checklistDao.deleteUtilityCheckList(conCheckListID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (utilityChecklistdeleted == null) {
			msg = "Utility was deleted";
		} else {
			msg = "Utility can not be deleted";
		}
		
		return msg;
		
	}

public UtilityCheckListe getUcModel() {
	return ucModel;
}

public void setUcModel(UtilityCheckListe ucModel) {
	this.ucModel = ucModel;
}

public List<UtilityCheckListDTO> getUtilityCheckList() {
	return utilityCheckList;
}

public void setUtilityCheckList(List<UtilityCheckListDTO> utilityCheckList) {
	this.utilityCheckList = utilityCheckList;
}

public List<UtilityCheckListDTO> getUtilityList() {
	return utilityList;
}

public void setUtilityList(List<UtilityCheckListDTO> utilityList) {
	this.utilityList = utilityList;
}

public Projects getProject() {
	return project;
}

public void setProject(Projects project) {
	this.project = project;
}

public Long getProjectIDSelected() {
	return projectIDSelected;
}

public void setProjectIDSelected(Long projectIDSelected) {
	this.projectIDSelected = projectIDSelected;
}

public Long getUtilityCheckListIDSelected() {
	return utilityCheckListIDSelected;
}

public void setUtilityCheckListIDSelected(Long utilityCheckListIDSelected) {
	this.utilityCheckListIDSelected = utilityCheckListIDSelected;
}

public List<UtilityCategory> getUtilityCategory() {
	return utilityCategory;
}

public void setUtilityCategory(List<UtilityCategory> utilityCategory) {
	this.utilityCategory = utilityCategory;
}
	
	

}

