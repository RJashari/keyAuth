package com.bpbbank.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpbbank.dao.DegaDao;
import com.bpbbank.service.KeyAuthenticationService;

@Service("crudService")
public class DegaServiceImpl implements KeyAuthenticationService{

	@Autowired
	DegaDao crudDao;

	@Override
	public void save(Dega dega) {
		crudDao.save(dega);
	}

	@Override
	public Dega getByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dega getByName(String dega) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Dega dega) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Dega dega) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Dega> getAllDeget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Dega> getAllDegetForUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
