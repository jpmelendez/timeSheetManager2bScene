package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.model.C_Person_Charge;

public interface StaffDAO {
	C_Person_Charge addStaff (C_Person_Charge c_person_charge);
	C_Person_Charge deleteStaff(Long id);
	C_Person_Charge updateStaff(C_Person_Charge c_person_charge);
	List<C_Person_Charge> listStaff();
	C_Person_Charge getStaffById(Long id);

}
