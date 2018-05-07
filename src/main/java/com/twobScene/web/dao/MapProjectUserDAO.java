package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.model.MapProjectUser;



public interface MapProjectUserDAO {

	MapProjectUser addMapProjectUser (MapProjectUser mapProjectUser);
	MapProjectUser deleteMapProjectUser(Long idPersonCharge, String username);
	MapProjectUser updateMapProjectUser(MapProjectUser mapProjectUser);
	List<MapProjectUser> listMapProjectUser();
	MapProjectUser getMapProjectUserByID(Long idPersonCharge, String username);
	
}
