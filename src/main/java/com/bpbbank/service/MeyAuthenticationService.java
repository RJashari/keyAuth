package com.bpbbank.service;

import com.bpbbank.Dega;

public interface MeyAuthenticationService {

	void add(Dega dega);
	
	void remove(Dega dega);
	
	void edit(Dega dega);
	
	Dega getByID(long id);
	
	Dega getByName(String dega);
}
