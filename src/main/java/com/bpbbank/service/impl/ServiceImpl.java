package com.bpbbank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpbbank.Dega;
import com.bpbbank.dao.CrudDao;
import com.bpbbank.service.MeyAuthenticationService;

@Service
public class ServiceImpl implements MeyAuthenticationService {

	@Autowired
	CrudDao crudDao;
	
	public void add(Dega dega) {
		
		crudDao.save(dega);
	}

	@Override
	public void remove(Dega dega) {
		
		crudDao.remove(dega);
		
	}

	@Override
	public void edit(Dega dega) {
		
		crudDao.update(dega);
		
	}

	@Override
	public Dega getByID(long id) {
		crudDao.getByID(id);
		return (Dega)crudDao;
	}

	@Override
	public Dega getByName(String dega) {
		
		crudDao.getByName(dega);
		return (Dega) crudDao;
	}
}
