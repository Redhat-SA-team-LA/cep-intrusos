package ar.gov.anses.seginf.intrusos.persistence;

import java.io.File;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ar.gov.anses.seginf.intrusos.SystemProperties;
import ar.gov.anses.seginf.intrusos.convert.SyslogMessage;

/**
 * Objeto encargado de realizar las funcionalidades basicas para loggear los
 * mensajes que llegan al JBoss Rules
 * 
 * Es un Singleton, para poder ser utilizada la misma instancia desde cualquier
 * lado
 * 
 * @author aparedes
 * 
 */
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

		// if (!SystemProperties.getInstance().getValue("repository"))
		// return;
		Configuration configuration = new Configuration()
				.configure(new File(
						"/media/unique/workspace/redhat/cep-intrusos/rsyslog-server/src/main/java/hibernate.cfg.xml"));
		configuration.addAnnotatedClass(SyslogMessage.class);

		this.sessionFactory = configuration.buildSessionFactory();
	}

	public void save(Object storable) {

		// if (!SystemProperties.getInstance().getValue("repository"))
		// return;

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(storable);
			tx.commit();
			session.flush();
			session.close();
			System.out.println(storable);
		} catch (HibernateException e) {
			e.printStackTrace();
			((SQLException) e.getCause()).getNextException().printStackTrace();
		}
	}

}
