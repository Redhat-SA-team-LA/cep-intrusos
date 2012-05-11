package com.redhat.seginf.ui.repository;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.redhat.seginf.domain.SyslogMessage;

public class Repository {
	private static Repository instance;

	public static Repository getInstance() {
		if (instance == null)
			instance = new Repository();
		return instance;
	}

	/*
	 * 
	 * OBJECT METHODS
	 */

	public SessionFactory sessionFactory;

	public Repository() {
		this.setUp();
	}

	private void setUp() {

		Configuration configuration = new Configuration().configure();

		configuration.addAnnotatedClass(SyslogMessage.class);

		this.sessionFactory = configuration.buildSessionFactory();
	}

	public void save(Object storable) {

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(storable);
			tx.commit(); 
			session.flush();
			session.close();
			System.out.println(storable);
		} catch (HibernateException e) {
			((SQLException) e.getCause()).getNextException().printStackTrace();
		}
	}

	public List<SyslogMessage> loadAll(){
		return sessionFactory.openSession().createSQLQuery("select * from SyslogMessage").list();
	}
	
	public SyslogMessage load(long id) {
		return (SyslogMessage) sessionFactory.openSession().get(
				SyslogMessage.class, id);
	}
}
