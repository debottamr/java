package com.app.util.saveupdate;

import org.hibernate.Session;

import com.app.entity.EmployeeEntity;
import com.app.util.HibernateUtil;

public class SimplestSaveEntityExample
{
   public static void main(String[] args)
   {
      Session sessionOne = HibernateUtil.getSessionFactory().openSession();
      sessionOne.beginTransaction();
       
      //Create new Employee object
      EmployeeEntity emp = new EmployeeEntity();
      emp.setEmployeeId(1);
      emp.setFirstName("Lokesh");
      emp.setLastName("Gupta");
      emp.setEmail("l@l.com");
      //Save employee
      sessionOne.save(emp);
       
      sessionOne.getTransaction().commit();
      HibernateUtil.shutdown();
   }   
}