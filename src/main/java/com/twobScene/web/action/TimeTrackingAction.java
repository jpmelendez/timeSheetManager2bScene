package com.twobScene.web.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;







import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;


import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import antlr.CppCodeGenerator;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.twobScene.web.dao.ActivitiesDAO;
import com.twobScene.web.dao.ActivitiesDAOImpl;
import com.twobScene.web.dao.ActivityHomeDAO;
import com.twobScene.web.dao.ActivityHomeDAOImpl;
import com.twobScene.web.dao.MapProjectsPersonChargeDAO;
import com.twobScene.web.dao.MapProjectsPersonChargeDAOImpl;
import com.twobScene.web.dao.ProjectActivityDAO;
import com.twobScene.web.dao.ProjectActivityDAOImpl;
import com.twobScene.web.dao.ProjectsDAO;
import com.twobScene.web.dao.ProjectsDAOImpl;
import com.twobScene.web.dao.StaffDAO;
import com.twobScene.web.dao.StaffDAOImpl;
import com.twobScene.web.dao.StagesDAO;
import com.twobScene.web.dao.StagesDAOImpl;
import com.twobScene.web.dao.TasksDAO;
import com.twobScene.web.dao.TasksDAOImpl;
import com.twobScene.web.dao.TimeTrackingDAO;
import com.twobScene.web.dao.TimeTrackingDAOImpl;
import com.twobScene.web.dto.ActivityHomeDTO;
import com.twobScene.web.dto.ProjectActivitiesDTO;
import com.twobScene.web.dto.TimeSheetDTO;
import com.twobScene.web.dto.projectsDTO;
import com.twobScene.web.model.Activity;
import com.twobScene.web.model.C_Person_Charge;
import com.twobScene.web.model.ProjectActivity;
import com.twobScene.web.model.Projects;
import com.twobScene.web.model.Stages;
import com.twobScene.web.model.Tasks;
import com.twobScene.web.model.TimeTracking;
import com.twobScene.web.model.User;

