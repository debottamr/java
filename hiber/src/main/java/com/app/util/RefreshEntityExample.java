package com.app.util;

import org.hibernate.Session;

import com.app.entity.EmployeeEntity;

public class RefreshEntityExample {

	 public static void main(String[] args)
	   {
	      Session sessionOne = HibernateUtil.getSessionFactory().openSession();
	      sessionOne.beginTransaction();
	       
	      //Create new Employee object
	      EmployeeEntity emp = new EmployeeEntity();
	      emp.setEmployeeId(2);
	      emp.setFirstName("Lokesh");
	      emp.setLastName("Gupta");
	      emp.setEmail("l1@l1.com");
	      //Save employee
	      sessionOne.save(emp);
	      sessionOne.getTransaction().commit();
	      sessionOne.close();
	       
	      //Verify employee's firstname
	      System.out.println(verifyEmployeeFirstName(1, "Lokesh"));
	       
	      Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
	      sessionTwo.beginTransaction();
	       
	      //This
	      emp.setFirstName("Vikas");
	      sessionTwo.refresh(emp);
	       
	      sessionTwo.getTransaction().commit();
	      sessionTwo.close();
	       
	      System.out.println(emp.getFirstName().equals("Lokesh"));
	       
	      HibernateUtil.shutdown();
	   } 
	    
	   private static boolean verifyEmployeeFirstName(Integer employeeId, String firstName){
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      EmployeeEntity employee = (EmployeeEntity) session.load(EmployeeEntity.class, employeeId);
	      //Verify first name
	      boolean result = firstName.equals(employee.getFirstName());
	      session.close();
	      //Return verification result
	      return result;
	   }
	}