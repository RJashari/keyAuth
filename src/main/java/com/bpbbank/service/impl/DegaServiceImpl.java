package com.bpbbank.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpbbank.dao.DegaDao;
import com.bpbbank.domain.Dega;
import com.bpbbank.service.DegaService;

@Service("crudService")
public class DegaServiceImpl implements DegaService{

	@Autowired
	DegaDao degaDao;

	@Override
	public void save(Dega dega) {
		degaDao.save(dega);
	}

	@Override
	public Dega getByID(long id) {
		
		return degaDao.getByID(id);
	}

	@Override
	public Dega getByName(String dega) {
		
		return degaDao.getByName(dega);
	}

	@Override
	public void remove(Dega dega) {
		degaDao.remove(dega);
	}

	@Override
	public void remove(long id) {
		degaDao.remove(id);
		
	}

	@Override
	public void update(Dega dega) {
		degaDao.update(dega);
		
	}

	@Override
	public void update(long id) {
		degaDao.update(id);
		
	}

	@Override
	public Set<Dega> getAllDeget() {
		
		return degaDao.getAllDeget();
	}

	@Override
	public Set<Dega> getAllDegetForUser(String username) {
		
		return degaDao.getAllDegetForUser(username);
	}
}
