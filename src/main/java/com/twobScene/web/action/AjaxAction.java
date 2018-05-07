package com.twobScene.web.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;














































import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

















import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.twobScene.web.dao.ActivitiesDAO;
import com.twobScene.web.dao.ActivitiesDAOImpl;
import com.twobScene.web.dao.MapProjectsPersonChargeDAO;
import com.twobScene.web.dao.MapProjectsPersonChargeDAOImpl;
import com.twobScene.web.dao.ProjectActivityDAO;
import com.twobScene.web.dao.ProjectActivityDAOImpl;
import com.twobScene.web.dao.ProjectsDAO;
import com.twobScene.web.dao.ProjectsDAOImpl;
import com.twobScene.web.dao.StagesDAO;
import com.twobScene.web.dao.StagesDAOImpl;
import com.twobScene.web.dao.TasksDAO;
import com.twobScene.web.dao.TasksDAOImpl;
import com.twobScene.web.dao.TimeTrackingDAO;
import com.twobScene.web.dao.TimeTrackingDAOImpl;
import com.twobScene.web.dto.ProjectActivitiesDTO;
import com.twobScene.web.dto.projectsDTO;
import com.twobScene.web.model.Activity;
import com.twobScene.web.model.ProjectActivity;
import com.twobScene.web.model.Projects;
import com.twobScene.web.model.Stages;
import com.twobScene.web.model.Tasks;
import com.twobScene.web.model.TimeTracking;
import com.twobScene.web.model.User;



public class AjaxAction extends ActionSupport implements Preparable,  SessionAware {

	/**
	 * this class contain all the ajax calls
	 */
	
	private static StandardServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    private Map<String, Object> sessionAttributes;
    
    private static Configuration configuration = new Configuration().configure();
	private static final long serialVersionUID = -4373049382371944633L;
	
	Long activitySelected;
	private ProjectActivity activityData;
	String strActivityDueDate;
	
	
	String activityDataJson;
	String activityDone;
	String strDueDate;
	String strActNote;
	String jsonDataTmp;
	String msgApp = "";
	String projectID;
	String pChargeID;
	String phaseID;
	
	public String projectIDString;
	public String stageIDString;
	public String taskIDString;
	public String activityIDString;
	public String startDateData;
	public String endDateData;
	public String staffID;
	public String MapActivityIdTmp;
	public String activityNoteData;
	public Long mapProjectActivity;
	public Long projectIdLong;
	public Long stageIDLong;
	public Long taskIDLong;
	public Long activityIDLong;
	public List<Stages> listStages;
	public List<Tasks> listTasks;
	public List<ProjectActivitiesDTO> listActivities;
	List<Activity> allActivities = new ArrayList<Activity>();
	private Long sessionUserID;
	private String userCredentials;
	
	
	//variables for overview tables
		private List<projectsDTO> projectStagesList;
		private List<projectsDTO> councilList;
		private List<projectsDTO> vcatList;
		private List<projectsDTO> amendPlansList;
		private List<projectsDTO> buildingPermitList;
		private List<projectsDTO> underConstructionList;
		private List<projectsDTO> tenderList;
	
	/*Variables for the week time*/
	private String mondayStr;
	private Long mondayTrackID;
	private Integer mondayMonth;
	private String tuesdayStr;
	private Long tuesdayTrackID;
	private Integer tuesdayMonth;
	private String wednesdayStr;
	private Long wednesdayTrackID;
	private Integer wednesdayMonth;
	private String thursdayStr;
	private Long thursdayTrackID;
	private Integer thursdayMonth;
	private String fridayStr;
	private Long fridayTrackID;
	private Integer fridayMonth;
	private String saturdayStr;
	private Long saturdayTrackID;
	private Integer saturdayMonth;
	private String sundayStr;
	private Long sundayTrackID;
	private Integer sundayMonth;
	private Double mondayData;
	private Double tuesdayData;
	private Double wednesdayData;
	private Double thursdayData;
	private Double fridayData;
	private Double saturdayData;
	private Double sundayData;
	
