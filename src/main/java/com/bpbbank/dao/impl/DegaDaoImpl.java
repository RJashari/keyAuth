package com.bpbbank.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bpbbank.dao.DegaDao;
import com.bpbbank.domain.Dega;
import com.bpbbank.dao.BaseDao;

@Repository
public class DegaDaoImpl extends BaseDao implements DegaDao{

	@Override
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
		session.merge(dega);
		tx.commit();
		
	}

	@Override
	public Dega getByName(String dega) {
		return (Dega)session.get(Dega.class, dega);
	}

	@Override
	public Set<Dega> getAllDeget() {
		StringBuilder sb = new StringBuilder("from dega");
		Query query = session.createQuery(sb.toString());
		return new HashSet<Dega>(query.list());
	}

	@Override
	public void remove(long id) {
		Transaction tx = session.beginTransaction();
		Dega dega = this.getByID(id);
		session.delete(dega);
		tx.commit();
	}
	
	@Override
	public void update(long id) {
		Transaction tx = session.beginTransaction();
		Dega dega = this.getByID(id);
		session.merge(dega);
		tx.commit();
	}
	
	@Override
	public Set<Dega> getAllDegetForUser(String username) {
		return new HashSet<>(session
				.createQuery("from dega where pergjegjesi=?")
				.setParameter(0, username)
				.list()
				);
	}
}
