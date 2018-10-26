package connect;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static Session getSession(Class<?> data) {
		SessionFactory fac = new Configuration().configure().addAnnotatedClass(data).buildSessionFactory();
		return fac.getCurrentSession();
	}

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
