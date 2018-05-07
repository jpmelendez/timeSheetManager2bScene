package com.twobScene.web.dao;

import java.util.List;

import com.twobScene.web.model.Clients;



public interface ClientsDAO {

	Clients addClient (Clients clients);
	Clients deleteClient(Long id);
	Clients updateClient(Clients clients);
	Clients getClientByID(Long id);
	List<Clients> listClients();
}
