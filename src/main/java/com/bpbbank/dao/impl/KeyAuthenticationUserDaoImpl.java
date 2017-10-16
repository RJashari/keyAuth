package com.bpbbank.dao.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bpbbank.dao.BaseDao;
import com.bpbbank.dao.KeyAuthenticationUserDao;
import com.bpbbank.domain.KeyAuthenticationUser;

@Repository
public class KeyAuthenticationUserDaoImpl extends BaseDao implements KeyAuthenticationUserDao {

	@Override
	public KeyAuthenticationUser findUserByUsername(String username) {
		List<KeyAuthenticationUser> users = this.session
			.createQuery("from " + KeyAuthenticationUser.class.getName() + " where username=?")
			.setParameter(0, username)
			.list();
		if(users.size() == 1) {
			System.out.println("KA USER");
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public void saveUser(KeyAuthenticationUser user) {
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}
}
