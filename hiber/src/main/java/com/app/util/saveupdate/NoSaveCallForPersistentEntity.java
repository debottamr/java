package com.app.util.saveupdate;

import java.io.Serializable;

import org.hibernate.Session;

import com.app.entity.EmployeeEntity;
import com.app.util.HibernateUtil;

public class NoSaveCallForPersistentEntity
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
      Integer id = (Integer)sessionOne.save(emp);
       
      emp.setLastName("temp");
       
      sessionOne.getTransaction().commit();
       
      //Let's see what got updated in DB
      Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
      sessionTwo.beginTransaction();
       
      EmployeeEntity employee = ( EmployeeEntity ) sessionTwo.load(EmployeeEntity.class, id);
      System.out.println(employee.getLastName());
       
      sessionTwo.getTransaction().commit();
      HibernateUtil.shutdown();
   }   
}