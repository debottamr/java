package com.app.cache;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class EntityCollectionCacheExample {

   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {

         //Get Department from DATABASE
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         Department department=session.get(Department.class, 1l);
         System.out.println("Department :"+department.getName());
         List<Employee> employees=department.getEmployees();
         for (Employee employee : employees) {
            System.out.println("\tEmployee Name : "+employee.getName());
         }
         transaction.commit();
         session.close();
         
         //Get Department from Cache
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         Department department2=session.get(Department.class, 1l);
         System.out.println("Department :"+department2.getName());
         List<Employee> employees2=department2.getEmployees();
         for (Employee employee : employees2) {
            System.out.println("\tEmployee Name : "+employee.getName());
         }
         transaction.commit();
         session.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      } 
      HibernateUtil.shutdown();
   }
}
