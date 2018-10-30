package com.app.filter;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.hibernate.Filter;
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
		
		StockDailyRecord stockDailyRecords1 = new StockDailyRecord();
		stockDailyRecords1.setPriceOpen(new Float("1.2"));
		stockDailyRecords1.setPriceClose(new Float("1.1"));
		stockDailyRecords1.setPriceChange(new Float("10.0"));
		stockDailyRecords1.setVolume(3000000L);
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH, -1);
		stockDailyRecords1.setDate(instance.getTime());
		
		
		StockDailyRecord stockDailyRecords2 = new StockDailyRecord();
		stockDailyRecords2.setPriceOpen(new Float("1.2"));
		stockDailyRecords2.setPriceClose(new Float("1.1"));
		stockDailyRecords2.setPriceChange(new Float("10.0"));
		stockDailyRecords2.setVolume(3000000L);
		Calendar instance1 = Calendar.getInstance();
		instance1.add(Calendar.DAY_OF_MONTH, 1);
		stockDailyRecords2.setDate(instance1.getTime());

		stockDailyRecords.setStock(stock);
		stockDailyRecords1.setStock(stock);
		stockDailyRecords2.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);
		stock.getStockDailyRecords().add(stockDailyRecords1);
		stock.getStockDailyRecords().add(stockDailyRecords2);
		session.persist(stock);
		Integer stockId = stock.getStockId();
		session.getTransaction().commit();
		System.out.println("Done");

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("****** Enabled Filter ******");

		Filter filter = session.enableFilter("stockRecordFilter");
		filter.setParameter("stockRecordFilterParam", new Date());

		stock = (Stock) session.get(Stock.class, stockId);
		Set<StockDailyRecord> sets = stock.getStockDailyRecords();

		for (StockDailyRecord sdr : sets) {
			System.out.println(sdr.getRecordId());
			System.out.println(sdr.getDate());
		}

		System.out.println("****** Disabled Filter ******");

		session.disableFilter("stockRecordFilter");
		// clear the loaded instance and get Stock again, for demo only
		session.evict(stock);

		Stock stock2 = (Stock) session.get(Stock.class, stockId);
		Set<StockDailyRecord> sets2 = stock2.getStockDailyRecords();

		for (StockDailyRecord sdr : sets2) {
			System.out.println(sdr.getRecordId());
			System.out.println(sdr.getDate());
		}

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}