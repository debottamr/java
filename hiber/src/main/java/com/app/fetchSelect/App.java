package com.app.fetchSelect;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

public class App {
	// This is the default fetching strategy. it enabled the lazy loading of all
	// it’s related collections. Let see the example…
	public static void main(String[] args) {

		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		session.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);

		session.save(stockDailyRecords);
		Integer stockId = stock.getStockId();
		session.getTransaction().commit();
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		stock = (Stock) session1.get(Stock.class, stockId);
		Set sets = stock.getStockDailyRecords();

		// call select from stock_daily_record
		for (Iterator iter = sets.iterator(); iter.hasNext();) {
			StockDailyRecord sdr = (StockDailyRecord) iter.next();
			System.out.println(sdr.getRecordId());
			System.out.println(sdr.getDate());
		}

		session1.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}
}