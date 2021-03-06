package com.app.fetchBatch;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

public class App {

	/*
	 * 
	 * 
	 * Now, Hibernate will per-fetch the collections, with a select *in* statement.
	 * If you have 20 stock records, it will generate 3 select statements.
	 * 
	 * 1. Select statement to retrieve all the Stock records. 
	 * 2. Select In statementto per-fetch its related collections (10 collections a time) 
	 * 3. Select In statement to per-fetch its related collections (next 10 collections a time)
	 */

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

		Stock stock1 = new Stock();
		createStock(session, stock1);
		Stock stock2 = new Stock();
		createStock(session, stock2);

		Integer stockId = stock.getStockId();
		session.getTransaction().commit();

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		List<Stock> list = session1.createQuery("from Stock").list();

		for (Stock st1 : list) {

			Set sets = st1.getStockDailyRecords();

			for (Iterator iter = sets.iterator(); iter.hasNext();) {
				StockDailyRecord sdr = (StockDailyRecord) iter.next();
				System.out.println(sdr.getRecordId());
				System.out.println(sdr.getDate());
			}
		}

		session1.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}

	private static void createStock(Session session, Stock stock) {

		stock.setStockCode("70521");
		stock.setStockName("PADINI1");
		session.save(stock);

		StockDailyRecord stockDailyRecords1 = new StockDailyRecord();
		stockDailyRecords1.setPriceOpen(new Float("1.2"));
		stockDailyRecords1.setPriceClose(new Float("1.1"));
		stockDailyRecords1.setPriceChange(new Float("10.0"));
		stockDailyRecords1.setVolume(3000000L);
		stockDailyRecords1.setDate(new Date());

		StockDailyRecord stockDailyRecords2 = new StockDailyRecord();
		stockDailyRecords2.setPriceOpen(new Float("1.2"));
		stockDailyRecords2.setPriceClose(new Float("1.1"));
		stockDailyRecords2.setPriceChange(new Float("10.0"));
		stockDailyRecords2.setVolume(3000000L);
		stockDailyRecords2.setDate(new Date());

		StockDailyRecord stockDailyRecords3 = new StockDailyRecord();
		stockDailyRecords3.setPriceOpen(new Float("1.2"));
		stockDailyRecords3.setPriceClose(new Float("1.1"));
		stockDailyRecords3.setPriceChange(new Float("10.0"));
		stockDailyRecords3.setVolume(3000000L);
		stockDailyRecords3.setDate(new Date());

		StockDailyRecord stockDailyRecords4 = new StockDailyRecord();
		stockDailyRecords4.setPriceOpen(new Float("1.2"));
		stockDailyRecords4.setPriceClose(new Float("1.1"));
		stockDailyRecords4.setPriceChange(new Float("10.0"));
		stockDailyRecords4.setVolume(3000000L);
		stockDailyRecords4.setDate(new Date());

		stockDailyRecords1.setStock(stock);
		stockDailyRecords2.setStock(stock);
		stockDailyRecords3.setStock(stock);
		stockDailyRecords4.setStock(stock);

		stock.getStockDailyRecords().add(stockDailyRecords1);
		stock.getStockDailyRecords().add(stockDailyRecords2);
		stock.getStockDailyRecords().add(stockDailyRecords3);
		stock.getStockDailyRecords().add(stockDailyRecords4);
	}
}