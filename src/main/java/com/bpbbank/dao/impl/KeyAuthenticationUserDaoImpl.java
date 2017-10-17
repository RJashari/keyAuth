package com.bpbbank.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.bpbbank.dao.BaseDao;
import com.bpbbank.dao.KeyAuthenticationUserDao;
import com.bpbbank.domain.KeyAuthenticationUser;

@Repository
public class KeyAuthenticationUserDaoImpl extends BaseDao implements KeyAuthenticationUserDao {

	private final Logger LOGGER = Logger.getLogger(KeyAuthenticationUserDaoImpl.class);
	private static final String DEFAULT_PASSWORD = "$2a$10$5kCKO/IAcqcrAy0IzrHFK.kEVBeBKVn8j/m4xcN7TTBhb1RJ3GJ7S";// password00

	@Override
	public KeyAuthenticationUser findUserByUsername(String username) {
		List<KeyAuthenticationUser> users = this.session
				.createQuery("from " + KeyAuthenticationUser.class.getName() + " WHERE username=?")
				.setParameter(0, username).list();
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public void saveUser(KeyAuthenticationUser user) {
		LOGGER.info("Saving user: " + user.getUsername());
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Some error occured: " + e);
			transaction.rollback();
		}
	}

	@Override
	public void resetUserPassword(String username) {
		LOGGER.info("Resetting user password: " + username);
		Transaction transaction = session.beginTransaction();
		try {
			KeyAuthenticationUser user = session.find(KeyAuthenticationUser.class, username);
			user.setPassword(DEFAULT_PASSWORD);
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Some error occured: " + e);
			transaction.rollback();
		}
	}

	@Override
	public void initializeUser(KeyAuthenticationUser user) {
		LOGGER.info("Initializing user: " + user.getUsername());
		Transaction transaction = session.beginTransaction();
		try {
			user.setPassword(DEFAULT_PASSWORD);
			user.setEnabled(true);
			session.merge(user);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Some error occured: " + e);
			transaction.rollback();
		}
	}

	@Override
	public List<KeyAuthenticationUser> getAll() {
		return this.session.createQuery("from " + KeyAuthenticationUser.class.getName()).list();
	}

	@Override
	public void removeUser(String username) {
		LOGGER.info("Removing user: " + username);
		Transaction transaction = session.beginTransaction();
		try {
			KeyAuthenticationUser user = this.findUserByUsername(username);
			session.remove(user);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Some error occured: " + e);
			transaction.rollback();
		}
	}

	@Override
	public void changeUserStatus(String username) {
		LOGGER.info("Changing the status of the user: " + username);
		Transaction transaction = session.beginTransaction();
		try {
			KeyAuthenticationUser user = this.findUserByUsername(username);
			user.setEnabled(!user.isEnabled());
			session.merge(user);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error("Some error occured: " + e);
			transaction.rollback();
		}
	}
}
