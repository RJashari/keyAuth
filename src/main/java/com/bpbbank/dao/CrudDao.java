package com.bpbbank.dao;

import java.util.Set;

import com.bpbbank.Dega;

public interface CrudDao{
	
	void save(Dega dega);
	
	Dega getByID(long id);
	
	Dega getByName(String dega);
	
	void remove(Dega dega);
	
	void update(Dega dega);
	
	Set<Dega> getAllDeget();
	
	
}
