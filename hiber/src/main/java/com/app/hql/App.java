package com.app.hql;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

public class App {
	public static void main(String[] args) {

		System.out.println("Hibernate many to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		session.save(stock);
		Query query = session.createQuery("from Stock where stockCode = :code ");
		query.setParameter("code", stock.getStockCode());
		List list = query.list();
		System.out.println(list.get(0));

		Query query1 = session.createQuery("from Stock where stockCode = '7277' ");
		List list1 = query1.list();
		System.out.println(list1);
		int id = stock.getStockId();

		query = session.createQuery("update Stock set stockName = :stockName" + " where stockCode = :stockCode");
		query.setParameter("stockName", "DIALOG1");
		query.setParameter("stockCode", "7277");
		int result = query.executeUpdate();

		query = session.createQuery("update Stock set stockName = 'DIALOG2'" + " where stockCode = '7277'");
		result = query.executeUpdate();
		
		
		query = session.createQuery("delete Stock where stockCode = :stockCode");
		query.setParameter("stockCode", "7277");
		result = query.executeUpdate();
		
		query = session.createQuery("delete Stock where stockCode = '7277'");
		result = query.executeUpdate();
		
		//Insert a stock record from another backup_stock table. This can also called bulk-insert statement.
		/*query = session.createQuery("insert into Stock(stock_code, stock_name)" +
    			"select stock_code, stock_name from backup_stock");
		result = query.executeUpdate();*/
		
		
		//1. Named parameters

		String hql = "from Stock s where s.stockCode = :stockCode";
		List list2 = session.createQuery(hql).setParameter("stockCode", "7277").list();
		
		
		hql = "from Stock s where s.stockCode = :stockCode";
		List list3 = session.createQuery(hql)
		.setString("stockCode", "7277")
		.list();
		
		
		Stock stock1 = new Stock();
		stock1.setStockCode("7277");
		hql = "from Stock s where s.stockCode = :stockCode";
		List list4 = session.createQuery(hql)
		.setProperties(stock1)
		.list();
		
		
		
		//2. Positional parameters

		hql = "from Stock s where s.stockCode = ?0 and s.stockName = ?1";
		List result5 = session.createQuery(hql)
		.setString(0, "7277")
		.setParameter(1, "DIALOG")
		.list();
		
		hql = "from Stock s where s.stockName = ?0 and s.stockCode = ?1";
		List result6 = session.createQuery(hql)
		.setParameter(0, "DIALOG")
		.setString(1, "7277")
		.list();
		
		hql = "from Stock s where s.stockCode = :stockCode";
		List result7 = session.createQuery(hql)
		.setString("stockCode", "7277")
		.setComment("+ INDEX(stock idx_stock_code)")
		.list();
		session.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}

}