package com.twobScene.web.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.twobScene.web.dto.ProjectActivitiesDTO;
import com.twobScene.web.helper.ProjectActivitiesVH;
import com.twobScene.web.model.Activity;
import com.twobScene.web.model.C_Person_Charge;
import com.twobScene.web.model.MapStaffActivity;
import com.twobScene.web.model.ProjectActivity;
import com.twobScene.web.model.ProjectActivityStatus;
import com.twobScene.web.model.Stages;
import com.twobScene.web.model.Tasks;
import com.twobScene.web.query.ProjectsActivitiesQRY;

public class ProjectActivityDAOImpl implements ProjectActivityDAO {
	
	/**
	 * 
	 */
	
	private SessionFactory sf;
	private Date cTime;
	Boolean emptyList = null;
	
	
	public ProjectActivityDAOImpl( SessionFactory sf){
		this.sf = sf;
	}

	
	@Override
	public ProjectActivity addProjectActivity(
			List<ProjectActivity> listActSelected) {
			int totalRecords = listActSelected.size();
			int numRecordsProcessed = 0;
			int numRecordsNOTSaved = 0;
			
			
			System.out.println("<<<<<public class ProjectActivityDAOImpl>>>>>>");
			System.out.println("Total Activities to be inserted: " + totalRecords);
			Iterator<ProjectActivity> it = listActSelected.iterator();
			while (it.hasNext()) {
				try {
					ProjectActivity projectActivity = (ProjectActivity) it.next();
					System.out.println("Activity to be inserted : Act ID : " + projectActivity.getActID() + " project ID : " + projectActivity.getProjectID());
					if (findActivity(projectActivity.getProjectID(), projectActivity.getStageID(), 
							projectActivity.getTaskID(), projectActivity.getActID()).equals(true)) {
						
						Session session = sf.openSession();
						Transaction tx = session.beginTransaction();
						Boolean setTrue = true;
						this.cTime = getCurrentTime();
						projectActivity.setCreatedDate(cTime);
						projectActivity.setCheckedPA(setTrue);
						projectActivity.setActivityDone(false);
						
						try {
							session.save(projectActivity);
							numRecordsProcessed++;
							
							session.flush();
							
						} catch (Exception e) {
							e.printStackTrace();
							session.flush();
							tx.commit();
							throw new HibernateException("Cannot save project activity", e);
						}finally{
							tx.commit();
							session.close();
						}
							
					}else{
						System.out.println("Activity aready exist: PROJECT_ID : " + projectActivity.getProjectID() + " ACTIVITY_ID: " + projectActivity.getActID());
						numRecordsNOTSaved++;
					}
					
				} catch (Exception e) {
					throw new HibernateException("Cannot save project activity", e);
					
				}
			}
        System.out.println("Number of records(Activities) processed: " + numRecordsProcessed);
        System.out.println("Number of records(Activities) Not saved: " + numRecordsNOTSaved);
		return null;
	}
	
	

	@Override
	public ProjectActivity deleteProjectActivity(Long projectActivityID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        
        try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProjectActivity> getProjectActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stages> getAllStages() {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<Stages> listStages = null;
        try {
        	listStages = session.createQuery("from Stages order by stagePriority").list();
        	session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listStages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tasks> getAllTasks() {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<Tasks> listTasks = null;
        
        try {
			
        	listTasks = session.createQuery("from Tasks order by stageID, taskPriority").list();
        	session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listTasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getAllActivities() {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        
        List<Activity> listActivities = null;
        try {
			
        	listActivities = session.createQuery("from Activity order by stageID, taskID, actPriority").list();
	        	session.flush();
			} catch (Exception e) {
				e.printStackTrace();
				tx.commit();
				session.close();
			}finally{
				tx.commit();
				session.close();
			}
        
		return listActivities;
	}

	private Date getCurrentTime(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
		
		
	}
	
	@SuppressWarnings("unchecked")
	private Boolean findActivity(Long projectID, Long stageID, Long taskID, Long actID) throws Exception{
		
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<ProjectActivity> projectActivity = null;
        
try {
	    	
	    	
	    	Query query = session.createQuery("FROM ProjectActivity WHERE projectID = :projectID and stageID = :stageID and taskID = :taskID and actID = :actID");
	    	query.setParameter("projectID", projectID);
	    	query.setParameter("stageID", stageID);
	    	query.setParameter("taskID", taskID);
	    	query.setParameter("actID", actID);
	    	projectActivity = query.list();
	         
	    	 session.flush();
	         
	    } catch (HibernateException e) {
	    	
	        e.printStackTrace();
			session.flush();
			tx.commit();
			session.close();
	    }finally{
	    	tx.commit();
	    	session.close();
			
	    }
        
		System.out.println("Activity found in DB : Size: " + projectActivity.size() + " Value : " + projectActivity.isEmpty());
		return projectActivity.isEmpty();
		
	}

	@Override
	public List<ProjectActivitiesDTO> getActivitiesChecked(Long projectID) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ProjectActivitiesDTO> activitiesChecked = new ArrayList<ProjectActivitiesDTO>();
		
		ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
		
		
		
		try {
			
			Query query = session.createSQLQuery(qry.getActivityCheckedByProjectID(projectID));
			emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					activitiesChecked.add(helper.getAllActivitiesChecked(obj));
				}
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getActivitiesChecked(Long projectID): Project ID: " + projectID 
						+  "Activities Cheched size: " + activitiesChecked.size());
				
			} else {
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getActivitiesChecked(Long projectID): NO ACTIVITIES ON CATALOG ");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			session.flush();
			tx.commit();
			session.close();
		}finally{
			session.flush();
			tx.commit();
			session.close();
		}
		return activitiesChecked;
	}


	@Override
	public List<Stages> getStagesByProjectID(Long projectID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<Stages> listStagesByProjID = new ArrayList<Stages>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
        try {
        	Query query = session.createSQLQuery(qry.searchStageBYProjectID(projectID));
        	
        	emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					listStagesByProjID.add(helper.getStageByProjectID(obj));
				}
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getStagesByProjectID: Project ID: " + projectID 
						+  "Activities Cheched size: " + listStagesByProjID.size());
				
			} else {
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getStagesByProjectID(Long projectID): NO STAGE for project :  " + projectID);
				
			}
    
        	session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listStagesByProjID;
	}


