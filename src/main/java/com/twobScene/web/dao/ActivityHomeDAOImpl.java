package com.twobScene.web.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.twobScene.web.dto.ActivityHomeDTO;
import com.twobScene.web.dto.projectsDTO;
import com.twobScene.web.helper.ActivityHomeVH;
import com.twobScene.web.helper.ProjectVH;
import com.twobScene.web.query.ActivityHomeQRY;
import com.twobScene.web.query.ProjectsQRY;



public class ActivityHomeDAOImpl implements ActivityHomeDAO {

	private SessionFactory sf;
	private Date cTime;
	
	public ActivityHomeDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}
	
	
	@Override
	public List<ActivityHomeDTO> getStartTodayActivities(String sysdate) {
	
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> todayActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getStartTodayActivities(sysdate));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				todayActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return todayActs;
	}


	@Override
	public List<ActivityHomeDTO> getDueTodayActivities(String sysdate) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> dueTodayActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getDueTodayActivities(sysdate));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				dueTodayActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return dueTodayActs;
	}


	@Override
	public List<ActivityHomeDTO> getStartThisWeekActivities(String firstDayWeek,
			String lastDayWeek) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> startThisWeekActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getStartThisWeekActivities(firstDayWeek, lastDayWeek));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				startThisWeekActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return startThisWeekActs;
	}


	@Override
	public List<ActivityHomeDTO> getDueThisWeekActivities(String firstDayWeek,
			String lastDayWeek) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> dueThisWeekActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getDueThisWeekActivities(firstDayWeek, lastDayWeek));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				dueThisWeekActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return dueThisWeekActs;
	}


	@Override
	public List<ActivityHomeDTO> getStartNextWeekActivities(
			String firstDayNextWeek, String lastDayNextWeek) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> startNextWeekActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getStartNextWeekActivities(firstDayNextWeek, lastDayNextWeek));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				startNextWeekActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return startNextWeekActs;
	}


	@Override
	public List<ActivityHomeDTO> getDueNextWeekActivities(
			String firstDayNextWeek, String lastDayNextWeek) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> dueNextWeekActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getDueNextWeekActivities(firstDayNextWeek, lastDayNextWeek));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				dueNextWeekActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return dueNextWeekActs;
	}


	@Override
	public List<ActivityHomeDTO> getStartThisMonthActivities(
			String firstDayMonth, String lastDayMonth) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> startMonthActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getStartMonthActivities(firstDayMonth, lastDayMonth));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				startMonthActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return startMonthActs;
	}


	@Override
	public List<ActivityHomeDTO> getDueThisMonthActivities(String firstDayMonth,
			String lastDayMonth) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> dueMonthActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getDueMonthActivities(firstDayMonth, lastDayMonth));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				dueMonthActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return dueMonthActs;
	}


	@Override
	public List<ActivityHomeDTO> getOverdueActivities(String sysdate) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> overdueActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getOverdueActivities());
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				overdueActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return overdueActs;
	}


	@Override
	public List<ActivityHomeDTO> getAlertsActivities(String sysdate) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> alertedActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getAlertedActivities());
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				alertedActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return alertedActs;
	}


	@Override
	public List<ActivityHomeDTO> getUnscheduledActivities() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> unscheduledActs = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getUnscheduledActivities());
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				unscheduledActs.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return unscheduledActs;
	}


	@Override
	public List<ActivityHomeDTO> getActivityByID(Long mapActId) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> actById = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getActivityById(mapActId));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				actById.add(helper.getActivityFullInfo(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		
	
		return actById;
	}
	
	/*** Here start the method for the new alerts ******/
	@Override
	public List<ActivityHomeDTO> getNewAlertsInbox(String sysdate, String projectsByUser, String userCredentials) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> inboxAlerts = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getNewAlertsInbox(sysdate, projectsByUser, userCredentials));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				inboxAlerts.add(helper.getNewAlertsHelper(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return inboxAlerts;
	}


	@Override
	public List<ActivityHomeDTO> getNewAlertsToday(String sysdate, String projectsByUser, String userCredentials) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> todayAlerts = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getNewAlertsToday(sysdate, projectsByUser, userCredentials));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				todayAlerts.add(helper.getNewAlertsHelper(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return todayAlerts;
	}


	@Override
	public List<ActivityHomeDTO> getNewAlertsThisWeek(String firstDayWeek,
			String lastDayWeek, String projectsByUser, String userCredentials) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> thisWeekAlerts = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getNewAlertsThisWeek(firstDayWeek, lastDayWeek, projectsByUser, userCredentials));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				thisWeekAlerts.add(helper.getNewAlertsHelper(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return thisWeekAlerts;
	}


	@Override
	public List<ActivityHomeDTO> getNewAlertsNextWeek(String firstDayNextWeek,
			String lastDayNextWeek, String projectsByUser, String userCredentials) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> nextWeekAlerts = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getNewAlertsNextWeek(firstDayNextWeek, lastDayNextWeek, projectsByUser, userCredentials));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				nextWeekAlerts.add(helper.getNewAlertsHelper(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return nextWeekAlerts;
	}


	@Override
	public List<ActivityHomeDTO> getNewAlertsThisMonth(String firstDayMonth,
			String lastDayMonth, String projectsByUser, String userCredentials) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> thisMonthAlerts = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getNewAlertsThisMonth(firstDayMonth, lastDayMonth, projectsByUser, userCredentials));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				thisMonthAlerts.add(helper.getNewAlertsHelper(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return thisMonthAlerts;
	}


	@Override
	public List<ActivityHomeDTO> getNewAlertsInactivityProject(
			String inactivityDate, String projectsByUser, String userCredentials) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<ActivityHomeDTO> inactivityAlerts = new ArrayList<ActivityHomeDTO>();
		
		ActivityHomeVH helper = new ActivityHomeVH();
		ActivityHomeQRY qry = new ActivityHomeQRY();
		try {
			Query query = session.createSQLQuery(qry.getNewAlertsInactivity(inactivityDate, projectsByUser, userCredentials));
			Iterator<?> iter = query.list().listIterator();
			
			while(iter.hasNext()){
				Object[] obj = (Object[])iter.next();
				inactivityAlerts.add(helper.getNewAlertsHelper(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.commit();
			session.close();
		}finally{
			tx.commit();
			session.close();
		}
		return inactivityAlerts;
	}

	
	
	

}
