package com.app.dynamicinsertupdate;

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
		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);
		session.save(stock);

		
		Integer stockId = stock.getStockId();
		session.getTransaction().commit();

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		Stock stock2 = session1.get(Stock.class, stockId);
		stock2.setStockName("DIALOG123");
		
		
		
		
		
		session1.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}

	
}