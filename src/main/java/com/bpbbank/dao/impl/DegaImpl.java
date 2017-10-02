package com.bpbbank.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;


import com.bpbbank.dao.CrudDao;
import com.bpbbank.dao.BaseDao;
import com.bpbbank.Dega;

@Component
public class DegaImpl extends BaseDao implements CrudDao{

	public void save(Dega dega) {
		
		Transaction tx = session.beginTransaction();
		session.save(dega);
		tx.commit();
	}

	@Override
	public Dega getByID(long id) {
		return (Dega)session.get(Dega.class, id);
	}

	@Override
	public void remove(Dega dega) {

		Transaction tx = session.beginTransaction();
		session.delete(dega);
		tx.commit();
	}

	@Override
	public void update(Dega dega) {
		
		Transaction tx = session.beginTransaction();
		session.update(dega);
		tx.commit();
		
	}

	@Override
	public Dega getByName(String dega) {
		return (Dega)session.get(Dega.class, dega);
	}

	@Override
	public Set<Dega> getAllDeget() {
		StringBuilder sb = new StringBuilder("FROM ");
		Query query = session.createQuery(sb.toString());
		return new HashSet<Dega>(query.list());
		
		
	}
}
