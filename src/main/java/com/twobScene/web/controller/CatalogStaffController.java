package com.twobScene.web.controller;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.twobScene.web.listener.HibernateServletListener;
import com.twobScene.web.model.C_Person_Charge;

public class CatalogStaffController extends HibernateServletListener {
	
	private SessionFactory sf;
    
    public CatalogStaffController(SessionFactory sf) {
    	this.sf = sf;
	}
        
	
	public C_Person_Charge addStaff(C_Person_Charge c_person_charge){
		
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(c_person_charge);
        session.getTransaction().commit();
        tx.commit();session.close();
       
		return c_person_charge;
		
	}
	
public C_Person_Charge deleteStaff(Long id){
		
		Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        C_Person_Charge c_person_charge = (C_Person_Charge) session.get(C_Person_Charge.class, id);
        try {
                 session.delete(c_person_charge);
            
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}finally{
			tx.commit();
			session.close();
		}
		return c_person_charge;
        
	}

@SuppressWarnings("unchecked")
public List<C_Person_Charge> listStaff(){
	
	Session session = sf.openSession();
    Transaction tx = session.beginTransaction();
    List<C_Person_Charge> c_person_charge = null;
    try {
         
    	c_person_charge = session.createQuery("from C_PERSON_CHARGE").list();
    	for (Iterator<C_Person_Charge> iterator = c_person_charge.iterator(); iterator.hasNext();){
    			C_Person_Charge personCharge = (C_Person_Charge) iterator.next(); 
    	}
         
    } catch (HibernateException e) {
    	if (tx!=null) tx.rollback();
        e.printStackTrace(); 
    }finally{
    	tx.commit();
		session.close();
    }
    return c_person_charge;
	
}

}
