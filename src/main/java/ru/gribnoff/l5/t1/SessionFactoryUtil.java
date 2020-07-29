package ru.gribnoff.l5.t1;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

class SessionFactoryUtil {
	private static SessionFactory sessionFactory;

	private SessionFactoryUtil() {}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
				Configuration configuration = new Configuration().configure();
				configuration.addAnnotatedClass(Student.class);
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());
		}
		return sessionFactory;
	}
}
