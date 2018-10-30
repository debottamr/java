package com.app.interceptor;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
	public static void main(String[] args) {

		System.out.println("Hibernate one to many (Annotation)");

		Session session = null;
		Transaction tx = null;
		try {

			AuditLogInterceptor interceptor = new AuditLogInterceptor();
			session = HibernateUtil.getSessionFactory().withOptions().interceptor(interceptor).openSession();
			interceptor.setSession(session);

			// test insert
			tx = session.beginTransaction();
			Stock stockInsert = new Stock();
			stockInsert.setStockCode("1111");
			stockInsert.setStockName("mkyong");
			session.saveOrUpdate(stockInsert);
			tx.commit();

			// test update
			tx = session.beginTransaction();
			Query query = session.createQuery("from Stock where stockCode = '1111'");
			Stock stockUpdate = (Stock) query.list().get(0);
			stockUpdate.setStockName("mkyong-update");
			session.saveOrUpdate(stockUpdate);
			tx.commit();

			// test delete
			tx = session.beginTransaction();
			session.delete(stockUpdate);
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				// log.error("Couldn’t roll back transaction", rbe);
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		HibernateUtil.shutdown();
	}
}