package com.app.mutable;

import java.util.Date;

import org.hibernate.Session;

public class App {

	//In case of immutable update is ignored
	//Insert and delete work
	

	public static void main(String[] args) {

		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		session.save(stock);

		
		Integer stockId = stock.getStockId();
		session.getTransaction().commit();

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		Stock stock2 = session1.get(Stock.class, stockId);
		stock2.setStockName("DIALOG123");
		
		StockDailyRecord sdr = new StockDailyRecord();
		sdr.setDate(new Date());        
		sdr.setStock(stock2);
		stock2.getStockDailyRecords().add(sdr);
		session1.saveOrUpdate(stock2);
		session1.getTransaction().commit();
		
		
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		session3.beginTransaction();
		Stock stock3 = session3.get(Stock.class, stockId);
		stock2.setStockName("DIALOG123");
		session3.remove(stock3);
		session3.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}

	
}