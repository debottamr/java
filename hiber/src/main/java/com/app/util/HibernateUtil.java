package com.app.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.app.entity.AccountEntity;
import com.app.entity.EmployeeEntity;

//version3 
//import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	// version 3
	/*
	 * private static SessionFactory buildSessionFactory() { try { // Create the
	 * SessionFactory from hibernate.cfg.xml return new
	 * AnnotationConfiguration().configure(new
	 * File("/home/debottamr/javaworkspace/hiber/hibernate.cgf.xml")).
	 * buildSessionFactory();
	 * 
	 * } catch (Throwable ex) { // Make sure you log the exception, as it might be
	 * swallowed System.err.println("Initial SessionFactory creation failed." + ex);
	 * throw new ExceptionInInitializerError(ex); } }
	 */

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// version 3
	/*
	 * public static void shutdown() { // Close caches and connection pools
	 * getSessionFactory().close(); }
	 */
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory buildSessionFactory() {
		if (sessionFactory == null) {
			try {

				// Create registry builder
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Map<String, String> settings = new HashMap<>();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernatetest");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				// Apply settings
				registryBuilder.applySettings(settings);

				// Create registry
				registry = registryBuilder.build();

				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(EmployeeEntity.class).addAnnotatedClass(AccountEntity.class);

				Metadata metadata = sources.getMetadataBuilder().build();

				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	private static SessionFactory buildSessionFactory1() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration()
						.configure(HibernateUtil.class.getResource("/home/debottamr/javaworkspace/hiber/hibernate.cgf.xml"));
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
