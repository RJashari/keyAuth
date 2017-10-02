package com.bpbbank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class BaseDao {

	public BaseDao() {
		
	}
	
	private final String HIBERNATE_CONFIG = "dbconfig/mssql.hibernate.cfg.xml";
	private Configuration configuration = new Configuration().configure(HIBERNATE_CONFIG);
	private SessionFactory sessionFactory = configuration.buildSessionFactory();
	protected Session session = sessionFactory.openSession();
}
