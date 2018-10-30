package com.app.criteria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


public class App {

	/*
	 * 
	 * 
	 * Now, Hibernate will per-fetch the collections, with a select *in* statement.
	 * If you have 20 stock records, it will generate 3 select statements.
	 * 
	 * 1. Select statement to retrieve all the Stock records. 2. Select In
	 * statementto per-fetch its related collections (10 collections a time) 3.
	 * Select In statement to per-fetch its related collections (next 10 collections
	 * a time)
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
		
		Criteria criteria = session.createCriteria(StockDailyRecord.class);

		
		Criteria criteria1 = session.createCriteria(StockDailyRecord.class)
			    .addOrder( Order.desc("date") );
			

		criteria = session.createCriteria(StockDailyRecord.class)
			    .addOrder( Order.desc("date") );
			
		
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.lt("volume", 10000));
		
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.le("volume", 10000));
		
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.gt("volume", 10000));
		
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.ge("volume", 10000));
		
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.like("stockName", "MKYONG%"));
		
		Date startDate = new Date();
		Date endDate = new Date();
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.between("date", startDate, endDate));
		
		
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.isNotNull("volume"));
		criteria = session.createCriteria(StockDailyRecord.class)
				   .add(Restrictions.isNull("volume"));
		
		
		criteria = session.createCriteria(StockDailyRecord.class);
		criteria.setMaxResults(10);
		criteria.setFirstResult(20);
		
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

	public static List getStockDailtRecord(Date startDate, Date endDate, Long volume, Session session) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean isFirst = true;

		StringBuilder query = new StringBuilder("from StockDailyRecord ");

		if (startDate != null) {
			if (isFirst) {
				query.append(" where date >= '" + sdf.format(startDate) + "'");
			} else {
				query.append(" and date >= '" + sdf.format(startDate) + "'");
			}
			isFirst = false;
		}

		if (endDate != null) {
			if (isFirst) {
				query.append(" where date <= '" + sdf.format(endDate) + "'");
			} else {
				query.append(" and date <= '" + sdf.format(endDate) + "'");
			}
			isFirst = false;
		}

		if (volume != null) {
			if (isFirst) {
				query.append(" where volume >= " + volume);
			} else {
				query.append(" and volume >= " + volume);
			}
			isFirst = false;
		}

		query.append(" order by date");
		Query result = session.createQuery(query.toString());

		return result.list();
	}

	public static List getStockDailyRecordCriteria(Date startDate, Date endDate, Long volume, Session session) {

		Criteria criteria = session.createCriteria(StockDailyRecord.class);
		if (startDate != null) {
			criteria.add(Expression.ge("date", startDate));
		}
		if (endDate != null) {
			criteria.add(Expression.le("date", endDate));
		}
		if (volume != null) {
			criteria.add(Expression.ge("volume", volume));
		}
		criteria.addOrder(Order.asc("date"));

		return criteria.list();
	}
}