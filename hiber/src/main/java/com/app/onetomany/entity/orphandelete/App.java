package com.app.onetomany.entity.orphandelete;

import java.util.Date;

import org.hibernate.Session;

public class App {
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
		
		
		
		
		
		StockDailyRecord stockDailyRecords1 = new StockDailyRecord();
		stockDailyRecords1.setPriceOpen(new Float("1.21"));
		stockDailyRecords1.setPriceClose(new Float("1.11"));
		stockDailyRecords1.setPriceChange(new Float("10.1"));
		stockDailyRecords1.setVolume(3000001L);
		Date date = new Date();
		date.setMonth(date.getMonth()+1);
		stockDailyRecords1.setDate(date);

		stockDailyRecords.setStock(stock);
		stockDailyRecords1.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);
		stock.getStockDailyRecords().add(stockDailyRecords1);

		session.persist(stock);
		session.getTransaction().commit();
		
		Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
		sessionTwo.beginTransaction();
		
		//delete orphan is able to handle delete in collection
		Stock load = sessionTwo.get(Stock.class, stock.getStockId());
		StockDailyRecord next = load.getStockDailyRecords().iterator().next();
		load.getStockDailyRecords().remove(next);
		
		sessionTwo.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}
}