	@Override
	public List<Tasks> getTasksByProjectID(Long projectID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<Tasks> listTasksByProjID = new ArrayList<Tasks>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
        try {
        	Query query = session.createSQLQuery(qry.searchTaskBYProjectID(projectID));
        	
        	emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					listTasksByProjID.add(helper.getTasksByProjectID(obj));
				}
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getTasksByProjectID(Long projectID): Project ID: " + projectID 
						+  "Activities Cheched size: " + listTasksByProjID.size());
				
			} else {
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getTasksByProjectID(Long projectID): NO TAsks for project :  " + projectID);
				
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listTasksByProjID;
	}
	

	@Override
	public List<Tasks> getTasksByProjectIDandStageID(Long projectID,
			Long stageID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<Tasks> listTasksByProjIDandStageID = new ArrayList<Tasks>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
        try {
        	Query query = session.createSQLQuery(qry.searchTaskBYProjectIDandStageID(projectID, stageID));
        	
        	emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					listTasksByProjIDandStageID.add(helper.getTasksByProjectID(obj));
				}
				
				
			} else {
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getTasksByProjectID(Long projectID): NO TAsks for project :  " + projectID);
				
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listTasksByProjIDandStageID;
	}


	@Override
	public List<ProjectActivitiesDTO> getActByProjectID(Long projectID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<ProjectActivitiesDTO> listActivityByProjID = new ArrayList<ProjectActivitiesDTO>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
        try {
        	Query query = session.createSQLQuery(qry.searchActivityBYProjectID(projectID));
        	
        	emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					listActivityByProjID.add(helper.getActivityByProjectID(obj));
				}
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getActByProjectID(Long projectID): Project ID: " + projectID 
						+  "Activities Cheched size: " + listActivityByProjID.size());
				
			} else {
				System.out.println("------> public class ProjectActivityDAOImpl ---------->");
				System.out.println("------> getActByProjectID(Long projectID): NO TAsks for project :  " + projectID);
				
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listActivityByProjID;
	}


	@Override
	public ProjectActivity getProjectActByMapID(Long projectActivityID) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		ProjectActivity projectActivity = new ProjectActivity();
		
		try {
			
			projectActivity = (ProjectActivity) session.get(ProjectActivity.class, projectActivityID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return projectActivity;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MapStaffActivity> getMapStaffByMapID(Long projectActivityID) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		List<MapStaffActivity> mapStaffAct = new ArrayList<MapStaffActivity>();
		
		try {
			
			Query query = session.createQuery("FROM MapStaffActivity WHERE mapProjectActID = :projectActivityID ORDER BY staffID");
	    	query.setParameter("projectActivityID", projectActivityID);
	    	mapStaffAct = query.list();
			session.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return mapStaffAct;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectActivityStatus> getProjectActivityStatus() {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        
        List<ProjectActivityStatus> listPAStatus = null;
        try {
			
        	listPAStatus = session.createQuery("from ProjectActivityStatus order by ptStatusID").list();
	        	session.flush();
			} catch (Exception e) {
				e.printStackTrace();
				tx.commit();
				session.close();
			}finally{
				tx.commit();
				session.close();
			}
        
		return listPAStatus;
	}


	@Override
	public List<ProjectActivitiesDTO> getProjectActivityDetail(Long selectedMapActID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<ProjectActivitiesDTO> listProjActDetail = new ArrayList<ProjectActivitiesDTO>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
		try {
			Query query = session.createSQLQuery(qry.getProjectActDetails(selectedMapActID));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				listProjActDetail.add(helper.getProjectActivityDetail(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
		
		return listProjActDetail;
	}


	@Override
	public List<C_Person_Charge> getProjectActivityStaff(Long selectedMapActID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<C_Person_Charge> listPAStaff = new ArrayList<C_Person_Charge>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
		try {
			Query query = session.createSQLQuery(qry.getProjectActStaff(selectedMapActID));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				listPAStaff.add(helper.getProjectActivityStaff(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
		
		return listPAStaff;
	}


	@Override
	public ProjectActivity updateProjectActivity(ProjectActivity projectActivity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		this.cTime = getCurrentTime();
		projectActivity.setCreatedDate(cTime);
		
		try {
			session.update(projectActivity);
			
			session.flush();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
		System.out.println("*++++++++++++ Activity updated succesfully DAO MASG: MAP_ID: " + projectActivity.getProjectActivityID() + " ProjectID : " + projectActivity.getProjectID());
		return projectActivity;
	}


	@Override
	public List<MapStaffActivity> insertProjectActiviyStaff(
			List<Long> ListPAStaffToInsert, Long selectedMapActID) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		if (!ListPAStaffToInsert.isEmpty()) {
			Iterator<Long> it = ListPAStaffToInsert.iterator();
			while (it.hasNext()) {
				Long mapStaffActivity = (Long) it.next();
				this.cTime = getCurrentTime();
				
				try {
					
					session.save(mapStaffActivity);
					
					session.flush();
					
				} catch (HibernateException e) {
					e.printStackTrace();
					tx.commit();
					session.close();
				}finally{
					tx.commit();
					session.close();
				}
				
			}
			
			
		} else {
			System.out.println("----> insertProjectActiviyStaff(" 
			+ "List<MapStaffActivity> ListPAStaffToInsert): There is not staff to insert");

		}
		
		
		
		return null;
	}


	@Override
	public List<MapStaffActivity> deleteProjActivityStaff(
			List<Long> ListPAStaffToDelete, Long selectedMapActID) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		if (!ListPAStaffToDelete.isEmpty()) {
			Iterator<Long> it = ListPAStaffToDelete.iterator();
			while (it.hasNext()) {
				Long mapStaffActivity = (Long) it.next();
				this.cTime = getCurrentTime();
			
				try {
					
					session.delete(mapStaffActivity);
					
					session.flush();
					
				} catch (HibernateException e) {
					e.printStackTrace();
					
					tx.commit();
					session.close();
				}finally{
					tx.commit();
					session.close();
				}
				
			}
			
			
		} else {
			System.out.println("----> insertProjectActiviyStaff(" 
			+ "List<MapStaffActivity> ListPAStaffToInsert): There is not staff to delete");

		}
		
		
		
		return null;
	}


	@Override
	public List<ProjectActivitiesDTO> getActByProjectIdStageIdTaskId(Long projectID,
			Long stageID, Long taskID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<ProjectActivitiesDTO> listActivityTmp = new ArrayList<ProjectActivitiesDTO>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
        try {
        	Query query = session.createSQLQuery(qry.searchActByProjectStageTask(projectID, stageID, taskID));
        	
        	emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					listActivityTmp.add(helper.getActByProjectStageTask(obj));
				}
				
				
			} else {
				System.out.println("------> getTasksByProjectID(Long projectID): NO Activities for project :  " + projectID);
				
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listActivityTmp;
	}


	@Override
	public List<ProjectActivitiesDTO> getActivityOnlyByStage(Long projectID,
			Long stageID) {
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<ProjectActivitiesDTO> listActivityTmp = new ArrayList<ProjectActivitiesDTO>();
        ProjectsActivitiesQRY qry = new ProjectsActivitiesQRY();
		ProjectActivitiesVH helper = new ProjectActivitiesVH();
        try {
        	Query query = session.createSQLQuery(qry.searchActByProjectStage(projectID, stageID));
        	
        	emptyList = query.list().isEmpty();
			if (emptyList.equals(false)) {
				
				Iterator<?> iter = query.list().listIterator();
				while (iter.hasNext()) {
					Object[] obj = (Object[])iter.next();
					listActivityTmp.add(helper.getActByProjectStageTask(obj));
				}
				
				
			} else {
				System.out.println("------> getTasksByProjectID(Long projectID): NO Activities for project :  " + projectID);
				
			}
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return listActivityTmp;
	}


	@Override
	public ProjectActivity getProjectActivityByProjectandActivity(
			Long projectID, Long activityID) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		ProjectActivity projectactivity = new ProjectActivity();
		
		try {
			
			Query query = session.createQuery("FROM ProjectActivity WHERE projectID = :projectID and actID = :activityID ");
	    	query.setLong("projectID", projectID);
	    	query.setLong("activityID", activityID);
	    	projectactivity = (ProjectActivity) query.uniqueResult();
			session.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return projectactivity;
	}


	@Override
	public Long addProjectActivityOneByOne(ProjectActivity projectActivity) {
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			System.out.println("Activity to be inserted : Act ID : " + projectActivity.getActID() + " project ID : " + projectActivity.getProjectID());
			
				
				
				Boolean setTrue = true;
				this.cTime = getCurrentTime();
				projectActivity.setCreatedDate(cTime);
				projectActivity.setCheckedPA(setTrue);
				projectActivity.setActivityDone(false);
				
			
				session.save(projectActivity);
					
					
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
			
		return projectActivity.getProjectActivityID();			
				
	}


	
	
	
	
	

}
