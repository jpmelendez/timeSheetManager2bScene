package com.twobScene.web.controller;

import java.util.List;

import com.twobScene.web.model.C_Person_Charge;

public interface CatalogStaff {
	
	C_Person_Charge addStaff (C_Person_Charge c_person_charge);
	C_Person_Charge deleteStaff(Long id);
	List<C_Person_Charge> listStaff();
	

}