	Map<String, String> stagesmap=new HashMap<String, String>();

	
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public String readStages(){
		System.out.println("------ INSIDE readStages() ----------");
		try {
			if (projectIDString.length() == 0) {
				listStages = null;
				return SUCCESS;
			} else {
				System.out.println("------ INSIDE else ----------");
				Long projectIDLong = new Long(projectIDString);
				System.out.println("project id Long: " + projectIDLong.toString());
				listStages = getStagesByProjectID(projectIDLong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		return SUCCESS;
		
	}
	
	public String readTasks(){
		System.out.println("------ INSIDE readTasks() ----------");
		try {
			if (projectIDString.length() == 0) {
				listStages = null;
				return SUCCESS;
			} else {
				System.out.println("------ INSIDE else ----------");
				
				if (stageIDString.length() == 0) {
					listTasks = null;
					
				} else {
					Long projectIDLong = new Long(projectIDString);
					Long stageIDLong = new Long(stageIDString);
					//System.out.println("project id Long: " + projectIDLong.toString());
					listTasks = getTasksByProjectIDandStageID(projectIDLong, stageIDLong);
					allActivities = getActivityOnlyByStage(stageIDLong);
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		return SUCCESS;
		
	} 
	
	public String readActivities(){
		System.out.println("------ INSIDE readActivity() ----------");
		if (projectIDString == null || stageIDString == null || taskIDString == null) {
			listActivities = null;
			return SUCCESS;
		} else {
			
			try {
				Long projectIDLong = new Long(projectIDString);
				Long stageIDLong = new Long(stageIDString);
				Long taskIdLong = new Long(taskIDString);
				//System.out.println("project id Long: " + projectIDLong.toString());
				listActivities = getActivityByProjectIDStageIDTaskID(projectIDLong, stageIDLong, taskIdLong);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(serviceRegistry!= null) {
			        StandardServiceRegistryBuilder.destroy(serviceRegistry);
			    }
			}
				
			}
			
		return SUCCESS;
		}
	
	public String readWeekData(){
		System.out.println("------ Trying to get the Week data and Note ----------");
		SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE", Locale.ENGLISH);
		SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		SimpleDateFormat sdfToMonth = new SimpleDateFormat("MM", Locale.ENGLISH);
		TimeTracking timetracking = new TimeTracking();
		
		try {
			System.out.println("------ activityIDString: " + activityIDString + " staffID : " + staffID + " startDateData" + startDateData);
			if (activityIDString != null && staffID != null && startDateData != null && projectIDString != null) {
				//getting Map Project Activity ID by project and activity ID
				Long activityID = new Long(activityIDString);
				Long pChargeID = new Long(staffID);
				Long projectID = new Long(projectIDString);
				ProjectActivity pa = getProjectActivityByProjectandActivity(projectID, activityID);
				//validating if the Project Activity exist in the table
				if (pa != null) { 
					/***Project Activity Did exist****/
					mapProjectActivity = pa.getProjectActivityID();
					projectIdLong = pa.getProjectID();
					stageIDLong = pa.getStageID();
					taskIDLong = pa.getTaskID();
					activityIDLong = pa.getActID();
					
					for(int i=0; i<7; i++){
			              Date dayOnSearch = getNextDay(startDateData, i);
			              String dayName = sdfDay.format(dayOnSearch);
			              System.out.println("Day of the week: " + dayName);
			              switch (dayName) {
						case "Monday":
							mondayStr = sdfNormal.format(dayOnSearch);
							mondayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							/**Session needed***/
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								mondayTrackID = timetracking.getTrackID();
								mondayData = timetracking.getTrackTimeDec();
								
							} else {
								mondayTrackID = null;
								mondayData = null;
							}
							break;
						case "Tuesday":
							tuesdayStr = sdfNormal.format(dayOnSearch);
							tuesdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								tuesdayTrackID = timetracking.getTrackID();
								tuesdayData = timetracking.getTrackTimeDec();
								
							} else {
								tuesdayTrackID = null;
								tuesdayData = null;
							}
							
							break;
						case "Wednesday":
							wednesdayStr = sdfNormal.format(dayOnSearch);
							wednesdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								wednesdayTrackID = timetracking.getTrackID();
								wednesdayData = timetracking.getTrackTimeDec();
								
							} else {
			
								wednesdayTrackID = null;
								wednesdayData = null;
							
							}
							
							break;
						case "Thursday":
							thursdayStr = sdfNormal.format(dayOnSearch);
							thursdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								thursdayTrackID = timetracking.getTrackID();
								thursdayData = timetracking.getTrackTimeDec();
								
							} else {
								thursdayData = null;
								thursdayTrackID = null;
							}
							
							break;
						case "Friday":
							fridayStr = sdfNormal.format(dayOnSearch);
							fridayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								fridayTrackID = timetracking.getTrackID();
								fridayData = timetracking.getTrackTimeDec();
							} else {
								fridayTrackID = null;
								fridayData = null;
							}
							
							break;
						case "Saturday":
							saturdayStr = sdfNormal.format(dayOnSearch);
							saturdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								saturdayTrackID = timetracking.getTrackID();
								saturdayData = timetracking.getTrackTimeDec();
								
							} else {
								saturdayTrackID = null;
								saturdayData = null;
							}
							break;
						case "Sunday":
							sundayStr = sdfNormal.format(dayOnSearch);
							sundayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							timetracking = getTimeByDayPChargeMapActID(dayOnSearch, mapProjectActivity, pChargeID);
							if (timetracking != null) {
								sundayTrackID = timetracking.getTrackID();
								sundayData = timetracking.getTrackTimeDec();
								
							} else {
								sundayTrackID = null;
								sundayData = null;
							}
							break;
						default:
							mondayStr = null;
							mondayMonth = null;
							mondayTrackID = null;
							mondayData = null;
							tuesdayStr = null;
							tuesdayMonth = null;
							tuesdayTrackID = null;
							tuesdayData = null;
							wednesdayStr = null;
							wednesdayTrackID = null;
							wednesdayData = null;
							wednesdayMonth = null;
							thursdayData = null;
							thursdayMonth = null;
							thursdayStr = null;
							thursdayTrackID = null;
							fridayStr = null;
							fridayMonth = null;
							fridayTrackID = null;
							fridayData = null;
							saturdayStr = null;
							saturdayMonth = null;
							saturdayTrackID = null;
							saturdayData = null;
							sundayStr = null;
							sundayMonth = null;
							sundayTrackID = null;
							sundayData = null;
							
							break;
						}
			         }
					/*****Session needed*****/
					ProjectActivity projectAct = getProjectActivityFullData(mapProjectActivity);
					if (projectAct != null) {
						activityNoteData = projectAct.getDescPA();
					}else {
						activityNoteData = null;
					}
					
				} else {
					/***Project Activity doesn't exist***/
					
					activityNoteData = null;
					mapProjectActivity = null;
					Activity activity = getActivityData(activityID);
					projectIdLong = projectID;
					stageIDLong = activity.getStageID();
					taskIDLong = activity.getTaskID();
					activityIDLong = activity.getActID();
					
					for(int i=0; i<7; i++){
			              Date dayOnSearch = getNextDay(startDateData, i);
			              String dayName = sdfDay.format(dayOnSearch);
			              System.out.println("Day of the week: " + dayName);
			              switch (dayName) {
						case "Monday":
							mondayStr = sdfNormal.format(dayOnSearch);
							mondayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							mondayTrackID = null;
							mondayData = null;
							break;
						case "Tuesday":
							tuesdayStr = sdfNormal.format(dayOnSearch);
							tuesdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
								tuesdayTrackID = null;
								tuesdayData = null;
							
							
							break;
						case "Wednesday":
							wednesdayStr = sdfNormal.format(dayOnSearch);
							wednesdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
								wednesdayTrackID = null;
								wednesdayData = null;
							
							break;
						case "Thursday":
							thursdayStr = sdfNormal.format(dayOnSearch);
							thursdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
								thursdayData = null;
								thursdayTrackID = null;							
							break;
						case "Friday":
							fridayStr = sdfNormal.format(dayOnSearch);
							fridayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							fridayTrackID = null;
							fridayData = null;
							
							break;
						case "Saturday":
							saturdayStr = sdfNormal.format(dayOnSearch);
							saturdayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
							saturdayTrackID = null;
							saturdayData = null;
							break;
						case "Sunday":
							sundayStr = sdfNormal.format(dayOnSearch);
							sundayMonth = Integer.parseInt(sdfToMonth.format(dayOnSearch));
								sundayTrackID = null;
								sundayData = null;
							break;
						default:
							mondayStr = null;
							mondayMonth = null;
							mondayTrackID = null;
							mondayData = null;
							tuesdayStr = null;
							tuesdayMonth = null;
							tuesdayTrackID = null;
							tuesdayData = null;
							wednesdayStr = null;
							wednesdayTrackID = null;
							wednesdayData = null;
							wednesdayMonth = null;
							thursdayData = null;
							thursdayMonth = null;
							thursdayStr = null;
							thursdayTrackID = null;
							fridayStr = null;
							fridayMonth = null;
							fridayTrackID = null;
							fridayData = null;
							saturdayStr = null;
							saturdayMonth = null;
							saturdayTrackID = null;
							saturdayData = null;
							sundayStr = null;
							sundayMonth = null;
							sundayTrackID = null;
							sundayData = null;
							
							break;
						}
			         }
					
				}
				
				
			}	
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		
		return SUCCESS;
	}
	
	public String readActivityByID() {
		Gson gson = new Gson();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		System.out.println("++++++++++ ----------- Trying to red activity data AAAAJJJAAAAAXXXXX........");
		//System.out.println("++++++++++ ----------- activityselected: " + activitySelected);
		
		try {
			if (activitySelected != null) {
				activityData = getActivityDataByID(activitySelected);
				if (activityData.getDueDate() == null) {
					strDueDate = null;
				} else {
					strDueDate = sdf.format(activityData.getDueDate());
				}
				 
				activityDataJson = gson.toJson(activityData);
			} else {
				activityData = null;
				activityDataJson = gson.toJson(activityData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		return SUCCESS;
		
	}
	
	
	
	public String updateMappedActivity(){
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		ProjectActivity activityDataTmp = new ProjectActivity(); 

		
		try {
			
			System.out.println("xxxxxxx----> Updating activity via Ajax...");
			if (activitySelected != null) {
				
				System.out.println("xxxxxxx----> Updating jsonDataTmp act ID: " + activitySelected);
				activityData = getActivityDataByID(activitySelected);
				
				activityDataTmp = activityData;
				activityDataTmp.setProjectActivityID(new Long(activitySelected));
				activityDataTmp.setDescPA(strActNote);
				if (strDueDate == null || strDueDate == "") {
				activityDataTmp.setDueDate(null);	
				} else {
					activityDataTmp.setDueDate(sdf.parse(strDueDate));
				}
				if (activityDone.contentEquals("true")) {
					activityDataTmp.setActivityDone(true);
				} else {
					activityDataTmp.setActivityDone(false);
				}
				System.out.println("xxxxxxx----> Updating done : " + activityDataTmp.getActivityDone());
				
				
					msgApp = updateActivityByID(activityDataTmp);
				
			} else {
				activityData = null;
				msgApp = "Data could not be saved. Try again please.";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		return SUCCESS;
		
	}
	
	public String deletePCharge(){
		
		try {
			if (projectID != null || pChargeID != null) {
				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				MapProjectsPersonChargeDAO mapProjectPerson = new MapProjectsPersonChargeDAOImpl(sessionFactory);
				Long projecttmp = new Long(projectID);
				Long persontmp = new Long(pChargeID);
				Integer rowDeleted = mapProjectPerson.deletePersonCharge(projecttmp, persontmp);
				msgApp = rowDeleted + " Person in charge was removed succesfully.";
			} else {
				msgApp = "Data could not be deleted. Try again please.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		return SUCCESS;
		
	}
	
public String updateProjectPhase(){
		
		try {
			if (projectID.length() > 0 && phaseID.length() > 0) {
				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				ProjectsDAO projectsdao = new ProjectsDAOImpl(sessionFactory) ;
				Long projecttmp = new Long(projectID);
				
				Integer phaseIdtmp = Integer.parseInt(phaseID);
				Projects project = projectsdao.getProjectById(projecttmp);
				project.setPhaseId(phaseIdtmp);
				updateProject(project);
				msgApp = "Data was saved";
			} else {
				msgApp = "Data could not be updated. Try again please.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		return SUCCESS;
		
	}

public String refreshTablesOverviewProject(){
	
	final String module = "overview";
	final char projectStagesPhase = '1';
	final char councilLodgmentPhase = '2';
	final char vcatPhase = '3';
	final char amendsPhase = '4';
	final char buldingPermitPhase = '5';
	final char constructionPhase = '6';
	final char tenderProjectPhase = '7';
	
	try {
		String projectsByUser = getUserProjects(sessionUserID);
		projectStagesList = getProjectsByPhase(projectStagesPhase, module, projectsByUser, userCredentials);
		councilList = getProjectsByPhase(councilLodgmentPhase, module, projectsByUser, userCredentials);
		vcatList = getProjectsByPhase(vcatPhase, module, projectsByUser, userCredentials);
		amendPlansList = getProjectsByPhase(amendsPhase, module, projectsByUser, userCredentials);
		buildingPermitList = getProjectsByPhase(buldingPermitPhase, module, projectsByUser, userCredentials);
		underConstructionList = getProjectsByPhase(constructionPhase, module, projectsByUser, userCredentials);
		tenderList = getProjectsByPhase(tenderProjectPhase, module, projectsByUser, userCredentials);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		if(serviceRegistry!= null) {
	        StandardServiceRegistryBuilder.destroy(serviceRegistry);
	    }
	}
	
	
	return SUCCESS;
	
}
	
	public String searchGeneral(){
		
		final String module = "search";
		
		try {
			projectStagesList = getSearchGeneral(module);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		return SUCCESS;
		
	} 
	
	private String getUserProjects(Long userID){
		
		
		String projectsStr = "";
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			MapProjectsPersonChargeDAO mapDao = new MapProjectsPersonChargeDAOImpl(sessionFactory);
			projectsStr = mapDao.getProjectsPerUserString(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return projectsStr;
		
		
	}
	
	
	
	
	private List<Stages> getStagesByProjectID(Long projectIDLong){
		
		List<Stages> listStagesTmp = null;
		
		
		try {
			System.out.println("-----trying to get the stsges dinamically");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			//gets all stages
			
			StagesDAO stagedao = new StagesDAOImpl(sessionFactory);
			listStagesTmp = stagedao.getStages();
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		return listStagesTmp;
		
	}
	
	private List<Tasks> getTasksByProjectIDandStageID(Long projectIDLong, Long stageIDLong){
		List<Tasks> listTasksTmp = null;
		
		try {
			System.out.println("-----trying to get the tasks dinamically");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			TasksDAO taskdao = new TasksDAOImpl(sessionFactory);
			listTasksTmp =  taskdao.getTasks(stageIDLong);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		
		return listTasksTmp;
		
	}
	
	private List<ProjectActivitiesDTO> getActivityByProjectIDStageIDTaskID(Long projectIDLong, Long stageIDLong, Long taskIDLong){
		List<ProjectActivitiesDTO> listActTmp = null;
		
		try {
			System.out.println("-----trying to get the tasks dinamically");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			ProjectActivityDAO projectActivityDAO = new ProjectActivityDAOImpl(sessionFactory);
			listActTmp = projectActivityDAO.getActByProjectIdStageIdTaskId(projectIDLong, stageIDLong, taskIDLong);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		
		return listActTmp;
		
	}
	
	private List<Activity> getActivityOnlyByStage(Long stageIDLong){
		
		
		try {
			System.out.println("-----trying to get the tasks dinamically");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			ActivitiesDAO actDao = new ActivitiesDAOImpl(sessionFactory);
			allActivities = actDao.getActivitiesByStage(stageIDLong);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		return allActivities;
		
	}
	
private ProjectActivity getActivityDataByID(Long activitySelected){
		
		
		try {
			System.out.println("-----trying to get the stsges dinamically");
			
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			ProjectActivityDAO projectActivityDAO = new ProjectActivityDAOImpl(sessionFactory);
			activityData = projectActivityDAO.getProjectActByMapID(activitySelected);
			
			if (activityData.equals(null)) {
				System.out.println("++++++++ the activity searcj is empty +++++++++++++");
			} else {
				System.out.println("******** the activity id returned: " + activityData.getProjectActivityID());
			}
			
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		return activityData;
		
	}
	
	
	private String updateActivityByID(ProjectActivity dataToSave){
		 
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			ProjectActivityDAO projectActivityDAO = new ProjectActivityDAOImpl(sessionFactory);
			
			projectActivityDAO.updateProjectActivity(dataToSave);
			
			msgApp = "Data saved succesfully";
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
			msgApp = "Data could not be saved";
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return msgApp;
	
	}
	
	
	
	private Projects updateProject(Projects project){
		Projects projectUpdated = new Projects();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    
	    try {
			ProjectsDAO projectsdao = new ProjectsDAOImpl(sessionFactory);
			projectUpdated = projectsdao.updateProject(project);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return projectUpdated;
		
	}
	
	private List<projectsDTO> getProjectsByPhase(char phaseID, String module, String projectsByUser, String userCredentials){
		List<projectsDTO> listProjectByPhase = new ArrayList<projectsDTO>();
		
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			ProjectsDAO projectsDAO = new ProjectsDAOImpl(sessionFactory);
			listProjectByPhase = projectsDAO.listAllProjects(phaseID, module, projectsByUser, userCredentials);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return listProjectByPhase;
		
	}
	
	private List<projectsDTO> getSearchGeneral(String module){
		List<projectsDTO> listProjectGeneral = new ArrayList<projectsDTO>();
		Long pchargeIDTemp;
		
		if (staffID != null) {
			if ("".equals(staffID)) {
				pchargeIDTemp = new Long("0");
			} else {
				pchargeIDTemp = new Long(staffID);
			}
		} else {
			pchargeIDTemp = new Long("0");
		}
		
		try {
			String projectsByUser = getUserProjects(pchargeIDTemp);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    
			ProjectsDAO projectsDAO = new ProjectsDAOImpl(sessionFactory);
			listProjectGeneral = projectsDAO.generalSearch(module, projectsByUser, userCredentials);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return listProjectGeneral;
		
	}
	
	private Date getCurrentTime(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
		
		
	}
	
	private Date getFirstDayWeek(Date currentDateDate){
		Date firstDayWeek;
		Calendar c = Calendar.getInstance();
		c.setTime(currentDateDate);
		c.set(Calendar.DAY_OF_WEEK, c.getActualMinimum(Calendar.DAY_OF_WEEK));
		firstDayWeek = c.getTime();
		
		return firstDayWeek;
	}
	
	private Date getLastDayWeek(Date currentDateDate){
		Date lastDayWeek;
		Calendar c = Calendar.getInstance();
		c.setTime(currentDateDate);
		c.set(Calendar.DAY_OF_WEEK, c.getActualMaximum(Calendar.DAY_OF_WEEK));
		lastDayWeek = c.getTime();
		
		return lastDayWeek;
	}
	
	private Date getNextDay(String dayOnfocus, Integer index){
		
		SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Date dayToSearch = new Date();
		try {
			Date dayTmp = sdfNormal.parse(dayOnfocus);
			if (index == 0) {
				dayToSearch = dayTmp;
			} else {
				Calendar c = Calendar.getInstance();
				c.setTime(dayTmp);
				c.add(Calendar.DATE, index);
				dayToSearch = c.getTime();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dayToSearch;
	}
	
	private TimeTracking getTimeByDayPChargeMapActID (Date dateToSearch, Long mapActID, Long pChargeID){
		TimeTracking timeT = new TimeTracking();
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			TimeTrackingDAO timetrackingdao = new TimeTrackingDAOImpl(sessionFactory);
			timeT = timetrackingdao.searchTimeByDatePChargeMapAct(mapActID, pChargeID, dateToSearch);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		
		
		return timeT;
		
	}
	
	private ProjectActivity getProjectActivityFullData(Long mapActivityID){
		ProjectActivity pAct = new ProjectActivity();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		ProjectActivityDAO projectActdto = new ProjectActivityDAOImpl(sessionFactory);
		try {
			pAct = projectActdto.getProjectActByMapID(mapActivityID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return pAct;
		
	}
	
	private ProjectActivity getProjectActivityByProjectandActivity(Long projectID, Long activityID){
		ProjectActivity pAct = new ProjectActivity();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		ProjectActivityDAO projectActdto = new ProjectActivityDAOImpl(sessionFactory);
		try {
			pAct = projectActdto.getProjectActivityByProjectandActivity(projectID, activityID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return pAct;
		
	}
	
	private Activity getActivityData(Long activityID){
		Activity activity = new Activity();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		try {
			ActivitiesDAO actDAO = new ActivitiesDAOImpl(sessionFactory);
			activity = actDAO.showActivityById(activityID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serviceRegistry!= null) {
		        StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    }
		}
		
		return activity;
		
	}
	
	
	

	public List<Tasks> getListTasks() {
		return listTasks;
	}




	public void setListTasks(List<Tasks> listTasks) {
		this.listTasks = listTasks;
	}


	public List<ProjectActivitiesDTO> getListActivities() {
		return listActivities;
	}




	public void setListActivities(List<ProjectActivitiesDTO> listActivities) {
		this.listActivities = listActivities;
	}




	public String getProjectIDString() {
		return projectIDString;
	}

	public void setProjectIDString(String projectIDString) {
		this.projectIDString = projectIDString;
	}

	public String getStageIDString() {
		return stageIDString;
	}

	public void setStageIDString(String stageIDString) {
		this.stageIDString = stageIDString;
	}

	public String getTaskIDString() {
		return taskIDString;
	}

	public void setTaskIDString(String taskIDString) {
		this.taskIDString = taskIDString;
	}

	public String getActivityIDString() {
		return activityIDString;
	}

	public void setActivityIDString(String activityIDString) {
		this.activityIDString = activityIDString;
	}

	public List<Stages> getListStages() {
		return listStages;
	}

	public void setListStages(List<Stages> listStages) {
		this.listStages = listStages;
	}

	public Map<String, String> getStagesmap() {
		return stagesmap;
	}

	public void setStagesmap(Map<String, String> stagesmap) {
		this.stagesmap = stagesmap;
	}


	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}



	public String getStartDateData() {
		return startDateData;
	}




	public void setStartDateData(String startDateData) {
		this.startDateData = startDateData;
	}




	public String getEndDateData() {
		return endDateData;
	}




	public void setEndDateData(String endDateData) {
		this.endDateData = endDateData;
	}




	public String getStaffID() {
		return staffID;
	}




	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}




	public String getMapActivityIdTmp() {
		return MapActivityIdTmp;
	}




	public void setMapActivityIdTmp(String mapActivityIdTmp) {
		MapActivityIdTmp = mapActivityIdTmp;
	}




	public String getMondayStr() {
		return mondayStr;
	}




	public void setMondayStr(String mondayStr) {
		this.mondayStr = mondayStr;
	}




	public Long getMondayTrackID() {
		return mondayTrackID;
	}




	public void setMondayTrackID(Long mondayTrackID) {
		this.mondayTrackID = mondayTrackID;
	}




	public Integer getMondayMonth() {
		return mondayMonth;
	}




	public void setMondayMonth(Integer mondayMonth) {
		this.mondayMonth = mondayMonth;
	}




	public String getTuesdayStr() {
		return tuesdayStr;
	}




	public void setTuesdayStr(String tuesdayStr) {
		this.tuesdayStr = tuesdayStr;
	}




	public Long getTuesdayTrackID() {
		return tuesdayTrackID;
	}




	public void setTuesdayTrackID(Long tuesdayTrackID) {
		this.tuesdayTrackID = tuesdayTrackID;
	}




	public Integer getTuesdayMonth() {
		return tuesdayMonth;
	}




	public void setTuesdayMonth(Integer tuesdayMonth) {
		this.tuesdayMonth = tuesdayMonth;
	}




	public String getWednesdayStr() {
		return wednesdayStr;
	}




	public void setWednesdayStr(String wednesdayStr) {
		this.wednesdayStr = wednesdayStr;
	}




	public Long getWednesdayTrackID() {
		return wednesdayTrackID;
	}




	public void setWednesdayTrackID(Long wednesdayTrackID) {
		this.wednesdayTrackID = wednesdayTrackID;
	}




	public Integer getWednesdayMonth() {
		return wednesdayMonth;
	}




	public void setWednesdayMonth(Integer wednesdayMonth) {
		this.wednesdayMonth = wednesdayMonth;
	}




	public String getThursdayStr() {
		return thursdayStr;
	}




	public void setThursdayStr(String thursdayStr) {
		this.thursdayStr = thursdayStr;
	}




	public Long getThursdayTrackID() {
		return thursdayTrackID;
	}




	public void setThursdayTrackID(Long thursdayTrackID) {
		this.thursdayTrackID = thursdayTrackID;
	}




	public Integer getThursdayMonth() {
		return thursdayMonth;
	}




	public void setThursdayMonth(Integer thursdayMonth) {
		this.thursdayMonth = thursdayMonth;
	}




	public String getFridayStr() {
		return fridayStr;
	}




	public void setFridayStr(String fridayStr) {
		this.fridayStr = fridayStr;
	}




	public Long getFridayTrackID() {
		return fridayTrackID;
	}




	public void setFridayTrackID(Long fridayTrackID) {
		this.fridayTrackID = fridayTrackID;
	}




	public Integer getFridayMonth() {
		return fridayMonth;
	}




	public void setFridayMonth(Integer fridayMonth) {
		this.fridayMonth = fridayMonth;
	}




	public String getSaturdayStr() {
		return saturdayStr;
	}




	public void setSaturdayStr(String saturdayStr) {
		this.saturdayStr = saturdayStr;
	}




	public Long getSaturdayTrackID() {
		return saturdayTrackID;
	}




	public void setSaturdayTrackID(Long saturdayTrackID) {
		this.saturdayTrackID = saturdayTrackID;
	}




	public Integer getSaturdayMonth() {
		return saturdayMonth;
	}




	public void setSaturdayMonth(Integer saturdayMonth) {
		this.saturdayMonth = saturdayMonth;
	}




	public String getSundayStr() {
		return sundayStr;
	}




	public void setSundayStr(String sundayStr) {
		this.sundayStr = sundayStr;
	}




	public Long getSundayTrackID() {
		return sundayTrackID;
	}




	public void setSundayTrackID(Long sundayTrackID) {
		this.sundayTrackID = sundayTrackID;
	}




	public Integer getSundayMonth() {
		return sundayMonth;
	}




	public void setSundayMonth(Integer sundayMonth) {
		this.sundayMonth = sundayMonth;
	}



	public Double getMondayData() {
		return mondayData;
	}




	public void setMondayData(Double mondayData) {
		this.mondayData = mondayData;
	}




	public Double getTuesdayData() {
		return tuesdayData;
	}




	public void setTuesdayData(Double tuesdayData) {
		this.tuesdayData = tuesdayData;
	}




	public Double getWednesdayData() {
		return wednesdayData;
	}




	public void setWednesdayData(Double wednesdayData) {
		this.wednesdayData = wednesdayData;
	}




	public Double getThursdayData() {
		return thursdayData;
	}




	public void setThursdayData(Double thursdayData) {
		this.thursdayData = thursdayData;
	}




	public Double getFridayData() {
		return fridayData;
	}




	public void setFridayData(Double fridayData) {
		this.fridayData = fridayData;
	}




	public Double getSaturdayData() {
		return saturdayData;
	}




	public void setSaturdayData(Double saturdayData) {
		this.saturdayData = saturdayData;
	}




	public Double getSundayData() {
		return sundayData;
	}




	public void setSundayData(Double sundayData) {
		this.sundayData = sundayData;
	}




	public String getActivityNoteData() {
		return activityNoteData;
	}




	public void setActivityNoteData(String activityNoteData) {
		this.activityNoteData = activityNoteData;
	}




	public List<Activity> getAllActivities() {
		return allActivities;
	}




	public void setAllActivities(List<Activity> allActivities) {
		this.allActivities = allActivities;
	}




	public Long getMapProjectActivity() {
		return mapProjectActivity;
	}




	public void setMapProjectActivity(Long mapProjectActivity) {
		this.mapProjectActivity = mapProjectActivity;
	}




	public Long getProjectIdLong() {
		return projectIdLong;
	}




	public void setProjectIdLong(Long projectIdLong) {
		this.projectIdLong = projectIdLong;
	}




	public Long getStageIDLong() {
		return stageIDLong;
	}




	public void setStageIDLong(Long stageIDLong) {
		this.stageIDLong = stageIDLong;
	}




	public Long getTaskIDLong() {
		return taskIDLong;
	}




	public void setTaskIDLong(Long taskIDLong) {
		this.taskIDLong = taskIDLong;
	}




	public Long getActivityIDLong() {
		return activityIDLong;
	}




	public void setActivityIDLong(Long activityIDLong) {
		this.activityIDLong = activityIDLong;
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




	public Long getActivitySelected() {
		return activitySelected;
	}




	public void setActivitySelected(Long activitySelected) {
		this.activitySelected = activitySelected;
	}




	public ProjectActivity getActivityData() {
		return activityData;
	}




	public void setActivityData(ProjectActivity activityData) {
		this.activityData = activityData;
	}




	public String getStrActivityDueDate() {
		return strActivityDueDate;
	}




	public void setStrActivityDueDate(String strActivityDueDate) {
		this.strActivityDueDate = strActivityDueDate;
	}




	public String getActivityDataJson() {
		return activityDataJson;
	}




	public void setActivityDataJson(String activityDataJson) {
		this.activityDataJson = activityDataJson;
	}




	public String getActivityDone() {
		return activityDone;
	}




	public void setActivityDone(String activityDone) {
		this.activityDone = activityDone;
	}




	public String getStrDueDate() {
		return strDueDate;
	}




	public void setStrDueDate(String strDueDate) {
		this.strDueDate = strDueDate;
	}




	public String getStrActNote() {
		return strActNote;
	}




	public void setStrActNote(String strActNote) {
		this.strActNote = strActNote;
	}




	public String getJsonDataTmp() {
		return jsonDataTmp;
	}




	public void setJsonDataTmp(String jsonDataTmp) {
		this.jsonDataTmp = jsonDataTmp;
	}




	public String getMsgApp() {
		return msgApp;
	}




	public void setMsgApp(String msgApp) {
		this.msgApp = msgApp;
	}




	public String getProjectID() {
		return projectID;
	}




	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}




	public String getpChargeID() {
		return pChargeID;
	}




	public void setpChargeID(String pChargeID) {
		this.pChargeID = pChargeID;
	}




	public String getPhaseID() {
		return phaseID;
	}




	public void setPhaseID(String phaseID) {
		this.phaseID = phaseID;
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




	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionAttributes = session;
		
	}

	
	

}