public class TimeTrackingAction extends ActionSupport implements Action, ModelDriven<TimeTracking>, ServletContextAware,  SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TimeTracking timeTracking = new TimeTracking();
	private Map<String, Object> sessionAttributes;
	private ServletContext ctx;
	private TimeSheetDTO timesheetdto = new TimeSheetDTO();
	private List<projectsDTO> listActiveProjectsByUser;
	private List<Stages> listStagesByProjectID;
	private List<Tasks> listTasksByProjectID;
	private List<Stages> listStages;
	private List<Activity> listAllActivities;
	private List<TimeSheetDTO> listTimeActivities;
	private List<TimeSheetDTO> listtotalTime;
	private List<ProjectActivitiesDTO> listActivityByProjectID;
	private List<ProjectActivitiesDTO> notePerRow;
	private List<TimeSheetDTO> listTimesOnWeek;
	private List<C_Person_Charge> pChargeList;
	private String todayDateStr;
	private ProjectActivity projectActivityData;
	private Projects projectDetail;
	private String projectString;
	private String totalTimeFormated;
	
	/* List per day */
	private Long activitySelected;
	private List<TimeSheetDTO> timesMon;
	private List<TimeSheetDTO> timesTue;
	private List<TimeSheetDTO> timesWed;
	private List<TimeSheetDTO> timesThu;
	private List<TimeSheetDTO> timesFri;
	private List<TimeSheetDTO> timesSat;
	private List<TimeSheetDTO> timesSun;
	
	
	/*Start variables for form*/
	private Long staffID;
	private String projectSelected;
	private String stageSelected;
	private String taskSelected;
	private Long selectedMapActID;
	private List<TimeSheetDTO> timeData;
	private String noteActivity;
	private String pCharge;
	/*end variables for form*/
	
	private String projectNamefromDB;
	private String datefield;
	private String timefield;
	private Long sessionUserID;
	private Double totalTimeUnique;
	private List<ActivityHomeDTO> detailedInfoActivityByID; 
	private C_Person_Charge uniquePCharge;
	private String startDayWeek = "";
	private String endDayWeek = "";
	private String dateSelected = null;
	
	/*Variables for the week time*/
	private String mondayStr;
	private Integer mondayMonth;
	private String tuesdayStr;
	private Integer tuesdayMonth;
	private String wednesdayStr;
	private Integer wednesdayMonth;
	private String thursdayStr;
	private Integer thursdayMonth;
	private String fridayStr;
	private Integer fridayMonth;
	private String saturdayStr;
	private Integer saturdayMonth;
	private String sundayStr;
	private Integer sundayMonth;
	private Integer mondayData;
	private Integer tuesdayData;
	private Integer wednesday;
	private Integer thursdayData;
	private Integer fridayData;
	private Integer saturdayData;
	private Integer sundayData;
	private Integer currentMonth;
	//variables for data already saved
	
	private String monNormal = "";
	private String tueNormal = "";
	private String wedNormal = "";
	private String thuNormal = "";
	private String friNormal = "";
	private String satNormal = "";
	private String sunNormal = "";
	private String weekTime = "";
	
	//previous and next
	private String previousWeekDay = "";
	private String nextWeekDay = "";
	
	private String tabactive = "0";

	@Override
	public TimeTracking getModel() {
		
		return timeTracking;
	}
	
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
		
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionAttributes = session;
		
	}
	
	public String execute(){
		
		User sessionUser = (User) sessionAttributes.get("USER");
		SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		SimpleDateFormat sdfToStr = new SimpleDateFormat("EEE, d MMM, yy", Locale.ENGLISH);
		SimpleDateFormat sdfQuery = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
		SimpleDateFormat sdfToMonth = new SimpleDateFormat("M", Locale.ENGLISH);
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		StaffDAO staffdao = new StaffDAOImpl(sf);
		Date todayDate = null;
		try {
			sessionUserID = sessionUser.getpChargeID();
			
			//if date was selected by the user
			if (dateSelected != null) {
				if ("".equals(dateSelected)) {
					todayDate = getCurrentTime();
				} else {
					todayDate = sdfNormal.parse(dateSelected);
				}
			} else {
				todayDate = getCurrentTime();
			}
			String userCredentials = sessionUser.getCredentials();
			
				uniquePCharge = staffdao.getStaffById(sessionUserID);
				String projectsByUser = getUserProjects(sessionUserID);
				ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
				this.listActiveProjectsByUser = projectsDAO.listActiveProjectsByUser(projectsByUser, userCredentials);
				
			/* getting week data*/ 
			/*Monday*/
			Date mondayDate = getFirstDayWeek(todayDate);
			mondayStr = sdfToStr.format(mondayDate);
			mondayMonth = Integer.parseInt(sdfToMonth.format(mondayDate));
			System.out.println("********** first day of the week: " + mondayStr); 
			/*Tuesday*/
			Date tuesdayDate = getNextDayWeek(mondayDate);
			tuesdayStr = sdfToStr.format(tuesdayDate);
			tuesdayMonth = Integer.parseInt(sdfToMonth.format(tuesdayDate));
			/*wednesday*/
			Date wednesdayDate = getNextDayWeek(tuesdayDate);
			wednesdayStr = sdfToStr.format(wednesdayDate);
			wednesdayMonth = Integer.parseInt(sdfToMonth.format(wednesdayDate));
			/*thursday*/
			Date thursdayDate = getNextDayWeek(wednesdayDate);
			thursdayStr = sdfToStr.format(thursdayDate);
			thursdayMonth = Integer.parseInt(sdfToMonth.format(thursdayDate));
			/*friday*/
			Date fridayDate = getNextDayWeek(thursdayDate);
			fridayStr = sdfToStr.format(fridayDate);
			fridayMonth = Integer.parseInt(sdfToMonth.format(fridayDate));
			/*saturday*/
			Date saturdayDate = getNextDayWeek(fridayDate);
			saturdayStr = sdfToStr.format(saturdayDate);
			saturdayMonth = Integer.parseInt(sdfToMonth.format(saturdayDate));
			/*sunday*/
			Date sundayDate = getNextDayWeek(saturdayDate);
			sundayStr = sdfToStr.format(sundayDate);
			sundayMonth = Integer.parseInt(sdfToMonth.format(sundayDate));
			System.out.println("^^^^^^^^ Last day of the week: " + sundayStr); 
			/*Current Month*/
			currentMonth = Integer.parseInt(sdfToMonth.format(todayDate));
			
			String monQry = sdfQuery.format(mondayDate);
			String tueQry = sdfQuery.format(tuesdayDate);
			String wedQry = sdfQuery.format(wednesdayDate);
			String thuQry = sdfQuery.format(thursdayDate);
			String friQry = sdfQuery.format(fridayDate);
			String satQry = sdfQuery.format(saturdayDate);
			String sunQry = sdfQuery.format(sundayDate);
			
			this.monNormal = sdfNormal.format(mondayDate);
			this.tueNormal = sdfNormal.format(tuesdayDate);
			this.wedNormal = sdfNormal.format(wednesdayDate);
			this.thuNormal = sdfNormal.format(thursdayDate);
			this.friNormal = sdfNormal.format(fridayDate);
			this.satNormal = sdfNormal.format(saturdayDate);
			this.sunNormal = sdfNormal.format(sundayDate);
			
			this.weekTime = monNormal + " - " + sunNormal;
			
			previousWeekDay = sdfNormal.format(getPreviousWeek(mondayDate));
			nextWeekDay = sdfNormal.format(getFollowingWeek(sundayDate));
		
			listTimesOnWeek = getTimesOnWeek(monQry, tueQry, wedQry, thuQry, friQry, satQry, sunQry, sessionUserID);
			listStages = getStages();
			listAllActivities = getFullActivities();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return SUCCESS;
		
	}
	
	public String showProjectTaskList(){
		this.listStagesByProjectID = null;
		this.listTasksByProjectID = null;
		this.listActivityByProjectID = null;
		this.projectDetail = null;
		this.projectNamefromDB = null;
		
		
		
		return SUCCESS;
		
	}
	
	public String timeSheetSubmitForm(){
		
		System.out.println("<--------- public class TimeTrackingAction : timeSheetSubmitForm()------------>");
		SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		SimpleDateFormat sdfQuery = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		User sessionUser = (User) sessionAttributes.get("USER");
		this.sessionUserID = sessionUser.getpChargeID();  
		TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAOImpl(sf);
		try {
			if (startDayWeek.length()>0) {
				Date monStartDate = sdfQuery.parse(startDayWeek);
				Date tueDate = getNextDayWeek(monStartDate);
				Date wedDate = getNextDayWeek(tueDate);
				Date thuDate = getNextDayWeek(wedDate);
				Date friDate = getNextDayWeek(thuDate);
				Date satDate = getNextDayWeek(friDate);
				Date sunEndDate = getNextDayWeek(satDate);
			
			} else {
				timeData = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SUCCESS;
		
	}
		
public String AddOrUpdateTime(){
	
	Long mapProjectActivityID = null;
	int i = 1;
	String msgTime = "";
	String msgNote = "";
	SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	SimpleDateFormat sdfQuery = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
	SimpleDateFormat sdfToStr = new SimpleDateFormat("EEE, d MMM, yy", Locale.ENGLISH);
	List<Long> listOfMapActivities = new ArrayList<Long>();
		try {
			if (timeData != null) {
				System.out.println("!!!! Start to save Time !!!!!");
				Iterator<TimeSheetDTO> iter = timeData.listIterator();
				while (iter.hasNext()) {
					TimeSheetDTO timesheetDto = (TimeSheetDTO) iter.next();
					//evaluating if activity is a valid value
					if (timesheetDto.getActID() != null) {
					
					ProjectActivity pa = getProjectActivityByProjectandActivity(timesheetDto.getProjectID(), timesheetDto.getActID());
					
					if (pa != null) {
						System.out.println(">>>> The Project Activity for this Task aready exist and the ID is :  "+ pa.getProjectActivityID().toString());
						//project activity exist trying to save the time
						mapProjectActivityID = pa.getProjectActivityID();
						listOfMapActivities.add(new Long(mapProjectActivityID));
						msgTime = saveOrUpdateTime(timesheetDto, mapProjectActivityID);
					} else {
						/*getting the full activity data*/
						//project activity does not exist trying to create time and then save the time
						System.out.println(">>>>>>> The Project Activity for this Task NOT exist the ACTIVITY is :  " + timesheetDto.getActID());
						Activity activityFull = getActivityData(timesheetDto.getActID());
						if (activityFull != null) {
							System.out.println("**** ACTIVITY found is :  " + activityFull.toString());
							ProjectActivity projectAct = new ProjectActivity();
							projectAct.setProjectID(timesheetDto.getProjectID());
							projectAct.setStageID(activityFull.getStageID());
							projectAct.setTaskID(activityFull.getTaskID());
							projectAct.setActID(activityFull.getActID());
							mapProjectActivityID = createProjectActivity(projectAct);
							System.out.println("---- This is the New ProjectActivity ID created :::: " + mapProjectActivityID.toString());
							listOfMapActivities.add(new Long(mapProjectActivityID));
							msgTime = saveOrUpdateTime(timesheetDto, mapProjectActivityID);
						}else {
							msgTime = "Data not saved";
						}
						
					}
					
					}else {//the activity value is 0 or null
						//System.out.println("*** The activity has an empty value can not save time ***");
					}
				}	
				System.out.println("!!!! End to save Time !!!!!");
			}
			if (notePerRow != null) {
				System.out.println("!!!! Start to save Notes !!!!!");
				Iterator<ProjectActivitiesDTO> iterNote = notePerRow.listIterator();
				while (iterNote.hasNext()) {
					ProjectActivitiesDTO noteDataPADTO = (ProjectActivitiesDTO) iterNote.next();
					if (noteDataPADTO.getAct_id() != null && noteDataPADTO.getProjectId() != null) {
						msgNote = saveMapProjectActNote(noteDataPADTO);
					} 
					else {
						System.out.println("*** The activity is empty can not save note ***");
						
					}
					
				}
			System.out.println("!!!! End to save Notes !!!!!");
			}
			
			addActionMessage(msgTime + msgNote);
			Set<Long> uniqueMapActID = new HashSet<Long>(listOfMapActivities);
			System.out.println("!!!! Total of ID's: " + listOfMapActivities.size());
			System.out.println("!!!! Unique Id's: : " + uniqueMapActID.size());
			
			//getting the list with the result to show the table with the times per week
			if (startDayWeek.length()>0 && endDayWeek.length() > 0) {
				String monStartDate = sdfQuery.format(sdfNormal.parse(startDayWeek));
				mondayStr = sdfToStr.format(sdfQuery.parse(monStartDate));
				String tueDate = sdfQuery.format(getNextDayWeek(sdfQuery.parse(monStartDate)));
				tuesdayStr = sdfToStr.format(sdfQuery.parse(tueDate));
				String wedDate = sdfQuery.format(getNextDayWeek(sdfQuery.parse(tueDate)));
				wednesdayStr = sdfToStr.format(sdfQuery.parse(wedDate));
				String thuDate = sdfQuery.format(getNextDayWeek(sdfQuery.parse(wedDate)));
				thursdayStr = sdfToStr.format(sdfQuery.parse(thuDate));
				String friDate = sdfQuery.format(getNextDayWeek(sdfQuery.parse(thuDate)));
				fridayStr = sdfToStr.format(sdfQuery.parse(friDate));
				String satDate = sdfQuery.format(getNextDayWeek(sdfQuery.parse(friDate)));
				saturdayStr = sdfToStr.format(sdfQuery.parse(satDate));
				String sunEndDate = sdfQuery.format(getNextDayWeek(sdfQuery.parse(satDate)));
				sundayStr = sdfToStr.format(sdfQuery.parse(sunEndDate));
				
				StringBuilder builder = new StringBuilder("(");
				for(Long s : uniqueMapActID) {
				    builder.append(s.toString());
				    if (i < uniqueMapActID.size()) {
				    	builder.append(", ");
					}
				    i++;
				}
				builder.append(")");
				String activitiesIn = builder.toString();
				//System.out.println("********** String Builder: " + activitiesIn); 
				listtotalTime = getTimesSavedPerWeek(monStartDate, tueDate, wedDate, thuDate, friDate, satDate, sunEndDate, staffID, activitiesIn);
				//System.out.println("********** List with the times size: " + listtotalTime.size()); 
				this.weekTime = mondayStr + " - " + sundayStr;
			} else {
				listtotalTime = null;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return SUCCESS;
}

public String ShowActivityTimes(){
	
	
	SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	User sessionUser = (User) sessionAttributes.get("USER");
	this.sessionUserID = sessionUser.getpChargeID();  
	ActivityHomeDAO ahd = new ActivityHomeDAOImpl(sf);
	TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAOImpl(sf);
	
	try {
		
		this.detailedInfoActivityByID = ahd.getActivityByID(selectedMapActID);
		this.listTimeActivities = timeTrackingDAO.listTimeTrackByActivity(selectedMapActID);
		//this.totalTimeFormated = getTotalTimePerActivity().toString();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return SUCCESS;
}


public String timesSheetsperDay(){
	SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	SimpleDateFormat sdfToStr = new SimpleDateFormat("EEE, d MMM, yy", Locale.ENGLISH);
	SimpleDateFormat sdfQuery = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
	SimpleDateFormat sdfToMonth = new SimpleDateFormat("M", Locale.ENGLISH);
	Date currentWeek = null;
	Boolean searchbyactivity = null;
	try {
		
		//fill up dropdown boxes
		User sessionUser = (User) sessionAttributes.get("USER");
		String userCredentials = sessionUser.getCredentials();
		sessionUserID = sessionUser.getpChargeID();
		String projectsByUser = getUserProjects(sessionUserID);
		this.listActiveProjectsByUser = getActiveProjectsByUser(projectsByUser, userCredentials);
		listStages = getStages();
		listAllActivities = getFullActivities();
		uniquePCharge = getStaffInformation(sessionUserID);
		
		//fill up time list per day
		if (dateSelected != null) {
			if ("".equals(dateSelected)) {
				currentWeek = getCurrentTime();
			} else {
				currentWeek = sdfNormal.parse(dateSelected);
			}
		} else {
			currentWeek = getCurrentTime();
		}
		
		
		/* getting week data*/ 
		/*Monday*/
		Date mondayDate = getFirstDayWeek(currentWeek);
		mondayStr = sdfToStr.format(mondayDate);
		mondayMonth = Integer.parseInt(sdfToMonth.format(mondayDate));
		System.out.println("********** first day of the week: " + mondayStr); 
		/*Tuesday*/
		Date tuesdayDate = getNextDayWeek(mondayDate);
		tuesdayStr = sdfToStr.format(tuesdayDate);
		tuesdayMonth = Integer.parseInt(sdfToMonth.format(tuesdayDate));
		/*wednesday*/
		Date wednesdayDate = getNextDayWeek(tuesdayDate);
		wednesdayStr = sdfToStr.format(wednesdayDate);
		wednesdayMonth = Integer.parseInt(sdfToMonth.format(wednesdayDate));
		/*thursday*/
		Date thursdayDate = getNextDayWeek(wednesdayDate);
		thursdayStr = sdfToStr.format(thursdayDate);
		thursdayMonth = Integer.parseInt(sdfToMonth.format(thursdayDate));
		/*friday*/
		Date fridayDate = getNextDayWeek(thursdayDate);
		fridayStr = sdfToStr.format(fridayDate);
		fridayMonth = Integer.parseInt(sdfToMonth.format(fridayDate));
		/*saturday*/
		Date saturdayDate = getNextDayWeek(fridayDate);
		saturdayStr = sdfToStr.format(saturdayDate);
		saturdayMonth = Integer.parseInt(sdfToMonth.format(saturdayDate));
		/*sunday*/
		Date sundayDate = getNextDayWeek(saturdayDate);
		sundayStr = sdfToStr.format(sundayDate);
		sundayMonth = Integer.parseInt(sdfToMonth.format(sundayDate));
		System.out.println("^^^^^^^^ Last day of the week: " + sundayStr); 
		/*Current Month*/
		currentMonth = Integer.parseInt(sdfToMonth.format(currentWeek));
		
		String monQry = sdfQuery.format(mondayDate);
		String tueQry = sdfQuery.format(tuesdayDate);
		String wedQry = sdfQuery.format(wednesdayDate);
		String thuQry = sdfQuery.format(thursdayDate);
		String friQry = sdfQuery.format(fridayDate);
		String satQry = sdfQuery.format(saturdayDate);
		String sunQry = sdfQuery.format(sundayDate);
		
		this.monNormal = sdfNormal.format(mondayDate);
		this.tueNormal = sdfNormal.format(tuesdayDate);
		this.wedNormal = sdfNormal.format(wednesdayDate);
		this.thuNormal = sdfNormal.format(thursdayDate);
		this.friNormal = sdfNormal.format(fridayDate);
		this.satNormal = sdfNormal.format(saturdayDate);
		this.sunNormal = sdfNormal.format(sundayDate);
		
		this.weekTime = monNormal + " - " + sunNormal;
		
		previousWeekDay = sdfNormal.format(getPreviousWeek(mondayDate));
		nextWeekDay = sdfNormal.format(getFollowingWeek(sundayDate));
		
		//check day selected per specific project or full week
		
		if (activitySelected != null) {
			if ("".equals(activitySelected)) {
				searchbyactivity = false;
			} else {
				searchbyactivity = true;
			}
		} else {
			searchbyactivity = false;
		}
		timesMon = getTimesPerDayData(sessionUserID, userCredentials, monQry, activitySelected, searchbyactivity);
		System.out.println("--------- Monday list size: " + timesMon.size());
		timesTue = getTimesPerDayData(sessionUserID, userCredentials, tueQry, activitySelected, searchbyactivity);
		timesWed = getTimesPerDayData(sessionUserID, userCredentials, wedQry, activitySelected, searchbyactivity);
		timesThu = getTimesPerDayData(sessionUserID, userCredentials, thuQry, activitySelected, searchbyactivity);
		timesFri = getTimesPerDayData(sessionUserID, userCredentials, friQry, activitySelected, searchbyactivity);
		timesSat = getTimesPerDayData(sessionUserID, userCredentials, satQry, activitySelected, searchbyactivity);
		timesSun = getTimesPerDayData(sessionUserID, userCredentials, sunQry, activitySelected, searchbyactivity);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return SUCCESS;
	
}

	private List<projectsDTO> getActiveProjectsByUser(String projectsByUser, String userCredentials){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		List<projectsDTO> projectsList = new ArrayList<>();
		try {
			ProjectsDAO projectsDAO = new ProjectsDAOImpl(sf);
			projectsList = projectsDAO.listActiveProjectsByUser(projectsByUser, userCredentials);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return projectsList;
		
		
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
	
	private C_Person_Charge getStaffInformation(Long pchargeID){
		
		C_Person_Charge staffInfo = new C_Person_Charge();
		
		try {
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			StaffDAO staffdao = new StaffDAOImpl(sf);
			staffInfo = staffdao.getStaffById(pchargeID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return staffInfo;
	}
	
	private List<TimeSheetDTO> getTimesSavedPerWeek(String monStartDate, String tueDate, String wedDate, String thuDate, String friDate, String satDate, String sunEndDate, Long pCharge, String activitiesIn){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAOImpl(sf);
		
		try {
			listtotalTime = timeTrackingDAO.getTimesAfterSave(monStartDate, tueDate, wedDate, thuDate, friDate, satDate, sunEndDate, pCharge, activitiesIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listtotalTime;
	}
	
	private List<TimeSheetDTO> getTimesOnWeek(String monStartDate, String tueDate, String wedDate, String thuDate, String friDate, String satDate, String sunEndDate, Long pCharge){
		
		List<TimeSheetDTO> timesOnWeek = new ArrayList<TimeSheetDTO>();
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAOImpl(sf);
		
		try {
			timesOnWeek = timeTrackingDAO.getAllTimesPerWeek(monStartDate, tueDate, wedDate, thuDate, friDate, satDate, sunEndDate, pCharge);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return timesOnWeek;
	}
	
	
	private List<Stages> getStagesByProjectID(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectActivityDAO projectActivityDAO = new ProjectActivityDAOImpl(sf);
		
		try {
			//this.listStagesByProjectID = projectActivityDAO.getStagesByProjectID(projectSelected);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}
		
		
		return listStagesByProjectID;
		
	}
	
	private List<Tasks> getTasksByProjectID(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectActivityDAO projectActivityDAO = new ProjectActivityDAOImpl(sf);
		
		try {
			//this.listTasksByProjectID = projectActivityDAO.getTasksByProjectID(projectSelected);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}
		
		
		return listTasksByProjectID;
		
	}
	
	private List<ProjectActivitiesDTO> getActivitiesByProjectID(){
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectActivityDAO projectActivityDAO = new ProjectActivityDAOImpl(sf);
		
		try {
			//this.listActivityByProjectID = projectActivityDAO.getActByProjectID(projectSelected);
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}
		
		
		return listActivityByProjectID;
		
	}
	
	private Projects getProjectDetail() {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectsDAO projectsDao = new ProjectsDAOImpl(sf);
		try {
			//this.projectDetail = projectsDao.getProjectById(projectSelected);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}
		
		return projectDetail;
	}
	
	private String saveOrUpdateTime(TimeSheetDTO timesheetDto, Long mapProjecActivityID){
		TimeTracking timeTrackingTmp = new TimeTracking();
		TimeTracking timeT = new TimeTracking();
		TimeTracking timeTrackingFromDB = new TimeTracking();
		String msgTime = "";
		User sessionUser = (User) sessionAttributes.get("USER");
		Date currentDate = getCurrentTime();
		SimpleDateFormat sdfToStr = new SimpleDateFormat("EEE, d MMM, yy", Locale.ENGLISH);
		SimpleDateFormat sdfNormal = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String pattern = "[0-9]+(\\.[0-9][0-9]?)?";
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAOImpl(sf);
		try {
			Date dateTime = sdfNormal.parse(timesheetDto.getTrackingDate());
			Long pchargeID = timesheetDto.getpChargeID();
			timeTrackingFromDB = getTimeRecordByDate(mapProjecActivityID, pchargeID, dateTime);
			
			if (timeTrackingFromDB != null) {
				System.out.println("----- Time did exist in DB ----- ");
				System.out.println("----- Date: " + timeTrackingFromDB.getTrackingDate());
				System.out.println("----- Tacking ID: " + timeTrackingFromDB.getTrackID());
				System.out.println("----- Person Charge ID: " + timeTrackingFromDB.getpChargeID());
				System.out.println("----- Time: " + timeTrackingFromDB.getTrackTimeDec());
				System.out.println("---------------------------------------------");
				//The time in DB exist but the time to submit is null so the time need to be deleted
				if (timesheetDto.getTrackingTime().length() == 0) {
					msgTime = deleteRecordWithNullTime(timeTrackingFromDB);
					System.out.println("***** DELETE : TimeTracking exist and the time from the app is null: Time was deleted ****");
				}
				else {
						System.out.println("--------> data received: " + timesheetDto.getTrackingTime().toString());
						if (timesheetDto.getTrackingTime().matches(pattern)) {
						Double timeSubmitted = Double.parseDouble(timesheetDto.getTrackingTime());
						String notePerHour = timesheetDto.getTrackingNote();
						String notePerHourFromDB = (timeTrackingFromDB.getTrackingNote() != null)?timeTrackingFromDB.getTrackingNote():"";
						Double timeInDB = (timeTrackingFromDB != null)?timeTrackingFromDB.getTrackTimeDec():0;
						int retval = Double.compare(timeSubmitted, timeInDB);
						     if(retval != 0 || !notePerHour.equals(notePerHourFromDB)) {
						    	  //new time
						    	  timeTrackingFromDB.setTrackTimeDec(timeSubmitted);
						    	  timeTrackingFromDB.setCreatedDate(currentDate);
						    	  //notes only for Day module
						    	  timeTrackingFromDB.setTrackingNote(notePerHour);
								  timeTrackingTmp = timeTrackingDAO.addTimeTrack(timeTrackingFromDB);
								  System.out.println("-> Time submitted is a valid pattern <-");
								  System.out.println("@@@@@ UPDATE: TimeTracking exist and is different from the Db: Time Updated @@@@");
								  msgTime = "Time updated successfully ";
						     } 
						     else {
								System.out.println("XXXX TimeTracking exist and is the same from the Db: Nothing to do XXXXX");
								timeTrackingTmp = null;
						     }
						}
						else {
							System.out.println("XXXXX Time submitted is NOT a valid pattern: Can not be saved XXXX");
							msgTime = "The time was not saved. ";
						}
				}
			}
			//time does NOT exist in DB
			else {
				if (timesheetDto.getTrackingTime().length() == 0) {
				System.out.println("XXXXX TIME NULL: Time to submit is null and there is no record in the DB: Nothing to do XXXXX");	
				}
				else {
					System.out.println("--------> data received: " + timesheetDto.getTrackingTime().toString());
					if (timesheetDto.getTrackingTime().matches(pattern)) {
					 Double timeSubmitted = Double.parseDouble(timesheetDto.getTrackingTime());
					 
					  //setting parameters of the model
					  timeT.setTrackID(null);
					  timeT.setMapPAID(mapProjecActivityID);
					  timeT.setpChargeID(timesheetDto.getpChargeID());
					  timeT.setTrackingDate(dateTime);
					  timeT.setTrackTimeDec(timeSubmitted);
					  timeT.setTrackingApproved(false);
					  timeT.setCreatedUser(sessionUser.getUsername());
					  timeT.setCreatedDate(currentDate);
					  //notes per day
					  timeT.setTrackingNote(timesheetDto.getTrackingNote());
					  timeTrackingTmp = timeTrackingDAO.addTimeTrack(timeT);
					  System.out.println("--> Time submitted is a valid pattern <---");
					  System.out.println("@@@@@ New Time Saved @@@@@");
					  msgTime = "Time saved successfully ";
					}
					else {
					System.out.println("XXXXXXX Time submitted is NOT a valid pattern: Can not be saved XXXXXXXX");
					msgTime = "The time was not saved. ";
					}
				}
			}						 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msgTime;
		
		
	}
	
	
	
	
	private Date getCurrentTime(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
		
		
	}
	
	private Double getTotalTimePerActivity(){
		
		Long timeHrs = null;
		Long timeMinutes = null;
		String totalTimeString = "";
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		TimeTrackingDAO timeTrackingDAO = new TimeTrackingDAOImpl(sf);
		
		
		try {
			this.totalTimeUnique = timeTrackingDAO.totalTimePerActivity(selectedMapActID);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return totalTimeUnique;
	}
	
	
	private Date getFirstDayWeek(Date currentDateDate){
		Date firstDayWeek;
		Calendar c = Calendar.getInstance();
		c.setTime(currentDateDate);
		c.set(Calendar.DAY_OF_WEEK, c.getActualMinimum(Calendar.DAY_OF_WEEK));
		c.add(Calendar.DATE, 1);
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
	private Date getNextDayWeek(Date date){
		Date nextDayWeek;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		nextDayWeek = c.getTime();
		
		return nextDayWeek;
	}
	
	private Date getPreviousWeek(Date date){
		Date previousWeek;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -3);
		previousWeek = c.getTime();
		
		return previousWeek;
	}
	
	private Date getFollowingWeek(Date date){
		Date nextDayWeek;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 3);
		nextDayWeek = c.getTime();
		
		return nextDayWeek;
	}
	
	private ProjectActivity getProjectActivityFullData(Long mapActivityID){
		ProjectActivity pAct = new ProjectActivity();
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectActivityDAO projectActdto = new ProjectActivityDAOImpl(sf);
		try {
			pAct = projectActdto.getProjectActByMapID(mapActivityID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pAct;
		
	}
	
	private String saveMapProjectActNote(ProjectActivitiesDTO noteDataPADTO){
		ProjectActivity pAct = new ProjectActivity();
		String msgTime = "";
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectActivityDAO projectActdto = new ProjectActivityDAOImpl(sf);
		try {
			if (noteDataPADTO != null) {
					ProjectActivity pa = getProjectActivityByProjectandActivity(noteDataPADTO.getProjectId(), noteDataPADTO.getAct_id());
					
					if (pa != null) {
						String noteToUpdate = (noteDataPADTO.getMapActDesc() != null)?noteDataPADTO.getMapActDesc():"";
						pa.setDescPA(noteToUpdate);
						pAct = projectActdto.updateProjectActivity(pa);
						if (pAct != null) {
							msgTime = " Note saved successfully ";
						} else {
							msgTime = "The Note was not saved. ";
						}
						
					}else {
						msgTime = "The Note was not saved. ";
					}
					
				
			} 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msgTime;
		
	}
	
	private Long createProjectActivity(ProjectActivity projectActivity){
		Long newProjectActivityID = null;
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			
			ProjectActivityDAO projectActDao = new ProjectActivityDAOImpl(sf);
			newProjectActivityID = projectActDao.addProjectActivityOneByOne(projectActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newProjectActivityID;
		
	}
	
	private ProjectActivity getProjectActivityByProjectandActivity(Long projectID, Long activityID){
		ProjectActivity pAct = new ProjectActivity();
		
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ProjectActivityDAO projectActdto = new ProjectActivityDAOImpl(sf);
		try {
			pAct = projectActdto.getProjectActivityByProjectandActivity(projectID, activityID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pAct;
		
	}
	
	private Activity getActivityData(Long activityID){
		Activity activity = new Activity();
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			ActivitiesDAO actDAO = new ActivitiesDAOImpl(sf);
			activity = actDAO.showActivityById(activityID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return activity;
		
	}
	
	private String deleteRecordWithNullTime(TimeTracking timeTrackingFromDB){
		String msgTime = "";
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		
		try {
			TimeTrackingDAO timetrackingDao = new TimeTrackingDAOImpl(sf);
			
			msgTime = timetrackingDao.deleteTimeTracking(timeTrackingFromDB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgTime;
			
	}
	
	private TimeTracking getTimeRecordByDate (Long mapProjecActivityID, Long pchargeID, Date dateTime){
		TimeTracking trackingdata = new TimeTracking();
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		try {
			TimeTrackingDAO timetrackingDao = new TimeTrackingDAOImpl(sf);
			trackingdata = timetrackingDao.searchTimeByDatePChargeMapAct(mapProjecActivityID, pchargeID, dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return trackingdata;	
	}
	
private List<Stages> getStages(){
		
		List<Stages> listStagesTmp = null;
		
		
		try {
			
			SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
			
			//gets all stages
			
			StagesDAO stagedao = new StagesDAOImpl(sf);
			listStagesTmp = stagedao.getStages();
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Oops.. There is a problem with the program. try again please.");
		}
		
		
		return listStagesTmp;
		
	}

private List<Activity> getFullActivities(){
	
	List<Activity> allActivitiesTMP = new ArrayList<Activity>();
	try {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ActivitiesDAO actDao = new ActivitiesDAOImpl(sf);
		allActivitiesTMP = actDao.getAllActivities();
		
		
	} catch (Exception e) {
		e.printStackTrace();
		addActionError("Oops.. There is a problem with the program. try again please.");
	}
	
	
	
	return allActivitiesTMP;
	
}

private List<TimeSheetDTO> getTimesPerDayData(Long userid, String priviledge, String datetime, Long mapActivity, Boolean searchbyactivity){
	
	List<TimeSheetDTO> listperday = new ArrayList<>();
	SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
	try {
		
		TimeTrackingDAO timetrackingDao = new TimeTrackingDAOImpl(sf);
		listperday = timetrackingDao.getTimesPerDay(userid, priviledge, datetime, mapActivity, searchbyactivity);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return listperday;
}
	
	
	

	public List<projectsDTO> getListActiveProjectsByUser() {
		return listActiveProjectsByUser;
	}

	public void setListActiveProjectsByUser(
			List<projectsDTO> listActiveProjectsByUser) {
		this.listActiveProjectsByUser = listActiveProjectsByUser;
	}


	public List<Stages> getListStagesByProjectID() {
		return listStagesByProjectID;
	}

	public void setListStagesByProjectID(List<Stages> listStagesByProjectID) {
		this.listStagesByProjectID = listStagesByProjectID;
	}

	public List<Tasks> getListTasksByProjectID() {
		return listTasksByProjectID;
	}

	public void setListTasksByProjectID(List<Tasks> listTasksByProjectID) {
		this.listTasksByProjectID = listTasksByProjectID;
	}

	public List<ProjectActivitiesDTO> getListActivityByProjectID() {
		return listActivityByProjectID;
	}

	public void setListActivityByProjectID(
			List<ProjectActivitiesDTO> listActivityByProjectID) {
		this.listActivityByProjectID = listActivityByProjectID;
	}

	public void setProjectDetail(Projects projectDetail) {
		this.projectDetail = projectDetail;
	}

	public String getProjectNamefromDB() {
		return projectNamefromDB;
	}

	public void setProjectNamefromDB(String projectNamefromDB) {
		this.projectNamefromDB = projectNamefromDB;
	}

	
	public Long getSelectedMapActID() {
		return selectedMapActID;
	}

	public void setSelectedMapActID(Long selectedMapActID) {
		this.selectedMapActID = selectedMapActID;
	}

	public List<ActivityHomeDTO> getDetailedInfoActivityByID() {
		return detailedInfoActivityByID;
	}

	public void setDetailedInfoActivityByID(
			List<ActivityHomeDTO> detailedInfoActivityByID) {
		this.detailedInfoActivityByID = detailedInfoActivityByID;
	}

	public String getDatefield() {
		return datefield;
	}

	public void setDatefield(String datefield) {
		this.datefield = datefield;
	}

	public String getTimefield() {
		return timefield;
	}

	public void setTimefield(String timefield) {
		this.timefield = timefield;
	}

	public Long getSessionUserID() {
		return sessionUserID;
	}

	public void setSessionUserID(Long sessionUserID) {
		this.sessionUserID = sessionUserID;
	}

	public List<TimeSheetDTO> getListTimeActivities() {
		return listTimeActivities;
	}

	public void setListTimeActivities(List<TimeSheetDTO> listTimeActivities) {
		this.listTimeActivities = listTimeActivities;
	}

	public List<TimeSheetDTO> getListtotalTime() {
		return listtotalTime;
	}

	public void setListtotalTime(List<TimeSheetDTO> listtotalTime) {
		this.listtotalTime = listtotalTime;
	}

	public ProjectActivity getProjectActivityData() {
		return projectActivityData;
	}

	public void setProjectActivityData(ProjectActivity projectActivityData) {
		this.projectActivityData = projectActivityData;
	}

	public Double getTotalTimeUnique() {
		return totalTimeUnique;
	}

	public void setTotalTimeUnique(Double totalTimeUnique) {
		this.totalTimeUnique = totalTimeUnique;
	}

	public String getTotalTimeFormated() {
		return totalTimeFormated;
	}

	public void setTotalTimeFormated(String totalTimeFormated) {
		this.totalTimeFormated = totalTimeFormated;
	}

	public String getProjectString() {
		return projectString;
	}

	public void setProjectString(String projectString) {
		this.projectString = projectString;
	}

	public List<C_Person_Charge> getpChargeList() {
		return pChargeList;
	}

	public void setpChargeList(List<C_Person_Charge> pChargeList) {
		this.pChargeList = pChargeList;
	}

	public String getTodayDateStr() {
		return todayDateStr;
	}

	public void setTodayDateStr(String todayDateStr) {
		this.todayDateStr = todayDateStr;
	}

	public C_Person_Charge getUniquePCharge() {
		return uniquePCharge;
	}

	public void setUniquePCharge(C_Person_Charge uniquePCharge) {
		this.uniquePCharge = uniquePCharge;
	}

	public TimeTracking getTimeTracking() {
		return timeTracking;
	}

	public void setTimeTracking(TimeTracking timeTracking) {
		this.timeTracking = timeTracking;
	}

	public Map<String, Object> getSessionAttributes() {
		return sessionAttributes;
	}

	public void setSessionAttributes(Map<String, Object> sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}

	public String getMondayStr() {
		return mondayStr;
	}

	public void setMondayStr(String mondayStr) {
		this.mondayStr = mondayStr;
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

	public Integer getSundayMonth() {
		return sundayMonth;
	}

	public void setSundayMonth(Integer sundayMonth) {
		this.sundayMonth = sundayMonth;
	}

	public Integer getMondayData() {
		return mondayData;
	}

	public void setMondayData(Integer mondayData) {
		this.mondayData = mondayData;
	}

	public Integer getTuesdayData() {
		return tuesdayData;
	}

	public void setTuesdayData(Integer tuesdayData) {
		this.tuesdayData = tuesdayData;
	}

	public Integer getWednesday() {
		return wednesday;
	}

	public void setWednesday(Integer wednesday) {
		this.wednesday = wednesday;
	}

	public Integer getThursdayData() {
		return thursdayData;
	}

	public void setThursdayData(Integer thursdayData) {
		this.thursdayData = thursdayData;
	}

	public Integer getFridayData() {
		return fridayData;
	}

	public void setFridayData(Integer fridayData) {
		this.fridayData = fridayData;
	}

	public Integer getSaturdayData() {
		return saturdayData;
	}

	public void setSaturdayData(Integer saturdayData) {
		this.saturdayData = saturdayData;
	}

	public Integer getSundayData() {
		return sundayData;
	}

	public void setSundayData(Integer sundayData) {
		this.sundayData = sundayData;
	}

	public Integer getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(Integer currentMonth) {
		this.currentMonth = currentMonth;
	}

	public Long getStaffID() {
		return staffID;
	}

	public void setStaffID(Long staffID) {
		this.staffID = staffID;
	}

	

	public String getProjectSelected() {
		return projectSelected;
	}

	public void setProjectSelected(String projectSelected) {
		this.projectSelected = projectSelected;
	}

	public String getStageSelected() {
		return stageSelected;
	}

	public void setStageSelected(String stageSelected) {
		this.stageSelected = stageSelected;
	}

	public String getTaskSelected() {
		return taskSelected;
	}

	public void setTaskSelected(String taskSelected) {
		this.taskSelected = taskSelected;
	}

	public List<TimeSheetDTO> getTimeData() {
		return timeData;
	}

	public void setTimeData(List<TimeSheetDTO> timeData) {
		this.timeData = timeData;
	}

	public String getNoteActivity() {
		return noteActivity;
	}

	public void setNoteActivity(String noteActivity) {
		this.noteActivity = noteActivity;
	}

	public String getpCharge() {
		return pCharge;
	}

	public void setpCharge(String pCharge) {
		this.pCharge = pCharge;
	}

	public List<ProjectActivitiesDTO> getNotePerRow() {
		return notePerRow;
	}

	public void setNotePerRow(List<ProjectActivitiesDTO> notePerRow) {
		this.notePerRow = notePerRow;
	}

	public String getStartDayWeek() {
		return startDayWeek;
	}

	public void setStartDayWeek(String startDayWeek) {
		this.startDayWeek = startDayWeek;
	}

	public String getEndDayWeek() {
		return endDayWeek;
	}

	public void setEndDayWeek(String endDayWeek) {
		this.endDayWeek = endDayWeek;
	}

	

	public List<TimeSheetDTO> getListTimesOnWeek() {
		return listTimesOnWeek;
	}

	public void setListTimesOnWeek(List<TimeSheetDTO> listTimesOnWeek) {
		this.listTimesOnWeek = listTimesOnWeek;
	}

	public String getDateSelected() {
		return dateSelected;
	}

	public void setDateSelected(String dateSelected) {
		this.dateSelected = dateSelected;
	}

	public List<Stages> getListStages() {
		return listStages;
	}

	public void setListStages(List<Stages> listStages) {
		this.listStages = listStages;
	}

	public List<Activity> getListAllActivities() {
		return listAllActivities;
	}

	public void setListAllActivities(List<Activity> listAllActivities) {
		this.listAllActivities = listAllActivities;
	}

	public String getMonNormal() {
		return monNormal;
	}

	public void setMonNormal(String monNormal) {
		this.monNormal = monNormal;
	}

	public String getTueNormal() {
		return tueNormal;
	}

	public void setTueNormal(String tueNormal) {
		this.tueNormal = tueNormal;
	}

	public String getWedNormal() {
		return wedNormal;
	}

	public void setWedNormal(String wedNormal) {
		this.wedNormal = wedNormal;
	}

	public String getThuNormal() {
		return thuNormal;
	}

	public void setThuNormal(String thuNormal) {
		this.thuNormal = thuNormal;
	}

	public String getFriNormal() {
		return friNormal;
	}

	public void setFriNormal(String friNormal) {
		this.friNormal = friNormal;
	}

	public String getSatNormal() {
		return satNormal;
	}

	public void setSatNormal(String satNormal) {
		this.satNormal = satNormal;
	}

	public String getSunNormal() {
		return sunNormal;
	}

	public void setSunNormal(String sunNormal) {
		this.sunNormal = sunNormal;
	}

	public String getWeekTime() {
		return weekTime;
	}

	public void setWeekTime(String weekTime) {
		this.weekTime = weekTime;
	}

	public Long getActivitySelected() {
		return activitySelected;
	}

	public void setActivitySelected(Long activitySelected) {
		this.activitySelected = activitySelected;
	}

	public List<TimeSheetDTO> getTimesMon() {
		return timesMon;
	}

	public void setTimesMon(List<TimeSheetDTO> timesMon) {
		this.timesMon = timesMon;
	}

	public List<TimeSheetDTO> getTimesTue() {
		return timesTue;
	}

	public void setTimesTue(List<TimeSheetDTO> timesTue) {
		this.timesTue = timesTue;
	}

	public List<TimeSheetDTO> getTimesWed() {
		return timesWed;
	}

	public void setTimesWed(List<TimeSheetDTO> timesWed) {
		this.timesWed = timesWed;
	}

	public List<TimeSheetDTO> getTimesThu() {
		return timesThu;
	}

	public void setTimesThu(List<TimeSheetDTO> timesThu) {
		this.timesThu = timesThu;
	}

	public List<TimeSheetDTO> getTimesFri() {
		return timesFri;
	}

	public void setTimesFri(List<TimeSheetDTO> timesFri) {
		this.timesFri = timesFri;
	}

	public List<TimeSheetDTO> getTimesSat() {
		return timesSat;
	}

	public void setTimesSat(List<TimeSheetDTO> timesSat) {
		this.timesSat = timesSat;
	}

	public List<TimeSheetDTO> getTimesSun() {
		return timesSun;
	}

	public void setTimesSun(List<TimeSheetDTO> timesSun) {
		this.timesSun = timesSun;
	}

	public String getPreviousWeekDay() {
		return previousWeekDay;
	}

	public void setPreviousWeekDay(String previousWeekDay) {
		this.previousWeekDay = previousWeekDay;
	}

	public String getNextWeekDay() {
		return nextWeekDay;
	}

	public void setNextWeekDay(String nextWeekDay) {
		this.nextWeekDay = nextWeekDay;
	}

	public String getTabactive() {
		return tabactive;
	}

	public void setTabactive(String tabactive) {
		this.tabactive = tabactive;
	}

	
	
	



}
