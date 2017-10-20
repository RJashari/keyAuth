package com.bpbbank.service;

import java.util.Set;

import com.bpbbank.domain.Dega;


public interface DegaService {

	void save(Dega dega);
	
	Dega getByID(long id);
	
	Dega getByName(String dega);
	
	void remove(Dega dega);
	
	void remove(long id);
	
	void update(Dega dega);
	
	void update(long id);
	
	Set<Dega> getAllDeget();
	
	Set<Dega> getAllDegetForUser(String username);

}
