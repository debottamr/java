package com.app.manytomanyextracol;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

public class App {
	public static void main(String[] args) {

		useCase1();
	}
	static void useCase1() {
		System.out.println("Hibernate many to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

	    session.beginTransaction();

	    Stock stock = new Stock();
	    stock.setStockCode("7052");
	    stock.setStockName("PADINI");
	 
	    Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
	    //new category, need save to get the id first
	    session.save(category1);
	    
	    StockCategory stockCategory = new StockCategory();
	    stockCategory.setStock(stock);
	    stockCategory.setCategory(category1);
	    stockCategory.setCreatedDate(new Date()); //extra column
	    stockCategory.setCreatedBy("system"); //extra column
	        
	    stock.getStockCategories().add(stockCategory);
	        
	    session.save(stock);
	       
	    session.getTransaction().commit();
		System.out.println("Done");
		HibernateUtil.shutdown();
	}
	static void useCase2() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

	    Stock stock = new Stock();
	    stock.setStockCode("7052");
	    stock.setStockName("PADINI");
	 
	    //assume category id is 7
	    Category category1 = (Category)session.get(Category.class, 7);
	    
	    StockCategory stockCategory = new StockCategory();
	    stockCategory.setStock(stock);
	    stockCategory.setCategory(category1);
	    stockCategory.setCreatedDate(new Date()); //extra column
	    stockCategory.setCreatedBy("system"); //extra column
	        
	    stock.getStockCategories().add(stockCategory);
	        
	    session.save(stock);
	       
	    session.getTransaction().commit();
	    HibernateUtil.shutdown();
	}
}